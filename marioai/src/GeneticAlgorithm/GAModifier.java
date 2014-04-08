package GeneticAlgorithm;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;


public class GAModifier {
	
	public GAModifier(){}

	public static Chromosome createRandomChromosome(int[] chromosomeToMake, int rangeOfRandom){
		Random randy = new Random();
		for (int i = 0; i < chromosomeToMake.length; i++){
			chromosomeToMake[i] = randy.nextInt(rangeOfRandom);	
		}
		return new Chromosome(chromosomeToMake);
	}
	
	public static Population createFirstGeneration(int popSize, int lengthChromo, int rangeOfRandom){
		ArrayList<Chromosome> emptyPop = new ArrayList<Chromosome>();
		Population populationOfGeneration = new Population(emptyPop);
		for (int i = 0; i < popSize; i++){
			Chromosome intitalChromo = createRandomChromosome(new int[lengthChromo], rangeOfRandom);
			populationOfGeneration.addToPopulation(intitalChromo);
		}
		return populationOfGeneration;
	}
	
	public static Chromosome breedChromosome(Chromosome first, Chromosome second){
		Random newRandom = new Random();
		int[] breeder = new int[first.chromosome.length];
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
	
	public static Chromosome randomMutation(Chromosome mutatedChromo){
		Random newRandom = new Random();
		int lengthChromo = mutatedChromo.chromosome.length;
		int [] mutateCopy = new int[lengthChromo];
		System.arraycopy(mutatedChromo.chromosome, 0, mutateCopy, 0, mutateCopy.length);
		int numberOfMutations = newRandom.nextInt(lengthChromo/3);
		for (int i = 0; i < numberOfMutations; i++){
			int indexToModify = newRandom.nextInt(lengthChromo);
			mutateCopy[indexToModify]= newRandom.nextInt(9);
		}
		return new Chromosome(mutateCopy);
		
	}
	
	public static Population breedPopulation(Population newPop, Chromosome bestChromo, int rangeOfRandom){
		Random testRandom = new Random();
		newPop.addToPopulation(bestChromo);
		
		int chromosomeSize = bestChromo.chromosome.length;
		for (int randomIndex = 0; randomIndex < 30; randomIndex++){
			newPop.addToPopulation(GAModifier.createRandomChromosome(new int[chromosomeSize], rangeOfRandom));
		}
		for (int z = 0; z< 20;z++){
			int mutateInt = testRandom.nextInt(newPop.getSize());
			Chromosome mutatedChromo = GAModifier.randomMutation(newPop.getChromosome(mutateInt));
			newPop.addToPopulation(mutatedChromo);
		}
		for (int j = 0; j < 29;j++){
			int firstInt = testRandom.nextInt(newPop.getSize());
			int secondInt = testRandom.nextInt(newPop.getSize());
			Chromosome first = newPop.getChromosome(firstInt);
			Chromosome second = newPop.getChromosome(secondInt);
			Chromosome child = GAModifier.breedChromosome(first, second);
			newPop.addToPopulation(child);
		}	
		for (int l = 0; l < 10;l++){
			int firstInt = testRandom.nextInt(newPop.getSize());
			Chromosome first = newPop.getChromosome(firstInt);
			Chromosome child = GAModifier.breedChromosome(first, bestChromo);
			newPop.addToPopulation(child);
		}
		for (int r = 0; r < 10; r++){
			Chromosome modified = GAModifier.randomMutation(bestChromo);
			newPop.addToPopulation(modified);		
		}
	Set<Chromosome> set = new HashSet<Chromosome>(newPop.population);
	newPop.population = new ArrayList<Chromosome>(set);
	
	while (newPop.getSize() < 200){
		newPop.addToPopulation(GAModifier.createRandomChromosome(new int[chromosomeSize],rangeOfRandom));
	}
	return newPop;
	}
}
