import java.util.List;
import java.util.Random;

public class Genome {

    private String word;
    private List<Character> charList;
    private double mutationRate;

    private char[] phenotypes = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '_', '-', '\'', ' ' };

    public Genome(double mutationRate) {

        word = "A";
        this.mutationRate = mutationRate;
    }

    public Genome(Genome gene) {

    }

    public void mutate() {

    }

    public void crossover(Genome other) {

    }

    /**
     * Determines the fitness of string, according to Wagner-Fischer algorithm.
     * 
     * @return Integer of fitness for current string.
     */
    public Integer fitness() {

        // Length of current string
        int testLength = word.length();

        // Length of target string
        int targetLength = Population.target.length();

        // Initialize 2D array of (testString + 1) x (targetString + 1)
        int fit2D[][] = new int[testLength + 1][targetLength + 1];

        // Create column indices
        for (int i = 1; i <= targetLength + 1; i++) {
            fit2D[0][i] = i;
        }

        // Create row indices
        for (int i = 1; i <= testLength + 1; i++) {
            fit2D[i][0] = i;
        }

        // Wagner-Fischer algorithm for calculating Levenshtein edit distance:
        for (int row = 1; row <= testLength + 1; row++) {
            for (int col = 1; col <= targetLength + 1; col++) {
                if (word.charAt(row - 1) == Population.target.charAt(row - 1)) {
                    fit2D[row][col] = fit2D[row - 1][col - 1];
                } else {
                    fit2D[row][col] = Math.min(fit2D[row - 1][col] + 1,
                            fit2D[row][col - 1] + 1);
                    fit2D[row][col] = Math.min(fit2D[row][col],
                            fit2D[row - 1][col - 1] + 1);
                }
            }
        }

        return fit2D[testLength][targetLength]
                + (Math.abs(testLength - targetLength) + 1) / 2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"(" + word + "\", " + fitness() + ")");
        return sb.toString();

    }
}
