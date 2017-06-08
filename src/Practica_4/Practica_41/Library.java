package Practica_4.Practica_41;
import java.util.Date;

/**
 * 
 * @author Silvia Rodriguez
 * 
 * The class Library contains the main method. It collects all the arguments entered 
 * by command line, initializes the threads and shows the number of pages read by each 
 * thread and the total execution time.
 * 
 */

public class Library {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numReaders = Integer.parseInt(args[0]); //Number of total readers
        int numPages = Integer.parseInt(args[1]); //Total pages of the book
        int readTime = Integer.parseInt(args[2]); //Time it takes a reader to read a page

        Reader[] readers = new Reader[numReaders];
        Book lib = new Book(numPages, readTime);
        double start = new Date().getTime(); //start time
        
        //Initialize threads (readers)
        for (int i = 0; i < numReaders; i++) {
            Reader r = new Reader(i, lib, numReaders);
            r.start();
            readers[i] = r;
        }

        //All threads must wait for their parent thread
        for (int i = 0; i < readers.length; i++) {
            try {
                readers[i].join();
            } catch (Exception e) {
            }
        }
    
        double end = new Date().getTime(); //end time

        //Show the total number of pages read by each reader
        for (int i = 0; i < readers.length; i++) {
            System.out.println("Thread " + readers[i].getName() + ": I have read " + readers[i].getPageRead() + " pages");
        }

        //Show total time and end of the program
        System.out.println("Time: " + (end - start));
        System.out.println("Program of exercise 4.1 has terminated");
    }

}
