package mines;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MineSweeper {
	
	public static void print(int[][] matrix) {
		for(int i = 0; i < matrix.length; i++) {
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j]);
			}
			System.out.println();				
		}		
	}
			
	private static boolean legalniIndeksi(int i, int j, int[][] matrix) {
		if(i>=0 && i<matrix.length)
			if(j>=0 && j<matrix[i].length)
				return true;
		return false;
	}
	
	private static int izracunaj(int[][] matrix, int i, int j) {
		int[][] pomeraji = { {-1, -1}, {0, -1}, {1, -1}, {-1, 0}, {1, 0}, {-1, 1}, {0, 1}, {1, 1}};
		int brojac = 0;
		for (int k=0; k<pomeraji.length; k++) {
			int i2 = i + pomeraji[k][0];
			int j2 = j + pomeraji[k][1];
			
			if (legalniIndeksi(i2, j2, matrix) && matrix[i2][j2]==1)
				brojac++;
		}
		return brojac;
	}
	
	private static int[][] ucitaj(String putanjaDoFajla) throws IOException{
		try(BufferedReader r = new BufferedReader(new FileReader("map.txt"))){
			String[] nums = r.readLine().split(" ");
			int M = Integer.parseInt(nums[0]);
			int N = Integer.parseInt(nums[1]);
			
			int matrix[][] = new int[M][N];
			
			for(int i = 0; i < matrix.length; i++) {
				String line = r.readLine();
				for(int j = 0; j < matrix[i].length; j++) {
					matrix[i][j] = Character.getNumericValue(line.charAt(j));
				}
			}
			
			return matrix;
		}
	}
	
	private static void obradiUFajlu(int[][] matrix, String putanjaDoFajla) throws IOException {
		try (FileWriter fw = new FileWriter(putanjaDoFajla)) {
			for (int i=0; i<matrix.length; i++) {
				for (int j=0; j<matrix[i].length; j++) {
					fw.write(Integer.toString(izracunaj(matrix,i,j)));
				}
				fw.write("\n");
			}		
		}
	}
	
	
	public static void main(String[] args) {	
		try {
			
			int matrix[][] = ucitaj("map.txt");
			
			obradiUFajlu(matrix, "bombs.txt");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
