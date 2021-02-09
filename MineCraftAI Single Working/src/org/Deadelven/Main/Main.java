package org.Deadelven.Main;

import java.util.ArrayList;

import org.Deadelven.Unsupervised.NeuralNetwork;

public class Main
{
	public static void main()
	{
		int[] layers = new int[]
		{ 1, 10, 25, 10, 5, 1 };
		NeuralNetwork nn = new NeuralNetwork(layers);
		ArrayList<Float> data = new ArrayList<Float>();
		data.add((float) 2);
		ArrayList<Float> result = nn.FeedForward(data);
		System.out.println(result);
	}

	public static boolean testIntEven(int i, NeuralNetwork nn)
	{
		// int[] layers = new int[]
		// { 1, 10, 25, 10, 5, 1 };
		// NeuralNetwork nn = new NeuralNetwork(layers);
		ArrayList<Float> data = new ArrayList<Float>();
		data.add((float) i);
		ArrayList<Float> result = nn.FeedForward(data);
		// System.out.println(result);
		if (result.get(0) > 0f)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public static boolean testIntOdd(int i, NeuralNetwork nn)
	{
		// int[] layers = new int[]
		// { 1, 10, 25, 10, 5, 1 };
		// NeuralNetwork nn = new NeuralNetwork(layers);
		ArrayList<Float> data = new ArrayList<Float>();
		data.add((float) i);
		ArrayList<Float> result = nn.FeedForward(data);
		// System.out.println(result);
		if (result.get(0) < 0f)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
