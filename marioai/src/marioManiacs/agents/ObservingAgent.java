/*
 * File: ObservingAgent.java
 * Author: Trent Morris, Zach Toben, Jesse Miller, Mat Kakavas
 * Last modified: 4/23/14
 */
package marioManiacs.agents;
import ch.idsia.agents.Agent;
import ch.idsia.benchmark.mario.environments.Environment;

public class ObservingAgent implements Agent {
	

protected int[] marioState = null;

	public ObservingAgent() {
		reset();
	}
	
	public void integrateObservation(Environment e) {
		marioState = e.getMarioState();
		
	}

	@Override
	public boolean[] getAction() {
		
		return null;
	}

	@Override
	public void giveIntermediateReward(float intermediateReward) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
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
