import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class PixelAverager {
	final static int rowStep = 8;
	final static int colStep = 5;
	final static int unit_area_pixel_num = rowStep * colStep;

	static BufferedImage copy_image;
	static int imgWidth;
	static int imgHeight;
	static int colNum;
	static int rowNum;

	static int[][] pixelMatrix;

	static void paramSetter(int width, int height) {
		colNum = width / colStep;
		rowNum = height / rowStep;
		pixelMatrix = new int[rowNum][colNum];
		
		//Used for info output during development
		//System.out.printf("%d, %d\n", width, height);
		//System.out.printf("%d, %d\n", colNum, rowNum);
	}

	static int unitAverage(int row, int column) {
		int average = 0;
		for (int i = 0; i < rowStep; i++) {
			for (int j = 0; j < colStep; j++) {
				Color color = new Color(copy_image.getRGB(column + j, row + i));
				average += color.getRed();
				//System.out.println(color.getRed());
			}
		}
		//System.out.printf("%d, %d\n", column, row);
		return average / unit_area_pixel_num;
	}

	static BufferedImage resize(BufferedImage image, int newWidth, int newHeight) { 
	    Image tmp = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
	    BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);

	    Graphics2D g2d = dimg.createGraphics();
	    g2d.drawImage(tmp, 0, 0, null);
	    g2d.dispose();

	    return dimg;
	}  
	
	static int[][] getPixelMatrix(BufferedImage image) {
		imgWidth = image.getWidth();
		imgHeight = image.getHeight();
		if(imgWidth > 2 * colStep * 80) {
			if(imgWidth >= imgHeight) {
				double ratio = imgWidth / imgHeight;
				image = resize(image, colStep * 80, (int) (colStep * 80 * ratio));
				imgWidth = image.getWidth();
				imgHeight = image.getHeight();
			} else {
				double ratio = imgHeight / imgWidth;
				image = resize(image, colStep * 80, (int) (colStep * 80 * ratio));
				imgWidth = image.getWidth();
				imgHeight = image.getHeight();
			}
		}
		copy_image = image;
		paramSetter(imgWidth, imgHeight);
		for (int row = 0; row < rowNum; row++) {
			for (int column = 0; column < colNum; column++) {
				pixelMatrix[row][column] = unitAverage(row * rowStep, column
						* colStep);
			}
		}
		return pixelMatrix;
	}
}
