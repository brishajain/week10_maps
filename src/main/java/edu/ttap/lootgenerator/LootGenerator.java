package edu.ttap.lootgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";
    private static final Random rand = new Random();
    
    public static class Monster{
        String name;
        String type;
        int level;
        String treasureClass;
        
        public Monster(String name, String type, int level, String treasureClass) 
        {
            this.name = name;
            this.type = type;
            this.level = level;
            this.treasureClass = treasureClass;
        }
    }
    
    public static class TreasureClass{
        String name;
        String item1;
        String item2;
        String item3;

        public TreasureClass(String name, String item1, String item2, String item3) {
            this.name = name;
            this.item1 = item1;
            this.item2 = item2;
            this.item3 = item3;
        }

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

    public static class Armor {
        String name;
        int minac;
        int maxac;

        public Armor(String name, int minac, int maxac) {
            this.name = name;
            this.minac = minac;
            this.maxac = maxac;
        }
    }

    public static class Affix {
        String name;
        String modCode;
        int modMin;
        int modMax;

        public Affix(String name, String modCode, int modMin, int modMax) {
            this.name = name;
            this.modCode = modCode;
            this.modMin = modMin;
            this.modMax = modMax;
        }
    }

    public ArrayList<Monster> parseMonsters(String filename) throws FileNotFoundException 
    {
        ArrayList<Monster> monsters = new ArrayList<>();
        Scanner file = new Scanner(new File(filename));
        
        while(file.hasNextLine()) {
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

    public HashMap<String, TreasureClass> parseTreasue(String filename)
    throws FileNotFoundException {
        Scanner file = new Scanner(new File(filename));
        HashMap<String, TreasureClass> treasureClasses = new HashMap<>();

        while(file.hasNextLine())
        {
            String line = file.nextLine().trim();
            String[] parts= line.split("\t");
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

    public Monster pickMonster(ArrayList<Monster> monsters) {
        int index = rand.nextInt(monsters.size());
        return monsters.get(index);
    }

    public String fetchTreasureClass(String treasureClass, HashMap<String, TreasureClass> treasureClasses) {
        String current = treasureClass;
        while(treasureClasses.containsKey(current)) {
        TreasureClass tc = treasureClasses.get(current);
        current = tc.randomDrop();
    }
    return current;
    }

    public static void main(String[] args) {
        System.out.println("This program kills monsters and generates loot!");
        // TOOD: Implement me!
    }
}