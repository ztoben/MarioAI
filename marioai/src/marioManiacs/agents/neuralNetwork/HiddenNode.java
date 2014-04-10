package marioManiacs.agents.neuralNetwork;
import java.util.*;

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


<<<<<<< HEAD
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

	
=======
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
			if (inputNodes[i].isFiring())
				sum += values[i];
		}
		
		if (sum > internalWeight)//to fire or not to fire
			bFire = true;
		else
			bFire = false;
	}

	
	public void addToRearConnections(InputNode node)
	{
		InputNode[] myNode = new InputNode[1];
		myNode[0] = node;
		
		//inputNodes = ArrayUtils.addAll(inputNodes, node);
	}
>>>>>>> branch 'NeuralNetwork' of https://github.com/abacon45/MarioAI.git
	
	public void setValue(int value) 
	{
		; // for input nodes only
	}
}
