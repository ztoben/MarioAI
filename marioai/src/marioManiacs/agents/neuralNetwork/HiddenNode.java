package marioManiacs.agents.neuralNetwork;

/* Represents a node within the Hidden Layer of the Neural Network
 * This node contains a weight value, a list of the nodes it is connected to
 * and a list of nodes it will receive values from
 */

public class HiddenNode implements NeuralNode
{
	float internalWeight;
	InputNode[] inputNodes;
	OutputNode[] outputNodes;
}
