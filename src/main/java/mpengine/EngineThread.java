package mpengine;

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

@SuppressWarnings("BusyWait")
public class EngineThread extends Thread {
    private final static double UPDATE_CAP = 1D / 60D;
    private final IEngineInterface engineInterface;
    private final EngineFrame frame;
    private boolean fpsChange = false;
    private int fpsCount = 0;

    public EngineThread(IEngineInterface engineInterface, EngineFrame frame) {
        this.engineInterface = engineInterface;
        this.frame = frame;
    }

    @Override
    public void run() {
        double first;
        double last = System.nanoTime() / 1000000000D;
        double passed;
        double unprocessed = 0D;

        double frame = 0D;
        int frames = 0;
        int fps;

        while (EngineThread.this.frame.isEnabled()) {
            boolean render = false;

            first = System.nanoTime() / 1000000000D;
            passed = first - last;
            last = first;

            unprocessed += passed;
            frame += passed;

            while (unprocessed >= UPDATE_CAP) {

                engineInterface.updateLoop();
                fpsChange = false;
                this.frame.getEngineInput().update();

                unprocessed -= UPDATE_CAP;
                render = true;

                if (frame >= 1D) {
                    frame = 0D;
                    fps = frames;
                    frames = 0;

                    fpsChange = true;
                    fpsCount = fps;
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
        return fpsChange;
    }

    public int getFps() {
        return fpsCount;
    }

    public EngineFrame getFrame() {
        return frame;
    }

    public EngineInput getEngineInput() {
        return frame.getEngineInput();
    }
}
