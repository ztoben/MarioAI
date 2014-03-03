package marioManiacs.agents;

import java.util.Random;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;

public class MostlyRandomAgent extends BasicMarioAIAgent implements Agent {
    protected String name;
   
    public MostlyRandomAgent(){
      super("RandyAgent");
      reset();
    }
   
    public boolean[] getAction(){
        boolean[] actionReturn = new boolean[6];
        Random r = new Random();
        actionReturn[0] = false; //left
        actionReturn[1] = true; //right
        actionReturn[2] = false; //down
        actionReturn[3] = r.nextBoolean(); // jump
        actionReturn[4] = true; // run
        actionReturn[5] = false; //up
        
        return actionReturn;
       
    }
   
    public void reset(){}
   
 
   
}