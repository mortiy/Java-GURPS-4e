package net.mortiy.gurps.rules.equipment.weapon;

import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.equipment.weapon.statistics.WeaponMode;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 16.12.12
 * Time: 1:32
 * To change this template use File | Settings | File Templates.
 */
public class FirearmRangedWeapon extends RangedWeapon {
    /**
     * A measure of how easy the weapon is to control when firing
     rapidly: the higher the value, the
     less controllable the weapon. Rcl 1
     means the weapon is recoilless, or
     nearly so.
     */
    protected Legality legalityClass;
    protected int recoil;

    public FirearmRangedWeapon(String name, TechLevel.Level techLevel, WeaponMode damageTypes[], float cost, float weight) {
        this(name, techLevel, Arrays.asList(damageTypes), cost, weight);
    }

    public FirearmRangedWeapon(String name, TechLevel.Level techLevel, List<WeaponMode> damageTypes, float cost, float weight) {
        super(name, techLevel, damageTypes, cost, weight);
    }


    public static enum Legality {
        /**
         * Banned. The item is restricted to the armed forces
         of certain governments, who will go to extremes to
         keep it out of the hands of individuals and “have-not”
         governments. Examples: nuclear and biological
         weapons.
         */
        LC0,
        /**
         * Military. The item is available only to armed
         forces or secret spy agencies in most societies.
         Examples: Anti-tank weapons; fighting vehicles
         */
        LC1,
        /**
         * Restricted. Only military, police, or intelligence
         agencies may possess the item in most societies –
         although some licensed civilians might be permitted
         to keep it on their own property. Examples: Assault
         rifle; armored vehicles.
         */
        LC2,
        /**
         * Licensed. The item requires registration with the
         authorities in most societies. Registration might
         involve a fee or examination, and might be denied to
         criminals, minors, etc. Examples: Automobile; handgun;
         hunting rifle.
         */
        LC3,
        /**
         * Open. The item is openly available in most societies,
         but tightly controlled societies might restrict
         access or use. Examples: Computer; sword; shotgun;
         motor scooter.
         */
        LC4
    }
}
