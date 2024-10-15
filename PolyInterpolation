package polynomial_interpolate;

import java.util.Scanner;

public class PolyInterpolation {
	
	
	public static double[][] getPoints(Scanner scan) {
		System.out.println("This program calculates the polynomial interpolation using Lagrange's method");
		System.out.println("How many points would you like to interpolate? Please eneter a number greater than or equal to 2");
		
		int numPoints = scan.nextInt();
		double[][] points = new double[numPoints][2];
		
		
		for(int i = 1; i <= numPoints; i++) {
			System.out.println("POINT " + i);
			System.out.print("Enter x" + i + ": ");
			points[i-1][0] = scan.nextDouble();
			
			System.out.print("Enter y" + i + ": ");
			points[i-1][1] = scan.nextDouble();
			System.out.println();
		}
		return points;
	
		
	}


	public static void print(double[] poly) {
		StringBuilder s = new StringBuilder();
		s.append("P_" + (poly.length - 1) + "(x) =");
		
	
		for(int i = poly.length - 1; i >= 1; i--) {
			s.append(" " + poly[i] + "x^" + i + " +");
		}
		s.append(" " + poly[0]);
		System.out.println(s.toString());
	}
	


	//multiply two polynomial via coefficients
	public static double[] multiplyPoly(double[] polyX, double[] polyY){
		double[] newPoly = new double[polyX.length + polyY.length - 1];
		
		for(int i = 0; i < polyX.length; i++) {
			for(int j = 0; j < polyY.length; j++) {
				newPoly[i + j] += polyX[i] * polyY[j];
			}
		}
		
		return newPoly;
		
	}
	
	
	//polyRet and polyY have to have same length
	public static double[] addPoly(double[] polyRet, double[] polyY, double scalarY) {
		for(int i = 0; i < polyY.length; i++) {
			polyRet[i] += scalarY*polyY[i];
		}
		return polyRet;
	}
	
	
	
	public static void dividePoly(double[] poly, double divisor) {
		for(int i = 0; i < poly.length; i++) {
			poly[i] /= divisor;
		}
	}
	
	
	 
	//converts points like (4,9) to (x - 1) or [4 9] --> in form ax + b: [b, a] --> [-4, 1]
	public static double[] PointToFactor(double[] point) {
		return new double[] {-1*point[0], 1};
		
	}
	
	public static String pointToStr(double[] point) {
		return "(" + point[0] + ", " + point[1] + ")";
	}
	
}
