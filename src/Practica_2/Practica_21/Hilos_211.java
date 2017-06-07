package Practica_2.Practica_21;

// These are the packages we need for the example.
import java.awt.image.*;
import java.io.File;
import javax.imageio.*;

public class Hilos_211 {
	/*
	 * (c) copyright 2017 Universidade de Vigo. All rights reserved.
	 * formella@uvigo.es, http://formella.webs.uvigo.es
	 */

	/**
	 * \file \brief Gray scale image read/write
	 */

	/// \brief Main class to launch application.
	/// \note We always print a final message before leaving.

	static BufferedImage img;

	public static void main(
			// Well, we still don't use the arguments.
			String[] args) {
		System.out.println("starting...");
		try {
			// Here, we just read a fixed file, the example is a 16-bit
			// gray scale image.
			File file_in = new File("nanotube.png");
			img = ImageIO.read(file_in);

			// We prepare our data array and a raster to access the image.
			final int width = img.getWidth();
			final int height = img.getHeight();
			int[][] data = new int[width][height];
			Raster raster_in = img.getData();

			// We copy the data from the image to our array.
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					final int d = raster_in.getSample(i, j, 0);
					data[i][j] = d;
				}
			}

			// We build an output raster and fill-in with some modified data.
			WritableRaster raster_out = img.getRaster();
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					raster_out.setSample(i, j, 0, data[i][j] / 2);
				}
			}

			// We set an output image with the raster and write the
			// image to a file.
			img.setData(raster_out);
			File file_out = new File("n.png");
			ImageIO.write(img, "png", file_out);
		} catch (Exception E) {
			// Here, we just exit the program, something more useful should
			// be implemented...
			System.exit(1);
		}
		// As always: out last line.
		System.out.println("Program of exercise 2.1.1 has terminated");
	}
}
