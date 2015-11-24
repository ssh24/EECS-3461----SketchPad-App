package controller.secondary.drawings;

/** Triangle drawing. */
public class TriangleDrawing extends ShapeDrawing {
    /**
     * Constructs and initializes an TriangleDrawing
     * from the specified coordinates.
     *
     * @param x     the X coordinate of the upper-left corner of the framing rectangle
     * @param y     the Y coordinate of the upper-left corner of the framing rectangle
     * @param w     the width of the framing rectangle
     * @param h     the height of the framing rectangle
     */
    public TriangleDrawing(double x, double y, double w, double h) {
        super(new PathDrawing()
            .moveTo(x, y + h)
            .lineTo(x + w, y + h)
            .lineTo(x + w/2, y)
            .closePath()
            .getShape());
    }
} // TriangleDrawing
