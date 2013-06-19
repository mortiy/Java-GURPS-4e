package net.mortiy.gurps.game.map;

import net.mortiy.gurps.rules.map.GameMap;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.util.FastTrig;
import org.newdawn.slick.util.pathfinding.Path;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 17.02.13
 * Time: 12:15
 * To change this template use File | Settings | File Templates.
 */
public class MapUI implements Observer {
    private HexMap hexMap;
    private MapLogic mapLogic;
    private Hex activeHex;
    private Token activeToken;
    private Map<Hex, Facing> facing = new HashMap<>();


    public MapUI(MapLogic mapLogic, HexMap hexMap) {
        this.mapLogic = mapLogic;
        this.hexMap = hexMap;
    }

    public Hex getActiveHex() {
        return activeHex;
    }

    public GameMap.MapToken getActiveToken() {
        return activeToken;
    }

    public Map<Hex, Facing> getFacing() {
        return facing;
    }

    public void setActiveHex(Hex hex) {
        this.activeHex = hex;
    }

    public void setActiveToken(Token token) {
        if (this.activeToken != null) {
            this.activeToken.deleteObserver(this);
        }
        this.activeToken = token;
        this.activeToken.addObserver(this);
        calcFacing();
    }

    public boolean hasActiveToken(){
        return this.activeToken != null;
    }

    public boolean hasActiveHex() {
        return this.activeHex != null;
    }

    private void calcFacing() {
        if (!hasActiveToken()) {
            return;
        }

        facing.clear();

        float tokenRotationAngle = 30 + activeToken.getRotationAngle();

        Vector2f polygonVector = new Vector2f(0, 0);
        Vector2f tokenVector = new Vector2f(
                (float) FastTrig.cos(Math.toRadians(tokenRotationAngle)),
                (float) FastTrig.sin(Math.toRadians(tokenRotationAngle))
        );

        float activeX = hexMap.getTokenHex(activeToken).getPolygon().getX();
        float activeY = hexMap.getTokenHex(activeToken).getPolygon().getY();

        for (Hex hex : hexMap.values()) {
            Polygon hexPolygon = hex.getPolygon();
            float dX = hexPolygon.getX() - activeX;
            float dY = hexPolygon.getY() - activeY;

            polygonVector.set(dX, dY);
            float hexAngle = 180 - (float) Math.floor(Math.toDegrees(Vector2f.angle(polygonVector, tokenVector)));
            if (hexAngle < activeToken.getViewAngle()) {
                facing.put(hex, Facing.Front);
            } else if (hexAngle > 125) {
                facing.put(hex, Facing.Back);
            } else {
                if (dX > 0) {
                    facing.put(hex, Facing.Right);
                } else {
                    facing.put(hex, Facing.Left);
                }
            }

        }
    }

    public void mouseClicked(int button, int x, int y) {

        if(activeHex != null){
            List<Token> tokens = hexMap.getTokens(activeHex);
            if(tokens != null && tokens.size() > 0){
                setActiveToken(tokens.get(0));
                return;
            }
        }

        if (activeToken != null) {
            if (button == Input.MOUSE_LEFT_BUTTON) {
                if(hasActiveToken()){
                    Path path = mapLogic.getPathfinder().getPath(hexMap.getTokenHex(activeToken), activeHex);
                    mapLogic.getMover().moveTo(activeToken, path);

                }
                //activeToken.setRotationAngle((activeToken.getRotationAngle() + 60) % 360);
            }
            if (button == Input.MOUSE_RIGHT_BUTTON) {
                activeToken.setViewAngle(activeToken.getViewAngle() >= 90 ? 30 : activeToken.getViewAngle() + 15);
            }
        }

//        if(obstacleMode){
//            hexMap.putToken(mapUI.getActiveHex(), new net.mortiy.gurps.rules.Character(100));
//            path = pathfinder.getPath();
//        } else {
//
//        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Token) {
            Token token = (Token) o;
            if (token == activeToken) {
                calcFacing();
            }
        }
    }
}
