package Practica_2.Practica_21;

// These are the packages we need for the example.
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;

public class Hilos_213 extends Thread {
	/*
	 * (c) copyright 2017 Universidade de Vigo. All rights reserved.
	 * formella@uvigo.es, http://formella.webs.uvigo.es
	 */

	static BufferedImage img;
	static int widthThread;
	static int heightThread;
	static int thresholdThread;
	
	public Hilos_213(int width, int height, int threshold) {
		widthThread = width;
		heightThread = height;
		thresholdThread = threshold;
	}

	public void run() {

		String threadName = Thread.currentThread().getName();
		System.out.println("Hello, I’m thread number " + threadName);

		try {
			int[][] data = new int[widthThread][heightThread];
			Raster raster_in = img.getData();
			System.out.println(widthThread + " " + heightThread);
			// We copy the data from the image to our array.
			for (int i = 0; i < widthThread; i++) {
				for (int j = 0; j < heightThread; j++) {
					final int d = raster_in.getSample(i, j, 0);
					if (d > thresholdThread) {
						data[i][j] = 16777215;
					} else {
						data[i][j] = 0;
					}
				}
			}

			// We build an output raster and fill-in with some modified data.
			WritableRaster raster_out = img.getRaster();
			for (int i = 0; i < widthThread; i++) {
				for (int j = 0; j < heightThread; j++) {
					raster_out.setSample(i, j, 0, data[i][j] / 2);
				}
			}

			// We set an output image with the raster and write the
			// image to a file.
			img.setData(raster_out);

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("Bye, this was thread number " + threadName);

	}

	public static void main(
			// Well, we still don't use the arguments.
			String[] args) {
		System.out.println("starting...");
		int threshold = Integer.parseInt(args[0]);
		int threads = Integer.parseInt(args[1]);
		try {
			// Here, we just read a fixed file, the example is a 16-bit
			// gray scale image.
			File file_in = new File("nanotube.png");
			img = ImageIO.read(file_in);

			// We prepare our data array and a raster to access the image.
			final int width = img.getWidth();
			final int height = img.getHeight();
			System.out.println("LA ALTURA TOTAL ES " + height);
			System.out.println("EL ANCHO TOTAL ES " + width);
			final int threadRange = height / threads;
			System.out.println("EL RANGO ES " + threadRange);
			
			for (int i = 0; i < threads; i++) {
				System.out.println("La multiplicación es: " + threadRange*i);
				int heightVar = threadRange*i;
				Hilos_213 thread = new Hilos_213(width,heightVar,threshold);
				thread.start();
			}

			File file_out = new File("n.png");
			ImageIO.write(img, "png", file_out);
		} catch (Exception E) {
			// Here, we just exit the program, something more useful should
			// be implemented...
			System.exit(1);
		}
		// As always: out last line.
		System.out.println("Program of exercise 2.1.3 has terminated");
	}
}