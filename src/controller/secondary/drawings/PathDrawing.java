package controller.secondary.drawings;

import java.awt.*;
import java.awt.geom.*;

/** Path drawing. */
public class PathDrawing extends ShapeDrawing {

    /**
     * Constructs a new empty PathDrawing object
     * with a default winding rule of WIND_NON_ZERO.
     */
    public PathDrawing() {
        super(new Path2D.Double());
    }

    /**
     * Constructs a new PathDrawing object from
     * an arbitrary Shape object. All of the initial geometry and
     * the winding rule for this path are taken from the specified
     * Shape object.
     *
     * @param shape     the specified Shape object
     */
    public PathDrawing(Shape shape) {
        super(new Path2D.Double(shape));
    }

    /**
     * Constructs a new empty double precision Path2D object with
     * the specified winding rule to control operations that require the
     * interior of the path to be defined.
     *
     * @param rule      the winding rule
     */
    public PathDrawing(int rule) {
        super(new Path2D.Double(rule));
    }

    /**
     * Returns the path object.
     *
     * @return the path object.
     */
    private Path2D getPath() {
        return (Path2D)super.shape;
    }

    /**
     * Adds a point to the path by drawing a straight line from the
     * current coordinates to the new specified coordinates specified in
     * double precision.
     *
     * @param x     the specified X coordinate
     * @param y     the specified Y coordinate
     * @return      this drawing object
     */
    public PathDrawing lineTo(double x, double y) {
        getPath().lineTo(x, y);
        return this;
    }

    /**
     * Adds a point to the path by moving to the
     * specified coordinates specified in double precision.
     *
     * @param x     the specified X coordinate
     * @param y     the specified Y coordinate
     * @return      this drawing object
     */
    public PathDrawing moveTo(double x, double y) {
        getPath().moveTo(x, y);
        return this;
    }

    /**
     * Adds a curved segment, defined by two new points, to the path by
     * drawing a Quadratic curve that intersects both the current coordinates
     * and the specified coordinates (x2,y2), using the specified point
     * (x1,y1) as a quadratic parametric control point.
     *
     * @param x1    the X coordinate of the quadratic control point
     * @param y1    the Y coordinate of the quadratic control point
     * @param x2    the X coordinate of the final end point
     * @param y2    the Y coordinate of the final end point
     * @return      this drawing object
     */
    public PathDrawing quadTo(double x1, double y1, double x2, double y2) {
        getPath().quadTo(x1, y1, x2, y2);
        return this;
    }

    /**
     * Adds a curved segment, defined by three new points, to the path by
     * drawing a B&eacute;zier curve that intersects both the current
     * coordinates and the specified coordinates {@code (x3,y3)},
     * using the specified points {@code (x1,y1)} and {@code (x2,y2)} as
     * B&eacute;zier control points.
     * All coordinates are specified in double precision.
     *
     * @param x1    the X coordinate of the first B&eacute;zier control point
     * @param y1    the Y coordinate of the first B&eacute;zier control point
     * @param x2    the X coordinate of the second B&eacute;zier control point
     * @param y2    the Y coordinate of the second B&eacute;zier control point
     * @param x3    the X coordinate of the final end point
     * @param y3    the Y coordinate of the final end point
     * @return      this drawing object
     */
    public PathDrawing curveTo(double x1, double y1, double x2, double y2, double x3, double y3) {
        getPath().curveTo(x1, y1, x2, y2, x3, y3);
        return this;
    }

    /**
     * Closes the current subpath by drawing a straight line back to
     * the coordinates of the last {@code moveTo}.  If the path is already
     * closed then this method has no effect.
     * 
     * @return      this drawing object
     */
    public PathDrawing closePath() {
        getPath().closePath();
        return this;
    }

    /**
     * Appends the geometry of the specified {@code Shape} object to the
     * path, possibly connecting the new geometry to the existing path
     * segments with a line segment.
     * If the {@code connect} parameter is {@code true} and the
     * path is not empty then any initial {@code moveTo} in the
     * geometry of the appended {@code Shape}
     * is turned into a {@code lineTo} segment.
     * If the destination coordinates of such a connecting {@code lineTo}
     * segment match the ending coordinates of a currently open
     * subpath then the segment is omitted as superfluous.
     * The winding rule of the specified {@code Shape} is ignored
     * and the appended geometry is governed by the winding
     * rule specified for this path.
     *
     * @param s         the {@code Shape} whose geometry is appended
     *                  to this path
     * @param connect   a boolean to control whether or not to turn an initial
     *                  {@code moveTo} segment into a {@code lineTo} segment
     *                  to connect the new geometry to the existing path
     * @return          this drawing object
     */
    public PathDrawing append(Shape s, boolean connect) {
        getPath().append(s, connect);
        return this;
    }

    /**
     * Appends the geometry of the specified
     * {@link PathIterator} object
     * to the path, possibly connecting the new geometry to the existing
     * path segments with a line segment.
     * If the {@code connect} parameter is {@code true} and the
     * path is not empty then any initial {@code moveTo} in the
     * geometry of the appended {@code Shape} is turned into a
     * {@code lineTo} segment.
     * If the destination coordinates of such a connecting {@code lineTo}
     * segment match the ending coordinates of a currently open
     * subpath then the segment is omitted as superfluous.
     * The winding rule of the specified {@code Shape} is ignored
     * and the appended geometry is governed by the winding
     * rule specified for this path.
     *
     * @param pi        the {@code PathIterator} whose geometry is appended to
     *                  this path
     * @param connect   a boolean to control whether or not to turn an initial
     *                  {@code moveTo} segment into a {@code lineTo} segment
     *                  to connect the new geometry to the existing path
     * @return          this drawing object
     */
    public PathDrawing append(PathIterator pi, boolean connect) {
        getPath().append(pi, connect);
        return this;
    }

    /**
     * Returns the fill style winding rule.
     *
     * @return an integer representing the current winding rule.
     * @see #WIND_EVEN_ODD
     * @see #WIND_NON_ZERO
     * @see #setWindingRule
     */
    public int getWindingRule() {
        return getPath().getWindingRule();
    }

    /**
     * Sets the winding rule for this path to the specified value.
     *
     * @param rule      an integer representing the specified
     *                  winding rule
     * @return          this drawing object
     * @exception       IllegalArgumentException if
     *                  {@code rule} is not either
     *                  {@link #WIND_EVEN_ODD} or
     *                  {@link #WIND_NON_ZERO}
     * @see #getWindingRule
     */
    public PathDrawing setWindingRule(int rule) {
        getPath().setWindingRule(rule);
        return this;
    }

    /**
     * Returns the coordinates most recently added to the end of the path
     * as a {@link Point2D} object.
     *
     * @return a {@code Point2D} object containing the ending coordinates of
     *         the path or {@code null} if there are no points in the path.
     */
    public Point2D getCurrentPoint() {
        return getPath().getCurrentPoint();
    }

    /**
     * Resets the path to empty.  The append position is set back to the
     * beginning of the path and all coordinates and point types are
     * forgotten.
     * 
     * @return          this drawing object
     */
    public PathDrawing reset() {
        getPath().reset();
        return this;
    }

    /**
     * Transforms the geometry of this path using the specified
     * {@link AffineTransform}.
     * The geometry is transformed in place, which permanently changes the
     * boundary defined by this object.
     *
     * @param at the {@code AffineTransform} used to transform the area
     */
    @Override public AffineTransform transform(AffineTransform at) {
        getPath().transform(at);
        return at;
    }

} // PathDrawing
