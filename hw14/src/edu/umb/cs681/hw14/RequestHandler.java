package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.atomic.*;
import java.util.concurrent.locks.ReentrantLock;

// The RequestHandler class is a runnable class that contains the run() method for each thread.
// It also contains the main method, which creates 11 different threads
public class RequestHandler implements Runnable {

	// ArrayList to hold all relative file paths
	// AmoticBoolean, Lock required for the 2 step thread termination
    private static ArrayList<Path> files = new ArrayList<>();
    private AtomicBoolean done = new AtomicBoolean(false);
    private final ReentrantLock lock = new ReentrantLock();
    
    // This is the flag that the main thread flips to signal to the other threads to stop
	public void setDone(){
		lock.lock();
		try {
			done.set(true);;
		}
		finally {
			lock.unlock();
		}
	}
	
	// Main method adds relative file paths to an array
	// Creates and starts 11 threads
	// Then interrupts and flips the flag on each thread, which ends them
	public static void main(String[] args){
		
		ArrayList<Thread> threads = new ArrayList<>();
		
		files.add(Paths.get("a.html"));
		files.add(Paths.get("b.html"));
		files.add(Paths.get("c.html"));
		files.add(Paths.get("d.html"));
		files.add(Paths.get("e.html"));
		
		RequestHandler request = new RequestHandler();
		
		// Thread creation
		for(int i = 0; i <= 11; i++) {
			Thread t = new Thread(request);
			threads.add(t);
			t.start();
		}
		
		// Interrupting and terminating threads
		for(Thread t : threads) {
			
			try {
			t.interrupt();
			request.setDone();
			t.join();
		
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		

		
	}

	@Override
	public void run() {
		
		// Each thread accesses the same AccessCounter
		AccessCounter counter = AccessCounter.getInstance();
		
		// Infinite loop for each thread to access a different file and add it to the counter
		while(true) {
			
			// After main thread flips flag, the thread will terminate and output the accesscounter it contains
			// Each thread should output the same count for each file
			if(done.get()) { 
				
				System.out.println(Thread.currentThread().toString());
				System.out.println("a.html count: " + counter.getCount(Paths.get("a.html")));
				System.out.println("b.html count: " + counter.getCount(Paths.get("b.html")));
				System.out.println("c.html count: " + counter.getCount(Paths.get("c.html")));
				System.out.println("d.html count: " + counter.getCount(Paths.get("d.html")));
				System.out.println("e.html count: " + counter.getCount(Paths.get("e.html")));
				
				break;
			}
			
			// Get a random file from the array
			int random = (int) (Math.random() * 5);
			Path path = files.get(random);
			
			// Increment the count of the file path
			counter.increment(path);
			counter.getCount(path);		
			
			// Thread sleeps for a few seconds
			try {
				Thread.sleep(3000);
			} catch(InterruptedException e) {
				
			}
			
		}

	}


}
