package net.mortiy.gurps.rules.traits.all;

import net.mortiy.gurps.rules.*;
import net.mortiy.gurps.rules.Character;
import net.mortiy.gurps.rules.traits.Advantage;
import net.mortiy.gurps.rules.traits.LevelTrait;

import java.util.HashMap;
import java.util.Map;

/**
 * Rank
 * ==============================
 * Specific sectors of society – e.g., the
 * civil service, the military, and certain
 * powerful religions – often have internal ranking systems, distinct from
 * Status. If an organization like this has
 * significant social influence, or access
 * to useful resources, then its members
 * must pay points for their rank within
 * the organization.
 * Rank is worth 5 points per level if it
 * coexists with Status, or 10 points per
 * level if it replaces Status.
 */
public class Rank extends LevelTrait implements Advantage {

    public interface RankType {};

    public static enum Type implements RankType {
        Administrative,
        Merchant,
        Military,
        Police,
        Religious
    }

    private Map<RankType, Integer> ranks = new HashMap<RankType, Integer>();

    public Rank(Character character) {
        super(character, "Rank");
    }

    public void setRank(RankType type, int rankLevel) {
        ranks.put(type, rankLevel);
    }

    @Override
    public int getRawCost() {
        int totalCost = 0;
        int levelCost = getLevelCost();
        for(Integer rankLevel : ranks.values()){
            totalCost += rankLevel;
        }
        return totalCost;
    }

    @Override
    protected int getLevelCost() {
        if(character.hasTrait("Status")){
            return 5;
        } else {
            return 10;
        }
    }
}
