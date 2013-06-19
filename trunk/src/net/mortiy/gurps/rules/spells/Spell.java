package net.mortiy.gurps.rules.spells;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 14.12.12
 * Time: 0:37
 * To change this template use File | Settings | File Templates.
 */
public abstract class Spell {
    public static enum DurationType {
        Instant,
        Short,
        Long
    }
    String name;
    int duration;
    int cost;
    int timeToCast;
    List<Spell> prerequisites = new ArrayList<Spell>();

    protected Spell(String name, int duration, int cost, int timeToCast) {
        this.name = name;
        this.duration = duration;
        this.cost = cost;
        this.timeToCast = timeToCast;
    }
}
