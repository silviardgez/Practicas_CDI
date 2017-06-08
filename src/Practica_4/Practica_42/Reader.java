package Practica_4.Practica_42;

/**
 *
 * @author Silvia Rodriguez
 * 
 * The class Reader extends Thread. Each reader will be a different thread.
 * This class contains the code that will be executed by each thread.
 */

public class Reader extends Thread {

     int idReader;
     Book book;
     int numReaders;
     int pageRead;

     /**
      * Reader Constructor 
      * 
      * @param idReader Reader (thread) identification number
      * @param book Book that will be read
      * @param numReaders Number of readers (number of threads) that will read that book
      */
     public  Reader(int idReader, Book book, int numReaders) {
        this.idReader = idReader;
        this.book = book;
        this.numReaders = numReaders;
    }

    /**
     * The void run() contains the code that will be executed by the threads.
     * If the book is not read yet and it is the thread's turn, it reads a 
     * page and notifies all threads when finished. If it is not their turn, 
     * it puts on hold.
     *
     */
    public void run() {
        while (!book.isRead()) {
            synchronized (book) { //Critical region. It can only be executed by a thread at the same time.
                if (book.MyTurn(idReader)) {
                    if (this.book.read(idReader)) {
                        pageRead++;
                    }
                    book.notifyAll();
                } else {
                    try {
                        book.wait();
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

    /**
     * @return Number of read pages
     */
    public int getPageRead() {
        return pageRead;
    }
}
