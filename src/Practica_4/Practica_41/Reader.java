
package Practica_4.Practica_41;

/**
 *
 * @author Silvia
 */
public class Reader extends Thread {

    private int idReader;
    private Book book;
    private int numReaders;
    private int pageRead;

    Reader(int idReader, Book book, int numReaders) {
        this.idReader = idReader;
        this.book = book;
        this.numReaders = numReaders;
    }

    public void run() {
        while (!book.isRead()) {
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

    public int getPageRead() {
        return pageRead;
    }
}
