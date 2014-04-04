package GeneticAlgorithm;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GAModifierTest {
	Population population = GAModifier.createFirstGeneration(100);
	Chromosome chromo = GAModifier.createRandomChromosome(new int[10]);

	@Test
	public void testInitialSize() {
		//Population population = GAModifier.createFirstGeneration(100);
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
		Population pop = GAModifier.createFirstGeneration(200);
		Population best = GAModifier.createFirstGeneration(50);
		int originalSize = pop.getSize();
		GAModifier.breedPopulation(pop,best,best.getChromosome(0));
		System.out.println(originalSize);
		for (int j = 0; j < originalSize; j++ ){
			pop.removeChromosome(j);
		}
		assertEquals(201,pop.getSize());
	}
}
