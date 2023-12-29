package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

    // Use a HashMap for memoization to store the minimum number of coins for given amounts
    private static Map<Long, Long> memo = new HashMap<>();
    
    // Function to find the minimum number of coins needed to make a given amount
    public static long minChange(long amount, List<Double> coins) {
        // Base case: if the amount is 0, no coins are needed
        if(amount == 0) {
            return 0;
        }
        
        // Base case: if the amount is negative, return -1 indicating it's not possible
        if(amount < 0) {
            return -1;
        }
        
        // Check if the minimum coins for the current amount is already computed
        if (memo.containsKey(amount)) {
            return memo.get(amount); // Return the stored value to avoid re-computation
        }
        
        long minCoins = -1; // Initialize minCoins as -1 to indicate not found or infinitely large
        
        // Loop through each coin to find the minimum number of coins
        for(double coin : coins) {
            long subAmount = (long) (amount - coin); // Subtract the coin value from the amount
            System.out.println("Calling minChange(" + subAmount + ", " + coins + ")");
            
            long subCoins = minChange(subAmount, coins); // Recursive call with the reduced amount
            
            // Check if a valid solution is found (subCoins != -1) and update minCoins if it's better
            if(subCoins != -1) {
                long numCoins = subCoins + 1;
                if(numCoins < minCoins || minCoins == -1) {
                    minCoins = numCoins;
                }
            }
        }
        
        memo.put(amount, minCoins); // Store the computed minimum coins in the memo map
        return minCoins; // Return the computed minimum coins
    }
    
    public static void main(String[] args) {
        // Define the list of coins available
        List<Double> coins = new ArrayList<>();
        coins.add(0.1);
        coins.add(0.2);
        coins.add(0.5);
        coins.add(1.0);
        coins.add(2.0);

        long amount = 4220; // Define the amount for which we need to find the minimum coins

        // Measure execution time
        long startTime = System.nanoTime();
        long minCoins = minChange(amount, coins); // Call the function to find minimum coins
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime; // Calculate the execution time

        // Output the result and execution time
        System.out.println("The minimum amount of coins for " + amount+ "? " + minCoins);
        System.out.println("Execution time: " + executionTime + " nanoseconds");
    }
}