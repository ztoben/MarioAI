/*
 * File: FullConnectionNeuralNetwork.java
 * Author: Mat Kakavas Jesse Miller
 * Last modified: 4/22/14
 */
package marioManiacs.agents.neuralNetwork;

public class FullConnectionNeuralNetwork extends BaseNeuralNetwork
{
	public FullConnectionNeuralNetwork(int numOfInputNodes, int numOfHiddenNodes)
	{
		super(numOfInputNodes, numOfHiddenNodes);
	}
	
	
	@Override
	public void createConnections()
	{
		//for each input node
			// assign input node front connections to network's hidden layer
		
		//for each hidden node
			// assign hidden node front connections to output layer
			// assign rear connection to input layer
		
		//for each output node
			// assign output node rear connection to output layer
		
		for (int i=0; i < inputLayer.length; i++)
		{
			inputLayer[i].setFrontConnections(hiddenLayer);
		}
		
		
		for (int i=0; i < hiddenLayer.length; i++)
		{
			hiddenLayer[i].setFrontConnections(outputLayer);
			hiddenLayer[i].setRearConnections(inputLayer);
		}
		
		
		for (int i=0; i < outputLayer.length; i++)
		{
			outputLayer[i].setRearConnections(hiddenLayer);
		}
	}
}
