package controller.secondary.drawings;

import java.awt.geom.*;

/** Rectangle drawing. */
public class RectangleDrawing extends ShapeDrawing {
    /**
     * Constructs and initializes a RectangleDrawing from the
     * specified coordinates.
     *
     * @param x     the X coordinate of the upper-left corner
     *              of the newly constructed RectangleDrawing
     * @param y     the Y coordinate of the upper-left corner
     *              of the newly constructed RectangleDrawing
     * @param w     the width of the newly constructed RectangleDrawing
     * @param h     the height of the newly constructed RectangleDrawing
     */
    public RectangleDrawing(double x, double y, double w, double h) {
        super(new Rectangle2D.Double(x, y, w, h));
    }
} // RectangleDrawing
