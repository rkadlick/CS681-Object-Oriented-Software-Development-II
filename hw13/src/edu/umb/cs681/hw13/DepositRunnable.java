package edu.umb.cs681.hw13;

import java.util.concurrent.atomic.AtomicBoolean;

/* This class handles threads that are depositing money in the bank account*/
public class DepositRunnable implements Runnable{
	private BankAccount account;
	
	// Atmoic Boolean used for thread termination
	private AtomicBoolean done = new AtomicBoolean(false);
	
	public DepositRunnable(BankAccount account) {
		this.account = account;
	}
	
	// Flips the atmoic boolean for each thread
	public void setDone() {
		this.done.set(true);
	}
	
	
	// This is the run method for when a Deposit thread is started
	public void run(){
		
		while(true) {
			
			// When the boolean flag is flipped, the thread will terminate
			if(done.get()) {
				System.out.println(Thread.currentThread().toString() + " Deposit Thread Terminated");
				break;
			}
			
			// 150 gets deposited into the account 
			System.out.println(Thread.currentThread().toString() + " Depositing Money");
			account.deposit(150);
			
			// Thread Sleeps
			try{
				Thread.sleep(2000);
			}catch(InterruptedException exception){}
		}

	}
}