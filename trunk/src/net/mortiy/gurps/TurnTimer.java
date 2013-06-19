package net.mortiy.gurps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.02.13
 * Time: 23:43
 * To change this template use File | Settings | File Templates.
 */
public class TurnTimer {

    interface TurnEvent extends Runnable {}

    private Map<Integer, List<TurnEvent>> turnEvents = new HashMap<>();
    private int turnDuration = 1000; // ms
    private int currentTurn = 0;

    public void turn(){
        currentTurn++;
        if(turnEvents.containsKey(currentTurn)){
            for(TurnEvent turnEvent : turnEvents.get(currentTurn)){
                turnEvent.run();
            }
        }
    }

    public void runAfterSeconds(float seconds, TurnEvent turnEvent){
        int turns = (int) Math.floor((seconds * 1000) / turnDuration);
        runAfterTurns(turns, turnEvent);
    }

    public void runAfterTurns(int turns, TurnEvent turnEvent){
        runOnTurn(currentTurn + turns, turnEvent);
    }

    private void runOnTurn(int turn, TurnEvent turnEvent){
        if(!turnEvents.containsKey(turn)){
            turnEvents.put(turn, new ArrayList<TurnEvent>());
        }
        turnEvents.get(turn).add(turnEvent);
    }

}
