package Practica_4.Practica_41;

import java.util.concurrent.atomic.LongAdder;

/**
 *
 * @author Silvia Rodriguez
 * 
 * The class Book represents the book to be read. It contains the methods 
 * that allow reading and knowing if the book is read.
 */
public class Book {

    int numPages;
    int readTime;
    
    //To implement a concurrent access counter in which there are no inconsistencies.
    LongAdder pagesRead;

    /**
     * Book Constructor
     *
     * @param numPages The total number of pages in the book
     * @param readTime Time it takes a reader to read a page
     */
    public Book(int numPages, int readTime) {
        this.numPages = numPages;
        this.readTime = readTime;
        this.pagesRead = new LongAdder();
    }

    /**
     * The function isRead() checks if the threads have finished reading the book. 
     * To do this, the pages read are subtracted to the total number of pages and 
     * verified that it is 0 or less.
     * 
     * @return True if the book is read, False if not
     *
     */
    final synchronized boolean isRead() {
        return (numPages - pagesRead.intValue()) <= 0;
    }

    /**
     * The function read() increases the number of pages read, prints it on the 
     * screen along with the thread that is reading that page and waits for the 
     * time to read a page.
     *
     * @param idReader Thread number (reader) that will read the page
     * @return 1
     * 
     */
    final synchronized int read(int idReader) {
        this.pagesRead.increment();
        System.out.println("Thread " + idReader + " is reading page " + this.pagesRead.intValue());

        try {
            Thread.sleep(readTime);
        } catch (Exception e) {
        }
        return 1;

    }
}
