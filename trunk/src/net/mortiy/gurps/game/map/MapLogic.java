package net.mortiy.gurps.game.map;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 17.02.13
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class MapLogic {
    HexPathfinder pathfinder;
    HexMap hexMap;
    Mover mover;

    public MapLogic(HexMap hexMap) {
        this.hexMap = hexMap;
        this.pathfinder = new HexPathfinder(hexMap);
        this.mover = new Mover(hexMap);
    }

    public HexPathfinder getPathfinder() {
        return pathfinder;
    }

    public Mover getMover() {
        return mover;
    }
}
