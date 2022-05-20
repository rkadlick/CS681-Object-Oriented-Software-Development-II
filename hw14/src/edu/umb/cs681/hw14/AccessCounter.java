package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

// The AccessCounter class is a singleton class. It is a reference to a hashmap which contains
// a relative file path and a number signifying it's access count.
public class AccessCounter {
	
	private AccessCounter() {};
	private static AccessCounter instance = null;
	
	// Creating static lock for the singleton instance of AcessCounter
	private static ReentrantLock sLock = new ReentrantLock();	
	
	// Changing the Hashmap from hw11 to the Concurrent Hashmap 
	// Eliminates need of nonstatic lock
	private ConcurrentHashMap<Path, AtomicInteger> map = new ConcurrentHashMap<Path, AtomicInteger>();
	
	// Getting instance of the singleton class requires the static lock to be thread safe
	public static AccessCounter getInstance(){
		sLock.lock();
		try{
			if(instance==null){ instance = new AccessCounter(); }
			return instance;
		}finally{
			sLock.unlock();
		}
	}
	
	// Increments the access count of the path in the hashmap
	// Non static lock not needed due to Concurrent HashMap
	public void increment(Path path) {
			
		// Lambda expression that will update the counter to 1 if the access count was null, otherwise it gets the current count and increments by 1
		map.compute(path, (key, value) -> {
			
			if(value == null) {
				return new AtomicInteger(1);
			}else {
				return new AtomicInteger(value.incrementAndGet());
			}
		});
		
	}
	
	// Gets the access count in the hashmap of the path given
	// Non static lock not needed due to Concurrent Hashmap
	public int getCount(Path path) {
		
		// Lambda expression that returns 0 if file was not counted in the access counter, otherwise returns the appropriate count amount
		return map.compute(path, (key, value) -> {
			
			if(value == null) {
				return new AtomicInteger(0);
			}else {
				return new AtomicInteger(value.get());
			}

		}).get();
		
	}

}
