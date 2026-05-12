package edu.ttap.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 * Main loot generator class that reads in monsters, treasure classes,
 * armor, and affixes from data files, then uses them to simulate
 * killing a monster and dropping loot.
 */
public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    private static final Random rand = new Random();

    /**
     * Reads the monsters file and returns a list of all monsters.
     * Each line in the file should be tab-separated with name, type, level, and
     * treasure class.
     * 
     * @param filename path to the monsters data file
     * @return a list of all the monsters we loaded
     * @throws FileNotFoundException if the file doesn't exist
     */
    public ArrayList<Monster> parseMonsters(String filename) throws FileNotFoundException {
        ArrayList<Monster> monsters = new ArrayList<>();
        Scanner file = new Scanner(new File(filename));

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();
            String[] parts = line.split("\t");
            String name = parts[0];
            String type = parts[1];
            int level = Integer.parseInt(parts[2]);
            String treasureClass = parts[3];

            Monster monster = new Monster(name, type, level, treasureClass);
            monsters.add(monster);
        }
        file.close();
        return monsters;
    }

    /**
     * Reads the treasure class file and returns a map of all treasure classes.
     * The map key is the treasure class name, which makes lookups easy later.
     * 
     * @param filename path to the treasure class data file
     * @return a map from treasure class name to TreasureClass object
     * @throws FileNotFoundException if the file doesn't exist
     */
    public HashMap<String, TreasureClass> parseTreasue(String filename)
            throws FileNotFoundException {
        Scanner file = new Scanner(new File(filename));
        HashMap<String, TreasureClass> treasureClasses = new HashMap<>();

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();
            String[] parts = line.split("\t");
            String treasure = parts[0];
            String item1 = parts[1];
            String item2 = parts[2];
            String item3 = parts[3];

            TreasureClass t = new TreasureClass(treasure, item1, item2, item3);
            treasureClasses.put(treasure, t);
        }
        file.close();
        return treasureClasses;
    }

    /**
     * Picks a random monster from the list. Every monster has an equal chance.
     * 
     * @param monsters the full list of monsters to pick from
     * @return the monster that got randomly selected
     */
    public Monster pickMonster(ArrayList<Monster> monsters) {
        int index = rand.nextInt(monsters.size());
        return monsters.get(index);
    }

    /**
     * Follows the treasure class chain until we land on an actual item name.
     * Treasure classes can point to other treasure classes, so we keep rolling
     * until we hit something that isn't in the map.
     * 
     * @param treasureClass   the starting treasure class name
     * @param treasureClasses the full map of all treasure classes
     * @return the name of the final item that dropped
     */
    public String fetchTreasureClass(String treasureClass,
            HashMap<String, TreasureClass> treasureClasses) {
        String current = treasureClass;
        while (treasureClasses.containsKey(current)) {
            TreasureClass tc = treasureClasses.get(current);
            current = tc.randomDrop();
        }
        return current;
    }

    /**
     * Reads the armor file and returns a map of all armor pieces.
     * 
     * @param filename path to the armor data file
     * @return a map from armor name to Armor object
     * @throws FileNotFoundException if the file doesn't exist
     */
    public HashMap<String, Armor> parseArmor(String filename)
            throws FileNotFoundException {

        Scanner file = new Scanner(new File(filename));

        HashMap<String, Armor> armors = new HashMap<>();

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();
            String[] parts = line.split("\t");
            String name = parts[0];
            int minac = Integer.parseInt(parts[1]);
            int maxac = Integer.parseInt(parts[2]);

            Armor a = new Armor(name, minac, maxac);
            armors.put(name, a);
        }
        file.close();
        return armors;
    }

    /**
     * Looks up an armor piece by name and returns it as the base item.
     * Returns null if it doesn't find it
     * 
     * @param armorItem the name of the armor to look up
     * @param armorMap  the full map of armor pieces
     * @return the matching Armor object, or null if not found
     */
    public Armor generateBaseItem(String armorItem, HashMap<String, Armor> armorMap) {
        return armorMap.get(armorItem);
    }

    /**
     * Rolls a random defense value for the given armor within its range
     * 
     * @param item the armor piece to roll stats for
     * @return a string of the Defense plus value
     */
    public String generateBaseStats(Armor item) {
        int val = rand.nextInt(item.maxac - item.minac + 1) + item.minac;
        return "Defense: " + val;
    }

    /**
     * Reads the affixes file and returns a list of all affixes.
     * 
     * @param filename path to the affixes data file
     * @return a list of all the affixes we loaded
     * @throws FileNotFoundException if the file doesn't exist
     */
    public ArrayList<Affix> parseAffixs(String filename) throws FileNotFoundException {
        ArrayList<Affix> affixes = new ArrayList<>();
        Scanner file = new Scanner(new File(filename));

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();
            String[] parts = line.split("\t");
            String name = parts[0];
            String mod1code = parts[1];
            int mod1min = Integer.parseInt(parts[2]);
            int mod1max = Integer.parseInt(parts[3]);

            Affix newAffix = new Affix(name, mod1code, mod1min, mod1max);
            affixes.add(newAffix);
        }
        file.close();
        return affixes;
    }

    /**
     * The entry point. Eventually this should pick a monster, kill it,
     * roll some loot, and print out what dropped. For now it's just a stub.
     * 
     * @param args Command lines
     */
    public static void main(String[] args) {
        System.out.println("This program kills monsters and generates loot!");
        // TOOD: Implement me!
    }
}