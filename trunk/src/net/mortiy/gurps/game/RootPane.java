package net.mortiy.gurps.game;

import de.matthiasmann.twl.DesktopArea;
import de.matthiasmann.twl.Event;
import de.matthiasmann.twl.Widget;

/**
 * RootPane for all game states.
 * It forwards input events which where not handled by the UI to the game state.
 * 
 * @author Matthias Mann
 */
public class RootPane extends DesktopArea {

    protected BasicTWLGameState state;
    protected int oldMouseX;
    protected int oldMouseY;

    public RootPane(BasicTWLGameState state) {
        if (state == null) {
            throw new NullPointerException("state");
        }
        this.state = state;

        setCanAcceptKeyboardFocus(true);
    }

    /**
     * When subclassing this class it's strongly suggested to provide
     * a default constructor to allow previewing in the Theme Editor.
     */
    protected RootPane() {
        this.state = null;
        
        setCanAcceptKeyboardFocus(true);
        
        System.err.println("This constructor is only intended to by called to preview subclass in the TWL Theme Editor");
    }

    /**
     * Returns the game state to which this root pane is associated with.
     * @return the game state or null when in preview mode (Theme Editor).
     * @see #isPreviewMode() 
     */
    public final BasicTWLGameState getState() {
        return state;
    }
    
    /**
     * Returns true when the root pane is in preview mode (Theme Editor).
     * @return true when the root pane is in preview mode (Theme Editor).
     */
    public final boolean isPreviewMode() {
        return state == null;
    }
    
    @Override
    protected void keyboardFocusLost() {
        if(state != null) {
            state.keyboardFocusLost();
        }
    }

    @Override
    protected boolean requestKeyboardFocus(Widget child) {
        if (child != null && state != null) {
            state.keyboardFocusLost();
        }
        return super.requestKeyboardFocus(child);
    }

    @Override
    protected boolean handleEvent(Event evt) {
        if (super.handleEvent(evt)) {
            return true;
        }

        if(state != null) {
            switch (evt.getType()) {
                case KEY_PRESSED:
                    state.keyPressed(evt.getKeyCode(), evt.getKeyChar());
                    break;
                case KEY_RELEASED:
                    state.keyReleased(evt.getKeyCode(), evt.getKeyChar());
                    break;
                case MOUSE_CLICKED:
                    state.mouseClicked(evt.getMouseButton(), evt.getMouseX(), evt.getMouseY(), evt.getMouseClickCount());
                    break;
                case MOUSE_BTNDOWN:
                    state.mousePressed(evt.getMouseButton(), evt.getMouseX(), evt.getMouseY());
                    break;
                case MOUSE_BTNUP:
                    state.mouseReleased(evt.getMouseButton(), evt.getMouseX(), evt.getMouseY());
                    break;
                case MOUSE_ENTERED:
                case MOUSE_MOVED:
                    state.mouseMoved(oldMouseX, oldMouseY, evt.getMouseX(), evt.getMouseY());
                    break;
                case MOUSE_DRAGGED:
                    state.mouseDragged(oldMouseX, oldMouseY, evt.getMouseX(), evt.getMouseY());
                    break;
                case MOUSE_WHEEL:
                    state.mouseWheelMoved(evt.getMouseWheelDelta());
                    break;
            }
        }

        if (evt.isMouseEvent()) {
            oldMouseX = evt.getMouseX();
            oldMouseY = evt.getMouseY();
        }
        return true;
    }

    @Override
    protected void layout() {
        super.layout();
        state.layoutRootPane();
    }
}
