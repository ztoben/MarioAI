/*
 * File: NeuralNetwork.java
 * Author: Jesse Miller Mat Kakavas
 * Last modified: 4/15/14
 */
package marioManiacs.agents.neuralNetwork;

/* Represents the neural network
 * base interface class that contains methods all neural networks must implement
 */

public interface NeuralNetwork
{

	
	public NeuralNode[] getInputLayer();
	
	
	public NeuralNode[] getHiddenLayer();

	
	public NeuralNode[] getOutputLayer();
	
	
	public boolean[] think(int[] gridValues);
	
	
	public void setInputLayerThresholds(float[] weights);
	
	
	public void setHiddenLayerThresholds(float[] weights);
	
	
	public void setOutputLayerThresholds(float[] weights);
	
	
	public void setInputLayerConnections();
	
	
	public void setHiddenLayerConnections();
	
	
}