package Practica_4.Practica_42;
import java.util.Date;

/**
 *
 * @author Silvia Rodriguez
 */

public class Practica42 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numReaders = Integer.parseInt(args[0]);
        int numPages = Integer.parseInt(args[1]);
        int readTime = Integer.parseInt(args[2]);

        Reader[] readers = new Reader[numReaders];
        Book lib = new Book(numPages, readTime, numReaders);
        double start = new Date().getTime();//end time
        for (int i = 0; i < numReaders; i++) {
            Reader r = new Reader(i, lib, numReaders);
            r.start();
            readers[i] = r;
        }

        for (int i = 0; i < readers.length; i++) {
            try {
                readers[i].join();
            } catch (Exception e) {
            }
        }
    
        double end = new Date().getTime();//end time

        // Show the total number of pages read by each reader
        for (int i = 0; i < readers.length; i++) {
            System.out.println("Thread " + readers[i].getName() + ": I have read " + readers[i].getPageRead() + " pages");
        }

        System.out.println("Time: " + (end - start));
        System.out.println("Fin del programa");
    }

}
