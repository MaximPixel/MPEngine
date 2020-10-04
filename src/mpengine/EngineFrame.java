package mpengine;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class EngineFrame extends JFrame {

    private final Canvas canvas;
    private final BufferStrategy strategy;
    private final EngineInput input;

    public EngineFrame() {
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Canvas canvas = new Canvas();
        canvas.setPreferredSize(getSize());

        canvas.setEnabled(true);

        add(canvas);
        pack();

        setLocationRelativeTo(null);

        setVisible(true);

        canvas.createBufferStrategy(2);
        BufferStrategy strategy = canvas.getBufferStrategy();

        canvas.setFocusable(true);

        this.canvas = canvas;
        this.strategy = strategy;

        input = new EngineInput(this);
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public BufferStrategy getBufferStrategy() {
        return strategy;
    }

    public EngineInput getEngineInput() {
        return input;
    }
}
