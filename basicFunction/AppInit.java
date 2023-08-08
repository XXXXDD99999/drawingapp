package basicFunction;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AppInit extends Frame {

    private DrawingCanvas canvas;

    public AppInit() {
        setSize(800, 600);
        setupUI();
        canvas = new DrawingCanvas();
        add(canvas, BorderLayout.CENTER);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setFocusable(true);
    }


    private void setupUI() {
        Panel controlPanel = new Panel();
        controlPanel.setLayout(new GridLayout(2, 1)); // Divide into 2 rows

        // Row 1: Drawing Modes
        Panel modePanel = new Panel();
        modePanel.setLayout(new FlowLayout());

        Label modeLabel = new Label("Modes:");
        modeLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 14)); // Set font to bold
        modePanel.add(modeLabel);

        DrawingMode[] modes = DrawingMode.values();
        for (DrawingMode mode : modes) {
            Button button = new Button(mode.getLabel());
            button.addActionListener(e -> canvas.setDrawingMode(mode));
            modePanel.add(button);
        }
        controlPanel.add(modePanel);

        // Row 2: Drawing Colors
        Panel colorPanel = new Panel();
        colorPanel.setLayout(new FlowLayout());

        Label colorLabel = new Label("Colours:");
        colorLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 14)); // Set font to bold
        colorPanel.add(colorLabel);

        DrawingColor[] colours = DrawingColor.values();
        for (DrawingColor color : colours) {
            Button button = new Button(color.getName());
            button.addActionListener(e -> canvas.setDrawingColor(color));
            colorPanel.add(button);
        }
        controlPanel.add(colorPanel);

        add(controlPanel, BorderLayout.NORTH);
    }

}
