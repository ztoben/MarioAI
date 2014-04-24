package GeneticAlgorithm;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;


public class GAModifier {

	public GAModifier(){}

	public static Chromosome createRandomChromosome(int inputLayerSize, int hiddenLayerSize, int outputLayerSize ){
		int size = inputLayerSize * 2 * hiddenLayerSize + hiddenLayerSize + outputLayerSize;
		float[] chromosomeToMake = new float[size];
		Random randy = new Random();

		for (int i = 0; i < size; i++){
			if (i <= hiddenLayerSize){
				chromosomeToMake[i] = (float) randy.nextInt(3* inputLayerSize);
			}
			else if (i <= hiddenLayerSize + outputLayerSize){
				chromosomeToMake[i] = (float) (randy.nextInt(hiddenLayerSize * 2) - 20);
			} 
			else if (i <= inputLayerSize + hiddenLayerSize + outputLayerSize)
			{
				chromosomeToMake[i] = (float) (randy.nextInt(50) - 25);
			} 
			else if (i <= inputLayerSize * 2 + hiddenLayerSize + outputLayerSize) 
			{
				chromosomeToMake[i] = randy.nextFloat() * 50 - 25;
			}
			else
			{
				chromosomeToMake[i] = randy.nextFloat();						
			}
		}
		return new Chromosome(chromosomeToMake);
	}

	public static Population createFirstGeneration(int inputLayerSize, int hiddenLayerSize, int outputLayerSize, int popSize){
		ArrayList<Chromosome> emptyPop = new ArrayList<Chromosome>();
		Population populationOfGeneration = new Population(emptyPop);
		for (int i = 0; i < popSize; i++){
			Chromosome intitalChromo = createRandomChromosome(inputLayerSize, hiddenLayerSize, outputLayerSize);
			populationOfGeneration.addToPopulation(intitalChromo);
		}
		return populationOfGeneration;
	}

	public static Chromosome breedChromosome(Chromosome first, Chromosome second,int inputLayerSize, int hiddenLayerSize, int outputLayerSize){
		Random newRandom = new Random();
		int size = inputLayerSize * 2 * hiddenLayerSize + hiddenLayerSize + outputLayerSize;
		float[] breeder =  new float[size];
		for (int i = 0; i < first.chromosome.length;i++){
			boolean testBool = newRandom.nextBoolean();
			if (testBool == true){
				breeder[i] = first.chromosome[i];
			}
			else {
				breeder[i] = second.chromosome[i];
			}			
		}
		return new Chromosome(breeder);	
	}

	public static Chromosome randomMutation(Chromosome mutatedChromo,int inputLayerSize, int hiddenLayerSize, int outputLayerSize){
		Random randy = new Random();
		int lengthChromo = mutatedChromo.chromosome.length;
		float [] mutateCopy = new float[lengthChromo];
		System.arraycopy(mutatedChromo.chromosome, 0, mutateCopy, 0, mutateCopy.length);
		int numberOfMutations = randy.nextInt(lengthChromo/3);

		for (int i = 0; i < numberOfMutations; i++){
			int indexToModify = randy.nextInt(lengthChromo);
			if (indexToModify <= hiddenLayerSize){
				mutateCopy[indexToModify] = (float) randy.nextInt(3* inputLayerSize);
			}
			else if (indexToModify <= inputLayerSize + hiddenLayerSize){
				mutateCopy[indexToModify] = (float) randy.nextInt(hiddenLayerSize);
			}
			else{
				mutateCopy[indexToModify] = randy.nextFloat();						
			}
		}
		return new Chromosome(mutateCopy);

	}

	public static Population breedPopulation(Population newPop, Chromosome bestChromo, int inputLayerSize, int hiddenLayerSize, int outputLayerSize){
		Random testRandom = new Random();
		int originalSize = newPop.getSize();
		newPop.addToPopulation(bestChromo);
		for (int randomIndex = 0; randomIndex < 30; randomIndex++){
			newPop.addToPopulation(GAModifier.createRandomChromosome(inputLayerSize, hiddenLayerSize, outputLayerSize));
		}
		for (int z = 0; z< 20;z++){
			int mutateInt = testRandom.nextInt(newPop.getSize());
			Chromosome mutatedChromo = GAModifier.randomMutation(newPop.getChromosome(mutateInt), inputLayerSize, hiddenLayerSize, outputLayerSize);
			newPop.addToPopulation(mutatedChromo);
		}
		for (int j = 0; j < 29;j++){
			int firstInt = testRandom.nextInt(newPop.getSize());
			int secondInt = testRandom.nextInt(newPop.getSize());
			Chromosome first = newPop.getChromosome(firstInt);
			Chromosome second = newPop.getChromosome(secondInt);
			Chromosome child = GAModifier.breedChromosome(first,second, inputLayerSize, hiddenLayerSize, outputLayerSize);
			newPop.addToPopulation(child);
		}	
		for (int l = 0; l < 10;l++){
			int firstInt = testRandom.nextInt(newPop.getSize());
			Chromosome first = newPop.getChromosome(firstInt);
			Chromosome child = GAModifier.breedChromosome(first,bestChromo, inputLayerSize, hiddenLayerSize, outputLayerSize);
			newPop.addToPopulation(child);
		}
		for (int r = 0; r < 10; r++){
			Chromosome modified = GAModifier.randomMutation(bestChromo, inputLayerSize, hiddenLayerSize, outputLayerSize);
			newPop.addToPopulation(modified);		
		}
		for (int t = 0; t < originalSize; t++){
			newPop.removeChromosome(0);
		}
		Set<Chromosome> set = new HashSet<Chromosome>(newPop.population);
		newPop.population = new ArrayList<Chromosome>(set);
		while (newPop.getSize() < 150){
			newPop.addToPopulation(GAModifier.createRandomChromosome(inputLayerSize, hiddenLayerSize, outputLayerSize));
		}
		return newPop;
	}
}
