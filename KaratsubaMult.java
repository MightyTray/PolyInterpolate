package polynomial_interpolate;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class KaratsubaMult {
	public static void main(String[] args) {
//		Scanner scan = new Scanner(System.in);
		int[] polyOne = new int[] {0, 1, 2, 3, 4, 5};
		int[] polyTwo = new int[] {0, 1, 2, 3, 4, 5};
		
//		int[] polyOne = new int[] {0, 1, 2, 3, 4};
//		int[] polyTwo = new int[] {0, 1, 2, 3, 4};
		
		printArray(kMult(polyOne, polyTwo));
		
		printArray(multiplyArrays(polyOne, polyTwo));
	}
	
	
	//for now assume length of 2 are the same
	
	//trying to implement karatsubaMultiplication with arrays(maybe use if for multiplying polynomials later)
	//length of array is right, but some of the numbers are off
	public static int[] kMult(int[] x, int[] y) {
		
		//need base case
		if(x.length == 1 || y.length == 1) {
			return multiplyArrays(x, y);
		}
		
		
		
		//x0 = lower x,  y0 = lower y
		//x1 = upper x,  y1 = upper y
		
		//z0 = x0*y0
		//z2 = x1*y1
		//z1 = (x0 + x1)*(y0 + y1) - z0 - z2
	
		//return z = z0 + z2 + z1
		//requires 3 array multiplications of quadratic time
		//array addition can be done linear time
		
		int[] lowerX = Arrays.copyOfRange(x, 0, x.length/2);
		int[] upperX = Arrays.copyOfRange(x, x.length/2, x.length);
		
		int[] lowerY = Arrays.copyOfRange(y, 0, y.length/2);
		int[] upperY = Arrays.copyOfRange(y,  y.length/2, y.length);
		
		
		
		int[] zLower = kMult(lowerX, lowerY);
		int[] zUpper = kMult(upperX, upperY);
		int[] zMid =  add(  add(  kMult( add(lowerX, upperX, 1), add(lowerY, upperY, 1) ), zLower, -1), zUpper, -1);  
		
		
		int[] z = new int[x.length + y.length - 1];
		addWithOffset(z, zLower, 0);
		//assume x.length/2 == y.length/2 (x and y have same length)
		addWithOffset(z, zMid,  x.length/2);
		addWithOffset(z, zUpper, x.length/2 + y.length/2);
		
			
		return z;
	}
	
	
	//polyRet and polyY have to have same length
	//scalar is 1 or -1 in this case
		public static int[] add(int[] one, int[] two, int scalar) {
			int[] ret = new int[one.length];
			for(int i = 0; i < one.length; i++) {
				ret[i] = one[i] + scalar*two[i];
			}
			return ret;
		}
		
		
		
		public static void addWithOffset(int[] ret, int[] extra, int offset) {
			for(int i = 0; i < extra.length; i++) {
				ret[i+offset] += extra[i];
			}
		}
		
		
		
		public static void printArray(int[] x) {
			System.out.println();
			for(int i: x) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
		
		
		//multiply arrays
		public static int[] multiplyArrays(int[] one, int[] two){
			int[] ret = new int[one.length + two.length - 1];
			
			for(int i = 0; i < one.length; i++) {
				for(int j = 0; j < two.length; j++) {
					ret[i + j] += one[i] * two[j];
				}
			}
			
			return ret;
			
		}
		
		
}
