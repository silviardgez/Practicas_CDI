
package Practica_4.Practica_41;

import java.util.concurrent.atomic.LongAdder;

/**
 *
 * @author Silvia
 */
public class Book {

    int numPages;
    int readTime;
    LongAdder pagesRead;

    Book(int numPages, int readTime) {
        this.numPages = numPages;
        this.readTime = readTime;
        this.pagesRead = new LongAdder();
    }

    final synchronized boolean isRead() {
        return (numPages - pagesRead.intValue()) <= 0;
    }

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
