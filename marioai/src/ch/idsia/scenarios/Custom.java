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

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.ForwardAgent;
import ch.idsia.benchmark.mario.environments.Environment;
import ch.idsia.benchmark.tasks.BasicTask;
import ch.idsia.tools.MarioAIOptions;
import marioManiacs.agents.*;
import marioManiacs.agents.neuralNetwork.BaseNeuralNetwork;
import GeneticAlgorithm.*;

import java.io.IOException;


/**
 * Created by IntelliJ IDEA.
 * User: Sergey Karakovskiy, sergey@idsia.ch
 * Date: May 7, 2009
 * Time: 4:38:23 PM
 * Package: ch.idsia
 */

public class Custom
{
	public static void main(String[] args)
	{
//final String argsString = "-vis on";
    final MarioAIOptions marioAIOptions = new MarioAIOptions(args);
    final ParserAgent agent = new ParserAgent("bob");
    Chromosome c;
    
    Population p = GAModifier.createFirstGeneration(100, 78, 3);
    //BaseNeuralNetwork base = new BaseNeuralNetwork(49, 20);
    //base.createRandomConnections();
    Chromosome bestChromo = p.getChromosome(0);
    int j = 0;
    int bestScore = 0;
    int newScore = 0;
    int bestCounter = 0;
    while(true){ // Base this off score eventually or just certain number of generations
        BaseNeuralNetwork base = new BaseNeuralNetwork(49, 20);
        base.createRandomConnections();
       // Moved Neural network in here just cause felt like getting little to no variation no matter how long it ran
        // Make it so can print connections of neural network so can manually enter those later?
    	if (j != 0){
    		GAModifier.breedPopulation(p, bestChromo, 3);
    	}
    	System.out.println("Generation "+ j + " Score - " +bestScore);
	    for (int i = 0; i < 101; i++) {
	    	if (i != 100){
	    		c = p.getChromosome(i);
	    		base.setWeights(c.chromosome);
	    	}
	    	else{
	    		base.setWeights(bestChromo.chromosome);
	    	}
	        agent.setNeuralNetwork(base);
	        
	        final BasicTask basicTask = new BasicTask(marioAIOptions);
	        marioAIOptions.setRecordFile("on");
	        int seed = 0;
	     //   do
	       // { Took out do while loop. Wasn't allowing us to move on to next chromosomes in population
	          	//marioAIOptions.setFlatLevel(true);
	           	//marioAIOptions.setFPS(1100);
	          	String tet = marioAIOptions.getEnemies();
	          	//System.out.println(tet);
	          	marioAIOptions.setFPS(1000);
	          	marioAIOptions.setVisualization(false); // Sets whether GUI comes up or not
	            marioAIOptions.setLevelDifficulty(0);
	            marioAIOptions.setLevelRandSeed(seed++);
	           // if (i == 100 && j %10 == 0 && j!=0 ){
		    	//	marioAIOptions.setVisualization(true); // Sets whether GUI comes up or not
		    	//}
		    	//else{
		    //		marioAIOptions.setVisualization(false); // Sets whether GUI comes up or not
		    	//}
	            if (bestCounter == 1){
	            	marioAIOptions.setVisualization(true);
	            	base.setWeights(bestChromo.chromosome);
	            	bestCounter = 0;
	            	marioAIOptions.setFPS(70);
	            }
	            else{
	            	marioAIOptions.setVisualization(false);
	            	marioAIOptions.setFPS(1000);
	            }
	            marioAIOptions.setAgent(agent);
	            basicTask.setOptionsAndReset(marioAIOptions);
	            basicTask.runSingleEpisode(1);
	            //marioAIOptions.isVisualization();
	            try
		        {
		            newScore = basicTask.getEvaluationInfo().computeBasicFitness();
		        } catch (NullPointerException e)
		        {
		        	newScore = 0;
		        }

	            if ( bestScore < newScore){
	            	bestCounter = 1;
	            	bestScore = newScore;
	            	bestChromo = p.getChromosome(i);
	            }
	          
	          //} while (basicTask.getEnvironment().getEvaluationInfo().marioStatus != Environment.MARIO_STATUS_WIN);
	        Runtime rt = Runtime.getRuntime();
	        try
	        {
	//                Process proc = rt.exec("/usr/local/bin/mate " + marioTraceFileName);
	            Process proc = rt.exec("python hello.py");
	        } catch (IOException e)
	        {
	            //e.printStackTrace();
	        }
	        
	    }
	   // System.exit(0);
	    j++;
		}
   // System.exit(0);
	}
}
