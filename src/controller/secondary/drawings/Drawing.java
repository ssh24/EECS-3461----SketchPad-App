package controller.secondary.drawings;

import java.awt.*;
import java.awt.geom.*;

/********The Drawing interface class includes methods to be implemented for drawing shapes.********/
public interface Drawing extends Shape 
{
	/********public method to draw the drawing objects *******/
    public void draw(Graphics2D g);

    
    /********public method that transforms the drawing object with the given affine transformation *******/
    public AffineTransform transform(AffineTransform at);

    
    /********public method that transforms the drawing object with the given affine transformation above the center *******/
    public AffineTransform transformOnCenter(AffineTransform at);

   
    /********public method that transforms the drawing object with the given affine transformation above the anchor point *******/
    public AffineTransform transform(AffineTransform at, double anchorx, double anchory);

    
    /********public method that translates the drawing object *******/
    public AffineTransform translate(double tx, double ty);


    /********public method that rotates the drawing object *******/
    public AffineTransform rotate(double theta);

 
    /********public method that rotates the drawing object around an anchor point *******/
    public AffineTransform rotate(double theta, double anchorx, double anchory);

    
    /********public method that rotates the drawing object according to a rotation vector *******/
    public AffineTransform rotate(double vecx, double vecy);

  
    /********public method that rotates the drawing object around an aanchor point according to a rotation vector *******/
    public AffineTransform rotate(double vecx, double vecy, double anchorx, double anchory);


    /********public method that rotates the drawing object by the specified number of quadrants *******/
    public AffineTransform quadrantRotate(int numquadrants);

    
    /********public method that rotates the drawing object by the specified number of quadrants around the anchor point *******/
    public AffineTransform quadrantRotate(int numquadrants, double anchorx, double anchory);

 
    /********public method transforms the drawing object with a scaling transformation with origin at the top-left corner *******/
    public AffineTransform scale(double sx, double sy);

  
    /********public method transforms the drawing object with a scaling transformation *******/
    public AffineTransform scale(double sx, double sy, double anchorx, double anchory);


    /********public method transforms the drawing object with a scaling transformation with origin at the center *******/
    public AffineTransform scaleOnCenter(double sx, double sy);


    /********public method transforms the drawing object with a shearing transformation with origin at the top-left corner *******/
    public AffineTransform shear(double shx, double shy);

    
    /********public method transforms the drawing object with a shearing transformation with origin at the center *******/
    public AffineTransform shearOnCenter(double shx, double shy);


    /********public method transforms the drawing object with a shearing transformation *******/
    public AffineTransform shear(double shx, double shy, double anchorx, double anchory);

   
    /********public method transforms the drawing object with a horizontal reflection with the given y axis *******/
    public AffineTransform reflectHorizontal(double y);

   
    /********public method transforms the drawing object with a horizontal reflection with the given y axis at the center *******/
    public AffineTransform reflectHorizontal();

    
    /********public method transforms the drawing object with a vertical reflection with the given y axis *******/
    public AffineTransform reflectVertical(double x);

    
    /********public method transforms the drawing object with a vertical reflection with the given y axis at the center *******/
    public AffineTransform reflectVertical();
} 
