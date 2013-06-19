package net.mortiy.gurps.rules.combat;

/**
 * ManeuverType
 * ==============
 * A “maneuver” is an action that
 * you can take on your turn. Each turn,
 * you must choose one of the following
 * maneuvers.
 */
public enum ManeuverType {
    /**
     * This is a full-turn maneuver used to aim a ranged weapon (or a device such as a camera or telescope).
     * You must choose a specific target. You can’t aim at something that you can’t see or otherwise detect.
     */
    Aim,
    /**
     * Attack any foe with a ready weapon, making no effort to defend against enemy attacks.
     */
    AllOutAttack,
    /**
     * This is the maneuver to choose when you’re beset by foes – especially
     * foes who like All-Out Attacks!
     */
    AllOutDefense,
    /**
     * Use this maneuver to make an armed or unarmed attack in melee
     * combat, or to use a thrown or missile weapon in ranged combat.
     * To use a weapon to attack, it must be ready.
     */
    Attack,
    /**
     * This maneuver lets you switch between any two “postures” (stances in which you can pose your body).
     */
    ChangePosture,
    /**
     * You concentrate on one primarily mental task (even it has a minor physical
     * component, like operating controls, gesturing, or speaking).
     * This may be casting a magical spell, using a psi ability, making a Sense roll to spot
     * an invisible warrior, making a Leadership roll to give orders, making
     * an Electronics Operation roll to operate a sensor, or any similar action,
     * including most IQ-based skill rolls.
     */
    Concentrate,
    /**
     * Anyone who is just standing still is assumed to be doing nothing. In particular,
     * when combat begins, anyone who has not yet taken a turn is treated
     * as if he took this maneuver before entering combat.
     */
    DoNothing,
    /**
     * This maneuver is the melee combat equivalent of Aim. It lets you take time
     * to study an adversary in order to gain a combat bonus on a subsequent
     * attack. You must specify one visible opponent who is close enough to
     * attack unarmed or with a ready melee weapon, or whom you could reach
     * with a single Move and Attack maneuver.
     * You are sizing him up and looking for the right moment to strike.
     */
    Evaluate,
    /**
     * “Fake” a melee attack. You cannot Feint someone unless you could have
     * hit him with a melee attack – that is, your weapon is ready and your foe is within reach.
     * This maneuver is not an attack, though, and does not make your weapon unready.
     */
    Feint,
    /**
     * Move, but take no other action except those specified under Free Actions (p. 363).
     * You may move any number of yards up to your full Move score.
     * Most other maneuvers allow at least some movement on your turn;
     * take this maneuver if all you want to do is move.
     */
    Move,
    /**
     * Move as described for the Move maneuver (p. 364), but during or after your move,
     * make a single, poorly aimed attack – either unarmed or with a ready weapon.
     */
    MoveAndAttack,
    /**
     * Take a Ready maneuver to pick up or draw any item and prepare it for use;
     * e.g., to pull a sword from its sheath or a gun from its holster, or to reload a firearm.
     * In some cases, you may also need a Ready maneuver to regain control of an unwieldy weapon
     * after a swing, or to adjust the reach of a long weapon – see the Melee Weapon Table (p. 271).
     */
    Ready,
    /**
     * Do nothing unless a particular event you specified in advance occurs before your next turn;
     * e.g., a foe moves into range. If that happens, you may transform your Wait into an Attack, Feint,
     * All-Out Attack (you must specify the option before acting), or Ready maneuver.
     * If you are reacting to someone else, this interrupts his turn, but he can
     * resume it after you’ve acted.
     */
    Wait
}
