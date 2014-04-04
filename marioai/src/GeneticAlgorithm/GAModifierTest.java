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
		
	}
}
