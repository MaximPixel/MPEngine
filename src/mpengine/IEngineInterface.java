package mpengine;

import java.awt.Canvas;
import java.awt.Graphics2D;

public interface IEngineInterface {

    void updateLoop();

    void drawLoop(Canvas canvas, Graphics2D graphics);
}
