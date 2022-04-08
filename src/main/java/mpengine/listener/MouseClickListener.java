package mpengine.listener;

import java.awt.event.MouseEvent;

public interface MouseClickListener extends MouseClickEventListener {
	void onClick(int mouseX, int mouseY, int button);

	@Override
	default void onClick(MouseEvent event) {
		onClick(event.getX(), event.getY(), event.getButton());
	}
}
