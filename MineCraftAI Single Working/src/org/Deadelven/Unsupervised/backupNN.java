package org.Deadelven.Unsupervised;

import java.util.Random;

public class backupNN implements Comparable<backupNN>
{
	public int[] layers;
	public float[][] neurons;
	public float[][][] weights;
	Random random = new Random(System.currentTimeMillis());
	private float fitness;

	public float getFitness()
	{
		return fitness;
	}

	public void setFitness(float fitness)
	{
		this.fitness = fitness;
	}

	public backupNN (int[] inputlayers)
	{
		this.layers = new int[inputlayers.length];
		for (int i = 0; i < inputlayers.length; i++)
		{
			this.layers[i] = inputlayers[i];
		}

		Random random = new Random(System.currentTimeMillis());
		InitNeurons();
		InitWeights();
	}

	public backupNN (backupNN copyNetwork)
	{
		this.layers = new int[copyNetwork.layers.length];
		for (int i = 0; i < copyNetwork.layers.length; i++)
		{
			this.layers[i] = copyNetwork.layers[i];
		}
		InitNeurons();
		InitWeights();

		copyWeights(copyNetwork.weights);
	}

	private void copyWeights(float[][][] copyWeights)
	{
		for (int i = 0; i < weights.length; i++)
		{
			for (int j = 0; j < weights[i].length; j++)
			{
				for (int k = 0; k < weights[i][j].length; k++)
				{
					weights[i][j][k] = copyWeights[i][j][k];
				}
			}
		}

	}

	private void InitNeurons()
	{
		// ArrayList<float[]> neuronsList = new ArrayList<float[]>();
		float[][] neuronsList = new float[layers.length][];
		for (int i = 0; i < layers.length; i++)
		{
			neuronsList[i] = new float[layers[i]];
		}
		System.out.println("Init neurons");
		System.out.println(neuronsList.length + " < list ");
		neurons = neuronsList;
		System.out.println("check " + neurons + " " + neuronsList);
		System.out.println("checknLength " + neurons.length + " " + neurons[0].length);
		System.out.println("checkLLength " + neuronsList.length + " " + neuronsList[0].length);
	}

	private void InitWeights()
	{
		// TODO Auto-generated method stub
		// ArrayList<float[][]> weightList = new ArrayList<float[][]>();
		float[][][] weightList = new float[layers.length][][];
		for (int i = 1; i < layers.length; i++)
		{
			// ArrayList<float[]> layerWeightList = new ArrayList<float[]>();
			float[][] layerWeightList = new float[neurons[i].length][];
			int neuronsInPreviousLayer = layers[i - 1];

			for (int j = 0; j < neurons[i].length; j++)
			{
				float[] neuronWeights = new float[neuronsInPreviousLayer];

				for (int k = 0; k < neuronsInPreviousLayer; k++)
				{
					// random weights
					neuronWeights[k] = (float) (random.nextDouble() - 0.5f);
				}
				// layerWeightList.add(neuronWeights);
				layerWeightList[j] = neuronWeights;

			}
			// weightList.add((float[][]) layerWeightList.toArray());
			weightList[i] = layerWeightList;
		}
		weights = (float[][][]) weightList;

	}

	public float[] FeedFoward(float[] inputs)
	{
		for (int i = 0; i < inputs.length; i++)
		{
			System.out.println("ErrorCheck");
			System.out.println(neurons[0].length + " " + neurons + " " + inputs.length);
			System.out.println(neurons.toString());

			neurons[0][i] = inputs[i];
		}
		for (int i = 1; i < layers.length; i++)
		{
			for (int j = 0; j < neurons[i].length; j++)
			{
				float value = 0.25f;
				for (int k = 0; k < neurons[i - 1].length; k++)
				{
					value += weights[i - 1][j][k] * neurons[i - 1][k];
				}
				neurons[i][j] = (float) Math.tanh(value);
			}
		}
		return neurons[neurons.length - 1];
	}

	public void Mutate()
	{
		for (int i = 0; i < weights.length; i++)
		{
			for (int j = 0; j < weights[i].length; j++)
			{
				for (int k = 0; k < weights[i][j].length; k++)
				{
					float weight = weights[i][j][k];

					random = new Random(System.currentTimeMillis());
					float randomNumber = (float) random.nextDouble() * 1000f;

					if (randomNumber <= 2f)
					{
						weight *= -1f;
					}
					else if (randomNumber <= 4f)
					{
						if (random.nextBoolean() == true)
						{
							weight = random.nextFloat();
						}
						else
						{
							float nextfloat = random.nextFloat();
							weight = (nextfloat - (nextfloat * 2));
						}
					}
					else if (randomNumber <= 6f)
					{
						float factor = random.nextFloat() + 1f;
						weight *= factor;
					}
					else if (randomNumber <= 8f)
					{
						float factor = random.nextFloat();
						weight *= factor;
					}

					// mutate
					weights[i][j][k] = weight;
				}
			}
		}
	}

	@Override
	public int compareTo(backupNN other)
	{
		// fitness start with 100 points. if leaves are below block 2 - 5 points if wood
		// isnt places - points

		if (other == null) return 1;
		if (fitness > other.fitness)
		{
			return 1;
		}
		else if (fitness < other.fitness)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}
