package GeneticAlgorithm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GAModifierTest {
	int inputLayerSize = 52;
	int hiddenLayerSize = 20;
	int outputLayerSize = 6;
	int populationSize = 100;
	
	Population population = GAModifier.createFirstGeneration(inputLayerSize, hiddenLayerSize,outputLayerSize, populationSize );
	Chromosome chromo = GAModifier.createRandomChromosome(inputLayerSize, hiddenLayerSize,outputLayerSize);

	@Test
	public void testInitialSize() {
		assertEquals(100, population.getSize());
	}
	@Test
	public void testModifiedSize(){
		population.addToPopulation(chromo);
		assertEquals(101,population.getSize());
	}
	@Test
	public void testGetMethod(){
		assertNotSame(population.getChromosome(0), population.getChromosome(1));
	}
	@Test
	public void testGetMethodTwo(){
		assertEquals(population.getChromosome(0),population.getChromosome(0));
	}
	@Test
	public void testSize(){ 
		
		GAModifier.breedPopulation(population, chromo,inputLayerSize, hiddenLayerSize, outputLayerSize);
		assertEquals(100,population.getSize());
	}
	@Test //
	public void testSize2(){
		for (int j = 0; j < 100; j++){
			int originalSize = population.getSize();
			GAModifier.breedPopulation(population, chromo,inputLayerSize, hiddenLayerSize,outputLayerSize);

		}
		assertEquals(100,population.getSize());
	}
	@Test
	public void testBestChromo(){
		float[] bestIntArray = population.getChromosome(0).chromosome;
		Chromosome bestChromo = population.getChromosome(0);
		for (int j = 0; j < 250; j++){
			GAModifier.breedPopulation(population, bestChromo,inputLayerSize, hiddenLayerSize,outputLayerSize);	
		}
		bestChromo.chromosomeToString();
		System.out.println();
		for (int i = 0; i < bestIntArray.length; i++){
			System.out.print(bestIntArray[i] + ", ");
		}
		assertEquals(bestChromo.chromosome,bestIntArray);
	}
}
