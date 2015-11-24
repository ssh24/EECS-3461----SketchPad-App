package controller.secondary.drawings;

import java.awt.geom.*;

/** Cubic Curve drawing. */
public class CubicCurveDrawing extends ShapeDrawing {
    /**
     * Constructs and initializes a CubicCurveDrawing
     * from the specified double coordinates.
     *
     * @param x1        the X coordinate for the start point of the resulting CubicCurveDrawing
     * @param y1        the Y coordinate for the start point of the resulting CubicCurveDrawing
     * @param ctrlx1    the X coordinate for the first control point of the resulting CubicCurveDrawing
     * @param ctrly1    the Y coordinate for the first control point of the resulting CubicCurveDrawing
     * @param ctrlx2    the X coordinate for the second control point of the resulting CubicCurveDrawing
     * @param ctrly2    the Y coordinate for the second control point of the resulting CubicCurveDrawing
     * @param x2        the X coordinate for the end point of the resulting CubicCurveDrawing
     * @param y2        the Y coordinate for the end point of the resulting CubicCurveDrawing
     */
    public CubicCurveDrawing(double x1, double y1, double ctrlx1, double ctrly1, double ctrlx2, double ctrly2, double x2, double y2) {
        super(new CubicCurve2D.Double(x1, y1, ctrlx1, ctrly1, ctrlx2, ctrly2, x2, y2));
    }
} // CubicCurveDrawing
