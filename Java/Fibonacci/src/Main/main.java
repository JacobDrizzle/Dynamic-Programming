package Main;

import java.util.HashMap;
import java.util.Map;

public class main {
	/* 
	EXPONENTIAL TIME COMPLEXITY
	
	public static int fib(int n) {
		if(n == 0 || n == 1) {
			return n;
		}
		return fib(n-1)+ fib(n-2);
	}
	*/
	
	// LINEAR TIME COMPLEXITY
	private static Map<Integer, Long> memo = new HashMap<>();

    public static long fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        // Check if the value is already memoized
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        // Calculate and memoize the Fibonacci value
        long result = fib(n - 1) + fib(n - 2);
        memo.put(n, result);
        return result;
    }
    
	public static void main(String[] args) {
		int n = 70; // You can change this to the desired Fibonacci sequence position

		long start = System.currentTimeMillis();
        for (int i = 0; i <= n; i++) {
            long startTime = System.currentTimeMillis();

            long result = fib(i);

            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            System.out.println("Fibonacci number at position " + i + ": " + result + " - Time taken: " + elapsedTime + "ms");
        }
        long end = System.currentTimeMillis();
        long elapsed = end - start;
        System.out.println("Time to Compute: " + elapsed + " ms");
	}
}