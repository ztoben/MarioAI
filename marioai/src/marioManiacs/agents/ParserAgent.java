/* This agent will parse the environment and store the variables we need as instance variables.
 * Our Advanced agents can extend this class and override the getAction() method
 * That way they can call and use the parseEnvironment() method and easily access the variables
 */

package marioManiacs.agents;

import java.util.Random;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.environments.Environment;

//one grid spot is 32x32

//if NO ground at bottom of grid, then there is a gap at this position

public class ParserAgent implements Agent
{
    protected String name;
    protected byte[][] mainGrid;
    protected byte[][] updatedGrid;
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
    	mainGrid = environment.getMergedObservationZZ(0, 0);
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
    	updatedGrid = new byte[a][a];
        updatedGrid[a/2][a/2] = mainGrid[marioPosition[0]][marioPosition[1]];

        int base = marioPosition[0] - (a/2);

        int x = base;
        int y = base;

        for (int i=0; i < a; i++)
        {
        	for (int j=0; j < a; j++)
        	{
        		updatedGrid[i][j] = mainGrid[x][y];
        		y+=1;
        	}
        	x+=1;
        	y = base;
        }
    }
    
    
    
    public boolean[] getAction()
    {
        boolean[] actionReturn = new boolean[6];
        Random r = new Random();
        if (r.nextInt(100) < 10)
        {
        	actionReturn[0] = true; //left
        	actionReturn[1] = false; //right
        	actionReturn[2] = true;
        }
        else
        {
        	actionReturn[0] = false; //left
        	actionReturn[1] = true; //right
        	actionReturn[2]= false;
        }
        
        //actionReturn[2] = false; //down
        actionReturn[3] = r.nextBoolean(); // jump
        actionReturn[4] = r.nextBoolean(); // run
        actionReturn[5] = false; //up
        
        return actionReturn;
        
    }
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
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