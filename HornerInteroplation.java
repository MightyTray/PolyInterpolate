package polynomial_interpolate;

import java.util.Scanner;

public class HornerInteroplation extends PolyInterpolation {
	
	static double[][] dp;
	static double[] coefficients;
	
	public static void main(String[] args) {

		System.out.println("USING HORNER INTERPOLATION");
		
		Scanner scan = new Scanner(System.in);
		double[][] points = getPoints(scan);
		
		
		dp = new double[points.length][points.length + 1];
		coefficients = new double[points.length];
		
		putPointsInDP(points);
		
		fillRestOfDP();
		
		printDPArray();
		printCoefficients();
		printHornerPoly();
		
		//Check LATER
		//double[] polynomial = getPolynomial();
//		double[] polynomial = getPolynomial();
//		print(polynomial);
	}
	
	
	public static double[] getPolynomial() {
		return null;
	}
	
	
	
	public static void fillRestOfDP() {
		int columns = dp[0].length;
		int rows = dp.length;
		double x1, Y1, x2, Y2;
		
		//numbers of elements in the current column that are 0
		//so for column 2(the 2 points or degree 1 polynomials), there is one 0(at first row)
		//for column 3(3 points, 2 degree polynomials), two 0(first and second row)...
		
		//at col 2, one
		int numZeroElements = 1;
		
		for(int col = 2; col < columns; col++) {

			for(int row = col - 1; row < rows; row++){
				x1 = dp[row - numZeroElements][0];
				Y1 = dp[row - 1][col - 1];
				
				x2 = dp[row][0];
				Y2 = dp[row][col - 1];
				
				dp[row][col] = getSlope(x1, Y1, x2, Y2);
			}
			
			numZeroElements++;
		}
		
		fillCoefficients();
		
	}
	
	public static void fillCoefficients() {
		for(int row = 0; row < dp.length; row++) {
			coefficients[row] = dp[row][row+1];
		}
	}
	
	
	
	public static void putPointsInDP(double[][] points) {
		for(int r = 0; r < points.length; r++) {
			dp[r][0] = points[r][0];
			dp[r][1] = points[r][1];
		}
	}
	

	
	public static double getSlope(double x1, double Y1, double x2, double Y2) {
		return (Y2 - Y1)/(x2 - x1);
	}
	
	

	public static void printDPColumnHeaders() {
		StringBuilder s = new StringBuilder();
		s.append("X\tY");
		for(int i = 0; i < dp.length; i++){
			s.append("(deg = " + i + ")\t");
		}
		System.out.println(s.toString());
	}
	
	
	
	public static void printDPArray(){
		System.out.print("\n\n\n");
		
		System.out.println("DP ARRAY\n");
		printDPColumnHeaders();
		
		for(int r = 0; r < dp.length; r++) {
			for(int c = 0; c < dp[r].length; c++) {
				System.out.printf("%.3f\t   ", dp[r][c]);
			}
			System.out.println();
		}
		System.out.println();
	}
	

	public static void printCoefficients() {
		System.out.println("Horner Coefficients");
		for(int i = 0; i < coefficients.length; i++) {
			System.out.print("a_" + i + " = " + coefficients[i] + "\t");
		}
		System.out.print("\n\n");
	}

	
	public static void printHornerPoly() {
		System.out.println("P_" + (coefficients.length - 1) + "(x) = ");
		printHornerPolyHelper(new StringBuilder(), 0);
		
	}
	
	private static void printHornerPolyHelper(StringBuilder s, int deg) {
		if(deg >= coefficients.length) return;
		//else
		
		String sign;
		double curCoeff = coefficients[deg];
		
		if(curCoeff > 0) {
			sign = "+";
		}else {
			//already included in negative sign of number
			sign = "";
		}
		
		
		System.out.println("\t" + sign + String.format("%.3f", curCoeff) + s.toString());
		//dp[0][deg] gets x1
		
		s.append("(x - " + dp[deg][0] + ")");
		printHornerPolyHelper(s, deg + 1);
	}
	
}
