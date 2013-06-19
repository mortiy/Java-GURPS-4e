package net.mortiy.gurps.rules.combat.maneuver;

import net.mortiy.gurps.rules.combat.Defense;
import net.mortiy.gurps.rules.combat.Fighter;
import net.mortiy.gurps.rules.combat.Maneuver;
import net.mortiy.gurps.rules.combat.ManeuverType;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 15.01.13
 * Time: 19:01
 * To change this template use File | Settings | File Templates.
 */
public class AllOutDefenseManeuver extends Maneuver {

    AllOutDefenseManeuver.Type defenseType;
    Defense.Strategy defenseStrategy;

    public AllOutDefenseManeuver(Fighter fighter, AllOutDefenseManeuver.Type defenseType) {
        this(fighter, defenseType, null);
    }

    public AllOutDefenseManeuver(Fighter fighter, AllOutDefenseManeuver.Type defenseType, Defense.Strategy defenseStrategy) {
        super(fighter, ManeuverType.AllOutDefense);
        this.defenseType = defenseType;
        this.defenseStrategy = defenseStrategy;
    }

    public enum Type {
        IncreasedDefense,
        DoubleDefense
    }

    public static Type[] types = Type.values();

    public static Type getType(int i){
        return types[i];
    }

    public Type getDefenseType() {
        return defenseType;
    }

    public Defense.Strategy getDefenseStrategy() {
        return defenseStrategy;
    }
}
