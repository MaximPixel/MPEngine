package mpengine.listener;

import java.awt.event.MouseEvent;

public interface MousePressListener extends MousePressEventListener {
	void onPress(int mouseX, int mouseY, int button);

	@Override
	default void onPress(MouseEvent event) {
		onPress(event.getX(), event.getY(), event.getButton());
	}
}
