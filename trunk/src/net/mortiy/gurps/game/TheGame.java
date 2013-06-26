package net.mortiy.gurps.game;

import net.mortiy.gurps.game.states.map.TacticalMapState;
import net.mortiy.gurps.game.twl.TWLStateBasedGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class TheGame extends TWLStateBasedGame {

    public static final int TACTICAL_MAP_STATE = 0;

    protected TheGame(String name) {
        super(name);
        this.addState(new TacticalMapState(TACTICAL_MAP_STATE));
    }

    @Override
    protected URL getThemeURL() {
        try {
            return new File("trunk/src/res/layout/inventory/inventory.xml").toURI().toURL();
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
