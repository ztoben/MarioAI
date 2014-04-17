package GeneticAlgorithm;

public class Chromosome {
	public float[] chromosome;
	
	public Chromosome(float[] child){
		chromosome = child;
	}

	public void chromosomeToString(){
		for (int i = 0; i < chromosome.length;i++){
			System.out.print(chromosome[i] + ", ");
		}
	}

}


