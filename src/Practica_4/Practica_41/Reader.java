
package Practica_4.Practica_41;

/**
 *
 * @author Silvia Rodriguez
 * 
 * The class Reader extends Thread. Each reader will be a different thread.
 * This class contains the code that will be executed by each thread.
 */
public class Reader extends Thread {

    private int idReader;
    private Book book;
    private int numReaders;
    private int pageRead;

    /**
     * Reader Constructor 
     * 
     * @param idReader Reader (thread) identification number
     * @param book Book that will be read
     * @param numReaders Number of readers (number of threads) that will read that book
     */
    Reader(int idReader, Book book, int numReaders) {
        this.idReader = idReader;
        this.book = book;
        this.numReaders = numReaders;
    }

    
    /**
     * The void run() contains the code that will be executed by the threads.
     * If the book is not read yet and wait time is greater than 0, it reads a 
     * page.
     *
     */
    public void run() {
        while (!book.isRead()) {
        	//Waiting time requested in the exercise
            int waitTime = book.readTime / (2 * numReaders);
            if (waitTime > 0) {
                try {
                    Thread.sleep(waitTime);
                } catch (Exception e) {
                }
            }
            pageRead += this.book.read(idReader);
        }
    }

    /**
     * @return Number of read pages
     */
    public int getPageRead() {
        return pageRead;
    }
}
