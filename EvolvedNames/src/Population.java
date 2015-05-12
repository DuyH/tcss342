import java.util.List;

public class Population {

    public static String target = "Duy Huynh";

    public Genome mostFit;

    public List<Genome> genomePopulation;

    public Population(Integer numGenomes, Double mutationRate) {

        // Fill and initialize genome population with numGenomes
        for (int i = 0; i < numGenomes; i++) {
            genomePopulation.add(new Genome(mutationRate));
        }

    }

    public void day() {

        // 1. Update most-fit Genome in the population (lowest fitness)

        // 2. Delete least fit half of population

        // 3. Create new genomes, using remaining, to restore population
        // a. Randomnly pick a genome, clone, mutate
        // b. Randomly pick a genome, clone, crossover, mutate

        // 4.
    }

}
