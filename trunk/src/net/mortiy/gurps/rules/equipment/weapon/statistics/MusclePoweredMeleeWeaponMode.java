package net.mortiy.gurps.rules.equipment.weapon.statistics;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents damage description of Melee Muscle Powered weapons
 * {@link MusclePoweredDamage}
 */
public class MusclePoweredMeleeWeaponMode extends WeaponMode {
    List<Reach> reaches = new ArrayList<>();
    Parry parry;
    private MusclePoweredDamage.Type basicType;

    public MusclePoweredMeleeWeaponMode(MusclePoweredDamage.Type basicType, WeaponDamage weaponDamage) {
        super(weaponDamage);
        this.basicType = basicType;
    }

    public MusclePoweredDamage.Type getBasicType() {
        return basicType;
    }

    public MusclePoweredMeleeWeaponMode addReach(int flags){
        reaches.add(new Reach(flags));
        return this;
    }
    public MusclePoweredMeleeWeaponMode addReach(int distance, int flags){
        reaches.add(new Reach(distance, flags));
        return this;
    }
    public MusclePoweredMeleeWeaponMode addReach(int minDistance, int maxDistance, int flags){
        reaches.add(new Reach(minDistance, maxDistance, flags));
        return this;
    }

    public MusclePoweredMeleeWeaponMode setParry(int modifier){
        parry = new Parry(modifier);
        return this;
    };
    public MusclePoweredMeleeWeaponMode setParry(int modifier, Parry.Type parryType){
        parry = new Parry(modifier, parryType);
        return this;
    };

}
