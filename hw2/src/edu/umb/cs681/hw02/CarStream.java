package edu.umb.cs681.hw02;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

// This class is used to further test the Stream API with Car objects, continuing from assignment 1
// This assignment was to find the min, max, and average prices of a list of cars
public class CarStream {
	
	// List of cars to be added into stream
	static Car car1 = new Car("Jeep", "Wrangler", 20000, 2020, 40000);
	static Car car2 = new Car("Chevrolet", "Cruze", 80000, 2015, 50000);
	static Car car3 = new Car("Honda", "Civic", 10000, 2018, 30000);
	static Car car4 = new Car("Audi", "A4", 11000, 2019, 45000);
	static Car car5 = new Car("Ford", "F-150", 50000, 2014, 20000);
	ArrayList<Car> carList = new ArrayList<Car>();
	
	
	// Main method to call all of created methods to use on the Stream
	public static void main(String[] args) {
		minPrice();
		maxPrice();
		avgPrice();
	}
	
	// This method determines the minimum price from a stream of Car objects
	// The expected value is taken from list of cars above
	public static float minPrice(){
		
		LinkedList<Car> cars = new LinkedList<Car>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		
		float price = 
				cars.stream()
				.map((Car car) -> car.getPrice() )
				.min(Comparator.comparing((Float price1) -> price1))
				.get();
		
		System.out.println("Minimum price - " + price);
		System.out.println("Expected price - 20000");
		return price;
	}
	
	// This method determines the maximum price from a stream of Car objects
	// The expected value is taken from list of cars above
	public static float maxPrice(){
		
		LinkedList<Car> cars = new LinkedList<Car>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		
		float price = 
				cars.stream()
				.map((Car car) -> car.getPrice() )
				.max(Comparator.comparing((Float price1) -> price1))
				.get();
		
		System.out.println("Maximum price - " + price);
		System.out.println("Expected price - 50000");
		return price;
	}
	
	// This method determines the average price of a stream of Car objects
	// The expected value is calculated from list of cars above
	public static float avgPrice(){
		
		LinkedList<Car> cars = new LinkedList<Car>();
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car5);
		
		int avgPrice = 
				cars.stream()
				.map((Car car) -> car.getPrice() )
				.reduce( new int[2],
						(result, price) ->{
							
							int[] result1 = new int[2]; 
							result1 = accumulate(result, price);
							
							
										return result;			},
						(finalResult, intermediateResult)-> finalResult)[1];
		
		System.out.println("Average price - " + avgPrice);
		System.out.println("Expected price - 37000");
		return avgPrice;
	}


	private static int[] accumulate(int[] result, Float price) {
		result[1] = (result[0]*result[1] + price.intValue())/(++result[0]);
		return result;
	}
	
	

	
}
