package marioManiacs.agents;

import java.util.Random;

import ch.idsia.agents.Agent;
import ch.idsia.agents.controllers.BasicMarioAIAgent;

public class RandomAgent extends BasicMarioAIAgent implements Agent 
{
    protected String name;
   
    public RandomAgent()
    {
      super("RandyAgent");
      reset();
    }
   
    public boolean[] getAction()
    {
        boolean[] actionReturn = new boolean[6];
        Random r = new Random();
        for (int i = 0; i < 6;i++)
        {
            actionReturn[i] = r.nextBoolean();
        }
        return actionReturn;
       
    }
   
    public void reset(){}
   
 
   
}