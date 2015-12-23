package controller.secondary.drawings;

import java.awt.geom.*;

/********The EllipseDrawing class to draw ellipse shapes.********/
public class EllipseDrawing extends ShapeDrawing 
{
	/********public constructor to draw an ellipse shape
	 * 
	 * x - x coordinate of the starting position of the framing rectangle
	 * y - y coordinate of the starting position of the framing rectangle
	 * w - width of the ellipse
	 * h - height of the ellipse
	 * 
	 * ********/
    public EllipseDrawing(double x, double y, double w, double h) 
    {
        super(new Ellipse2D.Double(x, y, w, h));
    }

    /********public method to draw an ellipse shape
	 * 
	 * x - x coordinate of the center of the ellipse
	 * y - y coordinate of the center of the ellipse
	 * rx - horizontal radius
	 * ry - vertical radius
	 * 
	 * ********/
    public static EllipseDrawing createEllipse(double x, double y, double rx, double ry) 
    {
        return new EllipseDrawing(x - rx, y - ry, rx * 2, ry * 2);
    }
} 
