package marioManiacs.agents.neuralNetwork;

/* BASE INTERFACE
 * All nodes will implement this interface
 */

public interface NeuralNode 
{
	int getWeight(); // Returns the weight OR value contained within the current node
	
	boolean isFiring(); // Returns a boolean representing whether or not the node is firing
	
	NeuralNode[] getForwardConnections(); // Returns an array of nodes that represents the nodes that this node will feed to
	
	NeuralNode[] getRearConnections(); // Returns an array of nodes that represents the nodes that this node is receiving info from
	
	void setWeight(int weight);
	
	void setFrontConnections(NeuralNode[] connections);
	
	void setRearConnections(NeuralNode[] connections);
	
	void setValue(int value);  //input node only
	
	void setValues();  //hidden and output nodes only
	
	void run();
	
	void setInputWeights(); // not currently using
}
