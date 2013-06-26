package net.mortiy.gurps.game.map;

import net.mortiy.gurps.game.map.hex.HexMap;
import net.mortiy.gurps.game.map.hex.HexPoint;
import org.newdawn.slick.util.pathfinding.Path;

import java.util.HashMap;
import java.util.Map;

/**
* Performs tokens movement on hexagonal map
*/
class Mover {
    HexMap hexMap;

    Map<Token, Path> pathList = new HashMap<>();
    Map<Path, Integer> activeStep = new HashMap<>();


    public Mover(HexMap hexMap) {
        this.hexMap = hexMap;
    }

    public void moveTo(Token token, Path path){
        pathList.put(token, path);
    }

    public void move(int delta) {
        for(Token t: pathList.keySet()){
            Path p = pathList.get(t);
            Integer stepIndex = activeStep.get(p);
            Path.Step activeStep = p.getStep(stepIndex);
            HexPoint activeHex = new HexPoint(activeStep.getX(), activeStep.getY());

            // TODO: Token movement
            // Note: Как я могу перемещать токен, если он привязан к хексу?
            // Возможно нужно отделить их положения?

        }

    }
}
