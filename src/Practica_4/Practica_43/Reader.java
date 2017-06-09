package Practica_4.Practica_43;

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
    private boolean printMode;
    
    /**
     * Reader Constructor 
     * 
     * @param idReader Reader (thread) identification number
     * @param book Book that will be read
     * @param numReaders Number of readers (number of threads) that will read that book
     * @param printMode Boolean that controls the outputs and waiting time of the threads according to its value
     */
    public Reader(int idReader, Book book, int numReaders, boolean printMode) {
        this.idReader = idReader;
        this.book = book;
        this.numReaders = numReaders;
        this.printMode = printMode;

    }

    /**
     * The void run() contains the code that will be executed by the threads.
     * If the book is not read yet and it is the thread's turn, it reads a 
     * page and notifies the next thread immediately when finished. If it is not 
     * their turn, it puts on hold.
     *
     */
    public void run() {
    	//If there is only one thread, it will read the entire book
        if (numReaders <= 1) {
            while (!book.isRead()) {
                this.book.read(idReader);
                pageRead++;
            }
        } else {
        	//Put all threads on hold
            synchronized (book.getSynchronizer(idReader)) {
                try {
                    book.getSynchronizer(idReader).wait();
                } catch (Exception E) {
                }
            }
            while (!book.isRead()) {
            	//If it is not its turn, it is put on hold
                while (!book.MyTurn(idReader)) { 
                    synchronized (book.getSynchronizer(idReader)) {
                        try {
                            book.getSynchronizer(idReader).wait();
                        } catch (Exception E) {
                        }
                    }
                }

                try {
                    if (this.printMode) {
                        Thread.sleep(book.readTime / (2 * numReaders));
                    }
                } catch (Exception e) {
                }

                //Sentence where the thread reads a page
                if (this.book.read(idReader)) {
                    pageRead++;
                }

              //Notifies just the next thread
                synchronized (book.getSynchronizer((idReader + 1) % numReaders)) { 
                    book.getSynchronizer((idReader + 1) % numReaders).notify();
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
