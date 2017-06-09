package Practica_3.Practica_31.LongAdder;

import java.util.Date;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * @author Silvia Rodriguez
 * 
 *         The class MultiLongAdder contains the main method. It collects all
 *         the arguments entered by command line, initializes the threads and
 *         shows the result of multiplication and the total execution time.
 */
public class MultiLongAdder {

	static LongAdder p, q, r;

	/**
	 * 
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		try {
			//Displays a message if the number of arguments is other than 3.
			if (args.length != 3) {
				System.out.println("provide 3 arguments...");
				System.exit(1);
			}
			
			p = new LongAdder();
			p.add(Long.parseLong(args[0])); //First factor
			q = new LongAdder();
			q.add(Long.parseLong(args[1])); //Second factor
			r = new LongAdder(); //Result

			int n = Integer.parseInt(args[2]); //Number of threads
			
			//Create threads and insert them into an array
			Thread[] threads = new Thread[n];
			for (int i = 0; i < threads.length; ++i) {
				threads[i] = new Thread(new MulLongAdder(i, p, q, r));
			}
			
			double start = new Date().getTime(); //Start time
			
			//Start threads
			for (int i = 0; i < threads.length; ++i) {
				threads[i].start();
			}
			
			//All threads must wait for their parent thread
			for (int i = 0; i < threads.length; ++i) {
				threads[i].join();
			}
			
			double end = new Date().getTime();//End time
			
			//Show multiplication and total time
			System.out.println(args[0] + "*" + args[1] + "=" + r.sum());
			System.out.println("Time: " + (end - start));
			
		} 
		catch (Exception E) {
			System.exit(1);
		}

		finally {
			System.out.println("exiting...");
		}
	}
}
