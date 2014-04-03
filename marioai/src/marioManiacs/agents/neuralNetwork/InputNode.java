package marioManiacs.agents.neuralNetwork;

/* Represents an input node on the Neural Network
 * Contains a value that it receives from the world, 
 * and a list of nodes that it will send that value to
 */

public class InputNode implements NeuralNode
{
	float value;
	HiddenNode[] nodesToFeed;
}
