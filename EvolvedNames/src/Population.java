import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Population {
    Random random = new Random();

    public static String target = "DUY HUYNH"; // about 70 mutations (sort, del,

    public Genome mostFit;

    public List<Genome> genomePopulation;

    public Population(Integer numGenomes, Double mutationRate) {

        // Fill and initialize genome population with numGenomes
        genomePopulation = new ArrayList<Genome>();
        for (int i = 0; i < numGenomes; i++) {
            genomePopulation.add(new Genome(mutationRate));
        }
        mostFit = genomePopulation.get(0); // initialize most fit

    }

    public void day() {

        // consider a sorting algorithm here to sort the array of genomes

        // 1. Update most-fit Genome in the population (lowest fitness)
        // a. We'll be able to see the most-fit if we had ordered
        sort(genomePopulation);
        mostFit = genomePopulation.get(0);

        // 2. Delete least fit half of population
        // a. We'll be able to know this with a sorted list.
        for (int i = genomePopulation.size() - 1; i >= 50; i--) {
            genomePopulation.remove(i);
        }

        // 3. Create new genomes, using remaining, to restore population
        // a. Randomnly pick a genome, clone, mutate
        // b. Randomly pick a genome, clone, crossover, mutate

        int survivors = genomePopulation.size();

        for (int i = 0; i < survivors; i++) {
            // Clone a survivor
            Genome cloned = new Genome(genomePopulation.get(random
                    .nextInt(survivors)));

            if (random.nextBoolean()) {
                // Mutate survivor and add
                cloned.mutate();
                genomePopulation.add(cloned);
            } else {
                // Crossover two random remaining Genomes and add
                Genome another = new Genome(genomePopulation.get(random
                        .nextInt(survivors)));
                cloned.crossover(another);
                genomePopulation.add(new Genome(cloned));
            }
        }

        // 4.
    }

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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sort(genomePopulation);
        for (int i = 0; i < genomePopulation.size(); i++) {
            sb.append(genomePopulation.get(i).toString() + " | ");
        }
        return sb.toString();
    }

}
