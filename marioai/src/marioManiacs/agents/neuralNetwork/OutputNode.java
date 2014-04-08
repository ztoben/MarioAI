package marioManiacs.agents.neuralNetwork;

/* Represents an output node of the Neural Network
 * Contains a weight value and a list of the nodes that it receives values from
 * Returns a boolean value that represents pushing a button
 */

public class OutputNode implements NeuralNode
{
	boolean bFire;
	float internalWeight;
	HiddenNode[] hiddenNodes;
	
	
	
	public float getWeight() 
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
	}


	public void setInputWeights() {} // not implemented yet


	@Override
	public void setValue(int value) {
		// TODO Auto-generated method stub
		
	}


	public void run() 
	{
		;// DO THIS
		//run through inputs, sum the values
		//if greater than weight set firing to true, else false
	}
	
	
}
