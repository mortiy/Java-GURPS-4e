package net.mortiy.gurps.rules;

import java.util.*;

/**
 * Time
 * Time management
 */
public class Time  {

    public static int TURN = 1000;

    public interface TimeTickListener {
        public void tick(long time);
    }
    public interface TimeEventListener {
        public void run();
    }

    Timer tickTimer = new Timer();
    Timer delayedTimer = new Timer();

    Date time = new Date();
    List<TimeTickListener> timeTickListeners = new ArrayList<TimeTickListener>();

    private static Time instance = new Time();
    public static Time getInstance(){
        return instance;
    }
    private Time() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                notifyListeners();
            }
        };
        tickTimer.schedule(timerTask, 0, 5000);
    }

    public TimerTask subscribeForTimeEvent(final TimeEventListener listener, long delayInMilliseconds){
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                listener.run();
            }
        };

        delayedTimer.schedule(timerTask, delayInMilliseconds);
        return timerTask;
    }

    public void subscribeForTick(TimeTickListener listener){
        timeTickListeners.add(listener);
    }

    public void unsubscribeFromTick(TimeTickListener listener){
        timeTickListeners.remove(listener);
    }

    public long getValue(){
        return time.getTime();
    }

    private void notifyListeners(){
        long currentTime = time.getTime();
        for(TimeTickListener l : timeTickListeners){
            l.tick(currentTime);
        }
    }
}
