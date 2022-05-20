package edu.umb.cs681.hw10;

// This immutable class is created to create Position objects when 
// referring to different positions of Aircrafts
public final class Position {

	// Arguments needed for Position object
	private final double latitude, longitude, altitude;
	
	// Position constructor
	public Position(double lat, double lon, double alt) {
		this.latitude = lat;
		this.longitude = lon;
		this.altitude = alt;
	}
	
	// Getters
	public double getLatitude() {
		return latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public double getAltitude() {
		return altitude;
	}
	
	// toStrig method
	public String toString(){
		return latitude + " - " + longitude + " - " + altitude;
	}
	
	// Testing if positions are equal
	public boolean equals(Position anotherPos) {
		if(this.toString().equals(anotherPos.toString())) {
			return true;
		}else {
			return false;
		}
	}
	
	// Changing 1 argument of the object
	public Position changeLat(double newLat) {
		return new Position(newLat, this.longitude, this.altitude);
	}
	
	public Position changeLong(double newLong) {
		return new Position(this.latitude, newLong, this.altitude);
	}
	
	public Position changeAlt(double newAlt) {
		return new Position(this.latitude, this.longitude, newAlt);
	}
	
	
	//Distance method to compare two positions
 	public double distanceTo(Position anotherPos) {
 		
 		final int R = 6371; // Radius of the earth

 	    double latDistance = Math.toRadians(latitude - anotherPos.getLatitude());
 	    double lonDistance = Math.toRadians(longitude - anotherPos.getLongitude());
 	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
 	            + Math.cos(Math.toRadians(anotherPos.getLatitude())) * Math.cos(Math.toRadians(latitude))
 	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
 	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
 	    double distance = R * c * 1000; // convert to meters

 	    double height = anotherPos.getAltitude() - altitude;

 	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

 	    return Math.sqrt(distance);
 		
 	}
 
}
