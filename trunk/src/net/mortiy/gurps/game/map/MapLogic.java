package net.mortiy.gurps.game.map;

import net.mortiy.gurps.game.map.hex.HexMap;
import net.mortiy.gurps.game.map.hex.HexPathfinder;

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
