package controller.secondary.drawings;

import java.awt.*;
import java.awt.geom.*;

/**
* This interface defines the commonly shared method
* headers of drawings.
*/
public interface Drawing extends Shape {

    /**
     * Invoked by application to paint drawing to the display.
     *
     * @param g     the Graphics context in which to paint
     */
    public void draw(Graphics2D g);

    /**
     * Generates the XML Node representing the drawing.
     * @param doc TODO
     *
     * @return the XML Node representing the drawing.
     */
    //public Node getXMLNode(Document doc);

    /**
     * Transforms the drawing with the given affine
     * transformation.
     *
     * @param at   the transformation
     * @return     the transformation applied
     */
    public AffineTransform transform(AffineTransform at);

    /**
     * Transforms the drawing with the given affine
     * transformation above the center of the drawing object.
     *
     * @param at   the transformation
     * @return     the transformation applied
     */
    public AffineTransform transformOnCenter(AffineTransform at);

    /**
     * Transforms the drawing with the given affine
     * transformation above the given anchor point.
     *
     * @param at        the transformation
     * @param anchorx   the X coordinate of the anchor point
     * @param anchory   the Y coordinate of the anchor point
     * @return          the transformation applied
     */
    public AffineTransform transform(AffineTransform at,
                                double anchorx, double anchory);

    /**
     * Transforms the drawing with a translation transformation.
     * This is equivalent to calling transform(T), where T is an
     * <code>AffineTransform</code> represented by the following matrix:
     * <pre>
     *          [   1    0    tx  ]
     *          [   0    1    ty  ]
     *          [   0    0    1   ]
     * </pre>
     *
     * @param tx    the distance by which coordinates are translated in the
     *              X axis direction
     * @param ty    the distance by which coordinates are translated in the
     *              Y axis direction
     * @return      the transformation applied
     */
    public AffineTransform translate(double tx, double ty);

    /**
     * Transforms the drawing with a rotation transformation above the
     * center of the drawing object.
     * Rotating by a positive angle theta rotates points on the positive
     * X axis toward the positive Y axis.
     * Note also the discussion of
     * <a href="#quadrantapproximation">Handling 90-Degree Rotations</a>
     * above.
     *
     * @param theta      the angle of rotation measured in radians
     * @return           the transformation applied
     */
    public AffineTransform rotate(double theta);

    /**
     * Transforms the drawing with a transform that rotates
     * coordinates around an anchor point.
     * This operation is equivalent to translating the coordinates so
     * that the anchor point is at the origin (S1), then rotating them
     * about the new origin (S2), and finally translating so that the
     * intermediate origin is restored to the coordinates of the original
     * anchor point (S3).
     * <p>
     * This operation is equivalent to the following sequence of calls:
     * <pre>
     *     translate(anchorx, anchory);      // S3: final translation
     *     rotate(theta);                    // S2: rotate around anchor
     *     translate(-anchorx, -anchory);    // S1: translate anchor to origin
     * </pre>
     * Rotating by a positive angle theta rotates points on the positive
     * X axis toward the positive Y axis.
     * Note also the discussion of
     * <a href="#quadrantapproximation">Handling 90-Degree Rotations</a>
     * above.
     *
     * @param theta      the angle of rotation measured in radians
     * @param anchorx    the X coordinate of the rotation anchor point
     * @param anchory    the Y coordinate of the rotation anchor point
     * @return           the transformation applied
     */
    public AffineTransform rotate(double theta, double anchorx, double anchory);

    /**
     * Transforms the drawing with a transform that rotates
     * coordinates according to a rotation vector.
     * All coordinates rotate about the origin by the same amount.
     * The amount of rotation is such that coordinates along the former
     * positive X axis will subsequently align with the vector pointing
     * from the origin to the specified vector coordinates.
     * If both <code>vecx</code> and <code>vecy</code> are 0.0,
     * no additional rotation is added to this transform.
     * This operation is equivalent to calling:
     * <pre>
     *          rotate(Math.atan2(vecy, vecx));
     * </pre>
     *
     * @param vecx       the X coordinate of the rotation vector
     * @param vecy       the Y coordinate of the rotation vector
     * @return           the transformation applied
     */
    public AffineTransform rotate(double vecx, double vecy);

    /**
     * Transforms the drawing with a transform that rotates
     * coordinates around an anchor point according to a rotation
     * vector.
     * All coordinates rotate about the specified anchor coordinates
     * by the same amount.
     * The amount of rotation is such that coordinates along the former
     * positive X axis will subsequently align with the vector pointing
     * from the origin to the specified vector coordinates.
     * If both <code>vecx</code> and <code>vecy</code> are 0.0,
     * the transform is not modified in any way.
     * This method is equivalent to calling:
     * <pre>
     *     rotate(Math.atan2(vecy, vecx), anchorx, anchory);
     * </pre>
     *
     * @param vecx       the X coordinate of the rotation vector
     * @param vecy       the Y coordinate of the rotation vector
     * @param anchorx    the X coordinate of the rotation anchor point
     * @param anchory    the Y coordinate of the rotation anchor point
     * @return           the transformation applied
     */
    public AffineTransform rotate(double vecx, double vecy,
                                double anchorx, double anchory);

    /**
     * Transforms the drawing with a transform that rotates
     * coordinates by the specified number of quadrants.
     * This is equivalent to calling:
     * <pre>
     *     rotate(numquadrants * Math.PI / 2.0);
     * </pre>
     * Rotating by a positive number of quadrants rotates points on
     * the positive X axis toward the positive Y axis.
     *
     * @param numquadrants    the number of 90 degree arcs to rotate by
     * @return                the transformation applied
     */
    public AffineTransform quadrantRotate(int numquadrants);

    /**
     * Transforms the drawing with a transform that rotates
     * coordinates by the specified number of quadrants around
     * the specified anchor point.
     * This method is equivalent to calling:
     * <pre>
     *     rotate(numquadrants * Math.PI / 2.0, anchorx, anchory);
     * </pre>
     * Rotating by a positive number of quadrants rotates points on
     * the positive X axis toward the positive Y axis.
     *
     * @param numquadrants  the number of 90 degree arcs to rotate by
     * @param anchorx       the X coordinate of the rotation anchor point
     * @param anchory       the Y coordinate of the rotation anchor point
     * @return              the transformation applied
     */
    public AffineTransform quadrantRotate(int numquadrants,
                                double anchorx, double anchory);

    /**
     * Transforms the drawing with a scaling transformation with origin
     * at the top-left corner of the drawing.
     *
     * @param sx    the factor by which coordinates are scaled along the
     *              X axis direction
     * @param sy    the factor by which coordinates are scaled along the
     *              Y axis direction
     * @return      the transformation applied
     */
    public AffineTransform scale(double sx, double sy);

    /**
     * Transforms the drawing with a scaling transformation.
     * This operation is equivalent to the following sequence of calls:
     * <pre>
     *     translate(anchorx, anchory);      // S3: final translation
     *     scale(sx, sy);                    // S2: scale around anchor
     *     translate(-anchorx, -anchory);    // S1: translate anchor to origin
     * </pre>
     *
     * @param sx        the factor by which coordinates are scaled along the
     *                  X axis direction
     * @param sy        the factor by which coordinates are scaled along the
     *                  Y axis direction
     * @param anchorx   the X coordinate of the scaling anchor point
     * @param anchory   the Y coordinate of the scaling anchor point
     * @return          the transformation applied
     */
    public AffineTransform scale(double sx, double sy,
                                double anchorx, double anchory);

    /**
     * Transforms the drawing with a scaling transformation with origin
     * at the center of the drawing.
     *
     * @param sx    the factor by which coordinates are scaled along the
     *              X axis direction
     * @param sy    the factor by which coordinates are scaled along the
     *              Y axis direction
     * @return      the transformation applied
     */
    public AffineTransform scaleOnCenter(double sx, double sy);

    /**
     * Transforms the drawing with a shearing transformation with origin
     * at the top-left corner of the drawing.
     *
     * @param shx   the multiplier by which coordinates are shifted in the
     *              direction of the positive X axis as a factor of their Y coordinate
     * @param shy   the multiplier by which coordinates are shifted in the
     *              direction of the positive Y axis as a factor of their X coordinate
     * @return      the transformation applied
     */
    public AffineTransform shear(double shx, double shy);

    /**
     * Transforms the drawing with a shearing transformation with origin
     * at the center of the drawing.
     *
     * @param shx   the multiplier by which coordinates are shifted in the
     *              direction of the positive X axis as a factor of their Y coordinate
     * @param shy   the multiplier by which coordinates are shifted in the
     *              direction of the positive Y axis as a factor of their X coordinate
     * @return      the transformation applied
     */
    public AffineTransform shearOnCenter(double shx, double shy);

    /**
     * Transforms the drawing with a shearing transformation.
     * This operation is equivalent to the following sequence of calls:
     * <pre>
     *     translate(anchorx, anchory);      // S3: final translation
     *     shear(shx, shy);                  // S2: shear around anchor
     *     translate(-anchorx, -anchory);    // S1: translate anchor to origin
     * </pre>
     *
     * @param shx       the multiplier by which coordinates are shifted in the
     *                  direction of the positive X axis as a factor of their Y coordinate
     * @param shy       the multiplier by which coordinates are shifted in the
     *                  direction of the positive Y axis as a factor of their X coordinate
     * @param anchorx   the X coordinate of the shearing anchor point
     * @param anchory   the Y coordinate of the shearing anchor point
     * @return          the transformation applied
     */
    public AffineTransform shear(double shx, double shy,
                                double anchorx, double anchory);

    /**
     * Transforms the drawing with a horizontal reflection
     * with the given Y-axis.
     *
     * @param x     the x value of the Y axis
     * @return      the transformation applied
     */
    public AffineTransform reflectHorizontal(double y);

    /**
     * Transforms the drawing with a vertical reflection
     * with the Y-axis at the center of the drawing.
     *
     * @return      the transformation applied
     */
    public AffineTransform reflectHorizontal();

    /**
     * Transforms the drawing with a vertical reflection
     * with the given X-axis.
     *
     * @param y     the y value of X axis
     * @return      the transformation applied
     */
    public AffineTransform reflectVertical(double x);

    /**
     * Transforms the drawing with a vertical reflection
     * with the X-axis at the center of the drawing.
     *
     * @return      the transformation applied
     */
    public AffineTransform reflectVertical();

} // Drawing
