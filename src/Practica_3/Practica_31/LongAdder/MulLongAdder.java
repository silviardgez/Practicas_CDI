package Practica_3.Practica_31.LongAdder;

import java.util.concurrent.atomic.LongAdder;
import java.util.Random;

/**
 *
 * @author Silvia Rodriguez
 * 
 * The class MulLongAdder implements Runnable and performs the multiplication.
 * This class contains the code that will be executed by each thread.
 */
public class MulLongAdder implements Runnable {
    int id; 
    LongAdder p;
    LongAdder q; 
    LongAdder r; 
    
    /**
	 * MulLongAdder Constructor
	 * 
	 * @param id Thread identity
	 * @param p Reference to shared first factor
	 * @param q Reference to shared second factor
	 * @param r Reference to shared result
	 */
    MulLongAdder(int id, LongAdder p, LongAdder q, LongAdder r){
        this.id=id;
        this.p=p;
        this.q=q;
        this.r=r;
    }
    
    /**
     * The void run() contains the code that will be executed by the threads.
     * If the second factor is not 0, the result variable is updated by adding 
     * the value of the first factor and the second factor is decreased by one.
     *
     */
    public void run(){
        try{
            Random random= new Random();
            System.out.println("Starting thread "+id);
     
            //Check that the sum of second factor is greater than 0
            while(q.sum()>0){
               
            	//Add the value of the first factor to the result
                r.add(p.sum());
                //Decrement second factor
                q.decrement();
              
                Thread.sleep(random.nextInt(50));             
            }   
        }
        
        catch(Exception E){
            System.out.println("Error "+id);
        }
        finally {
            System.out.println("Exiting..."+id);
        }
    }
}
