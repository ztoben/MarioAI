/* This agent will parse the environment and store the variables we need as instance variables.
 * Our Advanced agents can extend this class and override the getAction() method
 * That way they can call and use the parseEnvironment() method and easily access the variables
 */

package marioManiacs.agents;

import java.util.Random;
import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.environments.Environment;
import marioManiacs.agents.neuralNetwork.*;

//one grid spot is 32x32

//if NO ground at bottom of grid, then there is a gap at this position

public class ParserAgent implements Agent
{
	protected BaseNeuralNetwork myBrain;
	
    protected String name;
    protected byte[][] mainGrid;
    protected byte[][] worldState;
    protected int[] marioPosition = new int[2];
    protected int nodesToSearch = 7;//this represents x AND y dimensions, they should be the same and ODD
    
    protected int marioState; // 2 = fire; 1 = large; 0 = small
    protected boolean bMarioCanJump;
    protected boolean bMarioCanShoot;
    
    protected int gapLength;
    protected int distanceToGap;
    protected int distanceToEnemy;
    protected int obstacleDistance;
    protected int obstacleHeight;
    protected int deadEnd;
    
    //for dummy randomization
    protected int tick;
    protected Random generator = new Random();
    
    
    public ParserAgent(String s)
    {
      name = s;
    }
    
    
    public int getGapLength()
    {
    	return gapLength;
    }
    
    
    public int getDistanceToGap()
    {
    	return distanceToGap;
    }
    
    
    public int getDistanceToEnemy()
    {
    	return distanceToEnemy;
    }
    
    
    public int getMarioState()// 2 = fire; 1 = large; 0 = small
    {
    	return marioState;
    }
    
    
    public int getObstacleDistance()
    {
    	return obstacleDistance;
    }
    
    
    public int getObstacleHeight()
    {
    	return obstacleHeight;
    }
    
    
    public int getDeadEnd()
    {
    	return deadEnd;
    }
    
    
    public void parseEnvironment(Environment environment)
    //Main method that takes in an environment and loads up current instance variables
    {
    	mainGrid = environment.getMergedObservationZZ(0, 2);
    	marioPosition[0] = 9;
    	marioPosition[1] = 9;
    	loadUpdatedGrid(nodesToSearch);
    	marioPosition = environment.getMarioEgoPos();
    	
    	int[] marioStatus = environment.getMarioState();
    	
    	marioState = marioStatus[1];
    	bMarioCanShoot = (marioStatus[2] == 1);
    	bMarioCanJump = (marioStatus[3] == 1);
    	//input more stuff to load up here!!
    	//current implementation is fake!!
    	if (tick == 0)
    		randomizeValues();
    	else if (tick == 42)
    		tick=0;
    	else
    		tick+=1;
    }
    
    
    //TEMPORARY FUNCTION
    public void randomizeValues()
    {
    	gapLength = generator.nextInt(3);
        distanceToGap = generator.nextInt(3);
        distanceToEnemy = generator.nextInt(3);
        obstacleDistance = generator.nextInt(3);
        obstacleHeight = generator.nextInt(3);
        deadEnd = generator.nextInt(3);
    }
    
    
    
    
    public void loadUpdatedGrid(int a)
    {
    	worldState = new byte[a][a];
        worldState[a/2][a/2] = mainGrid[marioPosition[0]][marioPosition[1]];

        int base = marioPosition[0] - (a/2);

        int x = base;
        int y = base;
        
        int temp;

        for (int i=0; i < a; i++)
        {
        	for (int j=0; j < a; j++)
        	{
        		temp = mainGrid[x][y];
        		
        		switch (temp)
				{
					case 0: temp = 5; 		break;
					case -20: temp = 1; 	break;
					case -22: temp = 1; 	break;
					case 2: temp = 2; 		break;
					case -60: temp = 3; 	break;
					case -80: temp = 4; 	break;
					case -90: temp = 4; 	break;
					default: temp = 0;
				}
        		
        		worldState[i][j] = (byte) temp;
        		y+=1;
        	}
        	x+=1;
        	y = base;
        }
    }
    
    
    
    //new stuff
    
    public boolean[] getAction()
    //CURRENTLY USES NEURAL NETWORK TO PRODUCE ACTION
    {
        return myBrain.think(inputData());
    }
   
    
    public int[] inputData()
    // Converts the world state into a single int[] for the neural network to read in
    {
    	int[] inputData = new int[nodesToSearch*nodesToSearch + 3];
    	int bubbles = 3;
    	
    	inputData[0] = marioState;
    	
    	if (bMarioCanJump)
    		inputData[1] = 1;
    	else
    		inputData[1] = 0;
    	
    	if (bMarioCanShoot)
    		inputData[2] = 1;
    	else
    		inputData[2] = 0;
        
    	for (int i=0; i < worldState.length; i++)
    	{
    		for (int j=0; j < worldState[i].length; j++)
    		{
    			inputData[bubbles] = worldState[i][j];
    			bubbles++;
    		}
    	}
    	
    	return inputData;
    }
    
    
    public void setNeuralNetwork(BaseNeuralNetwork network)
    {
    	myBrain = network;
    }
    
    
    /*creating a new agent will look something like:
     * BaseNeuralNetwork network = new BaseNeuralNetwork(nodesToSearch*nodesToSearch,20);
     * network.createRandomConnections();
     * network.setWeights(myArrayOfWeights);
     * 
     * THIS IS INCOMPLETE BECAUSE WE ALSO NEED TO SET ***EACH*** NODE'S SET OF INPUT VALUES!!!
     * 
     * ParserAgent.setNeuralNetwork(network);
     * 
     * 
     * while running: (we shouldn't need to change this... I think)
     * 	ParserAgent.integrateObservation(environment);
     * 	ParserAgent.getAction();
     * 	
     */
    
    
    // end new stuff
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void reset(){}




	public void integrateObservation(Environment environment) 
	{
		parseEnvironment(environment);
	}




	@Override
	public void giveIntermediateReward(float intermediateReward) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void setObservationDetails(int rfWidth, int rfHeight, int egoRow,
			int egoCol) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		
	}
}