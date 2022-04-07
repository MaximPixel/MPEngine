package mpengine.example;

import mpengine.EngineFrame;
import mpengine.IEngineInterface;

import java.awt.*;

public class Example implements IEngineInterface {
	public static void main(String[] args) {
		IEngineInterface.start(new Example());
	}

	@Override
	public void init(EngineFrame engineFrame) {
		engineFrame.getEngineInput().addMouseClickListener(
				(mouseX, mouseY, button) -> System.out.println(mouseX + " " + mouseY + " " + button)
		);
	}

	@Override
	public void updateLoop() {

	}

	@Override
	public void drawLoop(Canvas canvas, Graphics2D graphics) {
		graphics.drawOval(0, 0, canvas.getWidth(), canvas.getHeight());
	}
}
