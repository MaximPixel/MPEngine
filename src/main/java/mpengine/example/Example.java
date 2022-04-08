package mpengine.example;

import mpengine.IEngineInterface;
import mpengine.Settings;

import java.awt.*;

public class Example implements IEngineInterface {
	private Point mousePoint;

	public static void main(String[] args) {
		IEngineInterface.start(new Example());
	}

	@Override
	public void init(Settings settings) {
		settings.getEngineInput().addMouseMoveListenerEvent(event -> mousePoint = event.getPoint());
	}

	@Override
	public void updateLoop() {

	}

	@Override
	public void drawLoop(Canvas canvas, Graphics2D graphics) {
		graphics.drawOval(0, 0, canvas.getWidth(), canvas.getHeight());
		if (mousePoint != null) {
			graphics.drawOval(mousePoint.x, mousePoint.y, 32, 32);
		}
	}
}
