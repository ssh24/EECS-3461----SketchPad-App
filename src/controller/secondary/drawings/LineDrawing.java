package controller.secondary.drawings;

import java.awt.geom.*;

/********The LineDrawing class to draw lines.********/
public class LineDrawing extends ShapeDrawing 
{
	/********public constructor which takes x1, y1 as starting points and x2, y2 as ending points to draw a line********/
    public LineDrawing(double x1, double y1, double x2, double y2) 
    {
        super(new Line2D.Double(x1, y1, x2, y2));
    }

    /********public method which takes x, y as starting points and (x + w) as w, (y + h) as h for ending points to draw a line********/
    public static LineDrawing createLine(double x, double y, double w, double h) 
    {
        return new LineDrawing(x, y, x + w, y + h);
    }
} 
