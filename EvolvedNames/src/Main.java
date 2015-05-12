import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Random random = new Random();

        int hit = 0;
        int miss = 0;

        for (int i = 0; i < 100; i++) {
            if (random.nextDouble() <= 0.25) {
                hit++;
            } else {
                miss++;
            }
        }
        System.out.println("Hit: " + hit);
        System.out.println("Miss: " + miss);
    }

    public void testGenome() {

    }

    public void testPopulation() {

    }
}
