package net.mortiy.gurps.rules;

public enum TechLevel {

    /**
     * Stone Age (Prehistory and later). $250.
     */
    TL0(250),

    /**
     * Bronze Age (3500 B.C.+). $500.
     */
    TL1(500),

    /**
     * Iron Age (1200 B.C.+). $750.
     */
    TL2(750),

    /**
     * Medieval (600 A.D.+). $1,000.
     */
    TL3(1000),

    /**
     * Age of Sail (1450+). $2,000.
     */
    TL4(2000),

    /**
     * Industrial Revolution (1730+). $5,000.
     */
    TL5(5000),

    /**
     * Mechanized Age (1880+). $10,000.
     */
    TL6(10000),

    /**
     * Nuclear Age (1940+). $15,000
     */
    TL7(15000),

    /**
     * Digital Age (1980+). $20,000
     */
    TL8(20000),

    /**
     * Microtech Age (2025+?). $30,000
     */
    TL9(30000),

    /**
     * Robotic Age (2070+?). $50,000.
     */
    TL10(50000),

    /**
     * Age of Exotic Matter. $75,000.
     */
    TL11(75000),

    /**
     * Whatever the GM likes! $100,000.
     */
    TL12(100000);

    private int startingWealth;

    TechLevel(int startingWealth) {
        this.startingWealth = startingWealth;
    }

    public int getStartingWealth() {
        return startingWealth;
    }

}
