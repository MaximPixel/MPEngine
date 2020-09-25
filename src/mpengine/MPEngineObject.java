package mpengine;

public class MPEngineObject {

    public EngineThread thread;
    public EngineFrame frame;

    public void start(IEngineInterface loop) {
        new Thread(thread = new EngineThread(loop, frame = new EngineFrame())).start();
    }
}
