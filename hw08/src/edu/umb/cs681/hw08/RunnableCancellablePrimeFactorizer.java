package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

// This class is implemented by extending the RunnablePrimeFactorizer class
// The assignment was to implement this class and make it thread-safe by adding flag-based thread termination
public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer{
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	
	
	public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend, from, to);
	}
	
	public void setDone(){
		// Adding a lock for when the thread has to access the done variable
		lock.lock();
		try {
			done = true;
		}finally {
			lock.unlock();
		}
		
	}
	
	public void generatePrimeFactors(){
		long divisor = 2;
		while( dividend != 1 && divisor <= to ){
			// Adding a lock for when the thread has to access the done variable
			lock.lock();
			// Stop generating prime numbers if done==true
			try{
				if(done){
			
				System.out.println("Stopped generating factors numbers.");
				this.factors.clear();
				break;
				}
			    if(dividend % divisor == 0) {
			        factors.add(divisor);
			        dividend /= divisor;
			    }else {
			    	if(divisor==2){ divisor++; }
			    	else{ divisor += 2; }
			    }
			
			}finally {
				lock.unlock();
			}
		}
	}
	
	public static void main(String[] args) {
		// Currently the PrimeFactorizer is set to provide prime factors of 84
		RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(84, 2, (long)Math.sqrt(84));
		Thread thread = new Thread(gen);
		thread.start();
		// The line below is used to change the done variable to true which will stop the prime factorizer 
		//gen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimeFactors().forEach( (Long factor)-> System.out.print(factor + ", ") );
		System.out.println("\n" + gen.getPrimeFactors().size() + " prime factors are found.");
	}
	
	

}
