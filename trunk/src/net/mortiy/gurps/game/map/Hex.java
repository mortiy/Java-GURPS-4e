package net.mortiy.gurps.game.map;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 15.02.13
 * Time: 18:03
 * To change this template use File | Settings | File Templates.
 */
public class Hex {
    HexPoint point;
    Polygon polygon;

    public Hex(Polygon polygon, HexPoint point) {
        this.polygon = polygon;
        this.point = point;
    }

    public void draw(Graphics g){
        g.draw(polygon);
    }

    public HexPoint getPoint() {
        return point;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    boolean isWalkable(){
        return true;
    }

    public int angle(Hex hex){
        Vector2f v1 = new Vector2f(hex.getPolygon().getX() - getPolygon().getX(), hex.getPolygon().getY() - getPolygon().getY());
        return (int) Math.floor(Math.toDegrees(Vector2f.angle(v1, new Vector2f(0, 0))));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hex hex = (Hex) o;

        if (point != null ? !point.equals(hex.point) : hex.point != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return point != null ? point.hashCode() : 0;
    }
}
