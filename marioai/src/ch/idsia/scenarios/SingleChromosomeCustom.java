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
		int inputLayerSize = 52;
		int hiddenLayerSize = 30;
		int seed = 4;
		int levelDifficulty = 1;
		
		MarioManiacsFitnessFunction fitnessFunction = new MarioManiacsFitnessFunction();
		final MarioAIOptions marioAIOptions = new MarioAIOptions(args);
		final ParserAgent agent = new ParserAgent("bob");
		final BasicTask basicTask = new BasicTask(marioAIOptions);
		
		//String path = System.getProperty("user.dir") +   "//src//bestChromo.csv";
		String path = System.getProperty("user.dir") +   "//src//EliteMario1.csv";  //seed=4, diff=1, input=52, hidden=30
		
		
		
		
		Chromosome c = GAModifier.getChromoFromCSV(path);
		
		BaseNeuralNetwork base = new FullConnectionNeuralNetwork(inputLayerSize,hiddenLayerSize);
		base.createConnections();
		base.setWeights(c.chromosome);
		agent.setNeuralNetwork(base);
		marioAIOptions.setAgent(agent);
		
		
		marioAIOptions.setLevelRandSeed(seed);
		marioAIOptions.setLevelDifficulty(levelDifficulty);
		marioAIOptions.setFPS(30);
		basicTask.runSingleEpisode(1);
		
		System.out.println("Score: " + fitnessFunction.computeScore(basicTask.getEvaluationInfo()));
	}
}