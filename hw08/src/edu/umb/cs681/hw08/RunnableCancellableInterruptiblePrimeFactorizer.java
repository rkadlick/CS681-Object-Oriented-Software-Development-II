package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

// This class is meant to extend RunnableCancellablePrimeFactorizer and the assigment
// was to make this class a hybrid of 2 different types of thread termination, flag based and interruption based.
public class RunnableCancellableInterruptiblePrimeFactorizer
	extends RunnableCancellablePrimeFactorizer{
		
		private boolean done = false;
		private final ReentrantLock lock = new ReentrantLock();
		
		public RunnableCancellableInterruptiblePrimeFactorizer(long dividend, long from, long to) {
			super(dividend, from, to);
		}
		
		public void setDone(){
			lock.lock();
			try {
				done = true;
			}
			finally {
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
				
					System.out.println("Stopped generating prime factors.");
					this.factors.clear();
					break;
					}
					
				    
					    if(dividend % divisor == 0) {
					        factors.add(divisor);
					        System.out.println(divisor);
					        dividend /= divisor;
					    }else {
					    	if(divisor==2){ divisor++; }
					    	else{ divisor += 2; }
					    }
				    
				}finally {
					lock.unlock();
				}
				// Changing the number below in the .sleep method will increase or decrease the amount of time that the thread sleeps after each factor is generated
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					System.out.println(e.toString());
					continue;
				}
			}
}

		public static void main(String[] args) {
			RunnableCancellableInterruptiblePrimeFactorizer gen = new RunnableCancellableInterruptiblePrimeFactorizer(84, 2, (long)Math.sqrt(84));
			Thread thread = new Thread(gen);
			thread.start();
			// Changing the number below in the .sleep method will increase or decrease the amount of time between when the thread generates 
			// the prime factors and outputs the final list.
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Uncommenting the lines below will cause the thread to terminate by first switching the done variable to true and then interrupting the thread
			// This is the example of mixing both flag based and interruption based thread termination.
			//gen.setDone();
			//thread.interrupt();
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			gen.getPrimeFactors().forEach( (Long factor)-> System.out.print(factor + ", ") );
			System.out.println("\n" + gen.getPrimeFactors().size() + " prime factors are found.");
		}

	}


