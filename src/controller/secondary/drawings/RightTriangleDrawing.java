package controller.secondary.drawings;

/** Right Triangle drawing. */
public class RightTriangleDrawing extends ShapeDrawing {
    /**
     * Constructs and initializes an RightTriangleDrawing
     * from the specified coordinates.
     *
     * @param x     the X coordinate of the upper-left corner of the framing rectangle
     * @param y     the Y coordinate of the upper-left corner of the framing rectangle
     * @param w     the width of the framing rectangle
     * @param h     the height of the framing rectangle
     */
    public RightTriangleDrawing(double x, double y, double w, double h) {
        super(new PathDrawing()
            .moveTo(x, y)
            .lineTo(x + w, y + h)
            .lineTo(x, y + h)
            .closePath()
            .getShape());
    }
} // RightTriangleDrawing
