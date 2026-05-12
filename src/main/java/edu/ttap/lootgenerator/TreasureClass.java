package edu.ttap.lootgenerator;

import java.util.Random;

/**
 * Represents one treasure class with three possible drops.
 */
public class TreasureClass {
    private final String name;

    private final String item1;

    private final String item2;

    private final String item3;

    /**
     * Constructs a TreasureClass with name and three possible item drops.
     * @param name the name of the treasure class
     * @param item1 the first possible item
     * @param item2 the second possible item
     * @param item3 the third possible item
     */
    public TreasureClass(String name, String item1, String item2, String item3) {
        this.name = name;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
    }

    /**
     * Returns the name of this treasure class.
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a randomly selected item from the three possible drops.
     * @param rand the random number generator
     * @return a randomly selected item
     */
    public String randomDrop(Random rand) {
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