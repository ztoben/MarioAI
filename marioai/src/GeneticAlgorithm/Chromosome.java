package GeneticAlgorithm;

public class Chromosome {
	int[] chromosome;
	
	public Chromosome(int[] child){
		chromosome = child;
	}

	public void chromosomeToString(){
		for (int i = 0; i < chromosome.length;i++){
			System.out.print(chromosome[i] + ", ");
		}
	}

}


