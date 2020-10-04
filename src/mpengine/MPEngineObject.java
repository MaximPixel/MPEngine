package mpengine;

public class MPEngineObject {

    private static EngineThread thread;
    private static EngineFrame frame;

    private MPEngineObject() {}

    public static void start(IEngineInterface loop) {
        new Thread(thread = new EngineThread(loop, frame = new EngineFrame())).start();
    }

    public static EngineThread getThread() {
        return thread;
    }

    public static EngineFrame getFrame() {
        return frame;
    }
}
