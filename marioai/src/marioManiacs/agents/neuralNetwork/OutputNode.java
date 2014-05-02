package marioManiacs.agents.neuralNetwork;

/* Represents an output node of the Neural Network
 * Contains a weight value and a list of the nodes that it receives values from
 * Returns a boolean value that represents pushing a button
 */

/*
 * File: OutputNode.java
 * Author: Jesse, Mat
 * Last modified: 4/15/14
 */

public class OutputNode implements NeuralNode
{
	boolean bFire;
	float internalWeight;
	HiddenNode[] hiddenNodes;		//hiddenNodes and values are PARALLEL ARRAYS
	float[] values;
	
	
	
	
	public float getWeight()
	{
		return internalWeight;
	}
	
	
	public float[] getValues()
	{
		return values;
	}
	
	
	public boolean isFiring() 
	{
		return bFire;
	}
	
	
	
	public NeuralNode[] getForwardConnections() // has no forward connections
	{
		return null;
	}

	
	
	public NeuralNode[] getRearConnections() 
	{
		return hiddenNodes;
	}


	public void setWeight(float weight)
	{
		internalWeight = weight;
	}


	public void setFrontConnections(NeuralNode[] connections) {}  // output nodes have no forward connections


	
	public void setRearConnections(NeuralNode[] connections)
	{
		hiddenNodes = (HiddenNode[]) connections;
	}


	public void setInputWeights() {} // not implemented yet
	
	
	public void setValues(float[] weights)
	{
		values = weights;
	}


	public void run() 
	{
		float sum = 0;
		
		for (int i=0; i < values.length; i++)
		{
			if (hiddenNodes[i].isFiring())
			{
				sum += values[i];
			}
		}
		
		if (sum > internalWeight)//to fire or not to fire
			bFire = true;
		else
			bFire = false;
	}
	
	
	
	
	public void setValue(int value) 
	{
		;// input nodes only
	}
}
