package edu.umb.cs681.hw17;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class PVIStream {
	
	// The object of this class and file is to read through a csv file listing PVI data for counties across the United States
	// The goal was to filter out the csv file to only focus on the data from Massachusetts counties
	// After filtering out the MA counties, the average is then computed
	
	//The new goal was to update hw3 cs681 by using parallel streams in the methods
	public static void main(String[] args) throws IOException {

		// Expected values and average of transmissable cases
		// [.5291, .4817, .5536, .581, .4551, .47407, .5206, .42045, .46073, .47966, .421, .45557, .43832, .46363]
		// 6.73453 /14 = 0.481037857142
		
		System.out.println("Expected average: 0.481037857142");
		System.out.print("Average of Infection Rate: Transmissble cases of all counties in MA: ");
		transmissibleCasesAvg();
		
		// Expected values and average of vaccines
		// [.2681, .3599, .4433, .34564, .3599, .3087, .19427, .2598, .200, .32777, .30750, .2038, .2038, .2038]
		// 3.98628 / 14 = 0.284734285714
		
		System.out.println("Expected average: 0.284734285");
		System.out.print("Average of Intervention: Vaccine Rate of all counties in MA: ");
		vaccineAvg();
		
	}
	
	// This method reads through the csv file and counts all the Infection Rate: Transmissble Cases number for all counties in Massachusetts
	// These numbers are combined together and divided to find the average infection rate: transmissible cases for only MA counties 
	public static void transmissibleCasesAvg() throws IOException{
	    
		// Path to PVI csv file
		Path path = Paths.get("src/Model_12.4_20220311_results.csv");
		try( Stream<String> lines = Files.lines(path) ){
			
			
			float avg = lines.map( line -> {
											// The start of the stream reads all of the data and removes the quotation marks
												return Stream
												.of( line.split(",") ) 
												.map(value->value.substring(1, value.length()-1))	
												
												.collect( Collectors.toList() ); }) 
									// creates parallel stream
									.parallel()
									// Filters the row to only show Massachusetts counties
									.filter((row) -> row.contains("Massachusett"))
									// get(7) targets the column of infection data: transmissible cases numbers
									.map((hey) -> hey.get(7))
									// Shortens the decimal
									.map((result) -> result.substring(1, result.length()-12))
									// Calculates the average
									.reduce( new float[2],
											(result, num) ->{
												
												float[] result1 = new float[2]; 
												result1 = accumulate(result, Float.valueOf(num).floatValue());
												
												
															return result;			},
											(finalResult, intermediateResult)-> finalResult)[1];

			System.out.println(avg);
			} catch (IOException ex) {} 
	    }
	
	// This method reads through the csv file and counts all the Intervention: Vaccine rates for all counties in MA
	// The method then computes the avergae of these numbers
	public static void vaccineAvg() throws IOException{
	    
		// Path to PVI csv file
		Path path = Paths.get("src/Model_12.4_20220311_results.csv");
		try( Stream<String> lines = Files.lines(path) ){
			
			
			float avg = lines.map( line -> {
											// The start of the stream reads all of the data and removes the quotation marks
												return Stream
												.of( line.split(",") ) 
												.map(value->value.substring(1, value.length()-1))	
												
												.collect( Collectors.toList() ); })
									// create parallel stream
									.parallel()
									// Filters the row to only show Massahusetts counties
									.filter((row) -> row.contains("Massachusett"))
									// get(11) targets the column of intervention: vaccine rates
									.map((hey) -> hey.get(11))
									// Shortens the decimal
									.map((result) -> result.substring(1, result.length()-12))
									// Calculates the average
									.reduce( new float[2],
											(result, num) ->{
												
												float[] result1 = new float[2]; 
												result1 = accumulate(result, Float.valueOf(num).floatValue());
												
												
															return result;			},
											(finalResult, intermediateResult)-> finalResult)[1];

			System.out.println(avg);
			} catch (IOException ex) {} 
	    }
	
	private static float[] accumulate(float[] result, float num) {
		result[1] = (result[0]*result[1] + num)/(++result[0]);
		return result;
	}
	}
