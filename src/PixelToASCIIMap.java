public class PixelToASCIIMap {
	static final char[] characters = { '@', '%', '#', '*', '+', '=', '-', ':',
			'.', ' ' };
	static int gray_step = 25;

	static char pixelCharacterize(int pixel) {
		if (pixel > 249) {
			return characters[9];
		}
		char character = characters[pixel / gray_step];
		return character;
	}

	static char[][] getCharMatrix(int[][] pixelMatrix) {
		int row_size = pixelMatrix.length;
		int column_size = pixelMatrix[0].length;
		char[][] charMatrix = new char[row_size][column_size];
		for (int row = 0; row < row_size; row++) {
			for (int column = 0; column < column_size; column++) {
				charMatrix[row][column] = pixelCharacterize(pixelMatrix[row][column]);
			}
		}
		return charMatrix;
	}
}
