
package Practica_4.Practica_41;

/**
 *
 * @author Silvia
 */
public class Practica41 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int numReaders = Integer.parseInt(args[0]);
        int numPages = Integer.parseInt(args[1]);
        int readTime = Integer.parseInt(args[2]);

        Reader[] readers = new Reader[numReaders];
        Book lib = new Book(numPages, readTime);

        for (int i = 0; i < readers.length; i++) {
            readers[i] = new Reader(i, lib, numReaders);
            readers[i].start();
        }

        // Show the total number of pages readed by each reader
        for (int i = 0; i < numReaders; i++) {
            System.out.println("Thread " + readers[i].getName() + ": I have read " + readers[i].getPageRead() + " pages");
        }
    }

}
