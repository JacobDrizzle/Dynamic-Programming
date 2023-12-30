package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class main {
	/*
	 // EXPONENTIAL TIME COMPLEXITY
	 
    public static int nonAdjacentSum(List<Integer> nums, int i) {
        if (i >= nums.size()) {
            return 0;
        }
        return Math.max(nums.get(i) + nonAdjacentSum(nums, i + 2), nonAdjacentSum(nums, i + 1));
    }
	*/

    // Memoization map to store results for indices
    private static Map<Integer, Integer> memo = new HashMap<>();

    // Function to calculate the maximum sum of non-adjacent numbers
    public static int nonAdjacentSum(List<Integer> nums, int i) {
        // Base case: if the index is beyond the list size
        if (i >= nums.size()) {
            return 0;
        }

        // Check if the result for this index is already computed
        if (memo.containsKey(i)) {
            return memo.get(i); // Return the stored result
        }

        // Calculate sum including the current element and skipping one element ahead
        int includeCurrent = nums.get(i) + nonAdjacentSum(nums, i + 2);
        // Calculate sum by skipping the current element
        int excludeCurrent = nonAdjacentSum(nums, i + 1);

        // Determine the maximum sum at this point
        int result = Math.max(includeCurrent, excludeCurrent);

        // Store the result in the map for memoization
        memo.put(i, result);

        return result; // Return the computed maximum sum
    }

    // Function to generate a list of random numbers
    private static List<Integer> generateNumbers(int size) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            numbers.add(random.nextInt(100)); // Generating random numbers up to 100
        }

        return numbers; // Return the generated list of numbers
    }
    
    public static void main(String[] args) {
        // Generate a list of random numbers of a specified size
        List<Integer> nums = generateNumbers(125);

        // Measure the execution time of the nonAdjacentSum function
        long startTime = System.nanoTime();
        int sum = nonAdjacentSum(nums, 0); // Calculate the sum starting from index 0
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Output the generated numbers, the sum, and the execution time
        System.out.println("Generated Numbers: " + nums);
        System.out.println("Sum is: " + sum);
        System.out.println("Execution time: " + executionTime + " nanoseconds");
    }
}