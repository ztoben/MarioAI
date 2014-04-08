package marioManiacs.agents.neuralNetwork;

/* Represents a node within the Hidden Layer of the Neural Network
 * This node contains a weight value, a list of the nodes it is connected to
 * and a list of nodes it will receive values from
 */

public class HiddenNode implements NeuralNode
{
	boolean bFire;
	float internalWeight;
	InputNode[] inputNodes;
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


	public void setValue(int value) {
		// TODO Auto-generated method stub
		
	}


	public void run() 
	{
		;//DO THIS
		//run through inputs, sum the values
		//if greater than weight set firing to true, else false
	}

}