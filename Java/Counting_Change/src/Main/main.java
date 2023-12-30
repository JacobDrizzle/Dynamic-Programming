package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

	/*
	// EXPONENTIAL TIME COMPLEXITY
	
	public static int countChange(int amount, int coinIndex, List<Integer> coins) {
		// If the amount becomes 0, we found a way to make change
		if (amount == 0) {
            return 1;
        }
        // Check if we've exhausted all available coins
        if (coinIndex >= coins.size()) {
            return 0;
        }

        int totalWays = 0;
        int value = coins.get(coinIndex);
        
        // Iterate through all possible quantities of the current coin
        for (int qty = 0; qty * value <= amount; qty += 1) {
        	
        	// Calculate the remaining amount after using the current quantity of the current coin
            int subAmount = amount - (qty * value);
            
            // Recursive call to explore combinations with the remaining amount and the next coin
            totalWays += countChange(subAmount, coinIndex + 1, coins);
        }

        return totalWays;
    }
	
	*/
	// Memoization: Map to store previously computed results
    private static Map<List<Integer>, Long> memo = new HashMap<>();
    // Function to count the number of ways to make change
    public static long countChange(int amount, int coinIndex, List<Integer> coins) {
        // Check if the result for this subproblem is already memoized
        List<Integer> key = List.of(amount, coinIndex);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // If the amount becomes 0, we found a way to make change
        if (amount == 0) {
            return 1;
        }

        // If we've exhausted all available coins
        if (coinIndex >= coins.size()) {
            return 0;
        }

        long totalWays = 0;
        int value = coins.get(coinIndex);

        // Iterate through all possible quantities of the current coin
        for (int qty = 0; qty * value <= amount; qty += 1) {
            // Calculate the remaining amount after using the current quantity of the current coin
            int subAmount = amount - (qty * value);

            // Recursive call to explore combinations with the remaining amount and the next coin
            totalWays += countChange(subAmount, coinIndex + 1, coins);
        }

        // Memoize the result before returning
        memo.put(key, totalWays);
        return totalWays;
    }
	
	public static void main(String[] args) {
		List<Integer> coins = new ArrayList<>();
        coins.add(1);
        coins.add(2);
        coins.add(3);

        int amount = 4;

        // Measure execution time
        long startTime = System.nanoTime();
        long Change = countChange(amount,0, coins);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        System.out.println("The number of ways to make change for " + amount + ": " + Change);
        System.out.println("Execution time: " + executionTime + " nanoseconds");
    }
}
