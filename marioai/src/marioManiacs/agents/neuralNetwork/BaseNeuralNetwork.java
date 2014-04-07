package marioManiacs.agents.neuralNetwork;

import java.util.Arrays;

public class BaseNeuralNetwork implements NeuralNetwork
{
	InputNode[] inputLayer;
	HiddenNode[] hiddenLayer;
	OutputNode[] outputLayer;
	int[] gridValues;
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
		for (int i=0; i < inputLayer.length; i++)
		{
			inputLayer[i].setValue(gridValues[i]);
		}
		
		for (int i=0; i < hiddenLayer.length; i++) 
		{
			hiddenLayer[i].run();
		}
		
		for (int i=0; i < outputLayer.length; i++)
		{
			if (outputLayer[i].isFiring())
				output[i] = true;
			else
				output[i] = false;
		}
		
		return output;
	}

	public void setWeights(float[] weights) 
	{
		float[] inputWeights = Arrays.copyOfRange(weights, 0, inputLayer.length - 1);
		float[] hiddenWeights = Arrays.copyOfRange(weights, inputLayer.length, hiddenLayer.length - 1);
		float[] outputWeights = Arrays.copyOfRange(weights, hiddenLayer.length, weights.length - 1);
		
		setInputLayerWeights(inputWeights);
		setHiddenLayerWeights(hiddenWeights);
		setOutputLayerWeights(outputWeights);
	}
	
	public void setInputLayerWeights(float[] weights) 
	{
		/*
		for (int i = 0; i < inputLayer.length; i++) {
			inputLayer[i].setWeight(weights[i]);
		}
		*/;
	}
	
	public void setHiddenLayerWeights(float[] weights) 
	{
		/*
		for (int i = 0; i < hiddenLayer.length; i++) {
			hiddenLayer[i].setWeight(weights[i]);
		}
		*/;
	}
	
	public void setOutputLayerWeights(float[] weights) 
	{
		/*
		for (int i = 0; i < outputLayer.length; i++) {
			outputLayer[i].setWeight(weights[i]);
		}
		*/;
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
