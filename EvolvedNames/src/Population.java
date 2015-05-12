import java.util.List;

public class Population {

	
	
	public List<Genome> genomePopulation;

	void Population(Integer numGenomes, Double mutationRate) {

		for (int i = 0; i < numGenomes; i++) {
			genomePopulation.add(new Genome(mutationRate));
		}

	}

	public void day() {

	}

}
