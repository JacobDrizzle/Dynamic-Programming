package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class main {
	/*
	 EXPONENTIAL TIME COMPLEXITY 
	 
	public static int countPaths(int r, int c, List<List<String>> grid) {
		
		 // check if out of bounds
        if (r == grid.size() || c == grid.get(0).size()) {
            System.out.println("Out of bounds at (" + r + ", " + c + ")");
            return 0;
        }

        if (grid.get(r).get(c).equals("X")) {
            System.out.println("Obstacle at (" + r + ", " + c + ")");
            return 0;
        }

        // Check if in the bottom right corner of the grid
        if (r == grid.size() - 1 && c == grid.get(0).size() - 1) {
            System.out.println("Reached the bottom right corner at (" + r + ", " + c + ")");
            return 1;
        }

        int pathsFromHere = countPaths(r + 1, c, grid) + countPaths(r, c + 1, grid);
        System.out.println("Paths from (" + r + ", " + c + "): " + pathsFromHere);
        
        return pathsFromHere;
	}
	*/
    
	// Memoization map to store previously computed path counts
    private static Map<List<Integer>, Long> memo = new HashMap<>();
    
    // Function to count the number of paths from top-left to bottom-right in a grid
    public static long countPaths(int r, int c, List<List<String>> grid) {
        // Check if the current position is out of bounds
        if (r == grid.size() || c == grid.get(0).size()) {
            System.out.println("Out of bounds at (" + r + ", " + c + ")");
            return 0;
        }

        // Check if the current position is an obstacle
        if (grid.get(r).get(c).equals("X")) {
            System.out.println("Obstacle at (" + r + ", " + c + ")");
            return 0;
        }

        // Check if the current position is the bottom-right corner of the grid
        if (r == grid.size() - 1 && c == grid.get(0).size() - 1) {
            System.out.println("Reached the bottom right corner at (" + r + ", " + c + ")");
            return 1;
        }

        // Create a key representing the current position
        List<Integer> pos = List.of(r, c);
        // Check if the number of paths from this position is already computed
        if(memo.containsKey(pos)) {
            return memo.get(pos); // Return the stored value to avoid re-computation
        }
        
        // Recursive calls to count the paths from the current position
        long result = countPaths(r + 1, c, grid) + countPaths(r, c + 1, grid);
        memo.put(pos, result); // Store the computed number of paths in the memo map
        
        System.out.println("Paths from (" + r + ", " + c + "): " + result);
        
        return result; // Return the total number of paths from the current position
    }
    
    // Function to generate a grid with random obstacles
    private static List<List<String>> generateGrid(int rows, int cols, double obstacleProbability) {
        List<List<String>> grid = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                // Add an obstacle or open path based on the obstacle probability
                row.add(random.nextDouble() < obstacleProbability ? "X" : "O");
            }
            grid.add(row);
        }

        return grid; // Return the generated grid
    }
    
    public static void main(String[] args) {
        // Generate a grid with a specified size and obstacle probability
        List<List<String>> grid = generateGrid(30, 30, 0.05); // 5% chance of obstacle

        // Measure the time taken to count the number of paths
        long startTime = System.currentTimeMillis();
        long result = countPaths(0, 0, grid); // Start counting paths from the top-left corner (0, 0)
        long endTime = System.currentTimeMillis();

        // Print the result and the time taken for execution
        System.out.println("Number of paths: " + result);
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }
}
