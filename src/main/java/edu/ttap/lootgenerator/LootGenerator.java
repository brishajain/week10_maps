package edu.ttap.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates random loot from monster, treasure, armor, and affix data.
 */
public class LootGenerator {
    private static final String DEFAULT_DATA_SET = "data/small";

    private static final Random RAND = new Random();

    /**
     * Parses monsters from a file.
     * @param filename the file path to read from
     * @return a list of parsed monsters
     * @throws FileNotFoundException if the file is not found
     */
    public List<Monster> parseMonsters(String filename)
            throws FileNotFoundException {
        List<Monster> monsters = new ArrayList<>();
        Scanner file = new Scanner(new File(filename));

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();

            if (line.length() == 0 || line.startsWith("Class")) {
                continue;
            }

            String[] parts = line.split("\t");
            if (parts.length >= 4) {
                monsters.add(new Monster(parts[0], parts[1],
                        Integer.parseInt(parts[2]), parts[3]));
            }
        }

        file.close();
        return monsters;
    }

    /**
     * Parses treasure classes from a file.
     * @param filename the file path to read from
     * @return a map of treasure classes keyed by name
     * @throws FileNotFoundException if the file is not found
     */
    public Map<String, TreasureClass> parseTreasureClasses(String filename)
            throws FileNotFoundException {
        Map<String, TreasureClass> treasureClasses = new HashMap<>();
        Scanner file = new Scanner(new File(filename));

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();

            if (line.length() == 0 || line.startsWith("Treasure Class")) {
                continue;
            }

            String[] parts = line.split("\t");
            if (parts.length >= 4) {
                TreasureClass treasureClass = new TreasureClass(parts[0],
                        parts[1], parts[2], parts[3]);
                treasureClasses.put(parts[0], treasureClass);
            }
        }

        file.close();
        return treasureClasses;
    }

    /**
     * Parses armor from a file.
     * @param filename the file path to read from
     * @return a map of armor items keyed by name
     * @throws FileNotFoundException if the file is not found
     */
    public Map<String, Armor> parseArmor(String filename)
            throws FileNotFoundException {
        Map<String, Armor> armorMap = new HashMap<>();
        Scanner file = new Scanner(new File(filename));

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();

            if (line.length() == 0 || line.startsWith("name")) {
                continue;
            }

            String[] parts = line.split("\t");
            if (parts.length >= 3) {
                armorMap.put(parts[0], new Armor(parts[0],
                        Integer.parseInt(parts[1]),
                        Integer.parseInt(parts[2])));
            }
        }

        file.close();
        return armorMap;
    }

    /**
     * Parses affixes from a file.
     * @param filename the file path to read from
     * @return a list of parsed affixes
     * @throws FileNotFoundException if the file is not found
     */
    public List<Affix> parseAffixes(String filename)
            throws FileNotFoundException {
        List<Affix> affixes = new ArrayList<>();
        Scanner file = new Scanner(new File(filename));

        while (file.hasNextLine()) {
            String line = file.nextLine().trim();

            if (line.length() == 0 || line.startsWith("Name")) {
                continue;
            }

            String[] parts = line.split("\t");
            if (parts.length >= 4) {
                affixes.add(new Affix(parts[0], parts[1],
                        Integer.parseInt(parts[2]),
                        Integer.parseInt(parts[3])));
            }
        }

        file.close();
        return affixes;
    }

    /**
     * Picks a random monster from the list.
     * @param monsters the list of monsters to choose from
     * @return a randomly selected monster
     */
    public Monster pickMonster(List<Monster> monsters) {
        return monsters.get(RAND.nextInt(monsters.size()));
    }

    /**
     * Generates a base item name by following the treasure class chain.
     * @param treasureClass the starting treasure class
     * @param treasureClasses the map of all treasure classes
     * @return the generated base item name
     */
    public String generateBaseItem(String treasureClass,
            Map<String, TreasureClass> treasureClasses) {
        String current = treasureClass;

        while (treasureClasses.containsKey(current)) {
            current = treasureClasses.get(current).randomDrop(RAND);
        }

        return current;
    }

    /**
     * Generates base stats for an armor item.
     * @param armor the armor to generate stats for
     * @return the defense stat as a string
     */
    public String generateBaseStats(Armor armor) {
        int value = randomBetween(armor.getMinac(), armor.getMaxac());
        return "Defense: " + value;
    }

    /**
     * Generates a random affix or returns null.
     * @param affixes the list of affixes to choose from
     * @return a randomly selected affix or null
     */
    public Affix generateAffix(List<Affix> affixes) {
        if (affixes.size() == 0) {
            return null;
        }

        if (RAND.nextBoolean()) {
            return affixes.get(RAND.nextInt(affixes.size()));
        }

        return null;
    }

    /**
     * Generates stats for an affix.
     * @param affix the affix to generate stats for
     * @return the affix stat as a string
     */
    public String generateAffixStat(Affix affix) {
        int value = randomBetween(affix.getModMin(), affix.getModMax());
        return value + " " + affix.getModCode();
    }

    /**
     * Plays one round of loot generation and displays the result.
     * @param monsters the list of monsters
     * @param treasureClasses the map of treasure classes
     * @param armorMap the map of armor items
     * @param prefixes the list of prefix affixes
     * @param suffixes the list of suffix affixes
     */
    public void playRound(List<Monster> monsters,
            Map<String, TreasureClass> treasureClasses,
            Map<String, Armor> armorMap,
            List<Affix> prefixes,
            List<Affix> suffixes) {
        Monster monster = pickMonster(monsters);
        String baseItemName = generateBaseItem(monster.getTreasureClass(),
                treasureClasses);
        Armor armor = armorMap.get(baseItemName);

        if (armor == null) {
            throw new IllegalArgumentException("Unknown armor: "
                    + baseItemName);
        }

        Affix prefix = generateAffix(prefixes);
        Affix suffix = generateAffix(suffixes);

        String itemName = armor.getName();

        if (prefix != null) {
            itemName = prefix.getName() + " " + itemName;
        }

        if (suffix != null) {
            itemName = itemName + " " + suffix.getName();
        }

        System.out.println("Fighting " + monster.getName() + "...");
        System.out.println("You have slain " + monster.getName() + "!");
        System.out.println(monster.getName() + " dropped:");
        System.out.println();
        System.out.println(itemName);
        System.out.println(generateBaseStats(armor));

        if (prefix != null) {
            System.out.println(generateAffixStat(prefix));
        }

        if (suffix != null) {
            System.out.println(generateAffixStat(suffix));
        }
    }

    /**
     * Returns a random integer between min and max (inclusive).
     * @param min the minimum value
     * @param max the maximum value
     * @return a random integer in the range
     */
    private static int randomBetween(int min, int max) {
        return RAND.nextInt(max - min + 1) + min;
    }

    /**
     * Main method to run the loot generator.
     * @param args optional command line arguments
     * @throws FileNotFoundException if a data file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        String dataSet = DEFAULT_DATA_SET;

        if (args.length > 0) {
            dataSet = args[0];
        }

        LootGenerator generator = new LootGenerator();

        List<Monster> monsters = generator.parseMonsters(dataSet
                + "/monstats.txt");
        Map<String, TreasureClass> treasureClasses =
                generator.parseTreasureClasses(dataSet
                        + "/TreasureClassEx.txt");
        Map<String, Armor> armorMap = generator.parseArmor(dataSet
                + "/armor.txt");
        List<Affix> prefixes = generator.parseAffixes(dataSet
                + "/MagicPrefix.txt");
        List<Affix> suffixes = generator.parseAffixes(dataSet
                + "/MagicSuffix.txt");

        Scanner input = new Scanner(System.in);
        boolean fighting = true;

        while (fighting) {
            generator.playRound(monsters, treasureClasses, armorMap,
                    prefixes, suffixes);

            String answer = "";
            while (!answer.equals("y") && !answer.equals("n")) {
                System.out.print("Fight again [y/n]? ");
                answer = input.nextLine().trim().toLowerCase();
            }

            fighting = answer.equals("y");
        }

        input.close();
    }
}