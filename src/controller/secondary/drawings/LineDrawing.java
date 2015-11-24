package controller.secondary.drawings;

import java.awt.geom.*;

/** Line drawing. */
public class LineDrawing extends ShapeDrawing {
    /**
     * Constructs and initializes a LineDrawing
     * from the specified coordinates.
     *
     * @param x1    the X coordinate of the start point
     * @param y1    the Y coordinate of the start point
     * @param x2    the X coordinate of the end point
     * @param y2    the Y coordinate of the end point
     */
    public LineDrawing(double x1, double y1, double x2, double y2) {
        super(new Line2D.Double(x1, y1, x2, y2));
    }

    /**
     * Constructs and initializes a LineDrawing
     * from the specified coordinates.
     *
     * @param x     the X coordinate of the start point
     * @param y     the Y coordinate of the start point
     * @param w     the rise of the newly constructed LineDrawing
     * @param h     the run of the newly constructed LineDrawing
     */
    public static LineDrawing createLine(double x, double y, double w, double h) {
        return new LineDrawing(x, y, x + w, y + h);
    }
} // LineDrawing
