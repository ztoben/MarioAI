package marioManiacs.agents.neuralNetwork;

import java.util.Arrays;
import java.util.Random;
import marioManiacs.agents.neuralNetwork.*;

public class BaseNeuralNetwork implements NeuralNetwork
{
	private InputNode[] inputLayer; // MUST BE ALL GRID SPACES + 3 (marioStates)
	private HiddenNode[] hiddenLayer;
	private OutputNode[] outputLayer;
	boolean[] output;

	
	
	public BaseNeuralNetwork(int numOfInputNodes, int numOfHiddenNodes)
	{
		inputLayer = new InputNode[numOfInputNodes+3];  // MUST BE ALL GRID SPACES + 3 (marioStates)
		hiddenLayer = new HiddenNode[numOfHiddenNodes];
		outputLayer = new OutputNode[6];
		output = new boolean[6];
		
		for (int i = 0; i < numOfInputNodes+3; i++) {
			inputLayer[i] = new InputNode();
		}
		
		for (int i = 0; i < numOfHiddenNodes; i++) {
			hiddenLayer[i] = new HiddenNode();
		}
		
		for (int i = 0; i < 6; i++) {
			outputLayer[i] = new OutputNode();
		}
		
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

	
	
	public boolean[] think(int[] gridValues) 
	{
		for (int i=0; i < inputLayer.length; i++)
		{
			inputLayer[i].setValue(gridValues[i]);
			inputLayer[i].run();
		}
		
		for (int i=0; i < hiddenLayer.length; i++) 
		{
			hiddenLayer[i].run();
		}
		
		for (int i=0; i < outputLayer.length; i++)
		{
			outputLayer[i].run();
			
			if (outputLayer[i].isFiring())
				output[i] = true;
			else
				output[i] = false;
		}
		
		return output;
	}

	
	public void setWeights(int[] weights) 
	{
		int[] inputWeights = Arrays.copyOfRange(weights, 0, inputLayer.length);
		int[] hiddenWeights = Arrays.copyOfRange(weights, inputLayer.length, inputLayer.length + hiddenLayer.length);
		int[] outputWeights = Arrays.copyOfRange(weights, inputLayer.length + hiddenLayer.length, weights.length);
		
		setInputLayerWeights(inputWeights);
		setHiddenLayerWeights(hiddenWeights);
		setOutputLayerWeights(outputWeights);
	}
	
	
	public void setInputLayerWeights(int[] weights) 
	{
		for (int i = 0; i < inputLayer.length; i++) 
		{
			this.inputLayer[i].setWeight(weights[i]);
		}
	}
	
	
	public void setHiddenLayerWeights(int[] weights) 
	{
		for (int i = 0; i < hiddenLayer.length; i++) 
		{
			hiddenLayer[i].setWeight(weights[i]);
		}
	}
	
	
	public void setOutputLayerWeights(int[] weights) 
	{
		for (int i = 0; i < outputLayer.length; i++) 
		{
			outputLayer[i].setWeight(weights[i]);
		}
	}

	
	public void createRandomConnections()
	{
		Random generator = new Random();
		int connectionSize = 5;
		int[] nodeIndexContainer = new int[connectionSize];
		int tempNumber;
		HiddenNode[] tempHiddenNodes;
		OutputNode[] tempOutputNodes;
		InputNode[] tempRearInputNodes = new InputNode[connectionSize];;
		HiddenNode[] tempRearHiddenNodes = new HiddenNode[connectionSize];;
		
		
		//SET INPUT LAYER FRONT CONNECTIONS
		for (int i=0; i < inputLayer.length; i++)//for each input node
		{
			int j = 0;
			while (j < connectionSize)
			{
				tempNumber = generator.nextInt(hiddenLayer.length); // get a location of a hidden node
				
				if (!Arrays.asList(nodeIndexContainer).contains(tempNumber)) // if that number is not yet in array
				{
					nodeIndexContainer[j] = tempNumber;
					j++;
				}
			}
			
			tempHiddenNodes = new HiddenNode[connectionSize];
			
			for (int z=0; z < connectionSize; z++)
			{
				tempHiddenNodes[z] = hiddenLayer[nodeIndexContainer[z]]; // grab the node from the index location
			}
			
			inputLayer[i].setFrontConnections(tempHiddenNodes);
		}
		
		
		//SET HIDDEN LAYER FRONT CONNECTIONS
		connectionSize = 2; // connect to 2 output nodes
		nodeIndexContainer = new int[connectionSize];
		
		for (int i=0; i < hiddenLayer.length; i++)//for each input node
		{
			int j = 0;
			while (j < connectionSize)
			{
				tempNumber = generator.nextInt(outputLayer.length); // get a location of a hidden node
				
				if (!Arrays.asList(nodeIndexContainer).contains(tempNumber)) // if that number is not yet in array
				{
					nodeIndexContainer[j] = tempNumber;
					j++;
				}
			}
			
			tempOutputNodes = new OutputNode[connectionSize];
			
			for (int z=0; z < connectionSize; z++)
			{
				tempOutputNodes[z] = outputLayer[nodeIndexContainer[z]]; // grab the node from the index location
			}
			
			hiddenLayer[i].setFrontConnections(tempOutputNodes);
		}
		
		
		
		//DO THIS
		
		
		//SET HIDDEN LAYER REAR CONNECTIONS
		//for each node in hidden layer
			//for each input layer node
				//if input node has hidden node in forward connections
					//add input node to hidden node rear connections
		int count = 0;
		
		for (int i=0; i < hiddenLayer.length; i++)
		{
			for (int j=0; j < inputLayer.length; j++)
			{
				if (Arrays.asList(inputLayer[j].getForwardConnections()).contains(hiddenLayer[i])) // if input node has hidden node in its connections
				{
					if (count == 5) {
						break;
					} else {
						tempRearInputNodes[count] = inputLayer[j];
						count++;
					}
				}
			}
			hiddenLayer[i].setRearConnections(tempRearInputNodes);
			count = 0;
		}
		
		
		
		//SET OUTPUT LAYER REAR CONNECTIONS
		//for each node in output layer
			//for each hidden layer node
				//if hidden node has output node in forward connections
					//add hidden node to output node rear connections
		count = 0;
		
		for (int i=0; i < outputLayer.length; i++)
		{
			for (int j=0; j < hiddenLayer.length; j++)
			{
				if (Arrays.asList(hiddenLayer[j].getForwardConnections()).contains(outputLayer[i])) // if hidden node has output node in its connections
				{
					if (count == 5) {
						break;
					} else {
						tempRearHiddenNodes[count] = hiddenLayer[j];
						count++;
					}
				}
			}
			count = 0;
			outputLayer[i].setRearConnections(tempRearHiddenNodes);
		}
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
