package net.mortiy.gurps;

import junit.framework.TestCase;
import net.mortiy.gurps.rules.Individual;
import net.mortiy.gurps.rules.TechLevel;
import net.mortiy.gurps.rules.World;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.individual.Build;
import net.mortiy.gurps.rules.equipment.weapon.statistics.MusclePoweredDamage;
import net.mortiy.gurps.rules.skills.all.*;
import net.mortiy.gurps.rules.skills.all.meleeweapon.sword.Knife;
import net.mortiy.gurps.rules.skills.all.meleeweapon.sword.Shortsword;
import net.mortiy.gurps.rules.table.RollFormula;
import net.mortiy.gurps.rules.traits.Disadvantage;
import net.mortiy.gurps.rules.traits.Quirk;
import net.mortiy.gurps.rules.traits.SelfControlDisadvantage;
import net.mortiy.gurps.rules.traits.all.*;
import net.mortiy.gurps.rules.traits.limitations.ESPLimitation;
import net.mortiy.gurps.rules.utils.Converter;

/**
 * Represents full character generation process on the example of
 * rulebook's example Dai Blackthorn
 */
public class CharacterTest extends TestCase {
    public void testCharacterCreation() throws Exception {
        /**
         * To illustrate character creation, we present Dai Blackthorn, thief
         * extraordinaire. We’ll create Dai as a full member of ISWAT.
         * As an established hero, he’ll have a base of 250 points.
         */
        Individual dai = new Individual(250);
        dai.setName("Dai Blackthorn");

        // region Basic Attributes

        /**
         *  Dai is on the small side: ST 8 (-20 points)
         */
        dai.setBasicAttribute(Attribute.Strength, 8);
        assertEquals("ST 8 (-20 points)", -20f, dai.getBasicAttribute(Attribute.Strength).getCost());
        /**
         * A “thief extraordinaire”
         * should have catlike grace, so we give him an amazing DX 15 (100 points).
         */
        dai.setBasicAttribute(Attribute.Dexterity, 15);
        assertEquals("DX 15 (100 points)", 100f, dai.getBasicAttribute(Attribute.Dexterity).getCost());
        /**
         * Dai is also cunning and tough enough to survive on the street; therefore,
         * we take IQ 12 (40 points) and HT 12 (20 points)
         */
        dai.setBasicAttribute(Attribute.Intelligence, 12);
        assertEquals("IQ 12 (40 points)", 40f, dai.getBasicAttribute(Attribute.Intelligence).getCost());
        dai.setBasicAttribute(Attribute.Health, 12);
        assertEquals("HT 12 (20 points)", 20f, dai.getBasicAttribute(Attribute.Health).getCost());

        /**
         * ST 8 gives a thrust damage of 1d-3, a swing damage of 1d-2, a Basic
         * Lift of 13 lbs., and 8 HP
         */
        assertEquals("ST 8 gives a thrust damage of 1d-3", new RollFormula(1, -3), MusclePoweredDamage.getDamageFormula(dai, MusclePoweredDamage.Type.Thrusting));
        assertEquals("ST 8 gives a swing damage of 1d-2", new RollFormula(1, -2), MusclePoweredDamage.getDamageFormula(dai, MusclePoweredDamage.Type.Swinging));
        assertEquals("ST 8 gives Basic Lift of 13 lbs", 13.0f, dai.getBasicLift().getValue());
        assertEquals("ST 8 gives 8 HP", 8.0f, dai.getHitpoints().getValue());

        // endregion

        // region Secondary Characteristics
        /**
         * But Dai is tough, and no easier to kill than the
         * average man, so we raise HP to 10 (4 points)
         */
        dai.increaseAttribute(Attribute.HitPoints, 2);
        assertEquals("Increase HP by 2 levels", 10.0f, dai.getHitpoints().getValue());
        assertEquals("HP 10 costs 4 points", 4f, dai.getHitpoints().getCost());

        /**
         * IQ 12 gives Dai a Will and Perception of 12. Since a talented thief must
         * be able to spot traps and pursuers, we increase Per to 15 (15 points) –
         * amazing, and a match for his DX!
         */
        assertEquals("IQ 12 gives Perception of 12", 12.0f, dai.getPerception().getValue());
        dai.increaseAttribute(Attribute.Perception, 3);
        assertEquals("Increase Perception to 15 points", 15f, dai.getPerception().getValue());
        assertEquals("Perception 15 costs 4 points", 15f, dai.getPerception().getCost());

        /**
         * HT 12 gives Dai 12 FP, but Dai prefers to avoid fatiguing labor in the
         * first place, so we lower FP to 10 (-6 points), which is average.
         */
        assertEquals("HT 12 gives Dai 12 Fatigue Points", 12.0f, dai.getFatiguePoints().getValue());
        dai.decreaseAttribute(Attribute.FatiguePoints, 2);
        assertEquals("Decrease Fatigue Points to 10 points", 10f, dai.getFatiguePoints().getValue());
        assertEquals("Fatigue Points 10 costs -6 points", -6f, dai.getFatiguePoints().getCost());

        /**
         * Dai’s Basic Speed is (15 + 12)/4 = 6.75. To get Dodge 10 and Basic Move
         * 7 – useful for evading enemies when his teleportation fails – we raise
         * Basic Speed to an even 7.00 (5 points).
         */
        assertEquals("Dai’s Basic Speed is (15 + 12)/4 = 6.75", 6.75f, dai.getBasicSpeed().getValue());
        dai.increaseAttribute(Attribute.BasicSpeed, 0.25f);
        assertEquals("Raise Basic Speed to 7.00", 7.00f, dai.getBasicSpeed().getValue());
        assertEquals("Dodge at Basic Speed 7.00", 10.0f, dai.getDodge().getValue());
        assertEquals("Basic Move at Basic Speed 7.00", 7f, dai.getBasicMove().getValue());

        /**
         * Adding everything up, these traits cost Dai 158 points.
         */
        assertEquals("Dai's traits cost is 158 points", 158f, dai.getCharacterCost());

        // endregion

        // region Body
        /**
         * We want Dai to look unremarkable – thieves who stand out don’t last
         * long! So we choose an Average build.
         */
        dai.setBuild(Build.Average);
        /**
         * For ST 8, this suggests a height
         * between 4’10” and 5’8,” and a weight of 90 to 150 lbs. We pick 5’6” and 115
         * lbs. We make Dai’s appearance Average as well.
         */
        Converter.Value heightValue = Converter.getInstance().parse("5'6\"");
        dai.getBody().setHeight(heightValue.value);
        dai.getBody().setHeight(115);
        /**
         * Since Dai is average in all respects, he pays 0 points.
         * His point total remains at 158 points.
         */
        assertEquals("Dai's is average in all respects, so cost is still 158 points", 158f, dai.getCharacterCost());

        // endregion

        // region Social

        /**
         * Dai is from a TL3 (medieval) world, but that’s “background color” – his
         & ISWAT trainers corrected this deficiency. He currently functions at TL8,
         & which is standard in the Infinite Worlds setting. The cost to be at the campaign-average tech level is 0 points.
         */
        dai.setTechLevel(TechLevel.TL8);

        /**
         * Dai is familiar with Yrth’s culture and knows one of its languages:
         & Anglish. This costs 0 points; everybody gets a culture and a language for
         & free.
         */
        dai.setNativeLanguage(World.Language.Anglish);
        dai.getLanguages().put(World.Language.English, Individual.LanguageLevel.Accented);

        /**
         * But Dai is also familiar with the culture of ISWAT’s world, Homeline,
         * and has passable English. Cultural Familiarity (Homeline) is 1 point,
         * while English (Accented) is another 4 points.
         */
        CulturalFamiliarity culturalFamiliarity = new CulturalFamiliarity(dai);
        dai.addTrait(culturalFamiliarity);
        culturalFamiliarity.learnCulture("ISWAT", CulturalFamiliarity.CultureType.Homeline);

        /**
         * Dai pays a total of 5 points for his social background.
         * This makes his current point total 163 points.
         */
        assertEquals("Dai's with social background costs 163 points", 163f, dai.getCharacterCost());

        /**
         * ISWAT feeds and clothes Dai, and issues him the equipment he needs
         * on a mission, but does not let him fetch his loot from Yrth.
         * Thus, he does not personally own much. We give him Wealth (Poor), for -15 points
         */

        dai.addTrait(new Wealth(dai))
                .changeLevel(Wealth.Levels.Poor);
        assertEquals("Dai's with pool wealth costs 148 points", 148f, dai.getCharacterCost());

        /**
         * Looking at the traits listed under Privilege  and Social Restraints, we
         choose two to reflect Dai’s job. ISWAT is powerful, and its agents’ Legal
         Enforcement Powers (p. 65) reach across time and space, for 15 points.
         But these powers come with a Duty (p. 133), which occurs on 15 or less
         and is extremely hazardous, for -20 points.
         */
        dai.addTrait(new LegalEnforcementPowers(dai))
                .changeLevel(LegalEnforcementPowers.Level.Special);
        dai.addTrait(new Duty(dai, Duty.Frequency.Always, Duty.Hazard.ExtremelyHazardous));


        /**
         * Dai’s wealth and influence are worth a net -20 points. This lowers his
         running point total to 143 points.
         */
        assertEquals("Dai's with poor wealth costs 143 points", 143f, dai.getCharacterCost());

        // endregion

        // region Advantages

        /**
         * Dai’s main advantage is that he can teleport. This is Warp (p. 97), which
         costs 100 points! But Dai has two special limitations to lower the cost.
         First, his Warp is psionic, so “anti-psi” can keep it from working. This gives
         the Psionic Teleportation limitation, worth -10%. Second, his ability has a
         very short range: 10 yards. That’s a Range Limit limitation worth -50%.
         These limitations mean that Dai gets Warp at 60% off, for 40 points.
         */
        Warp warp = new Warp(dai);
        dai.addTrait(warp);
        assertEquals("Dai's cost with Warp", 183f, dai.getCharacterCost());
        /**
         * We decide to give Dai another psi ability useful to a thief: a “sixth
         sense” that warns him of traps and similar dangers. This is Danger Sense
         (p. 47), with the ESP special limitation. Danger Sense costs a basic 15
         points, but the -10% limitation reduces this to 13.5 points, which rounds
         up to 14 points.
         */
        DangerSense dangerSense = new DangerSense(dai);
        dai.addTrait(dangerSense);
        dangerSense.addLimitation(new ESPLimitation());
        assertEquals("Danger Sense should have 10% discount", -10, dangerSense.getCostPercent());
        assertEquals("Danger Sense with ESP limitation should by 10% cheaper", 14, dangerSense.getCost());
        assertEquals("Dai's cost with Danger Sense", 197f, dai.getCharacterCost());

        /**
         * Even without his psi abilities, Dai is a gifted thief. His specialty is second-story work,
         so we add Flexibility (p. 56), for 5 points, because it gives
         a big bonus when climbing; Perfect Balance (p. 74), for 15 points, so he
         won’t lose his balance and fall off; and Absolute Direction (p. 34), for 5
         points, to help him negotiate back alleys and rooftops.
         */
        dai.addTrait(new Flexibility(dai));
        assertEquals("Flexibillity costs 5 points", 5, dai.getTrait("Flexibility").getCost());
        assertEquals("Dai's cost with Flexilibity", 202f, dai.getCharacterCost());
        dai.addTrait(new PerfectBalance(dai));
        assertEquals("Perfect Balance costs 15 points", 15, dai.getTrait("Perfect Balance").getCost());
        assertEquals("Dai's cost with Perfect Balance", 217f, dai.getCharacterCost());
        dai.addTrait(new AbsoluteDirection(dai));
        assertEquals("Absolute Direction costs 5 points", 5, dai.getTrait("Absolute Direction").getCost());
        assertEquals("Dai's cost with Absolute Direction", 222f, dai.getCharacterCost());

        /**
         * Since we want Dai to be able to disappear into a crowd, we throw in
         the 1-point Honest Face perk (p. 101) – he doesn’t "look like a thief."
         Dai’s advantages total 80 points, raising his current point total to 223 points.
         */
        dai.addTrait(new HonestFace(dai));
        assertEquals("Dai’s advantages total 80 points, raising his current point total to 223 points", 223f, dai.getCharacterCost());

        // endregion

        // region Disadvantages

        /**
         * Dai believes he can steal anything and escape any situation.
         * He definitely  suffers from Overconfidence (p. 148)!
         * This trait is worth "-5 points*." The “*” indicates a trait that requires a self-control number. To
         avoid crippling Dai, we decide that he can set his attitude aside to weigh
         risks “quite often,” or on a 12 or less. Overconfidence (12) is worth the listed cost: -5 points.
         */
        Overconfidence overconfidence = new Overconfidence(dai, SelfControlDisadvantage.Frequency.QuiteOften);
        dai.addTrait(overconfidence);
        assertEquals("Overconfidence (12) is worth the listed cost: -5 points", -5, overconfidence.getCost());

        /**
         * To play up Dai’s twitchy, catlike side, we decide that because of his high
         Perception and Danger Sense, almost any little disturbance wakes him
         up. This gives him Light Sleeper (p. 142), for -5 points
         */
        dai.addTrait(new LightSleeper(dai));
        /**
         * Finally, since an overconfident thief isn’t a typical team player, Dai
         needs a reason to stay with ISWAT. We decide that he has come to see
         those in his squad as a replacement for the “family” slain by the Thieves’
         Guild. Although he’d never admit it, he would die rather than let anything
         bad happen to this family. We represent this with a Sense of Duty (p. 153)
         to his squad – a small group – for -5 points.
         */
        dai.addTrait(new SenseOfDuty(dai));

        /**
         * These disadvantages come to -15 points. This lowers Dai’s running
         point total to 208 points
         */
        assertEquals("This lowers Dai’s running point total to 208 points", 208f, dai.getCharacterCost());
        assertEquals("Dai's disadvatages total cost is -50 points", -50f, dai.getTraitsCost(Disadvantage.class));

        dai.addTrait(new Quirk(dai, "Dislikes deep water"));
        dai.addTrait(new Quirk(dai, "Loves high places"));
        dai.addTrait(new Quirk(dai, "No drugs or alcohol"));
        dai.addTrait(new Quirk(dai, "Sensitive about his height"));
        dai.addTrait(new Quirk(dai, "Showoff"));

        assertEquals("Dai’s quirks are worth -1 point apiece, or -5 points total.", 203f, dai.getCharacterCost());

        // endregion Disadvantages

        // region Skills

        assertEquals("Dai has spent 203 of his 250 points, leaving him with 47 points for skills", 47f, dai.getRemainingPoints());
        /**
         First, a thief must be stealthy. For this, Dai needs the Stealth skill (p. 222).
         We want this to be reliable, so we choose skill level 16.
         Stealth is a DX/Average skill. Since Dai’s DX is 15, level 16 is DX+1 for him.
         From the Skill Cost Table (p. 170), we learn that a level of Attribute+1 in an Average skill costs 4 points.
         */
        dai.addSkill(new Stealth(dai), 16);
        assertEquals("Dai's cost with Stealth (16)", 207f, dai.getCharacterCost());


        /**
         * Any thief worth his salt can pick pockets and open locks.
         * This calls for Pickpocket (p. 213) and Lockpicking (p. 206).
         * We want to buy Dai a 15 in both – not as high as his Stealth, but still reliable.
         * With Dai’s IQ 12, skill 15 is IQ+3 level.
         * This costs 12 points – it’s very expensive to raise a skill so far above its controlling attribute!
         */
        dai.addSkill(new Pickpocket(dai), 15);
        dai.addSkill(new Lockpicking(dai), 15);
        assertEquals("Dai's cost with Lockpicking (15) (4 points) and Pickpocket (15) (12 points)", 223f, dai.getCharacterCost());

        /**
         * We also want Dai to be an adept second-story man
         and escape artist, so we spend 1 point apiece on
         Climbing (p. 183) and Escape (p. 192).
         Of course, we selected these skills knowing
         that Dai’s Flexibility advantage would give +3 to both!
         His Perfect Balance adds another +1 to Climbing, too.
         His final levels are Climbing at 18 and Escape at 16.
         */
        dai.addSkill(new Climbing(dai), 14);
        dai.addSkill(new Escape(dai), 13);

        assertTrue("Dai has Flexibility trait", dai.hasTrait("Flexibility"));
        assertTrue("Dai has Perfect Balance trait", dai.hasTrait("Perfect Balance"));
        assertEquals("Dai’s Flexibility advantage would give +3 to both", 18, dai.getSkill("Climbing").getLevel());
        assertEquals("Dai’s Flexibility advantage would give +3 to both", 16, dai.getSkill("Escape").getLevel());
        assertEquals("Dai's cost with Climbing(14) (4 points) and Escape(13)", 225f, dai.getCharacterCost());


        /**
         * To case an area before he strikes, Dai needs
         Observation skill (p. 211). This is Per/Average. But Dai’s
         Perception is a whopping 15, so he doesn’t need to
         spend many points: 2 points buys Observation at Per
         level (15), which is more than good enough.
         */
        dai.addSkill(new Observation(dai), 15);
        assertEquals("Obervation level is 15", 15, dai.getSkill("Observation").getLevel());
        assertEquals("2 points buys Observation at Per level (15)", 227f, dai.getCharacterCost());

        /**
         * Since stealth can fail, we want to give Dai some combat skills for backup.
         We decide that he prefers knives. Knife skill (p. 208) is fine for melee combat,
         but we also want Dai to be good at the quick draw and with throwing knives.
         Fast-Draw (p. 194) and Thrown Weapon (p. 226) fit the bill. Both require a specialty – in this case, “Knife.”
         All of these skills are DX/Easy. With Dai’s low ST, he’ll need superb aim to make a knife effective,
         so we settle on 17 in Knife and Thrown Weapon (Knife).
         This is DX+2 level, which costs 4 points per skill.
         Fast-Draw (Knife) is a neat trick, but skill 15 is plenty. This costs 1 point.
         */
        dai.addSkill(new Knife(dai), 17);
        dai.addSkill(new ThrownWeapon(dai, "Knife"), 17);
        dai.addSkill(new FastDraw(dai, "Knife"), 15);
        assertEquals("Dai's cost with Knife(17), Thrown Weapon(17), Fast Draw(15)", 236f, dai.getCharacterCost());

        /**
         * To reflect Dai’s medieval background, we decide that he is a fair hand with the shortsword.
         But not too good– swords are expensive, and Dai grew up poor.
         We give him the Shortsword skill (p. 209) at 15. Shortsword is DX/Average, so this costs 2 points.
         As an ISWAT officer, Dai should know how to shoot. A slim target pistol sounds like his kind of gun.
         Reviewing the Guns skill (p. 198), we see that pistols call for the “Pistol” specialty.
         Guns are new to Dai, so we spend only 1 point.
         Since Guns (Pistol) is DX/Easy, this buys DX level: a very adequate 15.
         To conceal all these weapons, Dai needs Holdout skill (p. 200). This is IQ/Average.
         Dai doesn’t routinely carry concealed weapons, so we just give him IQ level – 12 – for 2 points.
         */
        dai.addSkill(new Shortsword(dai), 15);
        dai.addSkill(new Guns(dai, "Pistol"), 15);
        dai.addSkill(new Holdout(dai), 12);

        assertEquals("Dai's cost with Shortsword(15), Guns(15), Holdout(12)", 241f, dai.getCharacterCost());

        /**
         * We decide to give Dai some “background skills” next.
         He grew up on the street, so Urban Survival (p. 228) fits: it’s the ability to scrounge food and shelter in the city.
         A Per/Average skill, 1 point buys Per-1 level, or 14. Filch (p. 195) covers shoplifting.
         It’s DX/Average; 1 point buys DX-1, also 14. Survival has a social side, too.
         We give Dai Fast-Talk (p. 195) to talk his way out of jams and Streetwise (p. 223) to deal with professional criminals.
         Both are IQ/Average. We buy IQ level (12) in each, at 2 points a skill.
         */
        dai.addSkill(new UrbanSurvival(dai), 14);
        dai.addSkill(new Filch(dai), 14);
        dai.addSkill(new FastTalk(dai), 12);
        dai.addSkill(new Streetwise(dai), 12);
        assertEquals("Dai's cost with UrbanSurvival(14), Filch(14), FastTalk(12) & Streetwise(12)", 247f, dai.getCharacterCost());
        /**
         * Dai has now spent 44 of his remaining 47 points. We
         decide to put his last three points into skills that complement his advantages.
         */
        assertEquals("Dai has now spent 44 of his remaining 47 points", 3f, dai.getRemainingPoints());
        /**
         * Rereading the descriptions of his advantages, we see
         that Perfect Balance gives +1 to Acrobatics (p. 174).
         That’s definitely Dai’s style! Acrobatics is DX/Hard, so 2
         points buys DX-1 level, or 14. With the +1 for Perfect
         Balance, he gets a 15.
         */
        dai.addSkill(new Acrobatics(dai), 14);
        assertEquals("Acrobatics with the +1 for Perfect Balance, he gets a 15.", 15, dai.getSkill("Acrobatics").getLevel());
        /**
         * We also discover that Absolute Direction gives +3 to Body Sense (p. 181):
         * the skill of reorienting yourself after teleportation.
         * This sounds ideal for Dai! We put 1 point into Body Sense, which is DX/Hard.
         * This buys DX-2 level, or 13.
         * The +3 for Absolute Direction makes this 16.
         */
        dai.addSkill(new BodySense(dai), 13);
        assertEquals("The +3 for Absolute Direction makes this 16.", 16, dai.getSkill("Body Sense").getLevel());

        assertEquals("At this stage, Dai has spent all 250 points.", 250f, dai.getCharacterCost());

        // endregion

    }

    public void testInvestigatorCharacter() throws Exception {
        Individual c = new Individual(100);
        c.setBasicAttribute(Attribute.Dexterity, 12);
        c.setBasicAttribute(Attribute.Intelligence, 12);
        c.setBasicAttribute(Attribute.Health, 10);

    }

}
