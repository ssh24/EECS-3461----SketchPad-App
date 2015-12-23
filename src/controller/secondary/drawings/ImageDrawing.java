package controller.secondary.drawings;

import java.io.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.imageio.*;

/******** The imageDrawing class ********/
public class ImageDrawing implements Drawing {
	/******** private variables *********/
	private ShapeDrawing bound;
	private AffineTransform transform = new AffineTransform();
	private BufferedImage image;

	// *******************************************Constructors
	// ****************************************************//
	public ImageDrawing(File source, double x, double y) {
		try {
			image = ImageIO.read(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
		bound = new RectangleDrawing(0, 0, image.getWidth(), image.getHeight());
		this.translate(x, y);
	}

	public ImageDrawing(InputStream source, AffineTransform at, Shape bound) {
		try {
			image = ImageIO.read(source);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.bound = new PathDrawing(bound);
		this.transform = at;
	}

	public ImageDrawing(ImageDrawing img) {
		this.image = img.getImage();
		this.transform = new AffineTransform(img.getTransform());
		this.bound = new PathDrawing(img.getShapeBound());
	}

	// *******************************************Methods
	// ****************************************************//
	public BufferedImage getImage() {
		return image;
	}

	public AffineTransform getTransform() {
		return transform;
	}

	public ShapeDrawing getShapeBound() {
		return bound;
	}

	public AffineTransform updateTransform(AffineTransform at) {
		transform.concatenate(at);
		return at;
	}

	@Override
	public void draw(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.transform(transform);
		g2d.drawImage(image, null, null);
		g2d.dispose();
	}

	@Override
	public Rectangle getBounds() {
		return bound.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D() {
		return bound.getBounds2D();
	}

	@Override
	public boolean contains(double x, double y) {
		return bound.contains(x, y);
	}

	@Override
	public boolean contains(Point2D p) {
		return bound.contains(p);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) {
		return bound.intersects(x, y, w, h);
	}

	@Override
	public boolean intersects(Rectangle2D r) {
		return bound.intersects(r);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) {
		return bound.contains(x, y, w, h);
	}

	@Override
	public boolean contains(Rectangle2D r) {
		return bound.contains(r);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) {
		return bound.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness) {
		return bound.getPathIterator(at, flatness);
	}

	@Override
	public AffineTransform transform(AffineTransform at) {
		return updateTransform(bound.transform(at));
	}

	@Override
	public AffineTransform transformOnCenter(AffineTransform at) {
		return updateTransform(bound.transformOnCenter(at));
	}

	@Override
	public AffineTransform transform(AffineTransform at, double anchorx,
			double anchory) {
		return updateTransform(bound.transform(at, anchorx, anchory));
	}

	@Override
	public AffineTransform translate(double tx, double ty) {
		return updateTransform(bound.translate(tx, ty));
	}

	@Override
	public AffineTransform rotate(double theta) {
		return updateTransform(bound.rotate(theta));
	}

	@Override
	public AffineTransform rotate(double theta, double anchorx, double anchory) {
		return updateTransform(bound.rotate(theta, anchorx, anchory));
	}

	@Override
	public AffineTransform rotate(double vecx, double vecy) {
		return updateTransform(bound.rotate(vecx, vecy));
	}

	@Override
	public AffineTransform rotate(double vecx, double vecy, double anchorx,
			double anchory) {
		return updateTransform(bound.rotate(vecx, vecy, anchorx, anchory));
	}

	@Override
	public AffineTransform quadrantRotate(int numquadrants) {
		return updateTransform(bound.quadrantRotate(numquadrants));
	}

	@Override
	public AffineTransform quadrantRotate(int numquadrants, double anchorx,
			double anchory) {
		return updateTransform(bound.quadrantRotate(numquadrants, anchorx,
				anchory));
	}

	@Override
	public AffineTransform scale(double sx, double sy) {
		return updateTransform(bound.scale(sx, sy));
	}

	@Override
	public AffineTransform scale(double sx, double sy, double anchorx,
			double anchory) {
		return updateTransform(bound.scale(sx, sy, anchorx, anchory));
	}

	@Override
	public AffineTransform scaleOnCenter(double sx, double sy) {
		return updateTransform(bound.scaleOnCenter(sx, sy));
	}

	@Override
	public AffineTransform shear(double shx, double shy) {
		return updateTransform(bound.shear(shx, shy));
	}

	@Override
	public AffineTransform shearOnCenter(double shx, double shy) {
		return updateTransform(bound.shearOnCenter(shx, shy));
	}

	@Override
	public AffineTransform shear(double shx, double shy, double anchorx,
			double anchory) {
		return updateTransform(bound.shear(shx, shy, anchorx, anchory));
	}

	@Override
	public AffineTransform reflectHorizontal(double y) {
		return updateTransform(bound.reflectHorizontal(y));
	}

	@Override
	public AffineTransform reflectHorizontal() {
		return updateTransform(bound.reflectHorizontal());
	}

	@Override
	public AffineTransform reflectVertical(double x) {
		return updateTransform(bound.reflectVertical(x));
	}

	@Override
	public AffineTransform reflectVertical() {
		return updateTransform(bound.reflectVertical());
	}
}
