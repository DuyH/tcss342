/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 3 - Compressed Literature
 * CodingTree.java
 * 
 */

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

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

    /** Priority queue, holding list of Nodes for Huffman algorithm. */
    public PriorityQueue<Node> myQueue;

    /** Encoded message after compressing with Huffman codes. */
    public String bits;

    /**
     * Constructor that takes a string to be compressed.
     * 
     * @param message Text to be compressed.
     */
    public CodingTree(final String message) {

        // Count the frequency of each char in text file:
        final CharFrequencyCounter charFreqCounter = new CharFrequencyCounter(message);

        // Implement Java's PriorityQueue, consisting of Nodes that contain char and freq
        myQueue = new PriorityQueue<Node>();

        // For each unique char found from file, create a Node and insert into priority q.
        // Note*: Node has to implement Comparable in order for PriorityQueue to sort
        // the nodes with lowest freq being highest priority (first one to be polled).
        for (Character c : charFreqCounter.charFrequency.keySet()) {
            myQueue.offer(new Node(c, charFreqCounter.charFrequency.get(c)));
        }

        // Build Huffman Coding Tree out of the nodes.
        buildHuffmanTree();

        // Create the codes for each character:
        codes = new HashMap<Character, String>();
        createCode(myQueue.poll(), "");

        // Create encoded text with Huffman codes
        bits = encode(message);

    }

    /**
     * Builds Huffman Coding Tree from the Nodes given by a PriorityQueue.
     */
    public void buildHuffmanTree() {

        // Merge singular char trees until only one Node remains in priority queue
        while (myQueue.size() > 1) {
            final Node left = myQueue.poll();
            final Node right = myQueue.poll();
            final Node parent = new Node('\0', left.myFreq + right.myFreq, left, right);
            myQueue.offer(parent);
        }
    }

    /**
     * Recursively create Huffman code for each leaf of the Node (leaf is a character).
     * 
     * @param encodeNode Node to be examined in recursion.
     * @param code String of Huffman code.
     */
    public void createCode(final Node encodeNode, final String code) {

        // Base case: We hit leaf (character) so add the character + builtup code to map
        if (encodeNode.isLeaf()) {
            codes.put(encodeNode.myChar, code);
        } else {
            // Recurse down the left and right childs, adding 0 for left, 1 for right.
            createCode(encodeNode.leftChild, code + "0");
            createCode(encodeNode.rightChild, code + "1");
        }

    }

    public String encode(String stringToEncode) {

        final StringBuilder encodedString = new StringBuilder();

        for (int i = 0; i < stringToEncode.length(); i++) {
            encodedString.append(codes.get(stringToEncode.charAt(i)));
        }
        return encodedString.toString();
    }

    // Optional: take output of Huffman's encoding and produce original text
    public String decode(String bits, Map<Character, String> codes) {

        final Map<String, Character> reversedMap = new HashMap<>();
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            reversedMap.put(entry.getValue(), entry.getKey());
        }

        final StringBuilder temp = new StringBuilder();

        int j = 0;
        for (int i = 0; i < bits.length(); i++) {
            for (String s : codes.values()) {
                if (bits.substring(j, i).equals(s)) {
                    if (reversedMap.get(s) == '\n' || reversedMap.get(s) == '\r') {
                        temp.append("\r\n");
                    } else {
                        temp.append(reversedMap.get(s));
                    }
                    j = i;
                }
            }
        }
        return temp.toString();

    }

    /**
     * A node that consists of a char, the frequency of that char, left and right Node.
     */
    @SuppressWarnings("rawtypes")
    private final class Node implements Comparable {

        /** Node's left child node. */
        private Node leftChild;

        /** Node's right child node. */
        private Node rightChild;

        /** Node's character. */
        private char myChar;

        /** Character frequency count. */
        private int myFreq;

        /**
         * Create a Node containing a character and frequency.
         * 
         * @param c The Node's character.
         * @param freq The Node's character frequency.
         */
        private Node(final char c, final int freq) {
            leftChild = null;
            rightChild = null;
            myChar = c;
            myFreq = freq;
        }

        /**
         * Create a Node containing a character, freq, and child nodes.
         * 
         * @param c The Node's character.
         * @param freq The Node's character frequency.
         * @param left The Node's left child.
         * @param right The Node's right child.
         */
        private Node(final char c, final int freq, final Node left, final Node right) {
            leftChild = left;
            rightChild = right;
            myChar = c;
            myFreq = freq;
        }

        /**
         * Returns whether this Node is a leaf (a Node with no children).
         * 
         * @return Whether this Node is a leaf.
         */
        private boolean isLeaf() {
            return leftChild == null && rightChild == null;
        }

        /**
         * Compares the frequencies of this node against the other.
         * 
         * @return -1 if this Node has smaller freq, 1 if larger, 0 if equal.
         * @param other The other node to compare frequences against.
         */
        @Override
        public int compareTo(final Object other) {

            final Node otherNode = (Node) other;

            // Return -1 if other node's freq is larger
            if (this.myFreq < otherNode.myFreq) {
                return -1;
                // Return 1 if other node's freq is smaller
            } else if (this.myFreq > otherNode.myFreq) {
                return 1;
            }
            // Or else return 0 if they are the same
            return 0;
        }

        @Override
        public String toString() {
            return "Char: " + myChar + " Freq: " + myFreq;
        }

    }

    /**
     * Given a string, counts the frequency of each character.
     * 
     * @author Duy Huynh
     * @version 25 May 2015
     */
    private final class CharFrequencyCounter {

        /** Map of Character and its frequency. */
        private Map<Character, Integer> charFrequency;

        /**
         * Counts frequency of characters from a String.
         * 
         * @param string String to count character frequency from.
         */
        private CharFrequencyCounter(final String string) {

            charFrequency = new HashMap<Character, Integer>();

            int i = 0;
            while (i < string.length()) {
                final char c = string.charAt(i);
                if (charFrequency.containsKey(c)) {
                    charFrequency.put(c, charFrequency.get(c) + 1);
                } else {
                    charFrequency.put(c, 1);
                }
                i++;
            }
        }

    }

}
