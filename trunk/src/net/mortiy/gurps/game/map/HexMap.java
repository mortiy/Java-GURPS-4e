package net.mortiy.gurps.game.map;

import org.newdawn.slick.geom.Polygon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 15.02.13
 * Time: 22:44
 * To change this template use File | Settings | File Templates.
 */
public class HexMap extends HashMap<HexPoint, Hex> {

    Map<Hex, List<Token>> hexToTokens = new HashMap<>();
    Map<Token, Hex> tokenToHex = new HashMap<>();

    HexFabric hexFabric;

    int mapWidth, mapHeight;
    int hexHeight = 64;

    public HexMap(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
        this.hexFabric = new HexFabric(hexHeight);

        for(int x = 0, startY = 1; x < mapWidth; x++, startY -= x % 2){
            for(int y = startY; y < mapHeight + startY; y++){
                put(new HexPoint(x, y), hexFabric.getHex(x, y));
            }
        }
    }
    public void setHexSize(int hexHeight){
        this.hexHeight = hexHeight;
    }

    public void putToken(Hex hex, Token mapToken){
        List<Token> tokens;
        if(!hexToTokens.containsKey(hex)){
            hexToTokens.put(hex, new ArrayList<Token>());
        }
        tokens = hexToTokens.get(hex);
        tokens.add(mapToken);
        tokenToHex.put(mapToken, hex);
    }
    public void removeToken(Token mapToken){
        Hex hex = tokenToHex.get(mapToken);
        hexToTokens.get(hex).remove(hex);
        tokenToHex.remove(mapToken);
    }

    public Hex getTokenHex(Token mapToken){
        return tokenToHex.get(mapToken);
    }

    public List<Token> getTokens(Hex hex){
        return hexToTokens.get(hex);
    }

    public Map<Hex, List<Token>> getTokensMap() {
        return hexToTokens;
    }

    public Hex getHexByScreenCoordinates(int screenX, int screenY){
        return get(hexFabric.getHexPoint(screenX, screenY));
    }

    boolean isWalkable(Hex hex){
        return !hexToTokens.containsKey(hex);
    }

    class HexFabric {
        private final int size, sizeHalf, side, sideHalf, sideDouble, shiftX;
        private final Polygon hexShape;

        HexFabric(Integer size) {
            this.size = size;
            this.sizeHalf = this.size / 2;
            this.side = Math.round(this.size / 1.777f);
            this.sideHalf = this.side / 2;
            this.sideDouble = this.side * 2;
            this.shiftX = Math.round(sideDouble / 1.3333f);

            this.hexShape = new Polygon();

            hexShape.addPoint(- side, 0);
            hexShape.addPoint(- sideHalf, - sizeHalf);
            hexShape.addPoint(+ sideHalf, - sizeHalf);
            hexShape.addPoint(+ side, 0);
            hexShape.addPoint(+ sideHalf, + sizeHalf);
            hexShape.addPoint(- sideHalf, + sizeHalf);

        }

        Hex getHex(int centerX, int centerY) {
            float canvasX = centerX * shiftX;
            float canvasY = centerY * size + centerX * sizeHalf;
            Polygon polygon = hexShape.copy();
            polygon.setLocation(canvasX, canvasY);
            Hex hex = new Hex(polygon, new HexPoint(centerX, centerY));
            return hex;

        };

        HexPoint getHexPoint(float screenX, float screenY) {
            int x = (int) Math.floor(screenX / shiftX);
            int y = (int) Math.floor((screenY - x * sizeHalf) / size) ;
            return new HexPoint(x, y);
        }
    }
}
