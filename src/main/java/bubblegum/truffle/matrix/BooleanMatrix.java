package bubblegum.truffle.matrix;

import java.util.Random;

public class BooleanMatrix{
	//Dimensions
	private final int m,n;
	private final boolean[][] data;
	
	public static BooleanMatrix createZeros(int m, int n){
		BooleanMatrix result = new BooleanMatrix(m, n);	
		return result;
	}
	
	public static BooleanMatrix createRandom(int m, int n){
		BooleanMatrix result = new BooleanMatrix(m, n);
		assignRandom(result.data);
		return result;
	}
	
	private BooleanMatrix(int m, int n) {
		this.m=m;
		this.n=n;
		data = new boolean[m][n];
	}
	
	private static void assignRandom(boolean[][] data){
		Random rnd = new Random();
		for(int i=0; i<data.length;i++)
			for(int j=0; j<data[0].length; j++)
				data[i][j]=rnd.nextBoolean();
	}
	
	public int getRows(){
		return m;
	}
	public int getCols(){
		return n;
	}
	public boolean[][] getData(){
		return data;
	}

	@Override
    public String toString() {
        return "["+m+","+n+"]"+" "+data.toString();
    }
	
}
