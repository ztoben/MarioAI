package ch.idsia.scenarios;


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


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import marioManiacs.agents.*;
import marioManiacs.agents.neuralNetwork.*;
import GeneticAlgorithm.*;


public class SingleChromosomeCustom
{
	public static void main(String[] args) throws IOException
	{
		final MarioAIOptions marioAIOptions = new MarioAIOptions(args);
		final ParserAgent agent = new ParserAgent("bob");
		String path = System.getProperty("user.dir")+"//src//bestChromo.csv";
		Chromosome c = GAModifier.getChromoFromCSV(path);

		BaseNeuralNetwork base = new FullConnectionNeuralNetwork(52,20);
		base.createConnections();
		for (int j = 0; j < 10; j++){ 

			base.setWeights(c.chromosome);
			marioAIOptions.setFPS(40);
			agent.setNeuralNetwork(base);

			final BasicTask basicTask = new BasicTask(marioAIOptions);
			marioAIOptions.setLevelDifficulty(j);
			for (int seed = 0; seed < 10; seed++){
				marioAIOptions.setLevelRandSeed(seed);
				marioAIOptions.setAgent(agent);
				basicTask.setOptionsAndReset(marioAIOptions);
				basicTask.runSingleEpisode(1);
			}
		}
	}
}