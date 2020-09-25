package mpengine;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class EngineThread extends Thread {

    private final static double UPDATE_CAP = 1D / 60D;
    private final IEngineInterface engineInterface;
    private final EngineFrame frame;
    private boolean fps_change = false;
    private int fps_count = 0;

    public EngineThread(IEngineInterface engineInterface, EngineFrame frame) {
        this.engineInterface = engineInterface;
        this.frame = frame;
    }

    @Override
    public void run() {
        boolean running = true;

        boolean render = false;
        double first = 0D;
        double last = System.nanoTime() / 1000000000D;
        double passed = 0D;
        double unprocessed = 0D;

        double frame = 0D;
        int frames = 0;
        int fps = 0;

        while (running) {

            render = false;

            first = System.nanoTime() / 1000000000D;
            passed = first - last;
            last = first;

            unprocessed += passed;
            frame += passed;

            while (unprocessed >= UPDATE_CAP) {

                engineInterface.updateLoop();
                fps_change = false;
                this.frame.getEngineInput().update();

                unprocessed -= UPDATE_CAP;
                render = true;

                if (frame >= 1D) {
                    frame = 0D;
                    fps = (int) frames;
                    frames = 0;

                    fps_change = true;
                    fps_count = fps;
                }
            }

            if (render) {

                BufferStrategy strategy = this.frame.getBufferStrategy();

                engineInterface.drawLoop(this.frame.getCanvas(), (Graphics2D) strategy.getDrawGraphics());

                strategy.show();

                frames++;

            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public boolean isFpsChange() {
        return fps_change;
    }

    public int getFps() {
        return fps_count;
    }

    public EngineFrame getFrame() {
        return frame;
    }

    public EngineInput getEngineInput() {
        return frame.getEngineInput();
    }
}
