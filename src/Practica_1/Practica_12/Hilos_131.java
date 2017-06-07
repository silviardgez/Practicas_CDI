package Practica_1.Practica_12;

public class Hilos_131 extends Thread {

	public void run() {

		String threadName = Thread.currentThread().getName();
		System.out.println("Hello, I’m thread number " + threadName);

		try {
			Thread.sleep(1000); // Stop the process 1 second
		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Bye, this was thread number " + threadName);

	}

	public static void main(String[] args) {

		if (args.length > 1 || args.length == 0) {
			System.out.println("Error in arguments");
		} else {

			// Create an array with the length equal to the number passed as argument
			Hilos_131[] threads = new Hilos_131[Integer.parseInt(args[0])];

			// Create and start threads, and added their into the array "threads"
			for (int i = 0; i < Integer.parseInt(args[0]); i++) {
				Hilos_131 thread = new Hilos_131();
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

		System.out.println("Program of exercise 2 has terminated");

	}

}