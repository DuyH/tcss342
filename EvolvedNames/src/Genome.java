import java.util.List;

public class Genome {

	private String word;
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

	public Integer fitness() {
		return null;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"(" + word + "\", " + fitness() + ")");
		return sb.toString();

	}
}
