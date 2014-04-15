/*
 * Copyright (c) 2009-2010, Sergey Karakovskiy and Julian Togelius
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
import marioManiacs.agents.neuralNetwork.BaseNeuralNetwork;
import GeneticAlgorithm.*;

import java.io.IOException;


public class Custom
{
	public static void main(String[] args)
	{
		final MarioAIOptions marioAIOptions = new MarioAIOptions(args);
		final ParserAgent agent = new ParserAgent("bob");
		int j = 0;
		int bestScore = 0;
		int newScore = 0;
		int bestCounter = 0;
		int seed = 1;
		Chromosome c;

		Population p = GAModifier.createFirstGeneration(100, 78, 3);
		Chromosome bestChromo = p.getChromosome(0);
		
		while(true)
		{ // Base this off score eventually or just certain number of generations
			BaseNeuralNetwork base = new BaseNeuralNetwork(49, 20);
			base.createConnections();
			// Moved Neural network in here just cause felt like getting little to no variation no matter how long it ran
			// Make it so can print connections of neural network so can manually enter those later?
			if (j != 0)
			{
				GAModifier.breedPopulation(p, bestChromo, 3);
			}
			
			System.out.println("Generation "+ j + " Score - " +bestScore);
			j++;
			
			for (int i = 0; i < 101; i++) // 100 generations
			{
				if (i != 100)
				{
					c = p.getChromosome(i);
					base.setWeights(c.chromosome);
				}
				else
				{
					base.setWeights(bestChromo.chromosome);
				}
				
				agent.setNeuralNetwork(base);

				final BasicTask basicTask = new BasicTask(marioAIOptions);
				marioAIOptions.setFPS(1000);
				marioAIOptions.setVisualization(false); // Sets whether GUI comes up or not
				marioAIOptions.setLevelDifficulty(0);
				marioAIOptions.setLevelRandSeed(seed);
				//seed++;

				if (bestCounter == 1)
				{
					marioAIOptions.setVisualization(true);
					base.setWeights(bestChromo.chromosome);
					bestCounter = 0;
					marioAIOptions.setFPS(70);
				}
				else
				{
					marioAIOptions.setVisualization(false);
					marioAIOptions.setFPS(1000);
				}
				
				marioAIOptions.setAgent(agent);
				basicTask.setOptionsAndReset(marioAIOptions);
				basicTask.runSingleEpisode(1);

				try
				{
					newScore = basicTask.getEvaluationInfo().computeBasicFitness();
				} 
				catch (NullPointerException e)
				{
					newScore = 0;
				}

				if (bestScore < newScore)
				{
					bestCounter = 1;
					bestScore = newScore;
					bestChromo = p.getChromosome(i);
				}
			}
		}
	}
}
