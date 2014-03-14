package net.mortiy.gurps.game.map;

import net.mortiy.gurps.rules.Individual;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 17.02.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class CharacterToken extends Token {
    Individual individual;
    public CharacterToken(Individual individual) {
        this.individual = individual;
    }
}
