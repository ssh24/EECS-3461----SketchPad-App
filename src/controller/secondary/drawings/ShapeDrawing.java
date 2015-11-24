package controller.secondary.drawings;

import java.awt.*;
import java.awt.geom.*;

import controller.primary.MainControl;


/**
 * This class provide a wrapper over a Shape object. Associates a paint fill and
 * stroke for the shape. Defines draw and fill methods that paint the shape to
 * the graphics context, a shape outline and filled respectively. Forwards the
 * Shape methods.
 *
 * Provides transforms on the shape. The transforms are: - scale - shear -
 * translate - rotate - reflect (horizontal, vertical)
 */
public class ShapeDrawing implements Drawing {

	protected Shape shape;
	protected Style style;
	
	private int counter = 1;

	public ShapeDrawing(Shape shape) {
		this.setShape(shape);
	}

	public ShapeDrawing(Shape shape, Style style) {
		this.setShape(shape);
		this.setStyle(style);
	}

	public ShapeDrawing(ShapeDrawing shape) {
		this.shape = new PathDrawing(shape.getShape());
		this.setStyle(shape.getStyle());
	}

	public ShapeDrawing(Shape shape, Color fill, Color line, Stroke stroke) {
		this(shape, new Style(fill, line, stroke));
	}

	public Shape getShape() {
		return shape;
	}

	public Style getStyle() {
		return style;
	}

	/**
	 * Set the internal shape object to that given.
	 *
	 * @param shape
	 *            the shape
	 * @return this drawing object
	 */
	public ShapeDrawing setShape(Shape shape) {
		this.shape = shape;
		return this;
	}

	/**
	 * Set the style of the drawing.
	 *
	 * @param style
	 *            the style to apply.
	 * @return this drawing object
	 */
	public ShapeDrawing setStyle(Style style) {
		this.style = new Style(style.fill, style.line, style.stroke);
		return this;
	}

	/**
	 * Set the style of the drawing.
	 *
	 * @param style
	 *            the style to apply.
	 * @return this drawing object
	 */
	public ShapeDrawing setStyle(Color fill, Color line, Stroke stroke) {
		this.style = new Style(fill, line, stroke);
		return this;
	}

	@Override
	public void draw(Graphics2D g) {
		if (style == null) {
			return;
		}
		Graphics2D g2d = (Graphics2D) g.create();
		if (style.getFillColor() != null) {
			g2d.setColor(style.getFillColor());
			g2d.fill(shape);
		}
		if (style.getLineColor() != null) {
			g2d.setColor(style.getLineColor());
		}
		if (style.getStroke() != null) {
			g2d.setStroke(style.getStroke());
			g2d.draw(shape);
		}
		g2d.dispose();
	} // draw

	// Transformations

	@Override
	public AffineTransform transform(AffineTransform at) {
		setShape(at.createTransformedShape(shape));
		return at;
	}

	@Override
	public AffineTransform transform(AffineTransform at, double anchorx,
			double anchory) {
		AffineTransform _at = new AffineTransform();
		_at.translate(anchorx, anchory);
		_at.concatenate(at);
		_at.translate(-anchorx, -anchory);
		return transform(_at);		
	}

	@Override
	public AffineTransform transformOnCenter(AffineTransform at) {
		return transform(at, getBounds().getCenterX(), getBounds().getCenterY());
	}

	@Override
	public AffineTransform translate(double tx, double ty) {
		return transform(AffineTransform.getTranslateInstance(tx, ty));
	}

	@Override
	public AffineTransform rotate(double theta) {
		
		if(counter == 2)
		{
			if(MainControl.drawings.contains(MainControl.selected))
			{
				counter++;
				return transformOnCenter(AffineTransform.getRotateInstance(theta));
			}
			else
			{
				MainControl.drawingArea.setSize(MainControl.size.width, MainControl.size.height);
				counter++;
				return transformOnCenter(AffineTransform.getRotateInstance(theta));
			}
		}
		else if(counter == 4)
		{
			if(MainControl.drawings.contains(MainControl.selected))
			{
				counter = 1;
				return transformOnCenter(AffineTransform.getRotateInstance(theta));
			}
			else
			{
				MainControl.drawingArea.setSize(MainControl.size.width, MainControl.size.height);
				counter = 1;
				return transformOnCenter(AffineTransform.getRotateInstance(theta));
			}
		}
		else
		{
			if(MainControl.drawings.contains(MainControl.selected))
			{
				counter++;
				return transformOnCenter(AffineTransform.getRotateInstance(theta));
			}
			else
			{
				MainControl.drawingArea.setSize(MainControl.size.height, MainControl.size.width);
				counter++;
				return transformOnCenter(AffineTransform.getRotateInstance(theta));
			}
		}
	}

	@Override
	public AffineTransform rotate(double theta, double anchorx, double anchory) {
		return transform(AffineTransform.getRotateInstance(theta, anchorx,
				anchory));
	}

	@Override
	public AffineTransform rotate(double vecx, double vecy) {
		return transformOnCenter(AffineTransform.getRotateInstance(vecx, vecy));
	}

	@Override
	public AffineTransform rotate(double vecx, double vecy, double anchorx,
			double anchory) {
		return transform(AffineTransform.getRotateInstance(vecx, vecy, anchorx,
				anchory));
	}

	@Override
	public AffineTransform quadrantRotate(int numquadrants) {
		return transformOnCenter(AffineTransform
				.getQuadrantRotateInstance(numquadrants));
	}

	@Override
	public AffineTransform quadrantRotate(int numquadrants, double anchorx,
			double anchory) {
		return transform(AffineTransform.getQuadrantRotateInstance(
				numquadrants, anchorx, anchory));
	}

	@Override
	public AffineTransform scale(double sx, double sy) {
		return scale(sx, sy, getBounds2D().getX(), getBounds2D().getY());
	}

	@Override
	public AffineTransform scaleOnCenter(double sx, double sy) {
		return transformOnCenter(AffineTransform.getScaleInstance(sx, sy));
	}

	@Override
	public AffineTransform scale(double sx, double sy, double anchorx,
			double anchory) {
		return transform(AffineTransform.getScaleInstance(sx, sy), anchorx,
				anchory);
	}

	@Override
	public AffineTransform shear(double shx, double shy) {
		return shear(shx, shy, getBounds2D().getX(), getBounds2D().getY());
	}

	@Override
	public AffineTransform shearOnCenter(double shx, double shy) {
		return transformOnCenter(AffineTransform.getShearInstance(shx, shy));
	}

	@Override
	public AffineTransform shear(double shx, double shy, double anchorx,
			double anchory) {
		return transform(AffineTransform.getShearInstance(shx, shy), anchorx,
				anchory);
	}

	@Override
	public AffineTransform reflectHorizontal(double x) {
		return transform(AffineTransform.getScaleInstance(-1, 1), x, 0.0);
	}

	@Override
	public AffineTransform reflectHorizontal() {
		return transformOnCenter(AffineTransform.getScaleInstance(-1, 1));
	}

	@Override
	public AffineTransform reflectVertical(double y) {
		return transform(AffineTransform.getScaleInstance(1, -1), 0.0, y);
	}

	@Override
	public AffineTransform reflectVertical() {
		return transformOnCenter(AffineTransform.getScaleInstance(1, -1));
	}

	// Shape methods -- Wrappers

	@Override
	public Rectangle getBounds() {
		return shape.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return shape.getBounds2D();
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return shape.contains(x, y, w, h);
	}

	@Override
	public boolean contains(double x, double y) {
		return shape.contains(x, y);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return shape.contains(r);
	}

	@Override
	public boolean contains(Point2D p) {
		return shape.contains(p);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return shape.intersects(x, y, w, h);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return shape.intersects(r);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return shape.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return shape.getPathIterator(at, flatness);
	}

	/**
	 * This class stores the styling properties of the shapes.
	 */
	public static class Style {
		private Color fill;
		private Color line;
		private Stroke stroke;

		public Style() {
		}

		public Style(Color fill, Color line, Stroke stroke) {
			this.fill = fill;
			this.line = line;
			this.stroke = stroke;
		}

		// Getters
		public Color getFillColor() {
			return fill;
		}

		public Color getLineColor() {
			return line;
		}

		public Stroke getStroke() {
			return stroke;
		}

		// Setters
		public void setFillColor(Color fill) {
			this.fill = fill;
		}

		public void setLineColor(Color line) {
			this.line = line;
		}

		public void setStroke(Stroke stroke) {
			this.stroke = stroke;
		}
	} // Style

} // ShapeDrawing
