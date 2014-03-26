package marioManiacs.agents;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;

public class GAAgent extends BasicMarioAIAgent implements Agent{
    protected String name;
    int floatCounter = 0;
    float lastPosition = 1000;
    float marioPosition = 0;
    public GAAgent()
    {
      super("GAAgent");
      reset();
    }
    
    public boolean[] getAction(){
    	setObservationDetails(2,2,2,2);
    	
    	boolean[] actionReturn = new boolean[6];

    	//System.out.println(enemiesFloatPos[0] - marioFloatPos[0]);
    	// -50 is a good time to jump over enemies
    	// -90 is about where Mario is at
    	// 2 == fire
    	
    	float enemyDistance = 200;
    	if ((lastPosition - marioFloatPos[0]) >= 0 && (lastPosition - marioFloatPos[0]) < 2 ){
    		floatCounter +=1;
    	}
    	else{
    		lastPosition = marioFloatPos[0];
    		floatCounter = 0;
    	}   	
    	try {
    		marioPosition = marioFloatPos[0];
    		enemyDistance = enemiesFloatPos[0];   		
    	}
    	catch (ArrayIndexOutOfBoundsException e){
    	}	 	
    	System.out.println(marioPosition);
    	
    	float distanceToEnemy = enemyDistance - marioFloatPos[0];
    	
    	if (distanceToEnemy < -50 && distanceToEnemy > -70){
    		actionReturn[3] = true;
    		System.out.println("Near the enemy");
    	}
    	else if (floatCounter == 24){
    		actionReturn[3] = true;
    		floatCounter = 0;
    	}
    		
    	else{
    		actionReturn[3] = false;
    	} 
    	System.out.println(marioState[0]);
    	if ((marioState[0] == 2) && distanceToEnemy < 250){
    		actionReturn[4] = true;
    	}
    	
    	actionReturn[1] = true;
    	return actionReturn;
    	
    }
    
    
    public void reset(){} 
	    
}
