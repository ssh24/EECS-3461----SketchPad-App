package controller.secondary.drawings;

import java.awt.geom.*;

/** Quad Curve drawing. */
public class QuadCurveDrawing extends ShapeDrawing {
    /**
     * Constructs and initializes a QuadCurveDrawing from the
     * specified coordinates.
     *
     * @param x1        the X coordinate of the start point
     * @param y1        the Y coordinate of the start point
     * @param ctrlx     the X coordinate of the control point
     * @param ctrly     the Y coordinate of the control point
     * @param x2        the X coordinate of the end point
     * @param y2        the Y coordinate of the end point
     */
    public QuadCurveDrawing(double x1, double y1, double ctrlx, double ctrly, double x2, double y2) {
        super(new QuadCurve2D.Double(x1, y1, ctrlx, ctrly, x2, y2));
    }
} // QuadCurveDrawing
