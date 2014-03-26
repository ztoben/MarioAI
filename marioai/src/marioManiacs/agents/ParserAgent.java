/* This agent will parse the environment and store the variables we need as instance variables.
 * Our Advanced agents can extend this class and override the getAction() method
 * That way they can call and use the parseEnvironment() method and easily access the variables
 */

package marioManiacs.agents;

import java.util.Random;

import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.environments.Environment;

public class ParserAgent implements Agent
{
    protected String name;
    public int x;
    public int y;
    
    public ParserAgent(String s)
    {
      name = s;
    }
    
    
    
    
    public void parseEnvironment()
    //Main method that takes in an environment and loads up current instance variables
    {
    	
    }
    
    
    
    public void setEnvironmentDetails(int x, int y)
    //Defines how many nodes to search: x=horizontal (left and right) // y=vertical (up and down)
    {
    	
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




	@Override
	public void integrateObservation(Environment environment) {
		// TODO Auto-generated method stub
		
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