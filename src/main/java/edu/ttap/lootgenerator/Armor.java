package edu.ttap.lootgenerator;

/**
 * Represents one armor item.
 */
public class Armor {
    private final String name;

    private final int minac;

    private final int maxac;

    /**
     * Constructs an Armor with name and min/max armor class values.
     * @param name the name of the armor
     * @param minac the minimum armor class
     * @param maxac the maximum armor class
     */
    public Armor(String name, int minac, int maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }

    /**
     * Returns the name of this armor.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the minimum armor class of this armor.
     * @return the minimum armor class
     */
    public int getMinac() {
        return minac;
    }

    /**
     * Returns the maximum armor class of this armor.
     * @return the maximum armor class
     */
    public int getMaxac() {
        return maxac;
    }
}