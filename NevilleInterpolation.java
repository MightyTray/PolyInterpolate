package polynomial_interpolate;

import java.util.HashMap;
import java.util.Scanner;

//NOT WORKING
public class NevilleInterpolation extends PolyInterpolation {
	
//	static double[][] points;
	//static boolean seeLangrangePolys;
	static HashMap<Integer, HashMap<Integer, double[]>> subPolynomials = new HashMap<Integer, HashMap<Integer, double[]>>();
	
	public static void main(String[] args) {
		
		System.out.println("USING NEVILLE INTERPOLATION");
		
		Scanner scan = new Scanner(System.in);
		double[][] points = getPoints(scan);
		
		for(int i = 0; i < points.length; i++) {
			subPolynomials.put(i, new HashMap<Integer, double[]> ());
		}
		
		//double[] polynomial = getPolynomial();
		double[] polynomial = getPolynomial(0, points.length, points);
		print(polynomial);
		
		
	
		
		//getLine() and addPoly() seem to work as intended
//		print(getLine(-1, -1, 2, 4));
//		print(getLine(0, 1, 2, 3));
//		
//		print(addPoly(getLine(-1, -1, 2, 4), getLine(0, 1, 2, 3), -1));
		
		
		//dividePoly() seems to work
//		double[] x = getLine(0,1,2,3);
//		print(x);
//		dividePoly(x, 4);
//		print(x);
	}
	
	
	
	
	//include start point
	//don't include end point
	public static double[] getPolynomial(int start, int end, double[][] points) {
		double[] poly;
		
		//2 points only --> so line
		if(end - start == 2) {
			poly = getLine(points[start][0], points[start][1], points[end-1][0], points[end-1][1]);
			subPolynomials.get(start).put(end, poly);
			
			return poly;
		}
		
		//is it a polynomial we already found?
		if(subPolynomials.containsKey(start)) {
			poly = subPolynomials.get(start).getOrDefault(end, null);
			if(poly != null)
				return poly;
		}
		
		
		double[] factorOne = PointToFactor(points[start]);
		double[] factorN  = PointToFactor(points[end - 1]);
		
		//not points[0][0], but should be points[start][0]
		double denominator = (points[end - 1][0] - points[start][0]);
		
		double[] polyNoOne = getPolynomial(start + 1, end, points);
		double[] polyNoEnd = getPolynomial(start, end - 1, points);
		
		
		//addPoly and dividePoly seems to work
		poly = addPoly(multiplyPoly(factorOne, polyNoOne), multiplyPoly(factorN, polyNoEnd), -1);
		dividePoly(poly, denominator);
		
		subPolynomials.get(start).put(end, poly);
		return poly;
		
	}
	
	//seems to work
	public static double[] getLine(double x1, double y1, double x2, double y2) {
		double slope = (y2- y1)/(x2-x1);
		double intercept = y2 - slope*x2;
		return new double[]{intercept, slope};
	}
	

}
