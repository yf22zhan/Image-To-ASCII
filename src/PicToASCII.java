import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PicToASCII {

	private static void ConvertToGrayScale(BufferedImage image) {
		int imgWidth = image.getWidth();
		int imgHeight = image.getHeight();
		for (int w = 0; w < imgWidth; w++) {
			for (int h = 0; h < imgHeight; h++) {
				Color color = new Color(image.getRGB(w, h));
				int gray = (77 * color.getRed() + 150 * color.getGreen() + 29 * color
						.getBlue()) >> 8;
				Color newColor = new Color(gray, gray, gray);
				image.setRGB(w, h, newColor.getRGB());
			}
		}
	}

	private static void print(BufferedWriter output, char[][] charMatrix)
			throws IOException {
		int row_size = charMatrix.length;
		for (int row = 0; row < row_size; row++) {
			output.write(charMatrix[row]);
			output.newLine();
		}
	}

	static void run(String filePath) {
		try {
			// read image file
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);

			// write file
			FileWriter outputStream = new FileWriter(
					"C:\\Users\\Yi Fan\\Desktop\\ASCII_ouput.txt");
			BufferedWriter output = new BufferedWriter(outputStream);

			// Grayscale the image
			ConvertToGrayScale(image);

			// Convert the image into a grayness matrix of a smaller scale
			int[][] pixelMatrix = PixelAverager.getPixelMatrix(image);

			// Convert the pixelMatrix into a charMatrix
			char[][] charMatrix = PixelToASCIIMap.getCharMatrix(pixelMatrix);
			
			// The commented-out creates a grayscaled image
			// File out = new File("C:\\Users\\Yi Fan\\Desktop\\logo.jpg");
			// ImageIO.write(image, "jpg", out);

			// print the charMatrix
			print(output, charMatrix);
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
