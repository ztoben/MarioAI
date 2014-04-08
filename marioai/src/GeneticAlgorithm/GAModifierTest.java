package GeneticAlgorithm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GAModifierTest {
	Population population = GAModifier.createFirstGeneration(200, 18, 10 );
	Population best = GAModifier.createFirstGeneration(50, 18, 10);
	Chromosome chromo = GAModifier.createRandomChromosome(new int[10], 10);

	@Test
	public void testInitialSize() {
		//Population population = GAModifier.createFirstGeneration(100);
		assertEquals(200, population.getSize());
	}
	@Test
	public void testModifiedSize(){
		population.addToPopulation(chromo);
		assertEquals(201,population.getSize());
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
		Population best = GAModifier.createFirstGeneration(50,18, 10);
		int originalSize = population.getSize();
		GAModifier.breedPopulation(population,best,best.getChromosome(0),10);
		System.out.println(originalSize);
		for (int j = 0; j < originalSize; j++ ){
			population.removeChromosome(j);
		}
		assertEquals(200,population.getSize());
	}
	@Test
	public void testSize2(){
		for (int j = 0; j < 100; j++){
			int originalSize = population.getSize();
			GAModifier.breedPopulation(population,  best,  best.getChromosome(0),10);
			for (int k = 0; k < originalSize-1; k++ ){
				population.removeChromosome(k);
			}
		}
		assertEquals(200,population.getSize());
	}
	@Test
	public void testBestChromo(){
		int[] bestIntArray = population.getChromosome(0).chromosome;
		Chromosome bestChromo = population.getChromosome(0);
		for (int j = 0; j < 250; j++){
			GAModifier.breedPopulation(population, best, bestChromo,10);	
		}
		bestChromo.chromosomeToString();
		System.out.println();
		for (int i = 0; i < bestIntArray.length; i++){
			System.out.print(bestIntArray[i] + ", ");
		}
		assertEquals(bestChromo.chromosome,bestIntArray);
	}
}
