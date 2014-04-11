package marioManiacs.agents.neuralNetwork;

/* Represents an output node of the Neural Network
 * Contains a weight value and a list of the nodes that it receives values from
 * Returns a boolean value that represents pushing a button
 */

public class OutputNode implements NeuralNode
{
	boolean bFire;
	int internalWeight;
	HiddenNode[] hiddenNodes;		//hiddenNodes and values are PARALLEL ARRAYS
	int[] values;
	
	
	
	
	public int getWeight()
	{
		return internalWeight;
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


	public void setWeight(int weight)
	{
		internalWeight = weight;
	}


	public void setFrontConnections(NeuralNode[] connections) {}  // output nodes have no forward connections


	
	public void setRearConnections(NeuralNode[] connections)
	{
		hiddenNodes = (HiddenNode[]) connections;
		setupValues();
		setValues();
	}


	public void setInputWeights() {} // not implemented yet

	private void setupValues() 
	{
		values = new int[hiddenNodes.length];
	}
	
	public void setValues()
	{
		/*
		for (int i=0; i < myValues.length; i++)
		{
			values[i] = myValues[i];
		}
		*/
		for (int i=0; i<values.length; i++)
		{
			values[i] = 1;
		}
	}


	public void run() 
	{
		int sum = 0;
		for (int i=0; i < values.length; i++)
		{
			if (hiddenNodes[i].isFiring())
				sum += values[i];
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
