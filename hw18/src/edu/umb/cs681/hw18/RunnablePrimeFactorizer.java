package edu.umb.cs681.hw18;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// Generates prime factors of a given number (dividend)
// Factorization is performed in the range of 2 and Math.sqrt(dividend)
//
// This file was edited from previous assignments hw8cs681 and hw4cs681
// The new assignment was to replace the existing Runnable/Thread code and use an Executor instead

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {
	
	public RunnablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException(
				"from must be >= 2, and to must be >= from." + "from==" + from + " to==" + to);
		}
	}
	
	protected boolean isEven(long n){
		if(n%2 == 0){ return true; }
		else{ return false; }
	}

	public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
	    	if( divisor > 2 && isEven(divisor)) {
	    		divisor++;
	    		continue;
	    	}
		    if(dividend % divisor == 0) {
		        factors.add(divisor);
		        dividend /= divisor;
		    }else {
		    	if(divisor==2){ divisor++; }
		    	else{ divisor += 2; }
		    }
		}
	}
	
	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().getId() + " generated " + factors);
	}

	public static void main(String[] args) {
		// Factorization of 36 with a separate thread
		System.out.println("Factorization of 168 using an Executor with 2 fixed threads");
		
		LinkedList<RunnablePrimeFactorizer> runnables = new LinkedList<RunnablePrimeFactorizer>();

		// Create runnables
		runnables.add( new RunnablePrimeFactorizer(168, 2, (long)Math.sqrt(168)/2 ));
		runnables.add( new RunnablePrimeFactorizer(168, 1+(long)Math.sqrt(168)/2, (long)Math.sqrt(168) ));
		
		// Executor uses both runnables made from above and runs them on a fixed 2 thread pool
		ExecutorService executor = Executors.newFixedThreadPool(2);

		executor.execute(runnables.get(0));
		
		executor.execute(runnables.get(1));
		
		executor.shutdown();
		
		// Print final result
		LinkedList<Long> factors2 = new LinkedList<Long>();
		runnables.forEach( (factorizer) -> factors2.addAll(factorizer.getPrimeFactors()) );
		System.out.println("Final result: " + factors2);
		
		

		
		
	}
}
