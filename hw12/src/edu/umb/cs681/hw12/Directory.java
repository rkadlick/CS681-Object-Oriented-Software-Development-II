package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.LinkedList;

/*This class holds Directory objects that come from the super class FSelement*/
//Taken from hw08 cs680, the new assignment was to make this class thread safe by using Reentrant Lock data field
public class Directory extends FSElement {
	
	//Holds the elements that are children to the Directory
	private LinkedList<FSElement> children = new LinkedList<FSElement>();
	
	public Directory (Directory parent, String name, int size, LocalDateTime creationTime) {
		
		super(parent, name, size, creationTime);
	}
	
	//Returns the children of that directory
	//Add Reentrant Lock because LinkedList is not thread safe
	public LinkedList<FSElement> getChildren() {
		lock.lock();
		try {
			return this.children;
		}finally {
			lock.unlock();
		}
	}
	
	//Adds an element to the children of that directory, sets the parent of the element to the directory
	//Add Reentrant Lock because LinkedList is not thread safe
	public void appendChild(FSElement child) {
		lock.lock();
		try {
			this.children.add(child);
			child.setParent(this);
		}finally {
			lock.unlock();
		}
		
		
	}
	
	//Returns number of children a directory has
	//Add Reentrant Lock because LinkedList is not thread safe
	public int countChildren() {
		lock.lock();
		try {
			return this.children.size();
		}finally {
			lock.unlock();
		}
		
		
	}
	
	//Returns only directories that are children of the directory
	//Add Reentrant Lock because LinkedList is not thread safe
	public LinkedList<Directory> getSubDirectories(){
		lock.lock();
		try {
			LinkedList<Directory> subDirectories = new LinkedList<Directory>();
			
			for(FSElement f : this.children) {
			
				if(f instanceof Directory) {
					
					Directory directory = (Directory)f;
					subDirectories.add(directory);
					
				}
			}
			
			return subDirectories;
		}finally {
			lock.unlock();
		}
		
		
	}
	
	//Returns only files that are children of the directory
	//Add Reentrant Lock because LinkedList is not thread safe
	public LinkedList<File> getFiles(){
		lock.lock();
		try {
			LinkedList<File> files = new LinkedList<File>();
			
			for(FSElement f : this.children) {
			
				if(f instanceof File) {
					
					File file = (File)f;
					files.add(file);
					
				}
			}
			
			return files;
		} finally {
			lock.unlock();
		}
		
		
	}
	
	
	//Returns the total size of all files in the directory, including subdirectories
	//Add Reentrant Lock because LinkedList is not thread safe
	public int getTotalSize() {
		lock.lock();
		try {
			int totalSize = 0;
			
			for(FSElement f : this.children) {
				
				totalSize = totalSize + f.getSize(); 
			
			}
			
			for(Directory d : this.getSubDirectories()) {
				totalSize = totalSize + d.getTotalSize();
			}
			
			return totalSize;
		}finally {
			lock.unlock();
		}

		
	}
	
	//truth
	public boolean isDirectory() {
		return true;
	}

}
