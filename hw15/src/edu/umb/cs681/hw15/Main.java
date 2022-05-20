package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.ArrayList;

/* This class is used to test the FSElement, File, and Directory class after adding the Reentrant Lock data field for thread safety */
public class Main{
	
	public static void main(String[] args) {
		
		//Creating fake directories and files for testing
		Directory root = new Directory(null, "Root", 0, LocalDateTime.of(2000, 1, 1, 1, 0));
		
		Directory applications = new Directory(root, "Applications", 0, LocalDateTime.of(2000, 1, 1, 2, 0));
		root.appendChild(applications);
		File a = new File(applications, "a", 1, LocalDateTime.of(2000, 1, 1, 3, 0));
		applications.appendChild(a);
		
		Directory home = new Directory (root, "Home", 0, LocalDateTime.of(2000, 1, 1, 4, 0));
		root.appendChild(home);
		File b = new File(home, "b", 2, LocalDateTime.of(2000, 1, 1, 5, 0));
		home.appendChild(b);
		
		Directory code = new Directory(home, "Code", 0, LocalDateTime.of(2000, 1, 1, 6, 0));
		home.appendChild(code);
		File c = new File(code, "c", 3, LocalDateTime.of(2000, 1, 1, 7, 0));
		code.appendChild(c);
		File d = new File(code, "d", 4, LocalDateTime.of(2000, 1, 1, 8, 0));
		code.appendChild(d);
		
		//Creating a list of all of threads 
		ArrayList<Thread> threads = new ArrayList<>();
		
		//Run function when a thread starts
		//Each thread will output the names of the directories and files, as well as the children of the root and code directories
		Runnable runn = () -> {
			System.out.println("Directory Names:");
			System.out.println(root.getName());
			System.out.println(applications.getName());
			System.out.println(home.getName());
			System.out.println(code.getName());
			
			System.out.println("File Names:");
			System.out.println(a.getName());
			System.out.println(b.getName());
			System.out.println(c.getName());
			System.out.println(d.getName());
			
			System.out.println("Total Children of Root: " + root.countChildren());
			System.out.println("Total Children of Code: " + code.countChildren());
			
			System.out.println("Children of root: " + root.getChildren().toString());
			
		};
		
		//Creating and starting 4 different threads
		for(int i = 0; i <= 3; i++) {
			Thread t = new Thread(runn);
			threads.add(t);
			t.start();
		}
		
		//Joins the threads
		for(Thread t : threads) {
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
