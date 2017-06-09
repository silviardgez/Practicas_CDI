package Practica_3.Practica_31.AtomicInteger;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Silvia Rodriguez
 * 
 * The class MulAtomicInteger implements Runnable and performs the multiplication.
 * This class contains the code that will be executed by each thread.
 */

public class MulAtomicInteger implements Runnable {
	int id;
	AtomicInteger p; 
	AtomicInteger q; 
	AtomicInteger r; 
	
	/**
	 * MulAtomicInteger Constructor
	 * 
	 * @param id Thread identity
	 * @param p Reference to shared first factor
	 * @param q Reference to shared second factor
	 * @param r Reference to shared result
	 */
	MulAtomicInteger(int id, AtomicInteger p, AtomicInteger q, AtomicInteger r) {
		this.id = id;
		this.p = p;
		this.q = q;
		this.r = r;
	}

	/**
     * The void run() contains the code that will be executed by the threads.
     * If the second factor is not 0, the result variable is updated by adding 
     * the value of the first factor and the second factor is decreased by one.
     *
     */
	public void run() {
		try {
			int num = 0;
			System.out.println("Starting thread " + id);

			//Check that the second factor is greater than 0
			while (q.get() > 0) {

				Set<Thread> threadSet = Thread.getAllStackTraces().keySet(); //Set of active threads
				num = threadSet.size(); //Number of active threads

				//Use 5 because there is always 4 active threads from the system
				if (num != 5 && q.get() == 0) { 
					break; // only last thread reaches q=0
				}
				
				//Add the value of the first factor to the result
				r.addAndGet(p.get());
				//Decrease second factor by one
				q.addAndGet(-1);
			}
		}

		catch (Exception E) {
			System.out.println("Error " + id);
		} finally {
			System.out.println("Exiting " + id);
		}
	}
}
