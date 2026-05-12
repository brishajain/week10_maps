package edu.ttap.lootgenerator;

/**
 * Represents one monster.
 */
public class Monster {
    private final String name;

    private final String type;

    private final int level;

    private final String treasureClass;

    /**
     * Constructs a Monster with name, type, level, and treasure class.
     * @param name the name of the monster
     * @param type the type of the monster
     * @param level the level of the monster
     * @param treasureClass the treasure class
     */
    public Monster(String name, String type, int level, String treasureClass) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.treasureClass = treasureClass;
    }

    /**
     * Returns the name of this monster.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the type of this monster.
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the level of this monster.
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the treasure class of this monster.
     * @return the treasure class
     */
    public String getTreasureClass() {
        return treasureClass;
    }
}