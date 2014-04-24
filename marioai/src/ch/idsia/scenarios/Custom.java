/*
// * Copyright (c) 2009-2010, Sergey Karakovskiy and Julian Togelius
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Mario AI nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 * IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 * NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package ch.idsia.scenarios;

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import marioManiacs.agents.*;
import marioManiacs.agents.neuralNetwork.*;
import GeneticAlgorithm.*;


public class Custom
{
	public static void main(String[] args)
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
		marioAIOptions.setLevelDifficulty(1);
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
					marioAIOptions.setVisualization(true);
					marioAIOptions.setFPS(99);
					base.setWeights(bestChromo);
					basicTask.runSingleEpisode(1);
				}
				else // we are running through the generation
				{
					marioAIOptions.setVisualization(false);
					base.setWeights(generation[i]);
					
					basicTask.runSingleEpisode(1);
					
					newScore = fitnessFunction.computeScore(basicTask.getEvaluationInfo());
					scores[i] = newScore;
					
					if (newScore > bestScore)
					{
						bestScore = newScore;
						bestChromo = generation[i];
					}
				}
			}
			
			generation = geneticAlgorithm.createNewGenerationV2(generation, scores);
		}
		
	}
}
