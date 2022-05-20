# CS681-Object-Oriented-Software-Development-II
This repository holds the entirety of the work (assignments) completed in CS681 Object Oriented Software Development.

----------------

# HW01
The assignment was to use the Car class created in cs680 hw 13 and implement four sorting methods using Stream API.

# HW02
This assignment was a continuation from hw01. Again using the Car class and Stream API, the program will find the maximum, minimum, and average price of al Cars in the stream.

# HW03
In this project, the java program reads a csv file full of Pandemic Vulnerability Index (PVI) data. It turns this data into a stream, filters out only the counties in Massachusetts, and then computes the average of both the Infection Rate: Transmissable Cases and the Intervention: Vaccine Rate.

# HW04
In this asignment, the java program generates prime numbers between a given start and end point. The goal was to show the difference in time the pgrogram takes when running in 1 thread, 2 threads, 4 threads, and 8 threads.

# HW05
This assignment was to revise the RunnableCancellablePrimeGenerator class to make it thread-safe by implementing flag based thread termination.

# HW06
This assignment was to implement RunnableCancellablePrimeFactorizer by extending RunnablePrimeFactorizer. The new class had to be thread-safe by implementing flag based thread termination.

# HW07
This assignment was to create the ConcurrentSingleton class. Then run multiple threads to call getInstance() and show that only instance is created among all threads.

# HW08
This assignment was to define the RunnableCancellableInterruptiblePrimeFactozier class. The class needed to be thread-safe using a 2 step thread termination and combining both flag-based and interruption basesd termination.

# HW09
This assignment was to revisit HW07 and revise the ConcurrentSingleton class. The goal was to take out the RentrantLock and use an AtomicReference variable instead to keep the class thread-safe.

# HW10
The assignment was to implement our own immutable class Position with various different methods. Then create an Aircraft class that is thread safe with either a ReentrantLock or AtomicReferenceType. Finally we had to create a Runnable class that calls Aircraft's setPosition() and getPosition() methods, with code to test it all.

# HW11
The assignment was to implement AccessCounter as a thread-safe Singleton class. Then create a Runnable class RequestHandler that would pick up a dummy file at random, call increment() and getCount() for that file on the AccessCounter, sleep for a few seconds and repeat on loop. RequestHandler needed 2-step thread termination with a volatile flag or AtomicBoolean. The main method had test code to create 10+ instances of RequestHandler and flip the flag on each RequestHandler.

# HW12
This assignment was to revisit the file system created in CS680. We needed to revise FSElement, Directory, and File to make the classes multi-threaded. This was done by defining a ReentrantLock in FSElement and using it to make the necessary methods in Directory and File thread-safe.

# HW13
This assignment was to complete 2-step thread termination in ThreadSafeBankAccount2, WithdrawRunnable, and DepositRunnable. The test code runs multiple "withdraw" and "deposit" threads on the same bank account, which are then terminated by the main thread.

# HW14
This assignment was to revise hw11 (AccessCounter) by eliminating client-locking to guard the hash-map and instead use a ConcurrentHashMap.

# HW15
This assignment was to revise hw12 (FSElement) by eliminating client-locking to guard the LinkedList and instead use a ConcurrentLinkedQueue.

# HW16
This assignment was to revisit hw01 and the Car class. The task was to implement this map-reduce operation with the 3rd version of reduce() using Parallel Streams.

# HW17
This assignment was to revise our hw03 PVIStream class by using parallel streams.

# HW18
This assignment was to revisit hw04 and the RunnablePrimeFactorizer class. The task was to revise the current code to use an Executor to handle and execute the threads.

