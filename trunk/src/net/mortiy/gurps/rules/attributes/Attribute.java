package net.mortiy.gurps.rules.attributes;

import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.skills.interfaces.ISkillDefault;

/**
* Created with IntelliJ IDEA.
* User: oleksandr.sidko
* Date: 09.12.12
* Time: 14:00
* To change this template use File | Settings | File Templates.
*/
public enum Attribute implements ISkillDefault, Modifier.IModifiable {
    Unknown,
    // Basic
    Strength,
    Dexterity,
    Intelligence,
    Health,

    // Secondary
    Damage,
    Dodge,
    BasicLift,
    HitPoints,
    Will,
    Perception,
    FatiguePoints,
    BasicSpeed,
    BasicMove
}
