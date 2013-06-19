package net.mortiy.gurps.canvas;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.02.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tests.states.TestState1;
import org.newdawn.slick.tests.states.TestState2;
import org.newdawn.slick.tests.states.TestState3;

/**
 * A test for the multi-state based functionality
 *
 * @author kevin
 */
public class StateBasedTest extends StateBasedGame {

    /**
     * Create a new test
     */
    public StateBasedTest() {
        super("State Based Test");
    }

    /**
     * @see org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick.GameContainer)
     */
    public void initStatesList(GameContainer container) {
        addState(new TestState1());
        addState(new TestState2());
        addState(new TestState3());
    }

    /**
     * Entry point to our test
     *
     * @param argv The arguments to pass into the test
     */
    public static void main(String[] argv) {
        try {
            AppGameContainer container = new AppGameContainer(new StateBasedTest());
            container.setDisplayMode(800,600,false);
            container.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
