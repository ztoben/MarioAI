package GeneticAlgorithm;
import java.util.ArrayList;
import java.util.Random;
import java.util.*;


public class GAModifier {
	
	public GAModifier(){}

	public static Chromosome createRandomChromosome(int[] chromosomeToMake){
		Random randy = new Random();
		for (int i = 0; i < chromosomeToMake.length; i++){
			chromosomeToMake[i] = randy.nextInt(9);	
		}
		return new Chromosome(chromosomeToMake);
	}
	
	public static Population createFirstGeneration(int popSize){
		ArrayList<Chromosome> emptyPop = new ArrayList<Chromosome>();
		Population populationOfGeneration = new Population(emptyPop);
		for (int i = 0; i < popSize; i++){
			Chromosome intitalChromo = createRandomChromosome(new int[18]);
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
	
	public static Population breedPopulation(Population newPop, Population bestPopulation, Chromosome bestChromo){
		Random testRandom = new Random();
		newPop.addToPopulation(bestChromo);
		
		int popSize = bestPopulation.getSize();
		int chromosomeSize = bestChromo.chromosome.length;
		if (popSize < 40){
			for (int randomIndex = 0; randomIndex < 60; randomIndex++){
				newPop.addToPopulation(GAModifier.createRandomChromosome(new int[chromosomeSize]));
			}
			for (int z = 0; z< 40;z++){
				int mutateInt = testRandom.nextInt(newPop.getSize());
				Chromosome mutatedChromo = GAModifier.randomMutation(newPop.getChromosome(mutateInt));
				newPop.addToPopulation(mutatedChromo);
			}
		}
		else{
			for (int bestIndex = 0; bestIndex < 59; bestIndex++){
				int firstInt = testRandom.nextInt(bestPopulation.getSize());
				int secondInt = testRandom.nextInt(bestPopulation.getSize());
				Chromosome first = bestPopulation.getChromosome(firstInt);
				Chromosome second = bestPopulation.getChromosome(secondInt);
				Chromosome child = GAModifier.breedChromosome(first, second);
				newPop.addToPopulation(child);
			}
			for (int mutateIndex = 0; mutateIndex < 40; mutateIndex++){
				int mutateInt = testRandom.nextInt(bestPopulation.getSize());
				Chromosome mutatedChromo = GAModifier.randomMutation(bestPopulation.getChromosome(mutateInt));
				newPop.addToPopulation(mutatedChromo);
			}			
		}
		for (int j = 0; j < 60;j++){
			int firstInt = testRandom.nextInt(newPop.getSize());
			int secondInt = testRandom.nextInt(newPop.getSize());
			Chromosome first = newPop.getChromosome(firstInt);
			Chromosome second = newPop.getChromosome(secondInt);
			Chromosome child = GAModifier.breedChromosome(first, second);
			newPop.addToPopulation(child);
		}	
		
		for (int r = 0; r < 40; r++){
			Chromosome modified = GAModifier.randomMutation(bestChromo);
			newPop.addToPopulation(modified);
			
		}
	Set<Chromosome> set = new HashSet<Chromosome>(newPop.population);
	newPop.population = new ArrayList<Chromosome>(set);
	
	while (newPop.getSize() < 200){
		newPop.addToPopulation(GAModifier.createRandomChromosome(new int[chromosomeSize]));
	}
	return newPop;
	}
}
