/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 4 - Compressed Literature 2
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
 * @version 6/1/2015
 *
 */
public class CodingTree {

    // Global fields:

    /** Bucket count for my hash table. */
    private static final int BUCKETS = 32678;

    /** Map of words in binary 1's, 0's created from compressed text. */
    public MyHashTable<String, String> codes;

    /** Priority queue, holding list of Nodes for Huffman algorithm. */
    public PriorityQueue<Node> myQueue;

    /** Encoded message after compressing with Huffman codes. */
    public String bits;

    private Node finalHuffman;

    /**
     * Constructor that takes a string to be compressed.
     * 
     * @param message Text to be compressed.
     */
    public CodingTree(final String message) {

        // Count the frequency of each word in text file:
        final WordFrequencyCounter wordFreqCounter = new WordFrequencyCounter(message);

        // Implement Java's PriorityQueue, consisting of Nodes that contain char and freq
        myQueue = new PriorityQueue<Node>();

        // For each unique word found from file, create a Node and insert into priority q.
        // Note*: Node has to implement Comparable in order for PriorityQueue to sort
        // the nodes with lowest freq being highest priority (first one to be polled).
        for (String s : wordFreqCounter.wordFreq.keySet()) {
            myQueue.offer(new Node(s, wordFreqCounter.wordFreq.get(s)));
        }

        // Build Huffman Coding Tree out of the nodes.
        buildHuffmanTree();

        // Create the codes for each word:
        codes = new MyHashTable<String, String>(BUCKETS);
        createCode(myQueue.poll(), "");

        // Create encoded text with Huffman codes
        bits = encode(message);

    }

    /**
     * Builds Huffman Coding Tree from the Nodes given by a PriorityQueue.
     */
    public void buildHuffmanTree() {

        // Merge singular word trees until only one Node remains in priority queue
        while (myQueue.size() > 1) {
            final Node left = myQueue.poll();
            final Node right = myQueue.poll();
            final Node parent = new Node(null, left.myFreq + right.myFreq, left, right);
            myQueue.offer(parent);
        }
        finalHuffman = myQueue.peek();

    }

    /**
     * Recursively create Huffman code for each leaf of the Node (leaf is a word).
     * 
     * @param encodeNode Node to be examined in recursion.
     * @param code String of Huffman code.
     */
    public void createCode(final Node encodeNode, final String code) {

        // Base case: We hit leaf (single word) so add the character + builtup code to map
        if (encodeNode.isLeaf()) {
            codes.put(encodeNode.myString, code);
        } else {
            // Recurse down the left and right childs, adding 0 for left, 1 for right.
            createCode(encodeNode.leftChild, code + "0");
            createCode(encodeNode.rightChild, code + "1");
        }

    }

    /**
     * Takes a String and encodes it to a new String, using Huffman codes.
     * 
     * @param stringToEncode String to compress.
     * @return Encoded String.
     */
    public String encode(final String stringToEncode) {

        final StringBuilder encodedString = new StringBuilder();
        StringBuilder newWord = new StringBuilder();

        for (int i = 0; i < stringToEncode.length(); i++) {

            // Grab current character of message
            char c = stringToEncode.charAt(i);

            // Append valid characters to new word
            if (('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')
                    || ('a' <= c && c <= 'z') || c == '\'' || c == '-') {
                newWord.append(c);
            } else {
                if (newWord.length() > 0) {
                    encodedString.append(codes.get(newWord.toString()));
                    encodedString.append(codes.get(Character.toString(c)));
                    newWord = new StringBuilder();
                }
                else {
                    encodedString.append(codes.get(Character.toString(c)));
                }
            }

        }
        return encodedString.toString();
    }

    // Optional: take output of Huffman's encoding and produce original text
    /**
     * Decode the compressed String to the original text.
     * 
     * @param bits The encoded String to be decompressed.
     * @param codes The mapping of Character to Huffman codes.
     * @return Uncompressed String (original text).
     */
    public String decode(final String bits) {

        StringBuilder decodedMessage = new StringBuilder();
        Node decodingHuffman = finalHuffman;

        for (int i = 0; i < bits.length(); i++) {
            if (decodingHuffman.isLeaf()) {
                decodedMessage.append(decodingHuffman.myString);
                decodingHuffman = finalHuffman;
                continue;
            } else if (bits.charAt(i) == '0') {
                decodingHuffman = decodingHuffman.leftChild;
            } else if (bits.charAt(i) == '1') {
                decodingHuffman = decodingHuffman.rightChild;
            }

        }

        // Need MyHashTable to switch key and value pair
        return decodedMessage.toString();

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
        private String myString;

        /** Character frequency count. */
        private int myFreq;

        /**
         * Create a Node containing a character and frequency.
         * 
         * @param s The Node's character.
         * @param freq The Node's character frequency.
         */
        private Node(final String s, final int freq) {
            leftChild = null;
            rightChild = null;
            myString = s;
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
        private Node(final String s, final int freq, final Node left, final Node right) {
            leftChild = left;
            rightChild = right;
            myString = s;
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
            return "String: " + myString + " Freq: " + myFreq;
        }

    }

    /**
     * Given a string, counts the frequency of each character.
     * 
     * @author Duy Huynh
     * @version 25 May 2015
     */
    private final class WordFrequencyCounter {

        /** Map of Word and its frequency. */
        private Map<String, Integer> wordFreq;

        /**
         * Counts frequency of words from a String.
         * 
         * @param string String to count word frequency from.
         */
        private WordFrequencyCounter(final String string) {

            StringBuilder newWord = new StringBuilder();
            wordFreq = new HashMap<String, Integer>();

            for (int i = 0; i < string.length(); i++) {

                // Grab current character of message
                char c = string.charAt(i);

                // Append valid characters to new word
                if (('A' <= c && c <= 'Z') || ('0' <= c && c <= '9')
                        || ('a' <= c && c <= 'z') || c == '\'' || c == '-') {
                    newWord.append(c);

                    // If not valid, then we stop and insert the word into hashtable
                } else {
                    String key = newWord.toString();
                    if (key.length() > 0) {
                        // Check if word is already in hashtable
                        if (wordFreq.containsKey(key)) {
                            // If word already in table, increment count
                            wordFreq.put(key, wordFreq.get(key) + 1);
                            // Otherwise add the word and set freq to 1
                        } else {
                            wordFreq.put(key, 1);
                        }
                        newWord = new StringBuilder();
                    }
                    // Add the invalid character, insert into table
                    String seperator = Character.toString(c);
                    if (wordFreq.containsKey(seperator)) {
                        wordFreq.put(seperator, wordFreq.get(seperator) + 1);
                    } else {
                        wordFreq.put(seperator, 1);
                    }

                }
            }

        }

    }

}
