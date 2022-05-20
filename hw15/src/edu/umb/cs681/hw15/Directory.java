package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/*This class holds Directory objects that come from the super class FSelement*/
//Taken from hw12 cs681, the new assignment was to remove the Reentrant Lock data field and to make this thread safe by using ConccurentLinkedQueue
public class Directory extends FSElement {
	
	//Holds the elements that are children to the Directory
	private ConcurrentLinkedQueue<FSElement> children = new ConcurrentLinkedQueue<FSElement>();
	
	public Directory (Directory parent, String name, int size, LocalDateTime creationTime) {
		
		super(parent, name, size, creationTime);
	}
	
	//Returns the children of that directory
	//Add Reentrant Lock because LinkedList is not thread safe
	public ConcurrentLinkedQueue<FSElement> getChildren() {
			return this.children;
	}
	
	//Adds an element to the children of that directory, sets the parent of the element to the directory
	public void appendChild(FSElement child) {

			this.children.add(child);
			child.setParent(this);
		
		
	}
	
	//Returns number of children a directory has
	public int countChildren() {

			return this.children.size();

		
	}
	
	//Returns only directories that are children of the directory
	public LinkedList<Directory> getSubDirectories(){

			LinkedList<Directory> subDirectories = new LinkedList<Directory>();
			
			for(FSElement f : this.children) {
			
				if(f instanceof Directory) {
					
					Directory directory = (Directory)f;
					subDirectories.add(directory);
					
				}
			}
			
			return subDirectories;

		
	}
	
	//Returns only files that are children of the directory
	public LinkedList<File> getFiles(){

			LinkedList<File> files = new LinkedList<File>();
			
			for(FSElement f : this.children) {
			
				if(f instanceof File) {
					
					File file = (File)f;
					files.add(file);
					
				}
			}
			
			return files;

		
	}
	
	
	//Returns the total size of all files in the directory, including subdirectories
	public int getTotalSize() {

			int totalSize = 0;
			
			for(FSElement f : this.children) {
				
				totalSize = totalSize + f.getSize(); 
			
			}
			
			for(Directory d : this.getSubDirectories()) {
				totalSize = totalSize + d.getTotalSize();
			}
			
			return totalSize;


		
	}
	
	//truth
	public boolean isDirectory() {
		return true;
	}

}
