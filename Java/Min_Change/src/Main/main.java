package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class main {

	/* 
	EXPONENTIAL TIME COMPLEXITY
	
	public static int minChange(int amount, List<Integer> coins) {
		if(amount == 0) {
			return 0;
		}
		
		if(amount < 0) {
			return -1;
		}
		
		int minCoins = -1;
		
		for(int coin : coins) {
			int subAmount = amount - coin;
			System.out.println("Calling minChange(" + subAmount + ", " + coins + ")");
			int subCoins = minChange(subAmount, coins);
			if(subCoins != -1) {
				int numCoins = subCoins + 1;
				if(numCoins < minCoins || minCoins == -1) {
					minCoins = numCoins;
				}
			}
		}
		
		return minCoins;
	}
	*/
	private static Map<Long, Long> memo = new HashMap<>();
	
	public static long minChange(long amount, List<Double> coins) {
		if(amount == 0) {
			return 0;
		}
		
		if(amount < 0) {
			return -1;
		}
		
		if (memo.containsKey(amount)) {
            return memo.get(amount);
        }
		
		long minCoins = -1;
		
		for(double coin : coins) {
			long subAmount = (long) (amount - coin);
			System.out.println("Calling minChange(" + subAmount + ", " + coins + ")");
			
			long subCoins = minChange(subAmount, coins);
			
			if(subCoins != -1) {
				long numCoins = subCoins + 1;
				if(numCoins < minCoins || minCoins == -1) {
					minCoins = numCoins;
				}
			}
		}
		
		memo.put(amount, minCoins);
		return minCoins;
	}
	
	public static void main(String[] args) {
		List<Double> coins = new ArrayList<>();
        coins.add(0.1);
        coins.add(0.2);
        coins.add(0.5);
        coins.add(1.0);
        coins.add(2.0);

        long amount = 4220;

        // Measure execution time
        long startTime = System.nanoTime();
        long minCoins = minChange(amount, coins);
        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        System.out.println("The minimum amount of coins for " + amount+ "? " + minCoins);
        System.out.println("Execution time: " + executionTime + " nanoseconds");
    }
}
