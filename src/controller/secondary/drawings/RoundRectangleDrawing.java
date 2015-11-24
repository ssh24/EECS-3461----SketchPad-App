package controller.secondary.drawings;

import java.awt.geom.*;

/** Round Rectangle drawing. */
public class RoundRectangleDrawing extends ShapeDrawing {
    /**
     * Constructs and initializes a RoundRectangleDrawing from the
     * specified coordinates.
     *
     * @param x     the X coordinate of the newly
     *              constructed RoundRectangleDrawing
     * @param y     the Y coordinate of the newly
     *              constructed RoundRectangleDrawing
     * @param w     the width to which to set the newly
     *              constructed RoundRectangleDrawing
     * @param h     the height to which to set the newly
     *              constructed RoundRectangleDrawing
     * @param arcw  the width of the arc to use to round off the
     *              corners of the newly constructed
     *              RoundRectangleDrawing
     * @param arch  the height of the arc to use to round off the
     *              corners of the newly constructed
     *              RoundRectangleDrawing
     */
    public RoundRectangleDrawing(double x, double y, double w, double h, double arcw, double arch) {
        super(new RoundRectangle2D.Double(x, y, w, h, arcw, arch));
    }
} // RoundRectangleDrawing
