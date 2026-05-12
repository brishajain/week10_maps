package edu.ttap.lootgenerator;

/**
 * Represents one prefix or suffix affix.
 */
public class Affix {
    private final String name;

    private final String modCode;

    private final int modMin;

    private final int modMax;

    /**
     * Constructs an Affix with name, mod code, and min/max mod values.
     * @param name the name of the affix
     * @param modCode the modification code
     * @param modMin the minimum modification value
     * @param modMax the maximum modification value
     */
    public Affix(String name, String modCode, int modMin, int modMax) {
        this.name = name;
        this.modCode = modCode;
        this.modMin = modMin;
        this.modMax = modMax;
    }

    /**
     * Returns the name of this affix.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the mod code of this affix.
     * @return the mod code
     */
    public String getModCode() {
        return modCode;
    }

    /**
     * Returns the minimum mod value of this affix.
     * @return the minimum mod value
     */
    public int getModMin() {
        return modMin;
    }

    /**
     * Returns the maximum mod value of this affix.
     * @return the maximum mod value
     */
    public int getModMax() {
        return modMax;
    }
}