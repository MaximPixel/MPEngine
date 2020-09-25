package mpengine;

import java.awt.Point;

public class EngineCamera {

    public static MPEngineObject engine;
    public float cameraX, cameraY;
    public final float minZoom, maxZoom;
    public float cameraZoom = 1F;

    public EngineCamera(MPEngineObject engine, float cameraX, float cameraY, float minZoom, float maxZoom) {
        this.engine = engine;
        this.cameraX = cameraX;
        this.cameraY = cameraY;
        this.minZoom = minZoom;
        this.maxZoom = maxZoom;
    }

    public EngineCamera(MPEngineObject engine, float cameraX, float cameraY) {
        this(engine, cameraX, cameraY, 1F / 16F, 1.5F);
    }

    public void addCameraZoom(float zoom) {
        cameraZoom += zoom;
        if (cameraZoom < minZoom)
            cameraZoom = minZoom;
        if (cameraZoom > maxZoom)
            cameraZoom = maxZoom;
    }

    public float getCameraTranslateX() {
        return (int) (-cameraX * cameraZoom + engine.frame.getCanvas().getWidth() / 2);
    }

    public float getCameraTranslateY() {
        return (int) (-cameraY * cameraZoom + engine.frame.getCanvas().getHeight() / 2);
    }

    public Point screenToWorldPoint(Point mp) {
        int xx = (int) (mp.x / cameraZoom - getCameraTranslateX() / cameraZoom);
        int yy = (int) (mp.y / cameraZoom - getCameraTranslateY() / cameraZoom);
        return new Point(xx, yy);
    }
}
