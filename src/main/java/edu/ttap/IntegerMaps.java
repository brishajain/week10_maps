package edu.ttap;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * Integer maps class
 */
public class IntegerMaps {
    /*
     * Part 1: Counting Letters
     *
     * Book used:
     * War and Peace by Leo Tolstoy
     * https://www.gutenberg.org/ebooks/2600
     *
     * Observations:
     * The text was generally consistent with the known frequency of letters in
     * English. Letters such as e, t, a, o, i, and n appeared very often, while
     * letters such as q, x, and z appeared much less often. This matches the
     * usual pattern seen in English writing.
     */

    /**
     * Prints the counts of the 26 English letters in the file at the given path.
     * Counting is case-insensitive, and all non-English letters are ignored.
     *
     * @param path path to the file
     * @throws FileNotFoundException if the file cannot be opened
     */
    public static void reportCounts(String path) throws FileNotFoundException {
        int[] counts = new int[26];
        Scanner input = new Scanner(new File(path));

        while (input.hasNextLine()) {
            String line = input.nextLine().toLowerCase();

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);

                if (ch >= 'a' && ch <= 'z') {
                    counts[ch - 'a']++;
                }
            }
        }

        input.close();

        for (int i = 0; i < 26; i++) {
            char letter = (char) ('a' + i);
            System.out.println(letter + ": " + counts[i]);
        }
    }

    /*
     * Part 2: Counting Characters
     *
     * Book used:
     * Πολιτεία, Τόμος 2 by Plato
     * https://www.gutenberg.org/ebooks/39493
     *
     * Observations:
     * This text contains many more unique characters because it is written in
     * Greek. There are accented letters, punctuation marks, spaces, and many
     * Unicode characters that are not part of the English alphabet.
     *
     * Will the technique from part 1 work for this book?
     * No. The technique from part 1 only works for the 26 English letters,
     * because it uses an array of length 26 and maps letters from a to z.
     * That does not scale well for Greek or other character sets with many
     * different symbols.
     */

    /**
     * Counts the number of unique characters in the file at the given path.
     * This method is case-sensitive. It also prints each character together with
     * its integer character value.
     *
     * @param path path to the file
     * @return the number of unique characters
     * @throws FileNotFoundException if the file cannot be opened
     */
    public static int countChars(String path) throws FileNotFoundException {
        Set<Character> chars = new TreeSet<>();
        Scanner input = new Scanner(new File(path));

        while (input.hasNextLine()) {
            String line = input.nextLine();

            for (int i = 0; i < line.length(); i++) {
                chars.add(line.charAt(i));
            }

            chars.add('\n');
        }

        input.close();

        Character[] charArray = chars.toArray(new Character[0]);

        for (int i = 0; i < charArray.length; i++) {
            char ch = charArray[i];
            if (ch == '\n') {
                System.out.print("'\\n'" + ": " + (int) ch + " ");
            } else if (ch == '\t') {
                System.out.print("'\\t'" + ": " + (int) ch + " ");
            } else if (ch == ' ') {
                System.out.print("' '" + ": " + (int) ch + " ");
            } else {
                System.out.print(ch + ": " + (int) ch + " ");
            }
        }

        System.out.println();
        return chars.size();
    }
}