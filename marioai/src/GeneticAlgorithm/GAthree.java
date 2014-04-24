package GeneticAlgorithm;

import java.util.Random;

//this is a test

public class GAthree 
{
	private int populationSize;
	private int eliteSize;
	private int inputLength;
	private int hiddenLength;
	private int outputLength;
	private int numberOfWeights;
	private Random generator;


	public GAthree(int popSize, int input, int hidden, int output)
	/* Standard constructor
	 * Assumes input nodes have no weights or thresholds
	 */
	{
		populationSize = popSize;
		eliteSize = (int) (popSize * .5); // 5% of the population

		inputLength = input;
		hiddenLength = hidden;
		outputLength = output;

		generator = new Random();

		numberOfWeights = hidden+output + input*hidden + hidden*output; //hidden thresholds + output thresholds + hiddenWeights + outputWeights
	}


	public float[][] generateFreshBatch()
	{
		float[][] generation = new float[populationSize][numberOfWeights];

		for (int i=0; i < populationSize; i++)
		{
			generation[i] = generateRandomChromosome();
		}

		return generation;
	}


	public float[][] createNewGeneration(float[][] oldGeneration, int[] scores)
	/* Takes the former generation and parallel array of scores
	 * Assumes oldGeneration[i] corresponds to scores[i]
	 * Takes the top 5% and carries them over to next generation
	 * 15% self mutations 			(of the top 5%)
	 * 10% singlePointCrossover 	(of the top 5%)
	 * 10% doublePointCrossover 	(of the top 5%)
	 * 10% breeding 				(of the top 5%)
	 * 10% threesome breeding 		(of the top 5%)
	 * 5% inversion					(of the top 5%)
	 * 5% singlePointCrossover		(of the top 5% with random generated parent)
	 * 5% doublePointCrossover		(of the top 5% with random generated parent)
	 * 5% breeding					(of the top 5% with random generated parent)
	 * 20% random generation
	 * 5+15+10+10+10+10+5+5+5+5+20 = 100
	 */
	{
		float[][] newGeneration = new float[populationSize][numberOfWeights];
		int bestChromo;

		if (scores[findMaxIndex(scores)] < 2000)
		{
			return generateFreshBatch();
		}

		for (int i=0; i < populationSize; i++)
		{
			if (i < eliteSize) // top 5%
			{
				bestChromo = findMaxIndex(scores);
				newGeneration[i] = oldGeneration[bestChromo];

				scores[bestChromo] = -10000;  // make sure we ignore that score next pass
			}
			else if (i < eliteSize * 4) // self mutate
			{
				newGeneration[i] = selfMutate(newGeneration[eliteSize % i]);
			}
			else if (i < eliteSize * 6) // single point crossover
			{
				if ((eliteSize % i) + 1 == eliteSize) // make sure to wrap around back to the top 5%
					newGeneration[i] = singlePointCrossover(newGeneration[eliteSize % i], newGeneration[0]);
				else
					newGeneration[i] = singlePointCrossover(newGeneration[eliteSize % i], newGeneration[(eliteSize % i) + 1]);
			}
			else if (i < eliteSize * 8) // double point crossover
			{
				if ((eliteSize % i) + 1 == eliteSize) // make sure to wrap around back to the top 5%
					newGeneration[i] = doublePointCrossover(newGeneration[eliteSize % i], newGeneration[0]);
				else
					newGeneration[i] = doublePointCrossover(newGeneration[eliteSize % i], newGeneration[(eliteSize % i) + 1]);
			}
			else if (i < eliteSize * 10) // breeding
			{
				if ((eliteSize % i) + 1 == eliteSize) // make sure to wrap around back to the top 5%
					newGeneration[i] = breed(newGeneration[eliteSize % i], newGeneration[0]);
				else
					newGeneration[i] = breed(newGeneration[eliteSize % i], newGeneration[(eliteSize % i) + 1]);
			}
			else if (i < eliteSize * 12) // threesome breeding
			{
				if ((eliteSize % i) + 1 == eliteSize) // make sure to wrap around back to the top 5%
					newGeneration[i] = threesome(newGeneration[eliteSize % i], newGeneration[0], newGeneration[1]);
				else if ((eliteSize % i) + 2 == eliteSize)
					newGeneration[i] = threesome(newGeneration[eliteSize % i], newGeneration[(eliteSize % i) + 1], newGeneration[0]);
				else
					newGeneration[i] = threesome(newGeneration[eliteSize % i], newGeneration[(eliteSize % i) + 1], newGeneration[(eliteSize % i) + 2]);
			}
			else if (i < eliteSize * 13) // inversion
			{
				newGeneration[i] = inversion(newGeneration[eliteSize % i]);
			}
			else if (i < eliteSize * 14) // single point crossover with random
			{
				newGeneration[i] = singlePointCrossover(newGeneration[eliteSize % i], generateRandomChromosome());
			}
			else if (i < eliteSize * 15) // double point crossover with random
			{
				newGeneration[i] = doublePointCrossover(newGeneration[eliteSize % i], generateRandomChromosome());
			}
			else if (i < eliteSize * 16) // breeding with random
			{
				newGeneration[i] = breed(newGeneration[eliteSize % i], generateRandomChromosome());
			}
			else // random leftovers
				newGeneration[i] = generateRandomChromosome();
		}

		return newGeneration;
	}


	public float[][] createNewGenerationV2(float[][] oldGeneration, int[] scores)
	/* Bring over top 5%
	 * N value mutate 25%
	 * 20% single point crossover
	 * 25% double point crossover
	 * 25% random breeding
	 */
	{
		float[][] newGeneration = new float[populationSize][numberOfWeights];
		int bestChromo;

		
		if (scores[findMaxIndex(scores)] < 2000)
		{
			return generateFreshBatch();
		}
		

		for (int i=0; i < populationSize; i++)
		{
			if (i < eliteSize) // top 5%
			{
				bestChromo = findMaxIndex(scores);
				newGeneration[i] = oldGeneration[bestChromo];

				scores[bestChromo] = -10000;  // make sure we ignore that score next pass
			}
			else if (i < eliteSize * 6) // self mutate
			{
				newGeneration[i] = nValueSelfMutate(newGeneration[eliteSize % i], 5);
			}
			else if (i < eliteSize * 10) // single point crossover
			{
				newGeneration[i] = singlePointCrossover(newGeneration[eliteSize % i], newGeneration[generator.nextInt(eliteSize)]);
			}
			else if (i < eliteSize * 15) // double point crossover
			{
				newGeneration[i] = doublePointCrossover(newGeneration[eliteSize % i], newGeneration[generator.nextInt(eliteSize)]);
			}
			else // random breeding
			{
				newGeneration[i] = breed(newGeneration[eliteSize % i], generateRandomChromosome());
			}
		}
		
		return newGeneration;
	}




		protected float[] nValueSelfMutate(float[] parent, int n)
		/* Takes in a parent and self mutates
		 * changes N random values within the chromosome
		 */
		{
			float[] chromosome = new float[numberOfWeights];
			int location;
			float mutatedGene;

			for (int i=0; i < numberOfWeights; i++)
				chromosome[i] = parent[i];

			for (int z=0; z < n; z++) // change n values
			{
				location = generator.nextInt(numberOfWeights);

				if (location < hiddenLength) // hidden threshold
					mutatedGene = generator.nextInt(inputLength);
				else if (location < hiddenLength + outputLength) // output threshold
					mutatedGene = generator.nextInt(hiddenLength);
				else // weight
				{
					if (generator.nextBoolean())
						mutatedGene = generator.nextFloat();
					else
						mutatedGene = -1 * generator.nextFloat();
				}

				chromosome[location] = mutatedGene;
			}

			return chromosome;
		}



		protected float[] selfMutate(float[] parent)
		/* Takes in a parent and self mutates
		 * if true, inherit parent gene
		 * if false, generate new weight / threshold value
		 */
		{
			float[] chromosome = new float[numberOfWeights];
			int thresholdMarker = hiddenLength + outputLength;
			int hiddenWeightMarker = thresholdMarker + hiddenLength*inputLength;

			for (int i=0; i < numberOfWeights; i++)
			{
				if (i < hiddenLength)//generate hidden thresholds
				{
					if (generator.nextBoolean())
						chromosome[i] = parent[i];
					else
						chromosome[i] = (float) generator.nextInt(inputLength);
				}
				else if (i < thresholdMarker)//generate output thresholds
				{
					if (generator.nextBoolean())
						chromosome[i] = parent[i];
					else
						chromosome[i] = (float) generator.nextInt(hiddenLength);
				}
				else if (i < hiddenWeightMarker)//generate hidden weights
				{
					if (generator.nextBoolean())
						chromosome[i] = parent[i];
					else
						if (generator.nextBoolean())
							chromosome[i] = generator.nextFloat();
						else
							chromosome[i] = -1 * generator.nextFloat();
				}
				else //generate output weights (same as before but can be changed if needed)
				{
					if (generator.nextBoolean())
						chromosome[i] = parent[i];
					else
						if (generator.nextBoolean())
							chromosome[i] = generator.nextFloat();
						else
							chromosome[i] = -1 * generator.nextFloat();
				}
			}

			return chromosome;
		}


		protected float[] singlePointCrossover(float[] father, float[] mother)
		/* Pivot point is selected,
		 * if i < pivot point, inherit from father
		 * if i > pivot point, inherit from mother
		 */
		{
			float[] chromosome = new float[numberOfWeights];
			int pivot = generator.nextInt(numberOfWeights);

			for (int i=0; i < numberOfWeights; i++)
			{
				if (i < pivot)
					chromosome[i] = father[i];
				else
					chromosome[i] = mother[i];
			}


			return chromosome;
		}


		protected float[] doublePointCrossover(float[] father, float[] mother)
		/* 2 pivots are selected,
		 * if i < pivot1, inherit from father,
		 * if i < pivot2 inherit from mother,
		 * if i > pivot2 inherit from father
		 */
		{
			float[] chromosome = new float[numberOfWeights];
			int pivot1 = generator.nextInt(numberOfWeights-1);
			int pivot2 = generator.nextInt(numberOfWeights - pivot1) + pivot1;

			for (int i=0; i < numberOfWeights; i++)
			{
				if (i < pivot1)
					chromosome[i] = father[i];
				else if (i < pivot2)
					chromosome[i] = mother[i];
				else
					chromosome[i] = father[i];
			}


			return chromosome;
		}


		protected float[] breed(float[] father, float[] mother)
		/* Creates children the old fashioned way...
		 * if true, inherit from father
		 * if false, inherit from mother
		 */
		{
			float[] chromosome = new float[numberOfWeights];

			for (int i=0; i < numberOfWeights; i++)
			{
				if (generator.nextBoolean())
					chromosome[i] = father[i];
				else
					chromosome[i] = mother[i];
			}


			return chromosome;
		}


		protected float[] threesome(float[] father, float[] mother, float[] voyeur)
		/* Creates children from 3 parents... (wait what?)
		 * if 0, inherit from father
		 * if 1, inherit from mother
		 * if 2, inherit from voyeur
		 */
		{
			float[] chromosome = new float[numberOfWeights];
			int tempParent;

			for (int i=0; i < numberOfWeights; i++)
			{
				tempParent = generator.nextInt(3);

				if (tempParent == 0)
					chromosome[i] = father[i];
				else if (tempParent == 1)
					chromosome[i] = mother[i];
				else
					chromosome[i] = voyeur[i];
			}


			return chromosome;
		}


		protected float[] inversion(float[] parent)
		/* Inverts a random number of WEIGHTS
		 * if true, weight is inverted
		 * example: .5 inverts to -.5
		 */
		{
			float[] chromosome = new float[numberOfWeights];

			for (int i=0; i < numberOfWeights; i++)
			{
				if (i > hiddenLength + outputLength) // must not invert thresholds, only weights
				{
					if (generator.nextBoolean())
						chromosome[i] = -1 * parent[i];
					else
						chromosome[i] = parent[i];
				}
			}

			return chromosome;
		}


		protected float[] generateRandomChromosome()
		{
			float[] chromosome = new float[numberOfWeights];
			int thresholdMarker = hiddenLength + outputLength;
			int hiddenWeightMarker = thresholdMarker + hiddenLength*inputLength;

			for (int i=0; i < numberOfWeights; i++)
			{
				if (i < hiddenLength)//generate hidden thresholds
				{
					chromosome[i] = (float) generator.nextInt(inputLength);
				}
				else if (i < thresholdMarker)//generate output thresholds
				{
					chromosome[i] = (float) generator.nextInt(5);
				}
				else if (i < hiddenWeightMarker)//generate hidden weights
				{
					if (generator.nextBoolean())
						chromosome[i] = generator.nextFloat();
					else
						chromosome[i] = -1 * generator.nextFloat();
				}
				else //generate output weights (same as before but can be changed if needed)
				{
					if (generator.nextBoolean())
						chromosome[i] = generator.nextFloat();
					else
						chromosome[i] = -1 * generator.nextFloat();
				}
			}

			return chromosome;
		}


		protected int findMaxIndex(int[] scores)
		{
			int maxIndex = 0;

			for (int i=1; i < scores.length; i++)
			{
				if (scores[i] > scores[maxIndex])
					maxIndex = i;
			}

			return maxIndex;
		}
	}
