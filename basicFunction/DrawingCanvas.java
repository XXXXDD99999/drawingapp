package basicFunction;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

public class DrawingCanvas extends Canvas{

    private List<Shape> shapes = new ArrayList<>();

    private List<Point> points = new ArrayList<>(); // List to store points for freehand drawing
    private DrawingMode drawingMode = DrawingMode.FREEHAND;

    private Color drawingColor = DrawingColor.BLACK.getColor();
    private int x0, y0, x1, y1;

    public DrawingCanvas() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                x0 = e.getX();
                y0 = e.getY();
                shapes.add(new Shape(drawingMode,drawingColor, x0, y0, x0, y0, new ArrayList<>()));
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                if (drawingMode == DrawingMode.FREEHAND && !points.isEmpty()) {
                    shapes.add(createFreehandShape());
                    points.clear();
                } else {
                    shapes.get(shapes.size() - 1).setEndPoint(e.getX(), e.getY());
                }
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (drawingMode == DrawingMode.FREEHAND) {
                    points.add(new Point(e.getX(), e.getY()));
                    repaint();
                } else {
                    shapes.get(shapes.size() - 1).setEndPoint(e.getX(), e.getY());
                    repaint();
                }
            }
        });
    }
    private Shape createFreehandShape() {
        return new Shape(DrawingMode.FREEHAND, drawingColor,0,0,0,0, new ArrayList<>(points));
    }
    public void setDrawingMode(DrawingMode mode) {
        drawingMode = mode;
    }

    public void setDrawingColor(DrawingColor color) {
        drawingColor = color.getColor();
    }

    public void paint(Graphics g) {
        for (Shape shape : shapes) {
            shape.draw(g);
        }
//        if (drawingMode == DrawingMode.FREEHAND && points.size() > 1) {
//            g.setColor(drawingColor);
//            for (int i = 1; i < points.size(); i++) {
//                Point p1 = points.get(i - 1);
//                Point p2 = points.get(i);
//                g.drawLine(p1.x, p1.y, p2.x, p2.y);
//            }
//        }
    }
}

