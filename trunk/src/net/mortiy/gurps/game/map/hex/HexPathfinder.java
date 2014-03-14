package net.mortiy.gurps.game.map.hex;


import org.newdawn.slick.util.pathfinding.Path;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 15.02.13
 * Time: 19:10
 * To change this template use File | Settings | File Templates.
 */
public class HexPathfinder {

    HexMap hexMap;
    Hex startNode, destNode;

    List<Hex> openList = new ArrayList<>();
    Set<Hex> closedList = new HashSet<>();

    Map<Hex, Hex> parents = new HashMap<>();
    Map<Hex, Cost> costs = new HashMap<>();

    Comparator<Hex> costComparator = new Comparator<Hex>() {
        @Override
        public int compare(Hex o1, Hex o2) {
            return costs.get(o1).F.compareTo(costs.get(o2).F);
        }
    };

    public class Cost {
        public Integer F;
        public Integer H;
        public Integer G;

        Cost(int h, int g) {
            H = h;
            G = g;
            F = h + g;
        }

        @Override
        public String toString() {
            return String.format("%d,%d", G, H);
        }
    }

    class HexPath extends Path {
        public void prependStep(HexPoint hexPoint) {
            super.prependStep(hexPoint.getX(), hexPoint.getY());    //To change body of overridden methods use File | Settings | File Templates.
        }
    }


    public HexPathfinder(HexMap hexMap) {
        this.hexMap = hexMap;
        this.openList = new ArrayList<>();
    }


    public HexPath getPath(Hex startNode, Hex destNode){

        this.startNode = startNode;
        this.destNode = destNode;

        openList.clear();
        closedList.clear();
        parents.clear();
        costs.clear();

        if(startNode.equals(destNode)){
            HexPath path = new HexPath();
            path.prependStep(destNode.getPoint());
            path.prependStep(startNode.getPoint());
            return path;
        }

        costs.put(startNode, new Cost(0, 0));
        openList.add(startNode);

        // a) Look for the lowest F cost square on the open list. We refer to this as the current square.
        Hex node = startNode;
        while(!closedList.contains(destNode)){
            node = nextNode(node);
            if(openList.size() == 0){
                System.out.println("No path found!");
                return new HexPath();
            }
        }

        HexPath path = new HexPath();
        Hex parent = destNode;
        while(parent != startNode){
            path.prependStep(parent.getPoint());
            parent = parents.get(parent);
        }
        path.prependStep(startNode.getPoint());

        return path;
    }

    private Integer getG(Hex h1, Hex h2){
        return h1.getPoint().distance(h2.getPoint()) * 2;
    }

    private Integer getH(Hex node){
        HexPoint h1 = node.getPoint();
        HexPoint h2 = destNode.getPoint();

        return (Math.abs(h2.getX() - h1.getX()) + Math.abs(h2.getY() - h1.getY()) + Math.abs(h2.getZ() - h1.getZ()));
    }



    private Hex nextNode(Hex node){
        // b) Switch it to the closed list.

        openList.remove(node);

        closedList.add(node);
        Cost nodeCost = costs.get(node);
        // c) For each of the 8 squares adjacent to this current square …
        List<Hex> adjacent = getAdjacent(node);
        for(Hex adjacentHex : adjacent){
            // If it is not walkable or if it is on the closed list, ignore it.
            if(adjacentHex == null || !hexMap.isWalkable(adjacentHex) || closedList.contains(adjacentHex)){
                continue;
            }
            //System.out.println(adjacentHex.getPoint().toString() + " = " + getCost(adjacentHex));

            // Otherwise do the following:
            // If it isn’t on the open list, add it to the open list.
            // Make the current square the parent of this square.
            // Record the F, G, and H costs of the square.
            if(!openList.contains(adjacentHex)){
                // Record the F, G, and H costs of the square.
                costs.put(adjacentHex, new Cost(getH(adjacentHex), getG(adjacentHex, node) + nodeCost.G));
                // If it isn’t on the open list, add it to the open list.
                openList.add(adjacentHex);
                // Make the current square the parent of this square:
                parents.put(adjacentHex, node);

            } else {
                //If it is on the open list already,
                Cost oldCost = costs.get(adjacentHex);
                Cost newCost = new Cost(getH(adjacentHex), getG(adjacentHex, node) + nodeCost.G);

                // check to see if this path to that square is better, using G cost as the measure.
                if(newCost.G < oldCost.G){

                    costs.put(adjacentHex, newCost);
                    parents.put(adjacentHex, node);
                    // Resort:
                    openList.remove(adjacentHex);
                    openList.add(adjacentHex);
                }

            }
        }

        if(openList.size() > 0){
            Collections.sort(openList, costComparator);
            return openList.get(0);
        } else {
            return null;
        }

    }

    private List<Hex> getAdjacent(final Hex hex){
        int hexX = hex.getPoint().getX();
        int hexY = hex.getPoint().getY();

        List<Hex> adjacentAll = new ArrayList<>();

        adjacentAll.add(hexMap.get(new HexPoint(hexX, hexY - 1)));
        adjacentAll.add(hexMap.get(new HexPoint(hexX + 1, hexY - 1)));
        adjacentAll.add(hexMap.get(new HexPoint(hexX + 1, hexY)));
        adjacentAll.add(hexMap.get(new HexPoint(hexX, hexY + 1)));
        adjacentAll.add(hexMap.get(new HexPoint(hexX - 1, hexY + 1)));
        adjacentAll.add(hexMap.get(new HexPoint(hexX - 1, hexY)));

        List<Hex> adjacentWalkable = new ArrayList<>();
        for(Hex h : adjacentAll){
            if(h == null || !hexMap.isWalkable(h) || closedList.contains(h)){
                continue;
            }
            adjacentWalkable.add(h);
        }
        return adjacentWalkable;
    }
}


