package Practica_4.Practica_43;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 * @author Silvia Rodriguez
 * 
 * The class Book represents the book to be read. It contains the methods 
 * that allow reading, knowing if the book is read, knowing if it is the 
 * current turn of the thread or increasing the turn.
 */
public class Book {

    int numPages;
    int readTime;
    
    //Variables to identify the current turn and the number of readers.
    int turnNumber;
    int numReaders;
    
    //New variables to identify the mode of application execution and the synchronizer for each thread
    boolean printMode;
    Synchronizer[] sync;
    
    //To implement a concurrent access counter in which there are no inconsistencies
    LongAdder pagesRead;

    /**
     * Book Constructor
     *
     * @param numPages The total number of pages in the book
     * @param readTime Time it takes a reader to read a page
     * @param numReaders Number of readers (number of threads)
     * @param printMode Boolean that controls the outputs and waiting time of the threads according to its value
     */
    Book(int numPages, int readTime, int numReaders, boolean printMode) {
        this.numPages = numPages;
        this.readTime = readTime;
        this.pagesRead = new LongAdder();
        this.numReaders = numReaders;
        this.printMode = printMode;
        
        //Synchronizers are created for each thread and saved in an array
        sync = new Synchronizer[numReaders];
        for (int i = 0; i < numReaders; i++) {
            sync[i] = new Synchronizer();
        }
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
     * If the book is not read yet, the function read() increases the number of pages 
     * read, prints it on the screen along with the thread that is reading that page and 
     * waits for the time to read a page.
     *
     * @param idReader Thread number (reader) that will read the page
     * @return False if the book is read, True if not
     * 
     */
    final synchronized boolean read(int idReader) {
        if (!isRead()) {
            this.pagesRead.increment();
            if (this.printMode) {
                System.out.println("Thread " + idReader + " is reading page " + this.pagesRead.intValue());
            }
            try {
                if (this.printMode) {
                    Thread.sleep(readTime);
                }
            } catch (Exception e) {
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * The function MyTurn() increments the turn if it is equal to the thread number. 
     * If the turn is equal to the total number of readers it is set to 0. 
     *
     * @param id Thread number (reader) attempting to read the page 
     * @return True if it is the turn of the thread, False if not
     * 
     */
    final synchronized boolean MyTurn(int id) {
        if (turnNumber == id) {
            turnNumber = NextTurn();
            if (turnNumber == numReaders) {
                turnNumber = 0;
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Increases the current turn by one unit.
     *
     * @return Turn number
     */
    final synchronized int NextTurn() {
        return turnNumber + 1;
    }
    
    /**
     * Get function of the variable sync
     * @param idReader Thread number from which the synchronizer will be obtained
     * @return Synchronizer of the thread
     */
    public Synchronizer getSynchronizer(int idReader) {
        return this.sync[idReader];
    }
}
