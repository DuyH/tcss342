/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 2 - Evolved Names
 * Main.java
 * 
 */

/**
 * Main that creates a Population of Genomes and runs
 * a simulation to determine how many days it takes to
 * reach the target String in Population.
 * 
 * @author Duy Huynh
 * @version 5 May 2015
 *
 */
public class Main {

    /** Number of Genomes in this Population. */
    private static final int POP_SIZE = 100;

    /** Mutation rate for this Population. */
    private static final double MUTATION_RATE = 0.05;

    /** How many mutations for testing purposes only. */
    private static final int NUM_MUTATIONS = 1000;

    /**
     * Main creates a Population and runs simulation.
     * 
     * @param args Command line arguments
     */
    public static void main(final String[] args) {

        // Run simulation for a number of days and print out time it took.
        final Population pool = new Population(POP_SIZE, MUTATION_RATE);
        int day = 0;
        final long startTime = System.currentTimeMillis();
        while (pool.mostFit.fitness() > 0) {
            pool.day();
            System.out.println("Day " + day++ + ": " + pool.mostFit.toString());
        }
        final long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) + "ms");

        // TESTING OF GENOME AND POPULATION CLASSES:
        // testGenome();
        // testPopulation();

    }

    /**
     * Test the Genome class.
     */
    public static void testGenome() {

        // Create Genome using constructor that only initializes with "A" and rate:
        final Genome startingGenome = new Genome(0.7);
        System.out.println("Genome: " + startingGenome + ", Mutation Rate: "
                + startingGenome.getMutationRate());

        // Test copy constructor of Genome class:
        final Genome copyGenome = new Genome(startingGenome);
        System.out.println("Copy Genome: " + copyGenome + ", Mutation Rate: "
                + copyGenome.getMutationRate());

        // Test mutation with 1.0 mutation rate chance
        final Genome mutatingGenome = new Genome(0.99);

        // Just test mutate and see what kind of mutations occur
        for (int i = 0; i < NUM_MUTATIONS; i++) {
            System.out.println("Genome: " + mutatingGenome);
            mutatingGenome.mutate();
        }

        // Test Crossover:
        Genome mom = new Genome(0.95);
        Genome dad = new Genome(0.95);

        // "Child" genome should have either only "M" or "D"
        System.out.println("'M' and 'D' genomes, expect only single letter child:");
        mom.setWord("M");
        System.out.println("Mom's genome: " + mom);
        dad.setWord("D");
        System.out.println("Dad's genome: " + dad);
        mom.crossover(dad);
        System.out.println("Child's genome: " + mom);

        // Child genome should only be limited to one char in string in this case:
        System.out.println("'MOM' and 'D' genomes, expect only single letter child:");
        mom.setWord("MOM");
        System.out.println("Mom's genome: " + mom);
        dad.setWord("D");
        System.out.println("Dad's genome: " + dad);
        mom.crossover(dad);
        System.out.println("Child's genome: " + mom);

        // Child genome should be a crossover of "mom" and "dad"
        System.out.println("Variable genomes, expect crossover:");
        mom.setWord("MOMMY DEAREST");
        System.out.println("Mom's genome: " + mom);
        dad.setWord("DUDE DAD");
        System.out.println("Dad's genome: " + dad);
        mom.crossover(dad);
        System.out.println("Child's genome: " + mom);

        // Test fitness:
        final Genome fitnessGenome = new Genome(0.95);
        System.out.println("Genome: " + fitnessGenome);

        // Change Target string to 'A', should result in fitness 0.
        System.out.println("Set target string to 'A', should result in fitness of 0");
        Population.target = "A";
        System.out.println("Genome: " + fitnessGenome);

        // Change Target to 'Z'
        System.out.println("Set target string to 'Z', should result in fitness of 1");
        Population.target = "Z";
        System.out.println("Genome: " + fitnessGenome);

        // Change Target to 'AA'
        System.out.println("Set target string to 'AA', should result in fitness of 1");
        Population.target = "AA";
        System.out.println("Genome: " + fitnessGenome);

        // Change Target to 'AZ'
        System.out.println("Set target string to 'ZZ', should result in fitness of 2");
        Population.target = "ZZ";
        System.out.println("Genome: " + fitnessGenome);

        // Change Genome to APPLE and target to AP PLE
        System.out.println("Set target string to 'AP PLE', and Genome to 'APPLE'");
        System.out.println("Fitness should be 1.");
        Population.target = "AP PLE";
        fitnessGenome.setWord("APPLE");
        System.out.println("Genome: " + fitnessGenome);

    }

    /**
     * Test the Population class.
     */
    public static void testPopulation() {

        // Rest population target in case it was changed from previous testing:
        Population.target = "A";

        // Create population of 10 Genomes and print to console:
        System.out.println("Population of 10; all should be just 'A'.");
        Population testPop = new Population(10, 0.95);
        System.out.println(testPop.toString());

        // Set half of population to 'B'
        System.out.println("Set half of Genome to 'B'");
        testPop.genomePopulation.get(0).setWord("B");
        testPop.genomePopulation.get(1).setWord("B");
        testPop.genomePopulation.get(2).setWord("B");
        testPop.genomePopulation.get(3).setWord("B");
        testPop.genomePopulation.get(4).setWord("B");
        System.out.println(testPop.toString());

        // Run a day on these 10 genomes
        System.out.println("Run one day, should cut in half and repopulate");
        System.out.println("Five of the 'A' genome remain, with others mutated");
        testPop.day();
        System.out.println(testPop.toString());

        // Randomly set Genomes and sort to test sorting
        System.out.println("Change genomes and shuffle order, then sort");
        System.out.println("Before sort:");
        testPop.genomePopulation.get(0).setWord("A WE  AD SF");
        testPop.genomePopulation.get(1).setWord("AS GGGE");
        testPop.genomePopulation.get(2).setWord("A");
        testPop.genomePopulation.get(3).setWord("AA");
        testPop.genomePopulation.get(4).setWord(" A");
        testPop.genomePopulation.get(5).setWord("B-'BA");
        testPop.genomePopulation.get(6).setWord("E-R - H");
        testPop.genomePopulation.get(7).setWord("EE");
        testPop.genomePopulation.get(8).setWord("SER");
        testPop.genomePopulation.get(9).setWord("BB");
        System.out.println(testPop.toString());
        System.out.println("After sort:");
        testPop.sort(testPop.genomePopulation);
        System.out.println(testPop.toString());

    }
}
