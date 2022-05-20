package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

// This class is used to further test the Stream API with Car objects, continuing from assignment 1
// This assignment was to find the min, max, and average prices of a list of cars
// The new assignment was to edit the submission from hw2 cs681 using parallel streams
public class CarStream {
	
	// List of cars to be added into stream
	static Car car1 = new Car("Jeep", "Wrangler", 20000, 2020, 40000);
	static Car car2 = new Car("Chevrolet", "Cruze", 80000, 2015, 50000);
	static Car car3 = new Car("Honda", "Civic", 10000, 2018, 30000);
	static Car car4 = new Car("Audi", "A4", 11000, 2019, 45000);
	static Car car5 = new Car("Ford", "F-150", 50000, 2014, 20000);
	static Car car6 = new Car("Audi", "A4", 11000, 2019, 45000);
	static Car car7 = new Car("Ford", "F-150", 50000, 2014, 20000);
	static ArrayList<Car> carList = new ArrayList<Car>();
	
	
	// Main method to call all of created methods to use on the Stream
	public static void main(String[] args) {
		
		carList.add(car1);
		carList.add(car2);
		carList.add(car3);
		carList.add(car4);
		carList.add(car5);
		carList.add(car6);
		carList.add(car7);
		
		System.out.println("Expeceted Number using getMakerNum: 7");
		System.out.println("Actual Number: " + getMakerNum());
		
		System.out.println("Expected Number using getModelNum: 7");
		System.out.println("Actual Number: " + getModelNum());
	}
	
	// Uses a parallel stream to add all of the cars by using their make
	public static int getMakerNum() {
		
		int carMakerNum = carList
							.stream()
							.parallel()
							.map ((Car car) -> car.getMake())
							.reduce(0,
									(result, carMaker) -> ++result, 
							 		(finalResult, intermediateResult) -> finalResult + intermediateResult
							 		);
		
		return carMakerNum;
		
	}
	
	// Uses a parallel stream to add all of the cars by using their model
	public static int getModelNum() {
		
		int carModelNum = carList
				.stream()
				.parallel()
				.map ((Car car) -> car.getModel())
				.reduce(0,
						(result, carModel) -> ++result, 
				 		(finalResult, intermediateResult) -> finalResult + intermediateResult
				 		);

		return carModelNum;
	}
	
	

	
}
