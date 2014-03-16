package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.combat.Damage;
import net.mortiy.gurps.rules.hazards.Acid;
import net.mortiy.gurps.rules.hazards.Hazard;
import net.mortiy.gurps.rules.hazards.afflictions.Euphoria;
import net.mortiy.gurps.rules.table.DiceRoller;
import net.mortiy.gurps.rules.table.RollFormula;
import net.mortiy.gurps.rules.table.Rolls;
import net.mortiy.gurps.rules.table.rolls.DamageRoll;

/**
 * Shoud test how different types of environment influence character
 * i.e. humidity, pressure, temperature
 */
public class HazardsTest extends TestCase {

    DiceRoller diceRoller = DiceRoller.getInstance();

    public void testAcid(){

        Individual character = new Individual(100);
        Hazard acid = new Acid(character);

        acid.affect();
    }

    public void testAfflictions(){
        Individual character = new Individual(100);
        Hazard euphoria = new Euphoria(character);

        euphoria.affect();
        assertEquals("Character with Euphoria should have -3 to all skill rolls", -3f, character.getTotalModifier(Rolls.SkillRoll));
        assertEquals("Character with Euphoria should have -3 to all self-control rolls", -3f, character.getTotalModifier(Rolls.SelfControlRoll));

        euphoria.remove();
        assertEquals("After Euphoria goes away, character should have normal stats", 0f, character.getTotalModifier(Rolls.SkillRoll));
        assertEquals("After Euphoria goes away, character should have normal stats", 0f, character.getTotalModifier(Rolls.SelfControlRoll));
    }

    public void testHumidity(){

    }


}

