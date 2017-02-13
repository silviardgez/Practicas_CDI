
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Hilos_142 extends Thread {

	public static Random r = new Random();
	public static final int DIAGRAM_POSITIONS = 100;

	public void run() {

		String threadName = Thread.currentThread().getName();
		System.out.println("Hello, I’m thread number " + threadName);
		try {
			Thread.sleep(r.nextInt(2000));// Stop the process 1 second
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Bye, this was thread number " + threadName);

	}

	public static void main(String[] args) { // function main
		long totalTime;
		long initialTime;

		// Init initial time
		initialTime = new Date().getTime();

		if (args.length > 1 || args.length == 0) { // comprobe that the num of args is bigger than 1
			System.out.println("Error in arguments");
		} else {
			int numHilos = Integer.parseInt(args[0]);
			long maxDuration = Long.MIN_VALUE;

			Hilos_142[] threads = new Hilos_142[numHilos];
			long[][] diagram = new long[numHilos][2];

			for (int i = 0; i < numHilos; i++) { // Appeal the num of args to create many threads as arguments
				Hilos_142 h = new Hilos_142();
				threads[i] = h;
				diagram[i][0] = new Date().getTime() - initialTime;
				h.start(); // init thread
				try {
					sleep(r.nextInt(200));
				} catch (InterruptedException e) {
					
					System.out.println(e);
				}
			}

			for (int i = 0; i < numHilos; i++) {
				try {
					threads[i].join();// threads must wait for their parent thread
					long duration = new Date().getTime() - initialTime;
					maxDuration = Long.max(maxDuration, duration);
					diagram[i][1] = duration;
				} catch (Exception e) {
					System.out.println(e);
				}
			}

			System.out.println();
			System.out.println();

			printTable(threads, diagram);

			System.out.println();
			System.out.println();

			// calculate max length of threads names
			int maxThreadLength = 0;
			for (Thread t : threads) {
				maxThreadLength = Integer.max(maxThreadLength, t.getName().length());
			}

			printDiagram(maxDuration, threads, diagram, maxThreadLength);

			printHorizontalLine(maxThreadLength);
		}
		System.out.println();
		System.out.println("Program of exercise 1.4.2 has terminated");

		totalTime = new Date().getTime() - initialTime; // Calcule total time
		System.out.println("The execution time is " + totalTime + " ms.");// print execute time(total time)
	}

	private static void printTable(Hilos_142[] threads, long[][] diagram) {

		ArrayList<String> toPrint = new ArrayList<>();
		for (int i = 0; i < diagram.length; i++) {
			toPrint.add("|\t" + threads[i].getName() + "\t|\t" + diagram[i][0] + "\t|\t" + diagram[i][1] + "\t|");
		}
	
		System.out.println("|\t Name \t\t|\tStart  \t|\tEnd \t|");
	
		for (String string : toPrint) {
			System.out.println(string);
		}
	

	}

	private static void printDiagram(long maxDuration, Hilos_142[] threads, long[][] diagram, int maxThreadLength) {
		for (int i = 0; i < diagram.length; i++) {
			long partLength = maxDuration / DIAGRAM_POSITIONS;
			boolean started = false;
			char toPrint = ' ';
			System.out.print(threads[i].getName() + " |");
			for (int j = 0; j < DIAGRAM_POSITIONS; j++) {
				if (diagram[i][0] > partLength * j || diagram[i][1] < partLength * j) {
					toPrint = ' ';
				} else {
					if (diagram[i][1] < partLength * (j + 1)) {
						toPrint = '>';

					} else if (started) {
						toPrint = '-';
					} else {
						toPrint = '<';
						started = true;
					}
				}
				System.out.print(toPrint);

			}
			if (toPrint == '-') {
				System.out.print('>');
			}

			System.out.println();

			if (i != diagram.length - 1) {
				for (int j = 0; j <= maxThreadLength; j++) {
					System.out.print(" ");
				}
				System.out.println("|");
			}
		}
	}

	//Method for printing the abscissa axis (X-axis)
	private static void printHorizontalLine(int maxThreadLength) {
		for (int j = 0; j <= maxThreadLength; j++) {
			System.out.print(" ");
		}
		System.out.print("|");
		for (int i = 0; i < DIAGRAM_POSITIONS+5; i++) {
			System.out.print("_");
		}
		System.out.println();
	}

}