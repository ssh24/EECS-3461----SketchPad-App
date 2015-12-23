package controller.secondary.drawings;

import java.awt.*;
import java.awt.geom.*;

/********The PathDrawing class to draw any shapes.********/
public class PathDrawing extends ShapeDrawing 
{
	
//*******************************************Constructors ****************************************************//
	
	/********public constructor to construct a new empty PathDrawing object. *********/
    public PathDrawing() 
    {
        super(new Path2D.Double());
    }

    
    /********public constructor to construct a new empty PathDrawing object.
	 * 
	 * shape - an arbitrary Shape object
	 * 
	 * ********/
    public PathDrawing(Shape shape) 
    {
        super(new Path2D.Double(shape));
    }

    
    /********public constructor to construct a new empty PathDrawing object.
   	 * 
   	 * rule - specified winding rule to determine interior of a path
   	 * 
   	 * ********/
    public PathDrawing(int rule) 
    {
        super(new Path2D.Double(rule));
    }

    
//*******************************************Methods ****************************************************//
    
    /********private method that returns the path object *********/
    private Path2D getPath()
    {
        return (Path2D)super.shape;
    }

    
    /********public method that adds a point to the path object and draws a line to the new point.
   	 * 
   	 * x - x coordinates of the new point
   	 * y - y coordinates of the new point
   	 * 
   	 * ********/
    public PathDrawing lineTo(double x, double y) 
    {
        getPath().lineTo(x, y);
        return this;
    }

   
    /********public method that adds a point to the path object and moves to the new point.
   	 * 
   	 * x - x coordinates of the new point
   	 * y - y coordinates of the new point
   	 * 
   	 * ********/
    public PathDrawing moveTo(double x, double y) 
    {
        getPath().moveTo(x, y);
        return this;
    }

   
    /********public method that adds quadratic curve to the path object.
   	 * 
   	 * x1 - the x coordinate of the quadratic control point
     * y1 - the y coordinate of the quadratic control point
     * x2 - the x coordinate of the specified coordinates
     * y2 - the y coordinate of the specified coordinates
   	 * 
   	 * ********/
    public PathDrawing quadTo(double x1, double y1, double x2, double y2) 
    {
        getPath().quadTo(x1, y1, x2, y2);
        return this;
    }

   
    /********public method that adds curve to the path object.
   	 * 
   	 * x1 - the x coordinate of the first control point
     * y1 - the y coordinate of the first control point
     * x2 - the x coordinate of the second control point
     * y2 - the y coordinate of the second control point
     * x3 - the x coordinate of the specified coordinates
     * y3 - the y coordinate of the specified coordinates
   	 * 
   	 * ********/
    public PathDrawing curveTo(double x1, double y1, double x2, double y2, double x3, double y3)
    {
        getPath().curveTo(x1, y1, x2, y2, x3, y3);
        return this;
    }

  
    /********public method that closes the path *********/
    public PathDrawing closePath() 
    {
        getPath().closePath();
        return this;
    }

    
    /********public method that appends the geometry of the specified Shape object to the path *********/
    public PathDrawing append(Shape s, boolean connect) 
    {
        getPath().append(s, connect);
        return this;
    }

    
    /********public method that appends the geometry of the specified PathIterator object to the path *********/
    public PathDrawing append(PathIterator pi, boolean connect) 
    {
        getPath().append(pi, connect);
        return this;
    }

   
    /********public method that returns the fill style winding rule for the path object *********/
    public int getWindingRule() 
    {
        return getPath().getWindingRule();
    }

    
    /********public method that sets the winding rule to the specified value for the path object *********/
    public PathDrawing setWindingRule(int rule) 
    {
        getPath().setWindingRule(rule);
        return this;
    }

    
    /********public method that returns the recently added point to the path object *********/
    public Point2D getCurrentPoint() 
    {
        return getPath().getCurrentPoint();
    }

   
    /********public method that resets the path object to empty *********/
    public PathDrawing reset()
    {
        getPath().reset();
        return this;
    }

   
    /********public method that transforms the geometry of the path object *********/
    @Override public AffineTransform transform(AffineTransform at) 
    {
        getPath().transform(at);
        return at;
    }
}
