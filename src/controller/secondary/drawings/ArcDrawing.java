package controller.secondary.drawings;

import java.awt.geom.*;

/** Arc drawing. */
public class ArcDrawing extends ShapeDrawing {
    /**
     * Constructs a new arc, initialized to the specified location,
     * size, angular extents, and closure type.
     *
     * @param x         The X coordinate of the upper-left corner of the arc's framing rectangle.
     * @param y         The Y coordinate of the upper-left corner of the arc's framing rectangle.
     * @param w         The overall width of the full ellipse of which this arc is a partial section.
     * @param h         The overall height of the full ellipse of which this arc is a partial section.
     * @param start     The starting angle of the arc in degrees.
     * @param extent    The angular extent of the arc in degrees.
     * @param type      The closure type for the arc: OPEN, CHORD, or PIE.
     */
    public ArcDrawing(double x, double y, double w, double h, double start, double extent, int type) {
        super(new Arc2D.Double(x, y, w, h, start, extent, type));
    }
} // ArcDrawing
