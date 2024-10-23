package polynomial_interpolate;

import java.util.Scanner;
import java.util.PriorityQueue;

public class LagrangeMethod extends PolyInterpolation {
	
	static double[][] points;
	static double[][] LangrangePolynomials;
	static double[] polynomial;
	
	
	public static void main(String[] args) {
	
		
		Scanner scan = new Scanner(System.in);
		points = getPoints(scan);
		
		double[] polynomial = getPolynomial();
		
		System.out.println("Do you want to see Langrange Polynomials? Y/N");
		
		if(scan.next().toLowerCase().equals("y")) {
			printLangrangePolys();
		
		}
		
		System.out.println("Interpolated Polynomial(Sum of Langrange Polynomials): ");
		print(polynomial);
		
		PolynomialGraph test = new PolynomialGraph(polynomial, points);
		test.graph();
		
	}
	
	
	
	
	public static void printLangrangePolys() {
		int i = 1;
		for(double[] poly: LangrangePolynomials) {
			System.out.println("Langrange " + i + " of " + points.length + " | Point: " + pointToStr(points[i-1]));
			print(poly);
			
			System.out.println();
			i++;
		}
	}

	
	
	public static double[] getPolynomial() {
		//should be n(points.length langrange polynomials
		//each polynomial should be degree n-1, so array of size n to represent each polynomial
		LangrangePolynomials = new double[points.length][points.length];
		polynomial = new double[points.length];
		
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
		
		
		
		//divide each coefficient in polynomial by denominator
		dividePoly(newPoly, denom);
		
		return newPoly;
		
	  }
	
}





//testing Priority QUEUE
//min heap
//PriorityQueue<double[]> pq = new PriorityQueue<double[]>((a1, a2) -> a1.length - a2.length);
//pq.add(new double[3]);
//pq.add(new double[5]);
//pq.add(new double[2]);
//pq.add(new double[122]);
//
//while(!pq.isEmpty()) {
//	System.out.println(pq.poll().length);
//}

//printing points(check scanner working)
//for(double[] x: points) {
//	System.out.println("(" + x[0] + ", " + x[1] + ")");
//}
