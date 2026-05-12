package edu.ttap.lootgenerator;

/**
 * A piece of armor with a defense range that gets
 * rolled randomly between a min and max value.
 */
public class Armor {
    String name;

    int minac;

    int maxac;

    /**
     * Creates an armor piece with its name and defense range.
     * 
     * @param name  the armor's name
     * @param minac the lowest possible defense roll
     * @param maxac the highest possible defense roll
     */
    public Armor(String name, int minac, int maxac) {
        this.name = name;
        this.minac = minac;
        this.maxac = maxac;
    }
}
