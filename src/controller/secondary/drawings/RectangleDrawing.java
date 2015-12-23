package controller.secondary.drawings;

import java.awt.geom.*;

/********The RectangleDrawing class to draw rectangle shapes.********/
public class RectangleDrawing extends ShapeDrawing 
{
    
	/********public constructor to draw a rectangle shape
	 * 
	 * x - x coordinate of the starting position
	 * y - y coordinate of the starting position
	 * w - width of the rectangle shape
	 * h - height of the rectangle shape
	 * 
	 * ********/
    public RectangleDrawing(double x, double y, double w, double h) 
    {
        super(new Rectangle2D.Double(x, y, w, h));
    }
} 
