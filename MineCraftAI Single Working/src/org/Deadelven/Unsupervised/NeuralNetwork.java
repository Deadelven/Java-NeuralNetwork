package org.Deadelven.Unsupervised;

import java.util.ArrayList;
import java.util.Random;

public class NeuralNetwork
{
	private ArrayList<Integer> layers; // layers
	private ArrayList<ArrayList<Float>> neurons; // neuron matix
	private ArrayList<ArrayList<ArrayList<Float>>> weights; // weight matrix
	private float fitness; // fitness of the network
	public Random rdm = new Random();

	/// <summary>
	/// Initilizes and neural network with random weights
	/// </summary>
	/// <param name="layers">layers to the neural network</param>
	public NeuralNetwork (int[] layersI)
	{
		// deep copy of layers of this network
		this.layers = new ArrayList<Integer>();
		for (int i = 0; i < layersI.length; i++)
		{
			// System.out.println("Layers Setup " + layersI.length + " " + layersI + " " +
			// i);
			this.layers.add(layersI[i]);
		}

		// generate matrix
		InitNeurons();
		InitWeights();
	}

	/// <summary>
	/// Create neuron matrix
	/// </summary>
	private void InitNeurons()
	{
		// Neuron Initilization
		ArrayList<ArrayList<Float>> neuronsList = new ArrayList<ArrayList<Float>>();

		for (int i = 0; i < layers.size(); i++) // run through all layers
		{
			// System.out.println("Setup layers " + layers.size() + " " + i);
			ArrayList<Float> ff = new ArrayList<Float>();
			ff.add((float) layers.get(i));
			neuronsList.add(ff); // add layer to neuron list
		}

		neurons = neuronsList; // convert list to array
	}

	/// <summary>
	/// Create weights matrix.
	/// </summary>
	private void InitWeights()
	{

		ArrayList<ArrayList<ArrayList<Float>>> weightsList = new ArrayList<ArrayList<ArrayList<Float>>>();

		// itterate over all neurons that have a weight connection
		for (int i = 1; i < layers.size(); i++)
		{
			ArrayList<ArrayList<Float>> layerWeightsList = new ArrayList<ArrayList<Float>>(); // layer weight list for
																								// this current layer
			// (will be
			// converted to 2D array)

			int neuronsInPreviousLayer = layers.get(i - 1);

			// itterate over all neurons in this current layer
			for (int j = 0; j < neurons.get(i).size(); j++)
			{
				ArrayList<Float> neuronWeights = new ArrayList<Float>(); // neruons weights

				// itterate over all neurons in the previous layer and set the weights randomly
				// between 0.5f and -0.5
				for (int k = 0; k < neuronsInPreviousLayer; k++)
				{
					// give random weights to neuron weights
					neuronWeights.add((rdm.nextFloat() - 0.5f));
				}

				layerWeightsList.add(neuronWeights); // add neuron weights of this current layer to layer weights
			}

			weightsList.add(layerWeightsList); // add this layers weights converted into 2D array into weights
												// list
		}

		weights = weightsList; // convert to 3D array
	}

	/// <summary>
	/// Feed forward this neural network with a given input array
	/// </summary>
	/// <param name="inputs">Inputs to network</param>
	/// <returns></returns>
	public ArrayList<Float> FeedForward(ArrayList<Float> inputs)
	{
		// Add inputs to the neuron matrix
		// System.out.println("Inputs size = " + inputs.size());
		// System.out.println("Neurons size = " + neurons.get(0).size());
		for (int i = 0; i < inputs.size(); i++)
		{
			neurons.get(0).set(i, inputs.get(i));
		}

		// itterate over all neurons and compute feedforward values
		for (int i = 1; i < layers.size(); i++)
		{
			for (int j = 0; j < neurons.get(i).size(); j++)
			{
				float value = 0f;

				for (int k = 0; k < neurons.get(i - 1).size(); k++)
				{
					value += weights.get(i - 1).get(j).set(k, neurons.get(i - 1).get(k)); // sum off all weights
																							// connections of this
																							// neuron
					// weight their values in previous layer
				}

				neurons.get(i).set(j, (float) Math.tanh(value)); // Hyperbolic tangent activation
			}
		}
		// System.out.println("FeedFowardResult " + neurons.get(neurons.size() - 1));
		return neurons.get(neurons.size() - 1); // return output layer
	}

}
