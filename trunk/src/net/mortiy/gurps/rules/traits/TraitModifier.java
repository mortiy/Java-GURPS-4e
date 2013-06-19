package net.mortiy.gurps.rules.traits;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 11.12.12
 * Time: 22:12
 * To change this template use File | Settings | File Templates.
 */
public class TraitModifier {
    String name;
    int percent;

    public TraitModifier(String name, int percent) {
        this.name = name;
        this.percent = percent;
    }
}
