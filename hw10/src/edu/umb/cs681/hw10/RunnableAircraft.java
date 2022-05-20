package edu.umb.cs681.hw10;

// This class was used to test and complete the assignment of making a thread safe Aircraft class and immutable Position class.
// This class creates multiple threads and assigns different positions to the aircraft object where
// we can see the position object is thread-safe.
public class RunnableAircraft implements Runnable {
	
	// Creates 3 different positions for our test code
	// Static to test the distanceTo() method
	static Position pos1 = new Position(42.36, -71.05, 19);
	static Position pos2 = new Position(-71.05, 42.36, 91);
	static Position pos3 = new Position(91, 36.42, 71.05);
	
	public static void main(String[] args){
		
		// Creates and runs 3 separate threads to test the Position and Aircraft Codes
		RunnableAircraft one = new RunnableAircraft();
		RunnableAircraft two = new RunnableAircraft();
		RunnableAircraft three = new RunnableAircraft();
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
		
		// Testing distanceTo() method
		System.out.println("The distance from the original and final position is: " + pos1.distanceTo(pos3));
		
	}
	
	
	// Runnable code when each thread starts
	// Each thread will be given 3 different positions and are meant to have the same position at each of the 3 intervals
	@Override
	public void run() {
		
		
		Aircraft air = new Aircraft(pos1);
		
		System.out.println("The Aircraft's orginal position is: " + air.getPosition());
		
		air.setPosition(air.getPosition().changeLat(-71.05).changeLong(42.36).changeAlt(91));
		
		System.out.println("The second position of the Aircraft is: " + air.getPosition());
		
		air.setPosition(air.getPosition().changeLat(91).changeLong(36.42).changeAlt(71.05));
		
		System.out.println("The final position of the Aircraft is: " + air.getPosition());
		
		
	}

}
