package net.mortiy.gurps.game.states.map;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 12.02.13
 * Time: 22:19
 * To change this template use File | Settings | File Templates.
 */

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.ResizableFrame;
import net.mortiy.gurps.game.map.hex.Hex;
import net.mortiy.gurps.game.map.hex.HexMap;
import net.mortiy.gurps.game.map.hex.HexPathfinder;
import net.mortiy.gurps.game.map.hex.HexPoint;
import net.mortiy.gurps.game.twl.BasicTWLGameState;
import net.mortiy.gurps.game.twl.RootPane;
import net.mortiy.gurps.game.map.*;
import net.mortiy.gurps.game.states.inventory.InventoryPanel;
import net.mortiy.gurps.rules.Character;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.pathfinding.Path;

import java.util.List;
import java.util.Map;

public class TacticalMapState extends BasicTWLGameState {

    private int stateID = -1;
    boolean obstacleMode = false;
    Path path;
    MapUI mapUI;
    MapLogic mapLogic;
    HexPathfinder pathfinder;

    ResizableFrame frame;
    Image grassImage;
    HexMap hexMap;


    public TacticalMapState(int stateID) {
        this.stateID = stateID;
    }

    @Override
    protected RootPane createRootPane() {

        RootPane rp = super.createRootPane();
        rp.setTheme("inventorydemo");

        Button btn = new Button("Hello World");
        btn.addCallback(new Runnable() {
            public void run() {
                System.out.println("It works!");
            }
        });

        frame = new ResizableFrame();
        frame.setTitle("Inventory");
        frame.setResizableAxis(ResizableFrame.ResizableAxis.NONE);
        frame.add(new InventoryPanel(5, 2));

        rp.add(frame);

        return rp;
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        super.enter(container, game);    //To change body of overridden methods use File | Settings | File Templates.
        frame.adjustSize();
    }

    public void init(GameContainer gc, StateBasedGame sbg) {

        // Resources loading;
        try {
            grassImage = new Image("res/grass.png");
        } catch (SlickException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // Map, Pathfinder, UI
        hexMap = new HexMap(18, 9);
        mapLogic = new MapLogic(hexMap);
        mapUI = new MapUI(mapLogic, hexMap);

        Character charA = new Character(100);
        Character charB = new Character(100);
        Token charTokenA = new CharacterToken(charA);
        Token charTokenB = new CharacterToken(charB);
        hexMap.putToken(hexMap.get(new HexPoint(4, 2)), charTokenA);
        hexMap.putToken(hexMap.get(new HexPoint(8, 1)), charTokenB);
        mapUI.setActiveToken(charTokenB);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame stateBasedGame, int delta) throws SlickException {
        Input input = gc.getInput();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame stateBasedGame, Graphics g) throws SlickException {

        g.setAntiAlias(true);
        g.setLineWidth(2);
        g.setColor(Color.white);

        for (int x = 0; x < gc.getWidth(); x += grassImage.getWidth()) {
            for (int y = 0; y < gc.getHeight(); y += grassImage.getHeight()) {
                grassImage.draw(x, y);
            }
        }

        // Render map tokens:
        Map<Hex, List<Token>> tokensMap = hexMap.getTokensMap();
        for (Hex hex : tokensMap.keySet()) {
            List<Token> tokens = tokensMap.get(hex);
            Polygon polygon = hex.getPolygon();
            for(Token t : tokens){

                t.getImage().draw(polygon.getX(), polygon.getY());
                //g.fill(, new GradientFill(0.1f, 0.1f, Color.yellow, 1.0f, 1.0f, Color.red));
            }

        }

        // Render movement path:
//        if(path != null){
//            for(int i = 0; i < path.getLength(); i++){
//                Path.Step step = path.getStep(i);
//                g.fill(hexMap.get(new HexPoint(step.getX(), step.getY())).getPolygon());
//            }
//        }

        //

        Map<Hex, Facing> facingMap = mapUI.getFacing();
        for (Hex hex : hexMap.values()) {
            g.setLineWidth(1);
            g.setColor(Color.white);

            if (mapUI.hasActiveToken()) {
                Facing facing = facingMap.get(hex);
                if (facing != null) {
                    switch (facing) {
                        case Front:
                            g.setLineWidth(3);
                            g.setColor(Color.white);
                            break;
                        case Back:
                            g.setColor(Color.black);
                            break;
                        case Left:
                        case Right:
                            g.setColor(Color.yellow);
                            break;
                    }
                }
            }
            hex.draw(g);

        }

        if (mapUI.hasActiveHex()) {
            g.setColor(Color.orange);
            g.setLineWidth(4);
            Polygon activePolygon = mapUI.getActiveHex().getPolygon();
            g.draw(activePolygon);
        }

    }


    @Override
    public void mouseReleased(int button, int x, int y) {
        super.mouseReleased(button, x, y);    //To change body of overridden methods use File | Settings | File Templates.
        mapUI.mouseClicked(button, x, y);
    }


    @Override
    public void mouseMoved(int oldX, int oldY, int newX, int newY) {

        super.mouseMoved(oldX, oldY, newX, newY);    //To change body of overridden methods use File | Settings | File Templates.

        Hex hex = hexMap.getHexByScreenCoordinates(newX, newY);
        if (hex != null && hex.getPolygon().contains(newX, newY)) {
            if (mapUI.getActiveHex() != hex) {
                mapUI.setActiveHex(hex);
                System.out.println(String.format("%s", hex.getPoint()));
            }
        }

    }


    @Override
    public int getID() {
        return stateID;  //To change body of implemented methods use File | Settings | File Templates.
    }


}
