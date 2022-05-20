package edu.umb.cs681.hw09;

import java.util.concurrent.atomic.AtomicReference;

// This class was created to test the Singleton pattern with multiple threads
// The assignment was to revise homework 7: remove the Re-Entrant Locks and use
// AtomicReference in order to keep the pattern thread-safe
public class ConcurrentSingleton{
	private ConcurrentSingleton(){};
	private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<>();
	
	// Method used to create and return the singleton instance
	public static AtomicReference<ConcurrentSingleton> getInstance(){
		
		//Compare and set checks if the instance is null, if yes it creates a new instance
		if(instance.compareAndSet(null, instance.get())){ 
			instance.set(new ConcurrentSingleton());
		}
		
		return instance;
	}
	
	// Outputs the instance for each thread (should be the same)
	public static void main(String[] args){ for(int i=0; i<10; i++){
		
		new Thread( ()->{System.out.println(ConcurrentSingleton.getInstance());}).start();
		
	}}
	
}
