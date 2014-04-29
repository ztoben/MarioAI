/*
 * File: Custom.java
 * Author: Trent Morris Jesse Miller Mat Kakavas Zach Toben
 * Last modified: 2/28/14
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
		MarioManiacsFitnessFunction fitnessFunction = new MarioManiacsFitnessFunction();
		int j = 0;
		
		float[] bestChromo;
		int bestScore = 0;
		int newScore = 0;
		int[] scores;
		
		int seed = 5;
		
		int inputLayerSize = 12;
		int hiddenLayerSize = 30;
		int outputLayerSize = 6;
		int populationSize = 200;
		
		GAthree geneticAlgorithm = new GAthree(populationSize, inputLayerSize, hiddenLayerSize, outputLayerSize);
		float[][] generation = geneticAlgorithm.generateFreshBatch();
		bestChromo = generation[0];
		
		BaseNeuralNetwork base = new FullConnectionNeuralNetwork(inputLayerSize,hiddenLayerSize);
		base.createConnections();
		agent.setNeuralNetwork(base);
		
		final BasicTask basicTask = new BasicTask(marioAIOptions);
		marioAIOptions.setLevelDifficulty(0);
		marioAIOptions.setLevelRandSeed(seed);
		marioAIOptions.setAgent(agent);
		
		while(true)
		{
			System.out.println("Generation "+ j + " Score - " +bestScore);
			scores = new int[populationSize];
			j++;
			bestScore = 0;
			
			for (int i=0; i < populationSize + 1; i++)
			{
				if (i == populationSize) // show the best agent
				{	
					if (j % 10==0){
						marioAIOptions.setVisualization(true);
					}
					marioAIOptions.setFPS(99);
					base.setWeights(bestChromo);
					basicTask.runSingleEpisode(1);
				}
				else // we are running through the generation
				{
					marioAIOptions.setVisualization(false);
					base.setWeights(generation[i]);
					
					basicTask.runSingleEpisode(1);
					
					try {
						newScore = fitnessFunction.computeScore(basicTask.getEvaluationInfo());
					}
					catch (Exception IOException){}
					
					scores[i] = newScore;
					
					if (newScore > bestScore)
					{
						bestScore = newScore;
						bestChromo = generation[i];
						new Chromosome(bestChromo).chromosomeToFile("//src//bestChromo.csv");
					}
				}
			}
			
			generation = geneticAlgorithm.createNewGenerationV2(generation, scores);
		}
		
	}
}
