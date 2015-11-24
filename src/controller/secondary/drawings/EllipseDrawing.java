package controller.secondary.drawings;

import java.awt.geom.*;

/** Ellipse drawing. */
public class EllipseDrawing extends ShapeDrawing {
    /**
     * Constructs and initializes an EllipseDrawing
     * from the specified coordinates.
     *
     * @param x     the X coordinate of the upper-left corner of the framing rectangle
     * @param y     the Y coordinate of the upper-left corner of the framing rectangle
     * @param w     the width of the framing rectangle
     * @param h     the height of the framing rectangle
     */
    public EllipseDrawing(double x, double y, double w, double h) {
        super(new Ellipse2D.Double(x, y, w, h));
    }

    /**
     * Constructs and initializes an EllipseDrawing
     * from the specified coordinates.
     *
     * @param x     the X coordinate of the center of the ellipse
     * @param y     the Y coordinate of the center of the ellipse
     * @param rx    the horizontal radius
     * @param ry    the vertical radius
     */
    public static EllipseDrawing createEllipse(double x, double y, double rx, double ry) {
        return new EllipseDrawing(x - rx, y - ry, rx * 2, ry * 2);
    }
} // EllipseDrawing
