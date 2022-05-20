package edu.umb.cs681.hw13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// This class contains both withdraw and deposit methods for the Bank Account
// It also contains the main method to test the classes
public class ThreadSafeBankAccount2 implements BankAccount{
	private double balance = 0;
	// Lock used for thread safety
	private ReentrantLock lock = new ReentrantLock();
	
	// Two condition objects to help threads temporarily release and re-aquire locks
	Condition sufficientFundsCondition = lock.newCondition();
	Condition belowUpperLimitFundsCondition = lock.newCondition();

	// Withdraw method
	public void withdraw(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (w): " + balance);
			
			//If balance is 0 or below, we can not withdraw any money so we tell the sufficientfundscondition to await
			while(balance <= 0) {
				// Wait for balance to exceed 0
				try {
					System.out.println(Thread.currentThread().toString() + " Balance is 0 or less");
					sufficientFundsCondition.await();
				} catch (InterruptedException e) {
					return;
				}
			}
			
			// Here is the steps to withdraw the money from the account, and to signal the belowUpperLimitFundsCondition that money has been withdrawn
			balance = balance - amount;
			System.out.println(", New balance (w): " + balance);
			belowUpperLimitFundsCondition.signalAll();
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	// Deposit Method
	public void deposit(double amount){
		lock.lock();
		try{
			System.out.println("Lock obtained");
			System.out.println("Current balance (d): " + balance);
			
			// If balance is 300 or more, we will not deposit any money, we tell belowUpperLimitFundsCondition to await
			while(balance >= 300) {
				// Wait for balance to go below 300
				try {
					System.out.println(Thread.currentThread().toString() + " Balance is over 300");
					belowUpperLimitFundsCondition.await();
				} catch (InterruptedException e) {
					return;
				}
			}
			// Here are the steps to deposit money to the account, and to signal the sufficientFundsCondition that money has been deposited
			balance = balance + amount;
			System.out.println("New balance (d): " + balance);
			sufficientFundsCondition.signalAll();
		}finally{
			lock.unlock();
			System.out.println("Lock released");
		}
	}
	
	// Main method to test the classes with thread termination and thread safety
	public static void main(String[] args){
		
		//Create bank account, runnables, and both deposit and withdraw threads
		ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();	
		
        WithdrawRunnable withdrawRunnable = new WithdrawRunnable(bankAccount);
        DepositRunnable depositRunnable = new DepositRunnable(bankAccount);
		
		Thread d1 = new Thread(depositRunnable);
		Thread d2 = new Thread(depositRunnable);
		Thread d3 = new Thread(depositRunnable);
		Thread d4 = new Thread(depositRunnable);
		Thread d5 = new Thread(depositRunnable);
		Thread w1 = new Thread(withdrawRunnable);
		Thread w2 = new Thread(withdrawRunnable);
		Thread w3 = new Thread(withdrawRunnable);
		Thread w4 = new Thread(withdrawRunnable);
		Thread w5 = new Thread(withdrawRunnable);
		
		//Starting all threads in different orders
		d1.start();
		w1.start();
		d2.start();
		w2.start();
		d3.start();
		w3.start();
		d4.start();
		w4.start();
		d5.start();
		w5.start();
		
		// Flip flags on the runnables
		withdrawRunnable.setDone();
		depositRunnable.setDone();
		
		// Interrupt all threads
		d1.interrupt();
		w1.interrupt();
		d2.interrupt();
		w2.interrupt();
		d3.interrupt();
		w3.interrupt();
		d4.interrupt();
		w4.interrupt();
		d5.interrupt();
		w5.interrupt();
		
		// Join threads
		try {
			d1.join();
			w1.join();
			d2.join();
			w2.join();
			d3.join();
			w3.join();
			d4.join();
			w4.join();
			d5.join();
			w5.join();
		}catch(InterruptedException e) {
			return;
		}
		
		
		
	}
}
