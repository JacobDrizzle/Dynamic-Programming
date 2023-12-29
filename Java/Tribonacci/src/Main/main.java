package Main;

import java.util.HashMap;
import java.util.Map;

public class main {
    // Use a HashMap for memoization to store previously computed Fibonacci numbers
    private static Map<Integer, Long> memo = new HashMap<>();

    // Efficient implementation of Fibonacci sequence with memoization
    public static long fib(int n) {
        // Base cases: return n for fib(0) and fib(1)
        if (n == 0 || n == 1) {
            return n;
        }

        // Additional base case: return 1 for fib(2)
        if (n == 2) {
            return 1;
        }

        // Check if the Fibonacci number for 'n' is already computed and stored in the map
        if (memo.containsKey(n)) {
            return memo.get(n); // Return the stored value to avoid re-computation
        }

        // Recursive call: compute the Fibonacci number using the formula and memoization
        long result = fib(n - 1) + fib(n - 2) + fib(n - 3);
        memo.put(n, result); // Store the computed value in the map for future reference
        return result;
    }

    public static void main(String[] args) {
        int n = 70; // Set the upper limit for Fibonacci sequence computation

        long start = System.currentTimeMillis(); // Start time for overall computation

        // Loop to compute Fibonacci numbers from 0 to n
        for (int i = 0; i <= n; i++) {
            long startTime = System.currentTimeMillis(); // Start time for individual Fibonacci number computation

            long result = fib(i); // Compute the Fibonacci number

            long endTime = System.currentTimeMillis(); // End time for individual Fibonacci number computation
            long elapsedTime = endTime - startTime; // Calculate time taken for individual Fibonacci number computation

            // Print the Fibonacci number and time taken for its computation
            System.out.println("Fibonacci number at position " + i + ": " + result + " - Time taken: " + elapsedTime + "ms");
        }
        long end = System.currentTimeMillis(); // End time for overall computation
        long elapsed = end - start; // Calculate total time taken for computing the entire sequence

        // Print the total time taken for computing the entire Fibonacci sequence
        System.out.println("Time to Compute: " + elapsed + " ms");
    }
}