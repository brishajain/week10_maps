package edu.ttap.lootgenerator;

/**
 * Monster class that can be killed for a loot and holds
 * a name, type, level and treasure class.
 */
public class Monster {
    String name;

    String type;

    int level;

    String treasureClass;

    /**
     * Represents the monster class with all parameters
     * 
     * @param name          The name of the monster
     * @param type          The type of the monster
     * @param level         The level of the monster
     * @param treasureClass The trasure class of the moster
     */
    public Monster(String name, String type, int level, String treasureClass) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.treasureClass = treasureClass;
    }
}
