package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.Reflection;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.combat.Preparable;
import net.mortiy.gurps.rules.modifiers.Modifier;
import net.mortiy.gurps.rules.modifiers.ValueModifier;

import java.io.IOException;
import java.util.*;

/**
 * Represents character's Advantages and Disadvantes.
 * Trait is assigned to character immediately during instantiation.
 */
public class Trait implements Preparable {

    private String name;
    private String description;
    protected Character character;
    private CostType costType;
    protected int cost;
    private Map<Modifier.IModifiable, Modifier> modifiers = new HashMap<Modifier.IModifiable, Modifier>();
    private List<TraitLimitation> traitLimitations = new ArrayList<TraitLimitation>();
    private List<TraitModifier> traitModifiers = new ArrayList<TraitModifier>();
    private static Map<String, Class> traitsClassesMap = new HashMap<String, Class>();

    static {
        try {
            List<Class> traitsClasses = Reflection.getClasses("net.mortiy.gurps.rules.traits.all");
            for(Class traitClass : traitsClasses){
                traitsClassesMap.put(traitClass.getSimpleName(), traitClass);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Trait(Character character, String name, CostType costType, int cost) {
        this.character = character;
        this.name = name;
        this.costType = costType;
        this.cost = cost;
    }

    public String getKey() {
        return name;
    }

    protected int getRawCost() {
        return cost;
    }

    /**
     * Get trait cost taking into consideration all limitations and modifiers
     * @return Final trait cost
     */
    public int getCost(){
        return Math.round(getRawCost() * (1.0f + getCostPercent() / 100f));
    }

    public String getName() {
        return name;
    }

    public Map<Modifier.IModifiable, Modifier> getModifiers() {
        return modifiers;
    }

    protected Modifier getModifier(Modifier.IModifiable modifiedEntity) {
        return modifiers.get(modifiedEntity);
    }

    protected Modifier addModifier(Modifier.IModifiable modifiedEntity, ValueModifier valueModifier) {
        Modifier modifier = new Modifier(character, modifiedEntity, valueModifier);
        addModifier(modifier);
        return modifier;
    }

    protected void addModifier(Modifier modifier) {
        modifiers.put(modifier.getModifiedEntity(), modifier);
    }


    // region Trait Limitations
    public void addLimitation(TraitLimitation limitation){
        traitLimitations.add(limitation);
    }

    public void removeLimitation(TraitLimitation limitation){
        traitLimitations.remove(limitation);
    }
    public boolean hasLimitation(TraitLimitation limitation){
        return traitLimitations.contains(limitation);
    }
    // endregion

    // region Trait Modifiers
    public void addModifier(TraitModifier modifier){
        traitModifiers.add(modifier);
    }

    public void removeModifier(TraitModifier modifier){
        traitModifiers.remove(modifier);
    }
    public boolean hasModifier(TraitModifier modifier){
        return traitModifiers.contains(modifier);
    }

    public int getCostPercent(){
        return getTotalLimitation() + getTotalModifiers();
    }

    private int getTotalLimitation(){
        int totalLimitPercent = 0;
        for(TraitLimitation t : traitLimitations){
            totalLimitPercent += t.percent;
        }
        return totalLimitPercent;
    }

    private int getTotalModifiers(){
        int totalModifiersPercent = 0;
        for(TraitModifier t : traitModifiers){
            totalModifiersPercent += t.percent;
        }
        return totalModifiersPercent;
    }
    // endregion

    public static Class forName(String traitName) throws ClassNotFoundException {
        int bracketIndex = traitName.indexOf("(");
        if(bracketIndex >= 0){
            traitName  = traitName.substring(0, bracketIndex);
        }
        String traitClassName = traitName.replaceAll("[\\-\\s]+", "").trim();
        return traitsClassesMap.get(traitClassName);
    }

}
