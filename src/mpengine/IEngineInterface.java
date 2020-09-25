package mpengine;

import java.awt.Canvas;
import java.awt.Graphics2D;

public interface IEngineInterface {

    public void updateLoop();

    public void drawLoop(Canvas canvas, Graphics2D graphics);
}
