package net.mortiy.gurps.game.map.hex;

/**
* Created with IntelliJ IDEA.
* User: oleksandr.sidko
* Date: 15.02.13
* Time: 17:58
* To change this template use File | Settings | File Templates.
*/
public class HexPoint {

    int x, y, z;

    public HexPoint(int x, int y) {
        this.x = x;
        this.y = y;
        this.z = -(x + y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setX(int x) {
        this.x = x;
        this.z = -(x + y);
    }

    public void setY(int y) {
        this.y = y;
        this.z = -(x + y);
    }

    public int distance(HexPoint p){
        int dX = p.getX() - x;
        int dY = p.getY() - y;
        int dZ = p.getZ() - z;
        return Math.max(Math.max(dX, dY), dZ);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HexPoint hexPoint = (HexPoint) o;

        if (Float.compare(hexPoint.x, x) != 0) return false;
        if (Float.compare(hexPoint.y, y) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (x != +0.0f ? Float.floatToIntBits(x) : 0);
        result = 31 * result + (y != +0.0f ? Float.floatToIntBits(y) : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("X: %d, Y: %d, Z: %d", getX(), getY(), getZ());
    }
}
