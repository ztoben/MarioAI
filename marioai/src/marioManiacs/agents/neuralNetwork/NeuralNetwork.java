package marioManiacs.agents.neuralNetwork;

/* Represents the neural network
 * base interface class that contains methods all neural networks must implement
 */

public interface NeuralNetwork
{

	
	public NeuralNode[] getInputLayer();
	
	
	public NeuralNode[] getHiddenLayer();

	
	public NeuralNode[] getOutputLayer();
	
	
	public boolean[] inputData();
	
	
	public void setHiddenLayerWeights();
	
	
	public void setOutputLayerWeights();
	
	
	public void setInputLayerConnections();
	
	
	public void setHiddenLayerConnections();
	
	
}