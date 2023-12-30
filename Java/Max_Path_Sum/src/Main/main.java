package Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class main {

	/* 
	// EXPONENTIAL TIME COMPLEXITY
	
	public static double maxSumPath(int r, int c, List<List<Integer>> grid, List<String> path, List<Integer> values) {
        if (r == grid.size() || c == grid.get(0).size()) {
            return Double.NEGATIVE_INFINITY;
        }

        if (r == grid.size() - 1 || c == grid.get(0).size() - 1) {
            path.add("(" + r + "," + c + ")");
            values.add(grid.get(r).get(c));
            return grid.get(r).get(c);
        }

        double rightSum = maxSumPath(r, c + 1, grid, path, values);
        double downSum = maxSumPath(r + 1, c, grid, path, values);

        if (rightSum > downSum) {
            path.add("(" + r + "," + c + ")");
            values.add(grid.get(r).get(c));
            return grid.get(r).get(c) + rightSum;
        } else {
            path.add("(" + r + "," + c + ")");
            values.add(grid.get(r).get(c));
            return grid.get(r).get(c) + downSum;
        }
    }
	*/

	// A map for memoization to store the maximum sum for given positions
    private static Map<String, Double> memo = new HashMap<>();

    // Recursive function to find the path with the maximum sum in a grid
    public static double maxSumPath(int r, int c, List<List<Integer>> grid, List<String> path, List<Integer> values) {
        String key = r + "," + c; // Create a unique key for the current position

        // Check if the result for this position is already computed
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // Check if out of bounds
        if (r == grid.size() || c == grid.get(0).size()) {
            return Double.NEGATIVE_INFINITY;
        }

        // Check if the bottom-right corner is reached
        if (r == grid.size() - 1 && c == grid.get(0).size() - 1) {
            path.add("(" + r + "," + c + ")");
            values.add(grid.get(r).get(c));
            return grid.get(r).get(c);
        }

        // Recursive call for right and down paths
        double rightSum = maxSumPath(r, c + 1, grid, path, values);
        double downSum = maxSumPath(r + 1, c, grid, path, values);

        // Store the better path (greater sum) and its value
        double result;
        if (rightSum > downSum) {
            path.add("(" + r + "," + c + ")");
            values.add(grid.get(r).get(c));
            result = grid.get(r).get(c) + rightSum;
        } else {
            path.add("(" + r + "," + c + ")");
            values.add(grid.get(r).get(c));
            result = grid.get(r).get(c) + downSum;
        }

        memo.put(key, result); // Memoize the result
        return result;
    }
    
    // Function to generate a grid of random integers
    private static List<List<Integer>> generateGrid(int numRows, int numCols) {
        Random random = new Random();
        List<List<Integer>> grid = new ArrayList<>();

        for (int i = 0; i < numRows; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < numCols; j++) {
                row.add(random.nextInt(100)); // Random number between 0 and 99
            }
            grid.add(row);
        }

        return grid;
    }
    
    public static void main(String[] args) {
        // Generate a grid of specified size
        int numRows = 130;
        int numCols = 130;
        List<List<Integer>> grid = generateGrid(numRows, numCols);
        
        List<String> path = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        
        memo.clear(); // Clear memoization map before each run

        // Measure the execution time of the maxSumPath function
        long startTime = System.nanoTime();
        double maxSum = maxSumPath(0, 0, grid, path, values);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Reverse the path and values to show from start to end
        Collections.reverse(path);
        Collections.reverse(values);

        // Output the result
        System.out.println("Path: " + path);
        System.out.println("\nValues: " + values);
        System.out.println("\nMaximum sum path is: " + maxSum);
        System.out.println("\nExecution time: " + executionTime + " nanoseconds");
    }
}