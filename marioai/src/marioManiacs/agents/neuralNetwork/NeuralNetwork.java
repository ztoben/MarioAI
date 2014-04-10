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
	
	
	public void setInputLayerWeights(int[] weights);
	
	
	public void setHiddenLayerWeights(int[] weights);
	
	
	public void setOutputLayerWeights(int[] weights);
	
	
	public void setInputLayerConnections();
	
	
	public void setHiddenLayerConnections();
	
	
}