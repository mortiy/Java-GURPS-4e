package net.mortiy.gurps.rules.equipment.weapon.statistics;

/**
* Created with IntelliJ IDEA.
* User: oleksandr.sidko
* Date: 16.12.12
* Time: 10:47
* To change this template use File | Settings | File Templates.
*/
public class Reach {
    protected int minDistance;
    protected int maxDistance;
    private int flags;

    // Flags
    public final static int NO_FLAGS = 0;
    public final static int HAS_CLOSE_COMBAT = 1;
    public final static int REQUIRES_READY_MANEUVER = 2;
    public final static int CAN_BE_THROWN = 4;

    public Reach(int flags) {
        this(0, flags);
    }

    public Reach(int distance, int flags) {
        this(distance, distance, flags);
    }

    public Reach(int minDistance, int maxDistance, int flags) {
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.flags = flags;
    }

    /**
     * Return minimum weapon's attack range
     * @return
     */
    public int getMinDistance() {
        return minDistance;
    }

    /**
     * Return maximum weapon's attack range.
     * @return
     */
    public int getMaxDistance() {
        return maxDistance;
    }

    public boolean hasCloseCombat() {
        return (flags & HAS_CLOSE_COMBAT) > 0;
    }

    public boolean requiresReadyManeuver() {
        return (flags & REQUIRES_READY_MANEUVER) > 0;
    }
}
