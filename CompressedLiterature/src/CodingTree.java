import java.util.Map;

/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 3 - Compressed Literature
 * Population.java
 * 
 */

/**
 * Compresses literature by using Huffman's coding algorithm.
 * 
 * @author Duy Huynh
 * @version 17 May 2015
 *
 */
public class CodingTree {

    // Global fields:

    /** Map of characters in binary 1's, 0's created from compressed text. */
    public Map<Character, String> codes;

    /** Encoded message after compressing with Huffman codes. */
    String bits;

    /**
     * Constructor that takes a string to be compressed.
     * 
     * @param message Text to be compressed.
     */
    public CodingTree(String message) {

    }

    // Optional: take output of Huffman's encoding and produce original text
    public String decode(String bits, Map<Character, String> codes) {
        return null;
    }

}
