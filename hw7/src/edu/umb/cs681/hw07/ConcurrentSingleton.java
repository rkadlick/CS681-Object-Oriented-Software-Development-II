package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

// This class was created to test the Singleton pattern with multiple threads
// The assignment was to first make the class thread-safe by defining a lock,
// then to run multiple threads that show only one instance is created
public class ConcurrentSingleton implements Runnable{
	private ConcurrentSingleton(){};
	private static ConcurrentSingleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();
	
	// Factory method to create or return the singleton instance 
	public static ConcurrentSingleton getInstance(){
		lock.lock();
		try{
			if(instance==null){ instance = new ConcurrentSingleton(); }
			return instance;
		}finally{
			lock.unlock();
		}
	}
	
	public static void main(String[] args){
		
		threeThreads();
		
	}
	
	// This method creates 3 threads and calls getInstance() on each thread,
	// each thread will then print the instance to the console to show that only one instance was created
	public static void threeThreads(){
		
		ConcurrentSingleton one = new ConcurrentSingleton();
		ConcurrentSingleton two = new ConcurrentSingleton();
		ConcurrentSingleton three = new ConcurrentSingleton();
		Thread t1 = new Thread(one);
		Thread t2 = new Thread(two);
		Thread t3 = new Thread(three);
		t1.start();
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {}
	}

	@Override
	public void run() {
		System.out.println("Thread Instance: " + ConcurrentSingleton.getInstance());
		
	}
	
}
