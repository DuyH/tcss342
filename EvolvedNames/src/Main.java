import java.util.Random;

public class Main {

    private static int POP_SIZE = 100;
    private static double MUTATION_RATE = 0.05;

    public static void main(String[] args) {

        Population pool = new Population(POP_SIZE, MUTATION_RATE);
        int day = 0;
        while (pool.mostFit.fitness() > 0) {
            pool.day();
            System.out.println("Day " + day++ + ": " + pool.mostFit.toString());
        }

    }

    public void testGenome() {

    }

    public void testPopulation() {

    }
}
