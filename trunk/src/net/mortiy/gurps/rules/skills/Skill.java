package net.mortiy.gurps.rules.skills;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.Reflection;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.CharacterAttribute;
import net.mortiy.gurps.rules.exceptions.NotEnoughCharacterPointsException;
import net.mortiy.gurps.rules.skills.interfaces.ISkillDefault;
import net.mortiy.gurps.rules.table.rolls.SuccessRoll;

import java.io.IOException;
import java.util.*;

/**
 * A “skill” is a particular kind of knowledge; for instance,
 * judo, physics, auto mechanics, or a death spell. Every skill is
 * separate, though some skills help you to learn others.
 * Just as in real life, you start your career with some
 * skills and can learn more if you spend time training.
 */
public class Skill implements ISkillDefault, Modifier.IModifiable {
    public static int[] costTable = new int[]{
            1, 2, 4, 8, 12, 16, 20, 24, 28
    };

    public static enum Difficulty {
        /**
         * Easy skills are things that anyone could do reasonably well after a short
         * learning period – whether because they are second nature to most people or
         * because there isn’t a whole lot to learn.
         */
        Easy,
        /**
         * Average skills include most combat skills, mundane job skills, and the
         * practical social and survival skills that ordinary people use daily. This is the
         * most common difficulty level.
         */
        Average,
        /**
         * Hard skills require intensive formal study. This is typical of most “academic”
         * skills, complex athletic and combat skills that require years of training,
         * and all but the most powerful of magic spells.
         */
        Hard,
        /**
         * Very Hard skills have prodigious scope, or are alien, counterintuitive, or
         * deliberately shrouded in secrecy. The most fundamental of sciences, and
         * many potent magic spells and secret martial-arts techniques, are Very Hard.
         */
        VeryHard,

        Unknown
    }


    private String name;
    private String speciality;
    protected Attribute attribute;
    protected Difficulty difficulty;
    protected Character character;
    private int level = 0;
    private Set<Skill> prerequisites = new HashSet<Skill>();
    protected List<SkillDefault> skillDefaults = new ArrayList<SkillDefault>();
    private static Map<String, Class> skillsClassesMap = new HashMap<String, Class>();
    static {
        try {
            List<Class> skillsClasses = Reflection.getClasses("net.mortiy.gurps.rules.skills.all");
            for(Class skillClass : skillsClasses){
                skillsClassesMap.put(skillClass.getSimpleName(), skillClass);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Skill(Character character, String name, Attribute attribute, Difficulty difficulty) {
        this(character, name, "", attribute, difficulty);
    }

    public Skill(Character character, String name, String speciality, Attribute attribute, Difficulty difficulty) {
        this.character = character;
        this.name = name;
        this.speciality = speciality;
        this.attribute = attribute;
        this.difficulty = difficulty;

        character.addSkill(this);
    }

    public boolean hasSpeciality() {
        return speciality.length() > 0;
    }

    public String getKey() {
        return getKey(this);
    }

    public static String getKey(Skill skill) {
        String skillKey = "";
        if (skill.hasSpeciality()) {
            skillKey = String.format("%s (%s)", skill.getName(), skill.getSpeciality());
        } else {
            skillKey = String.format("%s", skill.getName());
        }
        return skillKey;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    /**
     * Tries to increase or decrease skill level
     * @param level
     * @throws NotEnoughCharacterPointsException
     */
    public void setLevel(int level) {

        int skillCost = getCost(level);
        float remainingPoints = character.getRemainingPoints() + getCost(this.level);
        if (remainingPoints < skillCost) {
            Log.w("Skill", String.format("Characters remaining %.1f points, but skill cost is %d", remainingPoints, skillCost));
        }
        else {
            this.level = level;
        }
    }


    /**
     * Performs skill Success Roll for given Character
     *
     * @return
     */
    public SuccessRoll successRoll() {
        return successRoll(0);
    }

    /**
     * Performs skill Success Roll for given Character with roll modifiers
     * @param modifiers
     * @return
     */
    public SuccessRoll successRoll(int modifiers) {

        int baseSkillLevel = getSkillLevel();

        int effectiveSkill = baseSkillLevel + modifiers;

        return new SuccessRoll(effectiveSkill).roll();
    }

    /**
     * Returns skill default with highest level
     *
     * @return SkillDefault with highest level for this character
     */
    public SkillDefault findBestSkillDefault() {
        int lastBestSkillDefaultLevel = -1;
        SkillDefault bestSkillDefault = null;

        //Log.i("Skill", String.format("Started searching best default skill for '%s'", getKey()));

        for (SkillDefault skillDefault : skillDefaults) {

            int level = getDefaultSkillLevel(skillDefault);
            //Log.i("Skill", String.format("> %s = %d", skillDefault.skillDefault, level));

            if (level > lastBestSkillDefaultLevel) {
                lastBestSkillDefaultLevel = level;
                bestSkillDefault = skillDefault;
            }

        }
        //Log.i("Skill", String.format("Best one is: '%s'", bestSkillDefault.skillDefault.toString()));
        return bestSkillDefault;
    }


    /**
     * Returns Skill cost for Character regarding current skill level
     * @return Skill cost in character points
     */
    public int getCurrentCost() {
        return getCost(level);
    }

    /**
     * Return cost of the skill's desired level for given character
     *
     * @param desiredLevel Desired skill level
     * @return Cost in points for learn this skill
     */
    public int getCost(int desiredLevel) {
        if(difficulty == Difficulty.Unknown || attribute == Attribute.Unknown){
            return 0;
        }
        CharacterAttribute skillAttribute = character.getAttribute(attribute); // 15
        int levelDifference = Math.round(desiredLevel - skillAttribute.getValue()); // 0
        int costIndex = levelDifference + difficulty.ordinal();

        if (costIndex < 0) {
            return 0;
        }
        if (costIndex < costTable.length) {
            return costTable[costIndex];
        } else {
            return (costIndex - 1) * 4;
        }
    }

    /**
     * Returns current Skill level (Trained or Default) for given character .
     * @return Skill level. 0 if user can't use this skill.
     */
    public int getSkillLevel() {
        // Determine skill level:
        String skillKey = getKey();

        int skillLevel = getLevel();

        // Try to use default if skill wasn't learnt
        if(skillLevel == 0){
            // Check it skill has default at all
            if (skillDefaults.size() > 0) {
                SkillDefault bestDefaultValue = findBestSkillDefault();
                skillLevel = bestDefaultValue.getLevel(character) + bestDefaultValue.modifier;
            }
        }

        return skillLevel;
    }

    /**
     * Returns skill level using given Attribute or Related Skill
     * @param skillDefault Basic Attribute or Skill used as default for this skill
     * @return Returns skill level
     */
    public int getDefaultSkillLevel(SkillDefault skillDefault) {
        return skillDefault.getLevel(character) + skillDefault.modifier;
    }


    public List<SkillDefault> getSkillDefaults() {
        return skillDefaults;
    }


    /**
     * Get current learned skill level
     * @return Trained skill level
     */
    public int getRawLevel(){
        return level;
    }

    /**
     * Get current learned skill level with modifiers
     * @return Trained skill level with applied modifiers
     */
    public int getLevel() {
        return (int) Math.floor(level + character.getTotalModifier(this));
    }

    public String getName() {
        return name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setDefault(String skillName, int modifier) throws UnknownSkillDefaultSkillException {
        Skill skill = character.getSkill(skillName);
        skillDefaults.add(
                new SkillDefault(skill, modifier)
        );
    }

    public static Class forName(String skillName) throws ClassNotFoundException {
        int bracketIndex = skillName.indexOf("(");
        if(bracketIndex >= 0){
            skillName  = skillName.substring(0, bracketIndex);
        }
        String skillClassName = skillName.replaceAll("[\\-\\s/]+", "").trim();
        return skillsClassesMap.get(skillClassName);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "name='" + name + '\'' +
                '}';
    }

    protected class UnknownSkillDefaultSkillException extends Throwable {
        public UnknownSkillDefaultSkillException(Exception e) {
            //To change body of created methods use File | Settings | File Templates.
        }

        public UnknownSkillDefaultSkillException(String detailMessage, Throwable throwable) {
            super(detailMessage, throwable);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }


}
