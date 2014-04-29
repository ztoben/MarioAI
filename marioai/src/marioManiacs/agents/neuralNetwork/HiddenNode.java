/*
 * File: HiddenNode.java
 * Author: Jesse Miller Mat Kakavas
 * Last modified: 4/15/14
 */
package marioManiacs.agents.neuralNetwork;

/* Represents a node within the Hidden Layer of the Neural Network
 * This node contains a weight value, a list of the nodes it is connected to
 * and a list of nodes it will receive values from
 */

public class HiddenNode implements NeuralNode
{
	boolean bFire;
	float internalWeight;
	InputNode[] inputNodes;		//inputNodes and values are PARALLEL ARRAYS
	float[] values;
	OutputNode[] outputNodes;
	
	
	
	public float getWeight() 
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


	public void setWeight(float weight) 
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

	
	public void setValues(float[] generatedValues)
	{
		//values = new float[inputNodes.length];
		values = generatedValues;
	}


	public void run() 
	{
		float sum = 0;
		float tempValue;
		
		for (int i=0; i < values.length; i++)
		{
			tempValue = values[i] * inputNodes[i].getValue();
			sum += tempValue;
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
}
