package net.mortiy.gurps.game;

import net.mortiy.gurps.game.states.map.TacticalMapState;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 17.02.13
 * Time: 18:26
 * To change this template use File | Settings | File Templates.
 */
public class TheGame extends TWLStateBasedGame {

    public static final int TACTICAL_MAP_STATE = 0;

    protected TheGame(String name) {
        super(name);
        this.addState(new TacticalMapState(TACTICAL_MAP_STATE));
    }

    @Override
    protected URL getThemeURL() {
        try {
            return new File("res/layout/inventory/inventory.xml").toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        this.getState(TACTICAL_MAP_STATE).init(gameContainer, this);

    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new TheGame("GURPS Game"));
        app.setDisplayMode(1024, 768, false);
        app.start();
    }
}
