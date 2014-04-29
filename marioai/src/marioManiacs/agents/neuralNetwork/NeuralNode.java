package marioManiacs.agents.neuralNetwork;

/* BASE INTERFACE
 * All nodes will implement this interface
 */

/*
 * File: NeuralNode.java
 * Author: Jesse Miller, Mat Kakavas
 * Last modified: 4/15/14
 */
public interface NeuralNode 
{
	float getWeight(); // Returns the weight OR value contained within the current node
	
	boolean isFiring(); // Returns a boolean representing whether or not the node is firing
	
	NeuralNode[] getForwardConnections(); // Returns an array of nodes that represents the nodes that this node will feed to
	
	NeuralNode[] getRearConnections(); // Returns an array of nodes that represents the nodes that this node is receiving info from
	
	void setWeight(float weight);
	
	void setFrontConnections(NeuralNode[] connections);
	
	void setRearConnections(NeuralNode[] connections);
	
	void run();
}
