
public class Hilos_224 {

	public void run() {

		String threadName = Thread.currentThread().getName();
		System.out.println("Hello, I’m thread number " + threadName);
		try {
			Thread.sleep(2000);// Stop the process 1 second
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("Bye, this was thread number " + threadName);

	}

	public static void main(String[] args) { // function main
		
	}

	

}
