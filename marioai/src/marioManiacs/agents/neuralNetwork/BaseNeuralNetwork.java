package marioManiacs.agents.neuralNetwork;

public class BaseNeuralNetwork implements NeuralNetwork
{
	InputNode[] inputLayer;
	HiddenNode[] hiddenLayer;
	OutputNode[] outputLayer;
	boolean[] output;

	
	
	public BaseNeuralNetwork(int numOfInputNodes, int numOfHiddenNodes)
	{
		inputLayer = new InputNode[numOfInputNodes];
		hiddenLayer = new HiddenNode[numOfHiddenNodes];
		outputLayer = new OutputNode[6];
		output = new boolean[6];
	}
	
	
	
	public NeuralNode[] getInputLayer() 
	{
		return inputLayer;
	}

	
	
	public NeuralNode[] getHiddenLayer() 
	{
		return hiddenLayer;
	}

	
	
	public NeuralNode[] getOutputLayer() 
	{
		return outputLayer;
	}

	
	
	public boolean[] inputData() 
	{
		//input the data into input layer
		//fire input nodes to hidden layer
		//calculate weights in hidden layer and fire hidden layer to outputlayer
		//calculate weights in output layer and set fire
		
		
		for (int i=0; i < outputLayer.length; i++)
		{
			if (outputLayer[i].isFiring())
				output[i] = true;
			else
				output[i] = false;
		}
		
		return output;
	}

	
	
	public void setHiddenLayerWeights() 
	{
		;// DO THIS
	}

	
	
	public void setOutputLayerWeights() 
	{
		;// DO THIS
	}

	
	
	public void setInputLayerConnections() 
	{
		;// DO THIS
	}
	
	
	
	public void setHiddenLayerConnections() 
	{
		;// DO THIS
	}

}
