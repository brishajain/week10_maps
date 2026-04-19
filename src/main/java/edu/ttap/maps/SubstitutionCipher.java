package edu.ttap.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * A substitution cipher is a simple encryption scheme that associates each
 * letter of the alphabet with a different letter.
 */
public class SubstitutionCipher {
    /**
     * Creates a substitution cipher by reading a mapping of characters from the given
     * file. Each mapping of the file should be of the form "a b", where 'a' is mapped to
     * 'b' in the cipher. We require 
     * @param filename the name of the file containing the mapping
     * @return the cipher as a mapping between characters
     */
    public static Map<Character, Character> createCipher(String filename) throws FileNotFoundException
    {
        Map<Character, Character> encodings = new HashMap<>();

        Scanner scanner = new Scanner(new File(filename));
        while (scanner.hasNextLine()) {
                String keyStr = scanner.next();    
                String valStr = scanner.next();    

                char key = keyStr.charAt(0);
                char value = valStr.charAt(0);

                encodings.put(key, value);
    }
    scanner.close();
    return encodings;
        }

    /**
     * Determines whether the given mapping is a valid substitution cipher. A cipher is
     * valid if (a) it maps every letter of the alphabet (a–z) and (b) it is a bijection,
     * i.e., no two letters map to the same letter (so that we can roundtrip encode/decode
     * a message without loss of fidelity).
     * @param cipher
     * @return true iff the given mapping is a valid substitution cipher
     */
    public static boolean isValidCipher(Map<Character, Character> cipher) {
        if (cipher.size() != 26) {
            return false;
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (!cipher.containsKey(ch)) {
                return false;
            }

            char mapped = cipher.get(ch);
            if (mapped < 'a' || mapped > 'z') {
                return false;
            }
        }

        for (char c1 = 'a'; c1 <= 'z'; c1++) {
            for (char c2 = (char) (c1 + 1); c2 <= 'z'; c2++) {
                if (cipher.get(c1).equals(cipher.get(c2))) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Given a valid substitution cipher, produces the inverse mapping, which
     * can be used to decode the encoded massage. For example, if the cipher
     * maps 'a' to 'b', then the inverse mapping should map 'b' to 'a'.
     * @param cipher the cipher to invert
     * @return the inverse mapping of the given cipher
     */
    public static Map<Character, Character> invertCipher(Map<Character, Character> cipher) {
        Map<Character, Character> inverse = new AssociationList<>();

        for (char ch = 'a'; ch <= 'z'; ch++) {
            inverse.put(cipher.get(ch), ch);
        }
        return inverse;
    }

    /**
     * Translates the given string using the provided mapping.
     * @param s the string to translate
     * @param mapping the mapping to use
     * @return the translated string
     */
    public static String translate(String s, Map<Character, Character> mapping) {
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (mapping.containsKey(ch)) {
                result += mapping.get(ch);
            } else {
                result += ch;
            }
        }
        return result;
    }

    /**
     * The main driver for the substitution cipher program.
     * @param args the driver's command-line arguments
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 3) {
            System.err.println(
                "Usage: java SubstitutionCipher <encode|decode> <cipherfile> <filename>");
            System.exit(1);
        }
        String mode = args[0];
    String cipherFile = args[1];
    String filename = args[2];

    Map<Character, Character> cipher = createCipher(cipherFile);

    if (!isValidCipher(cipher)) {
        System.err.println("Invalid cipher.");
        System.exit(1);
    }

    Map<Character, Character> mapping;

    if (mode.equals("encode")) {
        mapping = cipher;
    } else if (mode.equals("decode")) {
        mapping = invertCipher(cipher);
    } else {
        System.err.println("First argument must be encode or decode.");
        System.exit(1);
        return;
    }

    Scanner scanner = new Scanner(new File(filename));

    while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        System.out.println(translate(line, mapping));
    }

    scanner.close();
    }
}