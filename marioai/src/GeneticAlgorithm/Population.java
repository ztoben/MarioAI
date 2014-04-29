package GeneticAlgorithm;

/*
 * File: Population.java
 * Author: Trent Morris, Zach Toben
 * Last modified: 4/06/14
 */
import java.util.ArrayList;


public class Population {
	ArrayList<Chromosome> population;
	
	public Population(ArrayList<Chromosome> populationVar){
		population = populationVar;
	}
	
	public void addToPopulation(Chromosome chromoToAdd){
		population.add(chromoToAdd);
			
	}
	public int getSize(){
		return population.size();
	}
	public Chromosome getChromosome(int chromoToGrab){
		return this.population.get(chromoToGrab);
	}
	public Chromosome removeChromosome(int chromoToGrab){
		return this.population.remove(chromoToGrab);
	}
	
	
	public void printPopulation(){
		for (int i = 0; i < population.size();i++){
			population.get(i).chromosomeToString();
			System.out.println();
		}
	}
}