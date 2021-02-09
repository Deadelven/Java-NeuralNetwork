package org.Deadelven.Main;

import static org.junit.jupiter.api.Assertions.fail;

import org.Deadelven.Unsupervised.NeuralNetwork;
import org.junit.jupiter.api.Test;

class testEven
{

	@Test
	void test()
	{
		int[] layers = new int[]
		{ 1, 10, 25, 10, 5, 1 };
		NeuralNetwork nn = new NeuralNetwork(layers);

		int counter = 0;
		int total = 0;

		int trval = 0;
		int bestval = 0;
		while (trval <= 5)
		{
			trval = 0;
			if (Main.testIntEven(2, nn))
			{
				trval += 1;
			}
			if (Main.testIntEven(4, nn))
			{
				trval += 1;
			}
			if (Main.testIntEven(6, nn))
			{
				trval += 1;
			}
			if (Main.testIntEven(8, nn))
			{
				trval += 1;
			}
			if (Main.testIntOdd(1, nn))
			{
				trval += 1;
			}
			if (Main.testIntOdd(3, nn))
			{
				trval += 1;
			}
			if (Main.testIntOdd(5, nn))
			{
				trval += 1;
			}
			if (Main.testIntOdd(7, nn))
			{
				trval += 1;
			}
			if (Main.testIntOdd(9, nn))
			{
				trval += 1;
			}

			if (trval > bestval)
			{
				bestval = trval;
				System.out.println("BestVal " + bestval);
			}
		}

		System.out.println(trval);
		/*
		 * while (Main.testIntEven(2, nn) == false || Main.testIntEven(4, nn) == false
		 * || Main.testIntEven(6, nn) == false || Main.testIntEven(8, nn) == false ||
		 * Main.testIntOdd(1, nn) == false || Main.testIntOdd(3, nn) == false ||
		 * Main.testIntOdd(5, nn) == false || Main.testIntOdd(7, nn) == false ||
		 * Main.testIntOdd(9, nn) == false) { nn = new NeuralNetwork(layers); counter +=
		 * 1; total += 1; if (counter >= 1000) { System.out.println("Count " + total);
		 * counter = 0; }
		 * 
		 * // System.out.println("Count " + counter); }
		 */

		fail("Not yet implemented");
	}

}
