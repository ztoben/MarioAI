package marioManiacs.agents.neuralNetwork;

/* Represents a node within the Hidden Layer of the Neural Network
 * This node contains a weight value, a list of the nodes it is connected to
 * and a list of nodes it will receive values from
 */

public class HiddenNode implements NeuralNode
{
	boolean bFire;
	int internalWeight;
	InputNode[] inputNodes;		//inputNodes and values are PARALLEL ARRAYS
	int[] values;
	OutputNode[] outputNodes;
	
	
	
	public int getWeight() 
	{
		return internalWeight;
	}
	
	
	public boolean isFiring() 
	{
		return bFire;
	}

	
	public NeuralNode[] getForwardConnections() 
	{
		return outputNodes;
	}

	
	
	public NeuralNode[] getRearConnections() 
	{
		return inputNodes;
	}


	public void setWeight(int weight) 
	{
		internalWeight = weight;
	}


	public void setFrontConnections(NeuralNode[] connections) 
	{
		outputNodes = (OutputNode[]) connections;
	}


	public void setRearConnections(NeuralNode[] connections) 
	{
		inputNodes = (InputNode[]) connections;
	}


	public void setInputWeights() {} // not implemented yet


	public void setValues(int[] myValues)
	{
		for (int i=0; i < myValues.length; i++)
		{
			values[i] = myValues[i];
		}
	}


	public void run() 
	{
		int sum = 0;
		for (int i=0; i < values.length; i++)
		{
			if (inputNodes[i].isFiring())
				sum += values[i];
		}
		
		if (sum > internalWeight)//to fire or not to fire
			bFire = true;
		else
			bFire = false;
	}

	
	
	public void setValue(int value) 
	{
		; // for input nodes only
	}
}