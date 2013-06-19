package net.mortiy.gurps.rules;

/**
 * Created with IntelliJ IDEA.
 * User: oleksandr.sidko
 * Date: 08.12.12
 * Time: 3:11
 * To change this template use File | Settings | File Templates.
 */
public class TechLevel {

    private static int wealth[] = new int[] {250, 500, 750, 1000, 2000, 5000, 10000, 15000, 20000, 30000, 50000, 75000, 100000};

    public static int getStartingWealth(Level level){
        return wealth[level.ordinal()];
    }

    public static enum Level {
        /**
         * Stone Age (Prehistory and later). $250.
         */
        TL0,

        /**
         * Bronze Age (3500 B.C.+). $500.
         */
        TL1,

        /**
         * Iron Age (1200 B.C.+). $750.
         */
        TL2,

        /**
         * Medieval (600 A.D.+). $1,000.
         */
        TL3,

        /**
         * Age of Sail (1450+). $2,000.
         */
        TL4,

        /**
         * Industrial Revolution (1730+). $5,000.
         */
        TL5,

        /**
         * Mechanized Age (1880+). $10,000.
         */
        TL6,

        /**
         * Nuclear Age (1940+). $15,000
         */
        TL7,

        /**
         * Digital Age (1980+). $20,000
         */
        TL8,

        /**
         * Microtech Age (2025+?). $30,000
         */
        TL9,

        /**
         * Robotic Age (2070+?). $50,000.
         */
        TL10,

        /**
         * Age of Exotic Matter. $75,000.
         */
        TL11,

        /**
         * Whatever the GM likes! $100,000.
         */
        TL12
    }

}
