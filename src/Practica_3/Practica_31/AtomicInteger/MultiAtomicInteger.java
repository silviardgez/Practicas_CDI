package Practica_3.Practica_31.AtomicInteger;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Silvia Rodriguez
 * 
 * The class MultiAtomicInteger contains the main method. It collects all the arguments entered 
 * by command line, initializes the threads and shows the result of multiplication and the total 
 * execution time.
 */
public class MultiAtomicInteger {

	static AtomicInteger p, q, r;
	
	/**
	 * 
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		try {
			//Displays a message if the number of arguments is other than 3.
			if (args.length != 3) {
				System.out.println("Provide 3 arguments.");
				System.exit(1);
			}
			
			p = new AtomicInteger(Integer.parseInt(args[0])); //First factor
			q = new AtomicInteger(Integer.parseInt(args[1])); //Second factor
			r = new AtomicInteger(0); //Result
			
			int n = Integer.parseInt(args[2]); //Number of threads
			
			//Create threads and insert them into an array
			Thread[] threads = new Thread[n];
			for (int i = 0; i < threads.length; ++i) {
				threads[i] = new Thread(new MulAtomicInteger(i, p, q, r));
			}
			
			double start = new Date().getTime(); //start time
			
			//Start threads
			for (int i = 0; i < threads.length; ++i) {
				threads[i].start();
			}
			
			//All threads must wait for their parent thread
			for (int i = 0; i < threads.length; ++i) {
				threads[i].join();
			}
			double end = new Date().getTime(); //end time
			
			//Show multiplication and total time
			System.out.println(args[0] + "*" + args[1] + "=" + r.get());
			System.out.println("Time: " + (end - start));
		}
		catch (Exception E) {
			System.exit(1);
		}

		finally {
			System.out.println("Exiting.");
		}
	}
}
