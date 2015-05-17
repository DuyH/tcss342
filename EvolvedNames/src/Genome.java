/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 2 - Evolved Names
 * Genome.java
 * 
 */
import java.util.Random;

/**
 * Represents a "Genome" (a string) that has fitness and can be mutated.
 * 
 * @author Duy Huynh
 * @version 5 May 2015
 *
 */
public class Genome {

    /** This Genome's string. */
    private StringBuilder word;

    /** Mutation rate. */
    private double mutationRate;

    /** Random generator. */
    private Random random = new Random();

    /** Array of chars to choose from when adding to Genome. */
    private char[] phenotypes = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '_', '-', '\'', ' ' };

    /**
     * Initialize a Genome with "A" and mutation rate.
     * 
     * @param mutationRate Mutation Rate for this Genome.
     */
    public Genome(final double mutationRate) {

        word = new StringBuilder("A");
        this.mutationRate = mutationRate;
    }

    /**
     * Copy constructor; Initialize new Genome with string and mutation rate.
     * 
     * @param gene Genome to be copied.
     */
    public Genome(final Genome gene) {
        word = new StringBuilder(gene.getWord());
        mutationRate = gene.getMutationRate();
    }

    /**
     * Mutate string with mutation rate chance by adding, deleting, or changing
     * characters in the string.
     */
    public void mutate() {

        // Randomly add char to random position
        if (random.nextDouble() < mutationRate) {
            final int randomSpot = random.nextInt(word.length() + 1);
            final int randomChar = random.nextInt(phenotypes.length);
            word.insert(randomSpot, phenotypes[randomChar]);
        }

        // Randomly delete single char, if length > 1
        if (random.nextDouble() < mutationRate && word.length() > 1) {
            word.deleteCharAt(random.nextInt(word.length()));

        }

        // Randomly replace a char
        for (int i = 0; i < word.length(); i++) {
            if (random.nextDouble() < mutationRate) {
                final int randomChar = random.nextInt(phenotypes.length);
                word.setCharAt(i, phenotypes[randomChar]);

            }
        }

    }

    /**
     * Update this Genome by crossing over with another.
     * 
     * @param other Other Genome to be crossed with.
     */
    public void crossover(final Genome other) {

        // Temporary StringBuilder for the "child" Genome.
        final StringBuilder child = new StringBuilder();
        final int childLength = Math.min(word.length(), other.getWord().length());

        // Create the "child" Genome by randomly selecting a char from the parents.
        for (int i = 0; i < childLength; i++) {
            if (random.nextBoolean()) {
                child.append(word.charAt(i));
            } else {
                child.append(other.getWord().charAt(i));
            }
        }
        word = child;

    }

    /**
     * Determines the fitness of string, according to Wagner-Fischer algorithm.
     * 
     * @return Integer of fitness for current string.
     */
    public Integer fitness() {

        // Regular algorithm from Assignment 2 handout
        // Note: Takes more days but quicker computation(?)
        /*
         * int n = word.length();
         * int m = Population.target.length();
         * int l = Math.max(n, m);
         * int f = Math.abs(m - n);
         * for (int i = 1; i <= l; i++) {
         * 
         * if (i <= Math.min(m, n)) {
         * 
         * if (word.charAt(i - 1) != Population.target.charAt(i - 1)) {
         * f++;
         * }
         * }
         * }
         * return f;
         */

        // Wagner-Fisher Algorithm for calculating Levenshtein edit distance
        // Adapted from Assignment 2 handout; takes less days than regular
        // algorithm but takes longer to compute(?)

        /*
         * // Length of current string
         * int n = word.length();
         * 
         * // Length of target string
         * int m = Population.target.length();
         * 
         * // Initialize 2D array of (testString + 1) x (targetString + 1)
         * int fit2D[][] = new int[n + 1][m + 1];
         * 
         * // Create row indices
         * for (int i = 0; i <= n; i++) {
         * fit2D[i][0] = i;
         * }
         * 
         * // Create column indices
         * for (int i = 0; i <= m; i++) {
         * fit2D[0][i] = i;
         * }
         * 
         * // Wagner-Fischer algorithm for calculating Levenshtein edit
         * for (int row = 1; row <= n; row++) {
         * for (int col = 1; col <= m; col++) {
         * 
         * if (m < row) {
         * continue;
         * }
         * Population.target.charAt(row - 1);
         * if (word.charAt(row - 1) == Population.target.charAt(row - 1)) {
         * fit2D[row][col] = fit2D[row - 1][col - 1];
         * } else {
         * fit2D[row][col] = Math.min(fit2D[row - 1][col] + 1,
         * fit2D[row][col - 1] + 1);
         * fit2D[row][col] = Math.min(fit2D[row][col],
         * fit2D[row - 1][col - 1] + 1);
         * }
         * }
         * }
         * return fit2D[n][m] + (Math.abs(n - m) + 1) / 2;
         */

        // Wagner-Fischer algo adapted from www.sanfoundry.com
        // Feels more efficient than algo above.
        final int currentLen = word.length();
        final int targetLen = Population.target.length();
        final int[][] fit2D = new int[currentLen + 1][targetLen + 1];
        for (int i = 0; i <= currentLen; i++) {
            fit2D[i][0] = i;
        }
        for (int i = 1; i <= targetLen; i++) {
            fit2D[0][i] = i;
        }
        for (int i = 1; i <= currentLen; i++) {
            for (int j = 1; j <= targetLen; j++) {
                final int m = (word.charAt(i - 1) == Population.target.charAt(j - 1)) ? 0
                        : 1;
                fit2D[i][j] = Math.min(
                        Math.min(fit2D[i - 1][j] + 1, fit2D[i][j - 1] + 1),
                        fit2D[i - 1][j - 1] + m);
            }
        }
        return fit2D[currentLen][targetLen];

    }

    /**
     * String representation of the Genome.
     * 
     * @return The string representing this Genome.
     */
    public String getWord() {
        return word.toString();
    }

    /**
     * This Genome's mutation rate.
     * 
     * @return Genome's mutation rate.
     */
    public double getMutationRate() {
        return mutationRate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("(\"" + word + "\", " + fitness() + ")");
        return sb.toString();
    }

}
