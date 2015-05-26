/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 3 - Compressed Literature
 * Main.java
 * 
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Main program that takes in a text file and performs Huffman coding
 * algorithm, compresses the text file, and outputs the compressed
 * text file along with the Huffman codes in a seperate file.
 * 
 * @author Duy Huynh
 * @version 20 May 2015
 *
 */
public class Main {

    // To test other text files, remove comments from one line and comment the others:
    /** Name of text file to compress. */
    private static final String TEXT_FILE = "src/WarAndPeace.txt";
    // private static final String TEXT_FILE = "src/The Adventures of Tom Sawyer.txt";
    // private static final String TEXT_FILE = "src/peskyNewLines.txt";

    /** Name of file containing Huffman codes. */
    private static final String CODES_FILE = "src/codes.txt";

    /** Name of file containing Huffman codes. */
    private static final String DECODED_FILE = "src/decoded.txt";

    /** Name of the compressed text file. */
    private static final String BINARY_FILE = "src/compressed.txt";

    /** For converting double to percent. */
    private static final int PERCENT = 100;

    /** Number of bits in a byte, for conversion. */
    private static final int BYTE = 8;

    /**
     * Main program that takes a text file, uses Huffman coding to output compressed file.
     * 
     * @param args Command line arguments
     */
    public static void main(final String[] args) {

        // Start timer:
        final long startTime = System.currentTimeMillis();

        // Convert a piece of text and insert into CodingTree
        // To test other files, remove comments in "Constants" section above
        final CodingTree codingTree = new CodingTree(convertTextToString(TEXT_FILE,
                Charset.defaultCharset()));

        // Create codes file
        createCodesFile(codingTree.codes);

        // Create compressed binary file:
        createBinaryFile(codingTree.bits);

        // Decode compressed message (uncomment to invoke):
        // createDecodedFile(codingTree);

        // End timer:
        final long endTime = System.currentTimeMillis();

        // Display statistics:
        final long uncompressedSize = new File(TEXT_FILE).length();
        final long compressedSize = new File(BINARY_FILE).length();
        System.out.println((endTime - startTime) + "ms");
        System.out.println("Uncompressed: " + uncompressedSize + " bytes");
        System.out.println("Compressed: " + compressedSize + " bytes");
        System.out.printf("Compression ratio: %.2f%%\n", (double) compressedSize
                / uncompressedSize * PERCENT);

    }

    /**
     * Takes a text file and converts it into a readable String for manipulation.
     * Adapted from http://stackoverflow.com/a/326440/3354122
     * 
     * @param textFile Original text file to be transferred to String.
     * @param charSet Character encoding set.
     * @return The text file converted into a String.
     */
    private static String convertTextToString(final String textFile, Charset charSet) {

        // Note**: Don't use Scanner because it eats away at the carriage returns...
        byte[] encoded = null;
        try {
            encoded = Files.readAllBytes(Paths.get(textFile));
        } catch (final IOException e) {
            e.printStackTrace();
        }
        return new String(encoded, charSet);
    }

    /**
     * Creates a file containing characters from a text and their respective Huffman code.
     * 
     * @param codes Map of Characters and their Huffman code.
     */
    private static void createCodesFile(final Map<Character, String> codes) {

        try {
            final FileWriter writer = new FileWriter(CODES_FILE);
            writer.write(codes.toString());
            writer.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes binary file output of the encoded String.
     * 
     * @param encodedString The encoded String in 'binary' 1's and 0's for file output.
     */
    private static void createBinaryFile(final String encodedString) {
        try {

            // Note to future self: Don't use FileOutputStream; it's SLOW!!! (4seconds)
            final ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream(BINARY_FILE));

            // Fix missing 0's at end so there is 8 bits with each write.
            final StringBuilder fixedString = new StringBuilder(encodedString);
            while (fixedString.length() % BYTE != 0) {
                fixedString.append('0');
            }

            // Write to binary file, 8 'bits' at a time.
            for (int i = 0; i < encodedString.length(); i += BYTE) {
                output.write(Integer.parseInt(fixedString.substring(i, i + BYTE), 2));
            }
            output.close();

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Decodes a compress String to output file.
     * 
     * @param codingTree The CodingTree used to compress original file to be decoded.
     */
    private static void createDecodedFile(final CodingTree codingTree) {
        final String decodedString = codingTree.decode(codingTree.bits, codingTree.codes);
        try {
            final FileWriter writer = new FileWriter(DECODED_FILE);
            writer.write(decodedString);
            writer.close();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
