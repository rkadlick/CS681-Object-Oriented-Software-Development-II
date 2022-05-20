package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

// The AccessCounter class is a singleton class. It is a reference to a hashmap which contains
// a relative file path and a number signifying it's access count.
public class AccessCounter {
	
	private AccessCounter() {};
	private static AccessCounter instance = null;
	
	// Creating both static and non static locks
	private  ReentrantLock nsLock = new ReentrantLock();
	private static ReentrantLock sLock = new ReentrantLock();	
	
	private HashMap<Path, Integer> map = new HashMap<Path, Integer>();
	
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
	// Non static lock required for thread safe
	public void increment(Path path) {
		nsLock.lock();
		try {
			if(map.get(path) != null) {
				
				map.put(path, map.get(path) + 1);
				
			}else {
				map.put(path,  1);
			}
			
		}finally {
			nsLock.unlock();
		}
	}
	
	// Gets the access count in the hashmap of the path given
	// Non static lock required for thread safe
	public int getCount(Path path) {
		nsLock.lock();
		try {
			if(map.get(path) != null) {
				
				return map.get(path);
				
			}else {
				return 0;
			}
			
		}finally {
			nsLock.unlock();
		}
		
		
	}

}
