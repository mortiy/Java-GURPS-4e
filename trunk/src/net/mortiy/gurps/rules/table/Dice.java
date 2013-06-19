package net.mortiy.gurps.rules.table;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 06.12.12
 * Time: 22:57
 * To change this template use File | Settings | File Templates.
 */
public class Dice {
    int sides;

    public Dice(int sides) {
        this.sides = sides;
    }

    public int roll(){
        return (int) Math.ceil(Math.random() * this.sides);
    }
}
