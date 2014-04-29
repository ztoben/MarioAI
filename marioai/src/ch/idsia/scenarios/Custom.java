/*
 * File: Custom.java
 * Author: Trent Morris, Zach Toben
 * Last modified: 4/25/14
 */

package ch.idsia.scenarios;

import java.io.IOException;

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import marioManiacs.agents.*;
import marioManiacs.agents.neuralNetwork.*;
import GeneticAlgorithm.*;


public class Custom
{
	public static void main(String[] args) throws IOException
	{
		final MarioAIOptions marioAIOptions = new MarioAIOptions(args);
		final ParserAgent agent = new ParserAgent("bob");
		int j = 0;
		int bestScore = 0;
		int newScore = 0;
		int bestCounter = 0;
		int seed = 1;
		Chromosome c;
		int inputLayerSize = 52;
		int hiddenLayerSize = 20;
		int outputLayerSize = 6;
		int populationSize = 150;
		Population p = GAModifier.createFirstGeneration(inputLayerSize, hiddenLayerSize, outputLayerSize, populationSize);
		Chromosome bestChromo = p.getChromosome(0);
		BaseNeuralNetwork base = new FullConnectionNeuralNetwork(52,20);
		base.createConnections();
		while(true)
		{ // Base this off score eventually or just certain number of generations

			if (j != 0)
			{
				GAModifier.breedPopulation(p, bestChromo,inputLayerSize, hiddenLayerSize, outputLayerSize);
			}

			System.out.println("Generation "+ j + " Score - " +bestScore);
			j++;

			for (int i = 0; i < 151; i++)
			{
				if (i != 150)
				{
					marioAIOptions.setVisualization(false);
					c = p.getChromosome(i);
					base.setWeights(c.chromosome);
				}
				else
				{
					base.setWeights(bestChromo.chromosome);
					if (j %10 ==0 && j != 0){
						marioAIOptions.setVisualization(true);
					}
					else {
						//-----z-z-marioAIOptions.setVisualization(false);
					}
					marioAIOptions.setFPS(90);
				}

				agent.setNeuralNetwork(base);

				final BasicTask basicTask = new BasicTask(marioAIOptions);
				marioAIOptions.setLevelDifficulty(1);
				marioAIOptions.setLevelRandSeed(seed);
				//seed++;

				/*			if (bestCounter == 1)
				{
					//marioAIOptions.setVisualization(true);
					base.setWeights(bestChromo.chromosome);
					bestCounter = 0;
					marioAIOptions.setFPS(70);
					bestChromo.chromosomeToString();
					System.out.println();
				}
				else
				{
					marioAIOptions.setVisualization(false);
					marioAIOptions.setFPS(1000);
				}
				 */
				marioAIOptions.setAgent(agent);
				basicTask.setOptionsAndReset(marioAIOptions);
				basicTask.runSingleEpisode(1);

				try
				{
					newScore = basicTask.getEvaluationInfo().computeWeightedFitness();
				} 
				catch (NullPointerException e)
				{
					newScore = 0;
				}

				if (bestScore < newScore)
				{
					bestCounter = 1;
					bestScore = newScore;
					if (i != 150){
						bestChromo = p.getChromosome(i);
						bestChromo.chromosomeToFile("//src//bestChromo.csv");
					}

				}
			}
		}
	}
}
