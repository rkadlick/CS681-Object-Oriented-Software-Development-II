package edu.umb.cs681.hw05;

import java.util.concurrent.locks.*;

// This class was pre-given and it's jobs is to generate prime numbers 
// The assignment was to revise this with ReentrantLocks and make it thread-safe
public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	private ReentrantLock lock = new ReentrantLock();
	private boolean done = false;
	
	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
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

	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// Adding a lock for when the thread has to access the done variable
			lock.lock();
			// Stop generating prime numbers if done==true
			try{
				if(done){
			
				System.out.println("Stopped generating prime numbers.");
				this.primes.clear();
				break;
			}
			if( isPrime(n) ){ this.primes.add(n); }
			}finally {
				lock.unlock();
			}
		}
	}

	public static void main(String[] args) {
		RunnableCancellablePrimeGenerator gen = new RunnableCancellablePrimeGenerator(1,100);
		Thread thread = new Thread(gen);
		thread.start();
		// The line below is used to change the done variable to true which will stop the prime generator
		//gen.setDone();
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		gen.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + gen.getPrimes().size() + " prime numbers are found.");
	}
}
