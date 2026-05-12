package edu.ttap.lootgenerator;

/**
 * A modifier affix that gets slapped onto an item,
 * like strength. Has a stat code and a roll range.
 */
public class Affix {
    String name;

    String modCode;

    int modMin;

    int modMax;

    /**
     * Creates an affix with its name, what it modifies, and how much.
     * 
     * @param name    the display name of the affix
     * @param modCode code for what stat it affects
     * @param modMin  the minimum value of the modifier
     * @param modMax  the maximum value of the modifier
     */
    public Affix(String name, String modCode, int modMin, int modMax) {
        this.name = name;
        this.modCode = modCode;
        this.modMin = modMin;
        this.modMax = modMax;
    }
}
