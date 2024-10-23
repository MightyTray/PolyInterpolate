//DONT DELETE THIS
//THIS IS YOUR BACK UP

package polynomial_interpolate;

import java.util.Scanner;
import java.util.PriorityQueue;

public class LagrangeMethod2{
	
	static double[][] points;
	static boolean seeLangrangePolys;
	
	public static void main(String[] args) {
		
		
		Scanner scan = new Scanner(System.in);
		points = getPoints(scan);
		
		double[] polynomial = getPolynomial();
		print(polynomial);
		
	}
	

	
	
	public static double[] getPolynomial() {
		//should be n(points.length langrange polynomials
		//each polynomial should be degree n-1, so array of size n to represent each polynomial
		double[][] LangrangePolynomials = new double[points.length][points.length];
		double [] polynomial = new double[points.length];
		
		//get each langrange polynomial
		for(int i = 0; i < points.length; i++) {
			LangrangePolynomials[i] = getLangrangeI(i);
		}
		
		double Yi;
		
		//scalar each polynomialI by Yi and add it to polynomial
		for(int polyI = 0; polyI < points.length; polyI++) {
			Yi = points[polyI][1];
			addPoly(polynomial, LangrangePolynomials[polyI], Yi);
			
		}
		
		return polynomial;
	}
	
	
	

	
	//calculates Langrange Polynomial L_(i, n)
	//degree of the polynomial should be (n - 1)
	//n = number of points
	public static double[] getLangrangeI(int i) {

		
		PriorityQueue<double[]> pq = new PriorityQueue<double[]>((a1, a2) -> a1.length - a2.length);
		
		
		double denom = 1;
		double Xi = points[i][0];
		
		//so in pq want to add each linear polynomial (x - x1) (x-x2) ....
		//x1 = points[1-1][0];
		// so x - x1 --> new double[]{-1*x1, 1}
		//use Xj for this: Xj instead of x1
		
		
		//two loops adds every point to pq except point i (ex:  LangrageI(point 1), point 1 is points[0], so add everything in points except points[0])
		//and to get denominator( Xi - X1)(Xi - X2) .... (Xi - Xi-1) (Xi - Xi+1) .... (Xi - Xn)
		double Xj;
		
		for(int j = 0; j < i; j++) {
			Xj = points[j][0];
			denom *= (Xi - Xj);
			
			pq.add(PointToFactor(points[j]));
		}
		
		
		for(int j = i + 1;  j < points.length; j++) {
			Xj = points[j][0];
			denom *= (Xi - Xj);
			
			pq.add(PointToFactor(points[j]));

		}
		
		
		
		double[] newPoly;
		//check if 2 points
		if(points.length == 2) {
			newPoly = pq.poll();
		}else {
		//n(number of points)  >= 3
			newPoly = multiplyPoly(pq.poll(), pq.poll());
		}
		
		
		
		//keep multiply polynomials from priority queue that have smallest orders
		//until you multiplied them all together ( this gets us the numerator)
		while(!pq.isEmpty()) {
			pq.add(newPoly);
			newPoly = multiplyPoly(pq.poll(), pq.poll());
		}
		
		
		
		//divide coefficients by denominator
		for(int j = 0; j < newPoly.length; j++) {
			newPoly[j] /= denom;
		}
		
		
		//if(seeLangrangePolys) {
			System.out.println("Langrange " + (i+1));
			print(newPoly);
			System.out.println();
			
		//}
		
		
		return newPoly;
		
	  }
	
	
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
	
	 
	//converts points like (1,5) to (x - 1) or [1 5] to [1 -1]
	public static double[] PointToFactor(double[] point) {
		return new double[] {-1*point[0], 1};
		
	}
	
	
}
