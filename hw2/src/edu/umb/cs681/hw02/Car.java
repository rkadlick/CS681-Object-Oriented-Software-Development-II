package edu.umb.cs681.hw02;

import java.util.ArrayList;
import java.util.LinkedList;

/*This class holds the Car object*/
public class Car {
	
	//Fields of a car object
	private String make, model;
	private int mileage, year, dominationCount;
	private float price;
	
	public Car(String make, String model, int mileage, int year, float price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}
	
	//getters and setters
	public String getMake() {
		return this.make;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public int getMileage() {
		return this.mileage;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public float getPrice() {
		return this.price;
	}
	
	/*Domination count is a representation of how many cars are "better" than a car from a list of cars,
	 * using price, mileage, and year
	 */
	public void setDominationCount(LinkedList<Car> cars) {
		int newDominationCount = 0;
		
		
		for(int i = 0; i<cars.size(); i++) {
			if (this.price <= cars.get(i).getPrice()) {
				if (this.year >= cars.get(i).getYear()) {
					if (this.mileage <= cars.get(i).getMileage()) {
					} else {
						newDominationCount++;
					}
				}else {
					newDominationCount++;
				}
			} else {
				 newDominationCount++;
			}
		}
		
		this.dominationCount = newDominationCount;
	}
	
	public int getDominationCount() {
		return this.dominationCount;
	}
	
	
}
