package net.mortiy.gurps.rules.traits;

import net.mortiy.gurps.Reflection;
import net.mortiy.gurps.rules.Individual;
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

    private final String name;
    private String description;
    protected final Individual individual;
    private CostType costType;
    protected int cost;
    private final Map<Modifier.IModifiable, Modifier> modifiers = new HashMap<>();
    private final List<TraitLimitation> traitLimitations = new ArrayList<>();
    private final List<TraitModifier> traitModifiers = new ArrayList<>();
    private static Map<String, Class> traitsClassesMap = new HashMap<>();

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

    public Trait(Individual individual, String name, CostType costType, int cost) {
        this.individual = individual;
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

    private int getTotalLimitation(){
        int totalLimitPercent = 0;
        for(TraitLimitation t : traitLimitations){
            totalLimitPercent += t.percent;
        }
        return totalLimitPercent;
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

    public Map<Modifier.IModifiable, Modifier> getModifiers() {
        return modifiers;
    }

    protected Modifier registerModifier(Modifier.IModifiable modifiedEntity, ValueModifier valueModifier) {
        Modifier modifier = individual.addModifier(modifiedEntity, valueModifier);
        registerModifier(modifier);
        return modifier;
    }

    protected void registerModifier(Modifier modifier) {
        modifiers.put(modifier.getModifiedEntity(), modifier);
    }

    private int getTotalModifiers(){
        int totalModifiersPercent = 0;
        for(TraitModifier t : traitModifiers){
            totalModifiersPercent += t.percent;
        }
        return totalModifiersPercent;
    }
    // endregion

    public int getCostPercent(){
        return getTotalLimitation() + getTotalModifiers();
    }

    public static Class forName(String traitName) throws ClassNotFoundException {
        int bracketIndex = traitName.indexOf("(");
        if(bracketIndex >= 0){
            traitName  = traitName.substring(0, bracketIndex);
        }
        String traitClassName = traitName.replaceAll("[\\-\\s]+", "").trim();
        return traitsClassesMap.get(traitClassName);
    }

}
