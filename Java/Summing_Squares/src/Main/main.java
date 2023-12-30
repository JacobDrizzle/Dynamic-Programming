package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class main {
	/*
	 // EXPONENTIAL TIME COMPLEXITY
	 
    public static int summingSquares(int n) {
        if (n == 0) {
        	return 0;
        }
        double minSquares = Double.POSITIVE_INFINITY;
        for(int i =1; i <= Math.sqrt(n); i+= 1) {
        	int square = i * i;
        	double numSquares = 1 + summingSquares(n - square);
        	if (numSquares < minSquares) {
        		minSquares = numSquares;
        	}
        }
        return (int)minSquares;
    }
	*/
    // A map for memoization to store the minimum number of squares that sum to 'n'
    private static Map<Integer, Double> memo = new HashMap<>();
    
    // Function to find the minimum number of perfect squares that sum to 'n'
    public static double summingSquares(int n) {
        System.out.println("Calculating summingSquares for n = " + n);

        // Base case: if n is 0, no squares are needed
        if (n == 0) {
            return 0;
        }

        // Check if the result for this 'n' is already computed
        if (memo.containsKey(n)) {
            double memoizedResult = memo.get(n);
            System.out.println("Found memoized result for n = " + n + ": " + memoizedResult);
            return memoizedResult;
        }

        // Initialize the minimum number of squares needed as infinity
        double minSquares = Double.POSITIVE_INFINITY;

        // Loop through all possible squares less than or equal to 'n'
        for (int i = 1; i <= Math.sqrt(n); i++) {
            int square = i * i; // Calculate the square of 'i'
            double numSquares = 1 + summingSquares(n - square); // Recursive call with the remainder

            // Update minSquares if a smaller number of squares is found
            if (numSquares < minSquares) {
                minSquares = numSquares;
            }
        }

        // Store the calculated result in memo map for 'n'
        double result = minSquares;
        memo.put(n, result);
        System.out.println("Calculated summingSquares for n = " + n + ": " + result);

        return result; // Return the minimum number of squares
    }
    
    public static void main(String[] args) {
        // Measure the execution time of summingSquares
        long startTime = System.nanoTime();
        double sum = summingSquares(235); // Calculate the minimum number of squares for 235
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Output the result and the execution time
        System.out.println("Sum is: " + sum);
        System.out.println("Execution time: " + executionTime + " nanoseconds");
    }
}