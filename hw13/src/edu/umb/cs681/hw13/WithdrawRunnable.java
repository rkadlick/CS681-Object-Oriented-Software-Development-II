package edu.umb.cs681.hw13;

import java.util.concurrent.atomic.AtomicBoolean;

// This class handles threads that are withdrawing money from the account
public class WithdrawRunnable implements Runnable{
	private BankAccount account;
	
	// Atomic boolean used for flag based thread termination
	private AtomicBoolean done = new AtomicBoolean(false);
	
	public WithdrawRunnable(BankAccount account) {
		this.account = account;
	}
	
	// Flips the atmoic boolean for each thread
	public void setDone() {
		this.done.set(true);
	}
	
	// Run method for when a withdraw thread is started
	public void run(){
		
		// Infinite loop
		while(true) {
			
			// Loop breaks when atomic boolean flag is flipped
			if(done.get()) {
				System.out.println(Thread.currentThread().toString() + " Withdraw thread terminated");
				break;
			}
			// Withdraw money from the account
			System.out.println(Thread.currentThread().toString() + " Withdrawing money");
			account.withdraw(100);
			
			// Thread sleep
			try{
				Thread.sleep(2000);
			}catch(InterruptedException exception){}
		}

	}
}

