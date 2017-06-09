package Practica_4.Practica_43;
import java.util.Date;

/**
 *
 * @author Silvia Rodriguez
 * 
 * The class Library contains the main method. It collects all the arguments entered 
 * by command line, initializes the threads and shows the number of pages read by each 
 * thread and the total execution time.
 */

public class Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numReaders = Integer.parseInt(args[0]);
        int numPages = Integer.parseInt(args[1]);
        int readTime = Integer.parseInt(args[2]);
        //New variable that allows to activate/deactivate sleeps and exits to console
        boolean printMode = Boolean.parseBoolean(args[3]);

        
        Reader[] readers = new Reader[numReaders];
        Book lib = new Book(numPages, readTime, numReaders, printMode);
        double start = new Date().getTime();//end time
        System.out.println("The program is starting");
        
        //Initialize threads (readers)
        for (int i = 0; i < numReaders; i++) {
            Reader r = new Reader(i, lib, numReaders, printMode);
            r.start();
            readers[i] = r;
        }

        //Sleep so that the thread 0 does not wake up early
        try {
            Thread.sleep(5);
        } catch (Exception e) {
        }
        
        //Thread 0 is notified 
        synchronized (lib.getSynchronizer(0)) {
            lib.getSynchronizer(0).notify();
        }

        //All threads must wait for their parent thread
        for (int i = 0; i < readers.length; i++) {
            try {
                readers[i].join();
            } catch (Exception e) {
            }
        }

        double end = new Date().getTime();//end time

        // Show the total number of pages read by each reader
        if (printMode) {
            for (int i = 0; i < readers.length; i++) {
                System.out.println("Thread " + readers[i].getName() + ": I have read " + readers[i].getPageRead() + " pages");
            }
        }

        //Show total time and end of the program
        System.out.println("Time: " + (end - start));
        System.out.println("Program of exercise 4.3 has terminated");
    }

}
