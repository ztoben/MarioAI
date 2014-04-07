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
	
	
	public void setInputLayerWeights(float[] weights);
	
	
	public void setHiddenLayerWeights(float[] weights);
	
	
	public void setOutputLayerWeights(float[] weights);
	
	
	public void setInputLayerConnections();
	
	
	public void setHiddenLayerConnections();
	
	
}