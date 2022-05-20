package edu.umb.cs681.hw01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// This class was created to test out the different sorting methods of Stream API
public class CarStream {
	
	static Car car1 = new Car("Jeep", "Wrangler", 20000, 2020, 40000);
	static Car car2 = new Car("Chevrolet", "Cruze", 80000, 2015, 50000);
	static Car car3 = new Car("Honda", "Civic", 10000, 2018, 30000);
	static Car car4 = new Car("Audi", "A4", 11000, 2019, 45000);
	static Car car5 = new Car("Ford", "F-150", 50000, 2014, 20000);
	ArrayList<Car> carList = new ArrayList<Car>();
	
	
	// Main method to call all Stream sorting methods
	public static void main(String[] args) {
		priceSort();
		yearSort();
		mileSort();
		dominationSort();
	}
	
	// Method to sort a stream of Car objects by price, smallest to largest
	public static List<Car> priceSort(){
		LinkedList<Car> cars = new LinkedList<Car>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		
		List<Car> sortedCars = 
				cars.stream()
					.sorted((Comparator.comparing((Car car)-> car.getPrice())))
					.collect(Collectors.toList());

		System.out.println("---Price Sort---");
		for(int i = 0; i < sortedCars.size(); i++) {
			System.out.println(sortedCars.get(i).getMake() + " - price: $" + sortedCars.get(i).getPrice());
		}
		return sortedCars;
	}
	
	// Method to sort a stream of Car objects by year, oldest to newest
	public static List<Car> yearSort(){
		LinkedList<Car> cars = new LinkedList<Car>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		
		List<Car> sortedCars = 
				cars.stream()
					.sorted((Comparator.comparing((Car car)-> car.getYear())))
					.collect(Collectors.toList());
		
		System.out.println("---Year Sort---");
		for(int i = 0; i < sortedCars.size(); i++) {
			System.out.println(sortedCars.get(i).getMake() + " - year: " + sortedCars.get(i).getYear());
		}
		return sortedCars;
	}
	
	// Method to sort a stream of Car objects by mileage, least to most
	public static List<Car> mileSort(){
		LinkedList<Car> cars = new LinkedList<Car>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		
		List<Car> sortedCars = 
				cars.stream()
					.sorted((Comparator.comparing((Car car)-> car.getMileage())))
					.collect(Collectors.toList());
		
		System.out.println("---Mileage Sort---");
		for(int i = 0; i < sortedCars.size(); i++) {
			System.out.println(sortedCars.get(i).getMake() + " - mileage: " + sortedCars.get(i).getMileage());
		}
		return sortedCars;
	}
	
	// Method to sort a stream of Car objects by domination count, smallest to largest
	public static List<Car> dominationSort(){
		LinkedList<Car> cars = new LinkedList<Car>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		
		for (Car c : cars) {
			c.setDominationCount(cars);
		}
		
		List<Car> sortedCars = 
				cars.stream()
					.sorted((Comparator.comparing((Car car)-> car.getDominationCount())))
					.collect(Collectors.toList());
		
		System.out.println("---Domination Sort---");
		for(int i = 0; i < sortedCars.size(); i++) {
			System.out.println(sortedCars.get(i).getMake() + " - dominationCount: " + sortedCars.get(i).getDominationCount());
		}
		return sortedCars;
	}
	
}
