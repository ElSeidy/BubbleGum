package bubblegum.truffle.matrix;

import java.util.Random;

public class DoubleMatrix {
	//Dimensions
	private final int m,n;
	private final double[][] data;

	public static DoubleMatrix createZeros(int m, int n){
		DoubleMatrix result = new DoubleMatrix(m, n);	
		return result;
	}

	public static DoubleMatrix createRandom(int m, int n){
		DoubleMatrix result = new DoubleMatrix(m, n);
		assignRandom(result.data);
		return result;
	}

	private DoubleMatrix(int m, int n) {
		this.m=m;
		this.n=n;
		data = new double[m][n];
	}

	private static void assignRandom(double[][] data){
		Random rnd = new Random();
		for(int i=0; i<data.length;i++)
			for(int j=0; j<data[0].length; j++)
				data[i][j]=rnd.nextDouble();
	}

	public int getRows(){
		return m;
	}
	public int getCols(){
		return n;
	}
	public double[][] getData(){
		return data;
	}

	@Override
	public String toString() {
		return "["+m+","+n+"]"+" "+data.toString();
	}
}
