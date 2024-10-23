package polynomial_interpolate;


//import java.awt.*;
//import javax.swing.*;
//
//import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PolynomialGraph extends JPanel {

    private double[] coefficients;
    private double[][] points;

    public PolynomialGraph(double[] coefficients, double[][] points) {
        this.coefficients = coefficients;
        this.points = points;
    }
    
  
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        // Draw axes
        g.drawLine(0, height / 2, width, height / 2); // X-axis
        g.drawLine(width / 2, 0, width / 2, height); // Y-axis
        
        
        double scale = 10;
        
        //g.drawLine(height, height, width, height)
        System.out.println("X-axis goes from: -" + (width/2.0)/scale + " to "  + (width/2.0)/scale);
        System.out.println("Y-axis goes from: -" + (height/2.0)/scale + " to " + (height/2.0)/scale);
        
        
        
        
        // Plot the polynomial
        g.setColor(Color.BLUE);
        for (int x = 0; x < width; x++) {  	
            double scaledX = (x - width / 2.0) / scale; // Scale x to fit the window
            double y = evaluatePolynomial(scaledX);
            int scaledY = (int) (height / 2.0 - y * scale); // Scale y to fit the window
            g.drawLine(x, scaledY, x, scaledY);
            
        }
    }

    
    
    private double evaluatePolynomial(double x) {
        double result = 0;
        for (int i = 0; i < coefficients.length; i++) {
            result += coefficients[i] * Math.pow(x, i);
        }
        return result;
    }

    
    
    public void graph() {
    	  JFrame frame = new JFrame("Polynomial Graph");
    	  frame.setSize(1000, 10000);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.add(new PolynomialGraph(coefficients, points));
	      frame.setVisible(true);
    }
   
    
}







//public static void main(String[] args) {
//  double[] coefficients = {0, 0, 1}; // Represents the polynomial x^2
//  JFrame frame = new JFrame("Polynomial Graph");
//  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//  frame.add(new PolynomialGraph(coefficients));
//  frame.setSize(800, 600);
//  frame.setVisible(true);
//}
