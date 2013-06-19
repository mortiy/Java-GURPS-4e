package net.mortiy.gurps.game.map;

import net.mortiy.gurps.rules.Character;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 17.02.13
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class CharacterToken extends Token {
    Character character;
    public CharacterToken(Character character) {
        this.character = character;
    }
}
