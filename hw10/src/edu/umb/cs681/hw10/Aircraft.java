package edu.umb.cs681.hw10;
import java.util.concurrent.atomic.AtomicReference;

// This class is used in RunnableAircraft and takes its
// positions from Position
public class Aircraft {

	// AtomicReference is used to guard a shared variable
	private AtomicReference<Position> position = new AtomicReference<>(); 
	
	
	// Create the Aircraft object using AtomicReference
	public Aircraft(Position pos){ 
		
		position = new AtomicReference<>(pos); 
		
	} 
	
	// Setting position
	public void setPosition(Position pos){

		position.set(pos);; 

	}
	
	// Getting position
	public Position getPosition(){ 
		
		return position.get();
	
	}
}