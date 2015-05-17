/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 2 - Evolved Names
 * Population.java
 * 
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * A Population of Genomes.
 * 
 * @author Duy Huynh
 * @version 5 May 2015
 *
 */
public class Population {

    /** Target string for Genomes. */
    public static String target = "DUY DO THANH HUYNH";

    /** Most fit Genome (0 is most fit). */
    public Genome mostFit;

    /** Collection of Genomes. */
    public List<Genome> genomePopulation;

    /** Random generator. */
    private Random random = new Random();

    /**
     * A collection of Genomes.
     * 
     * @param numGenomes Total number of Genomes in the population.
     * @param mutationRate The mutation rate.
     */
    public Population(final Integer numGenomes, final Double mutationRate) {

        // Fill and initialize genome population with numGenomes
        genomePopulation = new ArrayList<Genome>();
        for (int i = 0; i < numGenomes; i++) {
            genomePopulation.add(new Genome(mutationRate));
        }
        mostFit = genomePopulation.get(0); // initialize most fit

    }

    /**
     * A day consists of sorting the Genomes by fitness, removing least-fit members,
     * replenishing the population by mutating or crossover survivors.
     */
    public void day() {

        // consider a sorting algorithm here to sort the array of genomes

        // 1. Update most-fit Genome in the population (lowest fitness)
        // a. We'll be able to see the most-fit if we had ordered
        sort(genomePopulation);
        mostFit = genomePopulation.get(0);

        // 2. Delete least fit half of population
        // a. We'll be able to know this with a sorted list.
        final int popHalf = genomePopulation.size() / 2;
        for (int i = genomePopulation.size() - 1; i >= popHalf; i--) {
            genomePopulation.remove(i);
        }

        // 3. Create new genomes, using remaining, to restore population
        // a. Randomnly pick a genome, clone, mutate
        // b. Randomly pick a genome, clone, crossover, mutate

        final int survivors = genomePopulation.size();

        for (int i = 0; i < survivors; i++) {
            // Pick a survivor at random and clone it
            final Genome cloned = new Genome(genomePopulation.get(random
                    .nextInt(survivors)));

            // Then either mutate or crossover+mutate
            if (random.nextBoolean()) {
                // Mutate survivor and add
                cloned.mutate();
                genomePopulation.add(cloned);
            } else {
                // Crossover two random remaining Genomes and add to population
                final Genome another = new Genome(genomePopulation.get(random
                        .nextInt(survivors)));
                cloned.crossover(another);
                genomePopulation.add(new Genome(cloned));
            }
        }

    }

    /**
     * Sort the Genomes in the population.
     * 
     * @param genomePopulation Population to sort.
     */
    public void sort(List<Genome> genomePopulation) {

        // Bubble swap sorting:
        boolean noSwaps = false;

        for (int i = 0; i < genomePopulation.size() && !noSwaps; i++) {
            noSwaps = true;
            for (int j = 0; j < genomePopulation.size() - i - 1; j++) {
                if (genomePopulation.get(j).fitness() > genomePopulation.get(
                        j + 1).fitness()) {
                    Collections.swap(genomePopulation, j, j + 1);
                    noSwaps = false;
                }
            }
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sort(genomePopulation);
        for (int i = 0; i < genomePopulation.size(); i++) {
            sb.append(genomePopulation.get(i).toString() + " | ");
        }
        return sb.toString();
    }

}
