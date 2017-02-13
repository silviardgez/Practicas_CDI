import java.util.Date;

public class Hilos_141 extends Thread {

	public void run() {

		String threadName = Thread.currentThread().getName();
		System.out.println("Hello, I’m thread number " + threadName);

		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Bye, this was thread number " + threadName);

	}

	public static void main(String[] args) {
		// Declare variables to calculate the time
		long initialTime;
		long totalTime;

		// Init initial time
		initialTime = new Date().getTime();

		if (args.length > 1 || args.length == 0) {
			System.out.println("Error in arguments");
		} else {

			// Create an array with the length equal to the number passed as argument
			Hilos_141[] threads = new Hilos_141[Integer.parseInt(args[0])];

			// Create and start threads, and added their into the array "threads"
			for (int i = 0; i < Integer.parseInt(args[0]); i++) {
				Hilos_141 thread = new Hilos_141();
				threads[i] = thread;
				thread.start();
			}

			for (int i = 0; i < Integer.parseInt(args[0]); i++) {
				try {
					threads[i].join(); // Threads must wait for their parent thread
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}

		System.out.println("Program of exercise 1.4.1 has terminated");

		totalTime = new Date().getTime() - initialTime;
		System.out.println("The execution time is " + totalTime + " ms.");
	}
}
