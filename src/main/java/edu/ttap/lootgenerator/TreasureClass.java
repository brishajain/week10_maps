package edu.ttap.lootgenerator;

import java.util.Random;

/**
 * A loot table with three possible drops. Treasure classes
 * can chain into each other until we land on a real item.
 */
public class TreasureClass {
    private static final Random rand = new Random();

    String name;

    String item1;

    String item2;

    String item3;

    /**
     * Creates a treasure class with three possible outcomes.
     * 
     * @param name  the name of this treasure class
     * @param item1 the first possible drop
     * @param item2 the second possible drop
     * @param item3 the third possible drop
     */
    public TreasureClass(String name, String item1, String item2, String item3) {
        this.name = name;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    /**
     * Picks one of the three drops at random. Equal chance for each.
     * 
     * @return whichever item (or nested treasure class) got picked
     */
    public String randomDrop() {
        int choice = rand.nextInt(3);
        if (choice == 0) {
            return item1;
        } else if (choice == 1) {
            return item2;
        } else {
            return item3;
        }
    }
}
