package marioManiacs.agents.neuralNetwork;

/* Represents an input node on the Neural Network
 * Contains a value that it receives from the world, 
 * and a list of nodes that it will send that value to
 */

public class InputNode implements NeuralNode
{
	boolean bFire;
	int value;
	int internalWeight;
	HiddenNode[] hiddenNodes;
	
	
	
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
		return hiddenNodes;
	}


	
	public NeuralNode[] getRearConnections() // maybe we want to change this to be able to change the grid position it receives the value from??? (probly not)
	{
		return null;
	}



	@Override
	public void setWeight(int weight) //NOT BEING USED
	{
		internalWeight = weight;
	}



	public void setFrontConnections(NeuralNode[] connections) 
	{
		hiddenNodes = (HiddenNode[]) connections;
	}



	public void setRearConnections(NeuralNode[] connections) {}  // has no rear connections




	public void setInputWeights() {} // not implemented yet

	

	public void setValue(int inputData)
	{
		value = inputData;
	}


	
	public void run() 
	{
		if (value > internalWeight)
			bFire = true;
		else
			bFire = false;
	}
	
	
	

	public void setValues()
	{
		;// hidden and output nodes only
	}
}
