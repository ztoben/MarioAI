package GeneticAlgorithm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GAModifierTest {
	Population population = GAModifier.createFirstGeneration(100, 18, 100 );
	Chromosome chromo = GAModifier.createRandomChromosome(new int[18], 100);

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
		int originalSize = population.getSize();
		System.out.println(originalSize);
		GAModifier.breedPopulation(population,chromo,100);
		//for (int j = 0; j < originalSize; j++ ){
			//population.removeChromosome(0);
		//}
		assertEquals(100,population.getSize());
	}
	@Test //
	public void testSize2(){
		for (int j = 0; j < 100; j++){
			int originalSize = population.getSize();
			GAModifier.breedPopulation(population, chromo,100);

		}
		assertEquals(100,population.getSize());
	}
	@Test
	public void testBestChromo(){
		int[] bestIntArray = population.getChromosome(0).chromosome;
		Chromosome bestChromo = population.getChromosome(0);
		for (int j = 0; j < 250; j++){
			GAModifier.breedPopulation(population, bestChromo,100);	
		}
		bestChromo.chromosomeToString();
		System.out.println();
		for (int i = 0; i < bestIntArray.length; i++){
			System.out.print(bestIntArray[i] + ", ");
		}
		assertEquals(bestChromo.chromosome,bestIntArray);
	}
}
