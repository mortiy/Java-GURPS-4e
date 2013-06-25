package net.mortiy.gurps.rules.map;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.newdawn.slick.Image;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 29.12.12
 * Time: 1:48
 * To change this template use File | Settings | File Templates.
 */
public class GameMap {

    public interface MapToken {
        public Image getImage();
    }

    public class Point extends Vector2D {
        public Point(double x, double y) {
            super(x, y);
        }
    }

    Map<MapToken, Point> tokens = new HashMap<>();

    public GameMap() {

    }

    public void putToken(MapToken token, int x, int y) {
        putToken(token, new Point(x, y));
    }
    public void putToken(MapToken token, Point point){
        tokens.put(token, point);
    }

    public void moveToken(MapToken token, Point point){
        if(tokens.containsKey(token)){
            putToken(token, point);
        }
    }

    public void moveToken(MapToken token, int x, int y){
        if(tokens.containsKey(token)){
            putToken(token, x, y);
        }
    }

    public Point getTokenLocation(MapToken token){
        return tokens.get(token);
    }

    public double getDistance(MapToken t1, MapToken t2){
        return tokens.get(t1).distance(tokens.get(t2));
    }
}
