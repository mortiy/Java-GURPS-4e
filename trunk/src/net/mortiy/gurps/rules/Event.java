package net.mortiy.gurps.rules;

/**
 * Events that may happen and should be handled by system:
 * <p/>
 * 1. Magery (p. 66)
 * > The GM makes a Sense roll (p. 358) when you first see a magic item, and again when you first touch it.
 */
public class Event {

    public static interface Emitter {
        public void listen(Action action, Event.Listener listener);
    }

    public static interface Listener {
        public void emit();
    }

    public static void on(Individual c, Action action, Listener listener) {
        c.listen(action, listener);
    }
}
