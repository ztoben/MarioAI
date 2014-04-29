package ch.idsia.scenarios;


/*
 * File: SingleChromosomeCustom.java
 * Author: Trent Morris Zach Toben
 * Last modified: 4/29/14
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
		String path = System.getProperty("user.dir")+   "//src//bestChromo.csv";
		Chromosome c = GAModifier.getChromoFromCSV(path);
		
		BaseNeuralNetwork base = new FullConnectionNeuralNetwork(12,30);
		base.createConnections();
		for (int j = 0; j < 10; j++){ 
			System.out.println(c.size()+ "\n\n");
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