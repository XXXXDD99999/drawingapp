package basicFunction;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.MouseEvent;

public class Shape {

    private Color color;
    private DrawingMode mode;
    private int x0, y0, x1, y1;

    private List<Point> points; // List to store points for freehand drawing

    public Shape(DrawingMode mode,Color color, int x0, int y0, int x1, int y1, List<Point> points) {
        this.color = color;
        this.mode = mode;
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.points = points;
    }

    public void setEndPoint(int x, int y) {
        x1 = x;
        y1 = y;
    }



    public void draw(Graphics g) {
        g.setColor(color);
        switch (mode) {
            case FREEHAND:
                if (points.size() > 1) {
                    for (int i = 1; i < points.size(); i++) {
                        Point p1 = points.get(i - 1);
                        Point p2 = points.get(i);
                        g.drawLine(p1.x, p1.y, p2.x, p2.y);
                    }
                }
                break;
            case STRAIGHT_LINE:
                g.drawLine(x0, y0, x1, y1);
                break;
            case RECTANGLE:
                int width = x1 - x0;
                int height = y1 - y0;
                g.drawRect(x0, y0, width, height);
                break;
            case ELLIPSE:
                g.drawOval(x0, y0, x1 - x0, y1 - y0);
                break;
            case SPECIAL_SHAPE:
                int sideLength = Math.max(Math.abs(x1 - x0), Math.abs(y1 - y0));
                if (x1 > x0 && y1 > y0) {
                    g.drawOval(x0, y0, sideLength, sideLength);
                } else if (x1 > x0 && y1 < y0) {
                    g.drawOval(x0, y0 - sideLength, sideLength, sideLength);
                } else if (x1 < x0 && y1 > y0) {
                    g.drawOval(x0 - sideLength, y0, sideLength, sideLength);
                } else {
                    g.drawOval(x0 - sideLength, y0 - sideLength, sideLength, sideLength);
                }
                break;
            case POLYGON:
                int[] xPoints = {x0, x1, x0 - (x1 - x0), x0};
                int[] yPoints = {y0, y1, y1, y0};
                g.drawPolygon(xPoints, yPoints, 4);
                break;
        }
    }


}
