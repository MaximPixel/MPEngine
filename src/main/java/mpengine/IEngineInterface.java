package mpengine;

import java.awt.Canvas;
import java.awt.Graphics2D;

public interface IEngineInterface {
    void init(Settings settings);

    void updateLoop();

    void drawLoop(Canvas canvas, Graphics2D graphics);

    static void start(IEngineInterface engineInterface) {
        EngineFrame engineFrame = new EngineFrame();
        EngineThread engineThread = new EngineThread(engineInterface, engineFrame);
        engineInterface.init(engineFrame);
        new Thread(engineThread).start();
    }
}
