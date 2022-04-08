package mpengine;

import mpengine.listener.*;

import java.awt.Canvas;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class EngineInput implements KeyListener, MouseMotionListener, MouseListener {
    private ArrayList<MouseClickEventListener> mouseClickListeners;
    private ArrayList<MousePressEventListener> mousePressListeners;
    private ArrayList<MouseMoveEventListener>  mouseMoveListeners;

    public static final int KEYS_COUNT = 256;
    private final boolean[] keys = new boolean[KEYS_COUNT];
    private boolean[] keys_real = new boolean[KEYS_COUNT];
    private boolean[] keys_last = new boolean[KEYS_COUNT];
    private final char[] keys_chars = new char[KEYS_COUNT];

    private Point mousePos = new Point();

    public static final int BUTTONS_COUNT = 6;
    private final boolean[] buttons = new boolean[BUTTONS_COUNT];
    private boolean[] buttons_real = new boolean[BUTTONS_COUNT];
    private boolean[] buttons_last = new boolean[BUTTONS_COUNT];

    public EngineInput(EngineFrame frame) {
        Canvas canvas = frame.getCanvas();
        canvas.addKeyListener(this);
        canvas.addMouseMotionListener(this);
        canvas.addMouseListener(this);
    }

    public boolean isKey(int key) {
        return keys[key];
    }

    public boolean isKeyUp(int key) {
        return !keys[key] && keys_last[key];
    }

    public boolean isKeyDown(int key) {
        return keys[key] && !keys_last[key];
    }

    public boolean isKeyReal(int key) {
        return keys_real[key];
    }

    public char getKeyChar(int key) {
        return keys_chars[key];
    }

    public Point getMousePos() {
        return mousePos;
    }

    public boolean isButton(int button) {
        return buttons[button];
    }

    public boolean isButtonUp(int button) {
        return !buttons[button] && buttons_last[button];
    }

    public boolean isButtonDown(int button) {
        return buttons[button] && !buttons_last[button];
    }

    public boolean isButtonReal(int button) {
        return buttons_real[button];
    }

    public void update() {
        keys_last = keys.clone();
        keys_real = new boolean[KEYS_COUNT];
        buttons_last = buttons.clone();
        buttons_real = new boolean[KEYS_COUNT];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c < KEYS_COUNT) {
            keys[c] = true;
            keys_real[c] = true;
            keys_chars[c] = e.getKeyChar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int c = e.getKeyCode();
        if (c < KEYS_COUNT) {
            keys[c] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos = e.getPoint();
    }

    @Override
    public void mouseMoved(MouseEvent event) {
        mousePos = event.getPoint();
        if (mouseMoveListeners != null) {
            mouseMoveListeners.forEach(listener -> listener.onMove(event));
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        if (mouseClickListeners != null) {
            mouseClickListeners.forEach(listener -> listener.onClick(event));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent event) {
        buttons[event.getButton()] = true;
        buttons_real[event.getButton()] = true;
        if (mousePressListeners != null) {
            mousePressListeners.forEach(listener -> listener.onPress(event));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttons[e.getButton()] = false;
    }

    public void addMouseClickListenerEvent(MouseClickEventListener listener) {
        if (mouseClickListeners == null) {
            mouseClickListeners = new ArrayList<>();
        }
        mouseClickListeners.add(listener);
    }

    public void addMouseClickListener(MouseClickListener listener) {
        addMouseClickListenerEvent(listener);
    }

    public void addMousePressListenerEvent(MousePressEventListener listener) {
        if (mousePressListeners == null) {
            mousePressListeners = new ArrayList<>();
        }
        mousePressListeners.add(listener);
    }

    public void addMousePressListener(MousePressListener listener) {
        addMousePressListenerEvent(listener);
    }

    public void addMouseMoveListenerEvent(MouseMoveEventListener listener) {
        if (mouseMoveListeners == null) {
            mouseMoveListeners = new ArrayList<>();
        }
        mouseMoveListeners.add(listener);
    }
}
