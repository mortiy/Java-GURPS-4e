package net.mortiy.gurps.rules;

import net.mortiy.gurps.Log;
import net.mortiy.gurps.rules.attributes.Attribute;
import net.mortiy.gurps.rules.attributes.BasicAttribute;
import net.mortiy.gurps.rules.attributes.CharacterAttribute;
import net.mortiy.gurps.rules.attributes.basic.Dexterity;
import net.mortiy.gurps.rules.attributes.basic.Health;
import net.mortiy.gurps.rules.attributes.basic.Intelligence;
import net.mortiy.gurps.rules.attributes.basic.Strength;
import net.mortiy.gurps.rules.attributes.secondary.*;
import net.mortiy.gurps.rules.character.Body;
import net.mortiy.gurps.rules.character.Build;
import net.mortiy.gurps.rules.character.Encumbrance;
import net.mortiy.gurps.rules.character.Size;
import net.mortiy.gurps.rules.combat.Damage;
import net.mortiy.gurps.rules.equipment.ArmorItem;
import net.mortiy.gurps.rules.equipment.Equipment;
import net.mortiy.gurps.rules.equipment.Item;
import net.mortiy.gurps.rules.exceptions.NotEnoughCharacterPointsException;
import net.mortiy.gurps.rules.map.GameMap;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.MultiplierModifier;
import net.mortiy.gurps.rules.modifiers.ValueModifier;
import net.mortiy.gurps.rules.skills.Skill;
import net.mortiy.gurps.rules.table.rolls.SuccessRoll;
import net.mortiy.gurps.rules.traits.*;
import org.newdawn.slick.Image;

import java.lang.reflect.Constructor;
import java.util.*;

/**
 * Character entity
 * TODO: Age: Childhood & Elderly (p. 20)
 * TODO: Lifting, Running, Swimming (p. 353)
 */
public class Character implements Modifier.IInfluential, GameMap.MapToken {
    @Override
    public Image getImage() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    // region Enumerators

    public static enum Posture {
        Standing,
        Crouching,
        Kneeling,
        Crawling,
        Sitting,
        LyingDown
    }

    public static enum PhysicalState {
        Normal,
        Unconscious,
        Knockdown,
        Stunned,
        Dying,
        Dead,
        Destructed
    }

    private static Posture postures[] = Posture.values();

    public static Posture getPosture(int postureIndex) {
        return postures[postureIndex];
    }

    public static enum LanguageLevel {
        None,
        Broken,
        Accented,
        Native
    }

    // endregion

    /**
     * Character points are the “currency” of character creation.
     */
    private int startingPoints = 100;

    /**
     * Character's name
     */
    private String name;

    /**
     * Character's age
     */
    private int age = 25;
    /**
     * Character's body:
     */
    private Body body = new Body();
    private Build build = Build.Average;
    private Size size = Size.Normal;
    private Set<PhysicalState> physicalStates = new HashSet<>();
    private TechLevel techLevel;

    private World.Language nativeLanguage;
    private Posture posture = Posture.Standing;
    private Map<World.Language, LanguageLevel> languages = new HashMap<World.Language, LanguageLevel>();


    /**
     * Attributes
     */
    private Map<Attribute, CharacterAttribute> attributes = new HashMap<Attribute, CharacterAttribute>();

    /**
     * Advantages, disadvantages & skills
     */
    private Map<String, Trait> traits = new HashMap<String, Trait>();
    private Map<String, Skill> learntSkills = new HashMap<String, Skill>();

    /**
     * Attributes modifiers
     */
    private Map<Modifier.IModifiable, ModifiersList> modifiers = new HashMap<Modifier.IModifiable, ModifiersList>();

    /**
     * Equipment
     */
    Equipment equipment = new Equipment(this);


    // region Constructors

    /**
     * Create character with given amount of Character Points and default attributes
     *
     * @param startingPoints Starting points
     */
    public Character(int startingPoints) {
        this.startingPoints = startingPoints;

        attributes.put(Attribute.Strength, new Strength());
        attributes.put(Attribute.Dexterity, new Dexterity());
        attributes.put(Attribute.Intelligence, new Intelligence());
        attributes.put(Attribute.Health, new Health());

        // Initialization order is important:
        attributes.put(Attribute.BasicLift, new BasicLift(this));
        attributes.put(Attribute.BasicSpeed, new BasicSpeed(this));
        attributes.put(Attribute.BasicMove, new BasicMove(this));
        attributes.put(Attribute.Dodge, new Dodge(this));

        attributes.put(Attribute.HitPoints, new HitPoints(this));
        attributes.put(Attribute.FatiguePoints, new FatiguePoints(this));
        attributes.put(Attribute.Will, new Will(this));
        attributes.put(Attribute.Perception, new Perception(this));

    }

    // endregion

    // region Getters
    public int getMaximumPoints() {
        return startingPoints;
    }

    /**
     * Get character cost in points calculating all attributes, skills and traits.
     *
     * @return Character point cost.
     */
    public float getCharacterCost() {
        int points = 0;

        // Attributes cost:
        for (CharacterAttribute attribute : attributes.values()) {
            points += attribute.getCost();
        }

        // Traits cost:
        for (Trait trait : traits.values()) {
            points += trait.getCost();
        }

        // Skills cost:
        for (Skill skill : learntSkills.values()) {
            points += skill.getCost(skill.getRawLevel());
        }

        // Language cost:
        int[] languageCosts = new int[]{0, 2, 4, 6};
        for (LanguageLevel languageLevel : languages.values()) {
            points += languageCosts[languageLevel.ordinal()];
        }

        return points;
    }

    /**
     * Get cost of traits by specified class
     *
     * @param traitClass Trait class (e.g. Advantage, Disadvantage)
     * @return Cost in points of selected traits
     */
    public float getTraitsCost(Class traitClass) {
        // Traits cost:
        float points = 0;
        for (Trait trait : traits.values()) {
            if (traitClass.isInstance(trait)) {
                points += trait.getCost();
            }
        }
        return points;
    }

    public float getRemainingPoints() {
        return getMaximumPoints() - getCharacterCost();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Encumbrance getEncumbrance() {
        float basicLift = getBasicLift().getValue();
        float totalWeight = equipment.getTotalWeight();
        if (totalWeight < basicLift) {
            return Encumbrance.No;
        } else if (totalWeight < basicLift * 2) {
            return Encumbrance.Light;
        } else if (totalWeight < basicLift * 3) {
            return Encumbrance.Medium;
        } else if (totalWeight < basicLift * 6) {
            return Encumbrance.Heavy;
        } else if (totalWeight < basicLift * 10) {
            return Encumbrance.ExtraHeavy;
        } else {
            return Encumbrance.Impossible;
        }
    }

    public Build getBuild() {
        return build;
    }

    public World.Language getNativeLanguage() {
        return nativeLanguage;
    }

    public Map<World.Language, LanguageLevel> getLanguages() {
        return languages;
    }

    public Body getBody() {
        return body;
    }

    // endregion

    // region Setters

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setTechLevel(TechLevel techLevel) {
        this.techLevel = techLevel;
    }

    public void setNativeLanguage(World.Language nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }


    public void setBuild(Build build) {
        this.build = build;
    }
// endregion

    // region Modifiers

    public boolean hasModifier(Modifier.IModifiable modifiedEntity) {
        return modifiers.containsKey(modifiedEntity) && modifiers.get(modifiedEntity).size() > 0;
    }

    public float getTotalModifier(Modifier.IModifiable modifiedEntity) {
        if (!modifiers.containsKey(modifiedEntity)) {
            return 0f;
        }
        return modifiers.get(modifiedEntity).getTotal();
    }

    public ModifiersList getModifiersList(Modifier.IModifiable modifiedEntity) {
        if (!modifiers.containsKey(modifiedEntity)) {
            new ModifiersList();
        }
        return modifiers.get(modifiedEntity);
    }

    public Modifier addModifier(Modifier.IModifiable modifiedEntity, ValueModifier valueModifier) {
        return new Modifier(this, modifiedEntity, valueModifier);
    }

    public void removeModifier(Modifier modifier) {
        if (modifiers.get(modifier.modifiedEntity).contains(modifier)) {
            modifiers.get(modifier.modifiedEntity).remove(modifier);
        }
    }

    public TimeModifier addTimeModifier(Modifier.IModifiable modifiedEntity, float modifierValue, int timeInMilliseconds) {
        return new TimeModifier(this, modifiedEntity, modifierValue, timeInMilliseconds);

    }

    public void registerModifier(Modifier modifier) {
        if (!modifiers.containsKey(modifier.modifiedEntity)) {
            modifiers.put(modifier.modifiedEntity, new ModifiersList());
        }
        ModifiersList modifiersList = modifiers.get(modifier.modifiedEntity);
        if (!modifiersList.contains(modifier)) {
            modifiers.get(modifier.modifiedEntity).add(modifier);
        }
    }

    // endregion

    //region Traits
    public boolean hasTrait(String traitKey) {
        return traits.containsKey(traitKey);
    }

    /**
     * Remove traits and all its modifiers from character.
     *
     * @param trait Trait
     */
    public void removeTrait(Trait trait) {
        // Remove all modifiers given by this trait:
        Map<Modifier.IModifiable, Modifier> traitModifiers = trait.getModifiers();
        for (Modifier.IModifiable modifiedEntity : traitModifiers.keySet()) {
            modifiers.get(modifiedEntity).remove(traitModifiers.get(modifiedEntity));
        }
        // Remove trait itself:
        traits.remove(trait.getKey());
    }

    public Trait getTrait(String traitKey) {
        if (traits.containsKey(traitKey)) {
            return traits.get(traitKey);
        } else {
            return new Trait(this, "Unknown Trait", CostType.Fixed, 0);
        }
    }


    /**
     * Add Levelable trait to Character
     *
     * @param trait
     * @return Newly added trait for chaining
     */
    public LevelTrait addTrait(LevelTrait trait) {
        return (LevelTrait) addTrait((Trait) trait);
    }

    /**
     * Add Fixed trait to Character
     *
     * @param trait
     * @return Newly added trait for chaining
     */
    public FixedTrait addTrait(FixedTrait trait) {
        return (FixedTrait) addTrait((Trait) trait);
    }

    /**
     * Add Variable trait to Character
     *
     * @param trait
     * @return Newly added trait for chaining
     */
    public VariableTrait addTrait(VariableTrait trait) {
        return (VariableTrait) addTrait((Trait) trait);
    }

    /**
     * Gives trait to character.
     * Used by base Traits class to assign themselves to character.
     * Doesn't allow to add same traits.
     *
     * @param trait Trait
     * @return Returns newly added trait for chaining,
     *         or returns already existing trait if was assigned before
     */
    public Trait addTrait(Trait trait) {
        if (traits.containsKey(trait.getKey())) {
            Log.w("Character", String.format("Character already has trait '%s'", trait.getKey()));
            return traits.get(trait.getKey());
        } else {
            traits.put(trait.getKey(), trait);
            return trait;
        }
    }

    protected Trait addTrait(String traitKey) throws TraitNotFoundException, CharacterAlreadyHasTraitException {
        if (traits.containsKey(traitKey)) {
            return traits.get(traitKey);
        } else {
            try {
                Class traitClass = Trait.forName(traitKey);
                return addTrait(traitClass);
            } catch (Exception e) {
                Log.w("Character", String.format("Requested trait '%s' class was not found!", traitKey));
                return new Trait(this, "Unknown", CostType.Fixed, 0);
            }
        }
    }

    private Trait addTrait(Class traitClass) throws TraitNotFoundException, CharacterAlreadyHasTraitException {
        try {
            Constructor constructor = traitClass.getConstructor(Character.class);
            return (Trait) constructor.newInstance(this);
        } catch (Exception e) {
            throw new TraitNotFoundException(e);
        }
    }

    // endregion

    // region Skills

    public boolean hasLearntSkill(String skillName) {
        return learntSkills.containsKey(skillName) && learntSkills.get(skillName).getRawLevel() > 0;
    }

    public void forgetSkill(Skill skill) {
        // TODO: Maybe I need to remove link from skill to character
        learntSkills.remove(Skill.getKey(skill));
    }


    public void addSkill(Skill skill) {
        if (!learntSkills.containsKey(skill.getKey())) {
            learntSkills.put(skill.getKey(), skill);
        }
    }

    public void addSkill(Skill skill, int level) {
        if (!learntSkills.containsKey(skill.getKey())) {
            learntSkills.put(skill.getKey(), skill);
            skill.setLevel(level);
        } else {
            learntSkills.get(skill.getKey()).setLevel(level);
        }
    }


    public Skill getSkill(Class skillClass) {
        if (!Skill.class.isAssignableFrom(skillClass)) {
            return new Skill(this, "Unknown Skill", Attribute.Unknown, Skill.Difficulty.Unknown);
        }
        for (Skill skill : learntSkills.values()) {
            if (skillClass.isInstance(skill)) {
                return skill;
            }
        }
        Skill skill = null;
        try {
            skill = (Skill) skillClass.getConstructor(Character.class).newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
            return new Skill(this, "Unknown Skill", Attribute.Unknown, Skill.Difficulty.Unknown);
            //throw new SkillClassNotFoundException(String.format("Cannot find skill class by key: %s", skillKey));
        }

        learntSkills.put(skill.getKey(), skill);
        return skill;

    }

    public Skill getSkill(String skillKey) {
        if (learntSkills.containsKey(skillKey)) {
            return learntSkills.get(skillKey);
        } else try {
            Skill skill = (Skill) Skill.forName(skillKey).getConstructor(Character.class).newInstance(this);
            learntSkills.put(skill.getKey(), skill);
            return skill;
        } catch (Exception e) {
            e.printStackTrace();
            return new Skill(this, "Unknown Skill", Attribute.Unknown, Skill.Difficulty.Unknown);
            //throw new SkillClassNotFoundException(String.format("Cannot find skill class by key: %s", skillKey));
        }
    }

    public SuccessRoll useSkill(String skillKey) throws SkillNotFoundException {
        return getSkill(skillKey).successRoll();
    }


    // endregion

    // region Attributes

    public BasicAttribute getBasicAttribute(Attribute attribute) {
        return (BasicAttribute) getAttribute(attribute);
    }

    public CharacterAttribute getAttribute(Attribute attribute) {
        CharacterAttribute attr = attributes.get(attribute);
        if (attr == null) {
            return new CharacterAttribute(Attribute.Unknown);
            //throw new CharacterDoesNotHaveAttributeException("Character doesn't have attribute: " + attribute);
        }
        return attributes.get(attribute);
    }

    /**
     * Get attribute value with all modifiers applied
     *
     * @param attribute Attribute
     * @return Value with applied modifiers
     */
    public float getModifiedAttribute(Attribute attribute) {
        CharacterAttribute attr = getAttribute(attribute);
        float value = attr.getValue();
        if (modifiers.containsKey(attribute)) {
            value += getTotalModifier(attribute);
        }
        return value;
    }

    /**
     * Set given attribute to specified value
     *
     * @param attribute Attribute
     * @param value     Attribute's value
     * @throws NotEnoughCharacterPointsException
     *
     */
    public void setBasicAttribute(Attribute attribute, int value) throws NotEnoughCharacterPointsException {
        int currentValue = getBasicAttribute(attribute).getLevel();
        int difference = value - currentValue;
        if (value > 0) {
            increaseAttribute(attribute, difference);
        } else if (value < 0) {
            decreaseAttribute(attribute, difference);
        }
    }

    public void increaseAttribute(Attribute attribute) throws NotEnoughCharacterPointsException {
        increaseAttribute(attribute, 1);
    }

    public void increaseAttribute(Attribute attribute, float modifier) throws NotEnoughCharacterPointsException {
        CharacterAttribute characterAttribute = attributes.get(attribute);
        float attributeCost = characterAttribute.getLevelCost() * (modifier / characterAttribute.getLevelIncrement());
        float remainingPoints = getRemainingPoints();
        if (remainingPoints < attributeCost) {
            Log.w("Character", String.format("Characters remaining %.1f points, but attribute cost is %.1f", remainingPoints, attributeCost));
            // throw new NotEnoughCharacterPointsException(String.format("Characters remaining %.1f points, but attribute cost is %.1f", remainingPoints, attributeCost));
        }
        attributes.get(attribute).increase(modifier);

    }

    public void decreaseAttribute(Attribute attribute) {
        decreaseAttribute(attribute, 1);
    }

    public void decreaseAttribute(Attribute attribute, int modifier) {
        attributes.get(attribute).decrease(modifier);
    }
    //endregion

    // region Secondary characteristics

    public BasicLift getBasicLift() {
        return (BasicLift) getAttribute(Attribute.BasicLift);
    }

    public HitPoints getHitpoints() {
        return (HitPoints) getAttribute(Attribute.HitPoints);
    }

    public Will getWill() {
        return (Will) getAttribute(Attribute.Will);
    }

    public Perception getPerception() {
        return (Perception) getAttribute(Attribute.Perception);
    }

    public BasicSpeed getBasicSpeed() {
        return (BasicSpeed) getAttribute(Attribute.BasicSpeed);
    }

    public FatiguePoints getFatiguePoints() {
        return (FatiguePoints) getAttribute(Attribute.FatiguePoints);
    }

    public BasicMove getBasicMove() {
        return (BasicMove) getAttribute(Attribute.BasicMove);
    }


    public Dodge getDodge() {
        return (Dodge) getAttribute(Attribute.Dodge);
    }

    public Size getSize() {
        return size;
    }

    // endregion

    // region Equipment
    // TODO: Equip items to character, so he could use them (in the fight for example)
    public boolean equip(Item item, Body.Part bodyPart) {
        return getEquipment().equipItem(item, bodyPart);
    }

    // endregion

    // region Behaviour

    /**
     * Wounds and ailments cause “injury”: a
     * (usually) temporary loss of Hit Points.
     * Thus, your HP score measures your ability
     * to sustain injury; see Hit Points (p. 6).
     * TODO: Injury, Illness, and Fatigue
     *
     * @param injury
     */
    public void injure(int injury) {
        // TODO: Calculate wound effect;
        HitPoints HP = getHitpoints();
        HP.modifyValue(-injury);

        float currentHP = HP.getCurrentValue();
        float maxHP = HP.getValue();
        SuccessRoll rollHP = new SuccessRoll((int) getHitpoints().getValue());

        // Shock:
        // TODO: Shock affects DX- and IQ-based skills, but not active defenses or other defensive reactions.
        int shockModifier = injury > 4 ? 4 : injury;
        addTimeModifier(getAttribute(Attribute.Intelligence), -shockModifier, Time.TURN);
        addTimeModifier(getAttribute(Attribute.Dexterity), -shockModifier, Time.TURN);

        // Remember quantity of physical states:
        int physicalStatesCount = physicalStates.size();

        // Check for Major wound:
        // TODO: Knockdown (p. 30)
        if(injury > maxHP / 2){
            SuccessRoll woundRoll = rollHP.roll();
            switch (woundRoll.getRollResult()){
                case Failure:
                    if(woundRoll.getMargin() >= 5){
                        physicalStates.add(PhysicalState.Unconscious);
                    }
                    break;
                case CriticalFailure:
                    physicalStates.add(PhysicalState.Unconscious);
                    physicalStates.add(PhysicalState.Stunned);
                    physicalStates.add(PhysicalState.Knockdown);
                    break;
            }
        }



        if (currentHP <= Math.floor(maxHP / 3f)) {
            addModifier(getDodge(), new MultiplierModifier(0.5f, MultiplierModifier.Mode.RoundUp));
            addModifier(getBasicMove(), new MultiplierModifier(0.5f, MultiplierModifier.Mode.RoundUp));
        }
        if (currentHP <= 0 && !physicalStates.contains(PhysicalState.Unconscious)) {

            int negativeMultiple = (int) Math.floor(currentHP / maxHP);
            SuccessRoll consciousRoll = rollHP.roll(negativeMultiple);
            switch (consciousRoll.getRollResult()) {
                case Failure:
                case CriticalFailure:
                    physicalStates.add(PhysicalState.Unconscious);
                    break;
            }

            // Simply stop
        }
        /**
         * In addition to the above effects, make an immediate HT roll or die.
         * (If you fail by only 1 or 2, you’re dying, but not dead – see Mortal Wounds, below).
         * If you succeed, you can still talk, fight, etc., as above (until you fail a HT roll and collapse).
         * Roll again each time you suffer injury equal to a further multiple of your HP, whether as a result of
         * one wound or many. For instance, if you have 11 HP, you must roll to avoid death at -11 HP.
         * If you survive, you must roll again at -22 HP, -33 HP, and so on...
         */
        if (currentHP <= (-1 * maxHP) && !physicalStates.contains(PhysicalState.Dead)) {
            SuccessRoll deathRoll = rollHP.roll();
            switch (deathRoll.getRollResult()) {
                case Failure:
                    physicalStates.add(PhysicalState.Dying);
                    break;
                case CriticalFailure:
                    physicalStates.add(PhysicalState.Dead);
                    break;
            }

        }
        /**
         * You die immediately. You have lost a total of 6 times your HP!
         * Nobody can survive that much injury.
         */
        if (currentHP <= (-5 * maxHP)) {
            physicalStates.add(PhysicalState.Dead);
        }
        /**
         * Total bodily destruction, if this makes sense given the source of the
         damage — 200 points of arrow wounds leave a messy but recognizable corpse;
         200 points of fire injury leaves nothing but an unrecognizable lump of charcoal.
         The difference can be important in settings where resurrection, reanimation,
         etc. are possible!
         */
        if (currentHP <= (-10 * maxHP) && !physicalStates.contains(PhysicalState.Destructed)) {
            physicalStates.add(PhysicalState.Destructed);
        }

        // Notify if physical states changes:
        if(physicalStates.size() > physicalStatesCount){
            Log.i("Character", String.format("%s is now %s", getName(), Arrays.toString(physicalStates.toArray())));
        }

    }

    public boolean changePosture(Posture posture) {
        this.posture = posture;
        return true;
    }

    public Posture getPosture() {
        return posture;
    }
    // endregion

    // region Exceptions

    public class SkillNotFoundException extends Exception {
        public SkillNotFoundException(String detailMessage) {
            super(detailMessage);    //To change body of overridden methods use File | Settings | File Templates.
        }

        public SkillNotFoundException(Throwable throwable) {
            super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

    private class CharacterDoesNotHaveAttributeException extends Throwable {
        public CharacterDoesNotHaveAttributeException(String detailMessage) {
            super(detailMessage);    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

    public class TraitNotFoundException extends Throwable {
        public TraitNotFoundException(Throwable throwable) {
            super(throwable);    //To change body of overridden methods use File | Settings | File Templates.
        }

        public TraitNotFoundException(String detailMessage) {
            super(detailMessage);    //To change body of overridden methods use File | Settings | File Templates.
        }

        public TraitNotFoundException() {
            super();    //To change body of overridden methods use File | Settings | File Templates.
        }
    }

    public class CharacterAlreadyHasSkillException extends Throwable {
    }

    public class CharacterAlreadyHasTraitException extends Throwable {
    }

    public class SkillClassNotFoundException extends Throwable {
        public SkillClassNotFoundException(String format) {
        }
    }

    @Override
    public String toString() {
        return String.format("%s [HP=%.0f]", getName(), getHitpoints().getCurrentValue());
    }


    // endregion
}
