package controller.secondary.drawings;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

/********The TextDrawing class********/
public class TextDrawing implements Drawing 
{
	 /********private variables *********/
	private String text;
	private int x_coor, y_coor;

	private ShapeDrawing bound;
	private AffineTransform transform = new AffineTransform();
	
	private Color color;
	private String fonT;
	private int sizE;
	private int stylE;

	
//*******************************************Constructors ****************************************************//
	public TextDrawing(String s, Color c, String font, int size, int style, int x, int y) 
	{
		text = s;
		x_coor = x;
		y_coor = y;
		color = c;
		fonT = font;
		sizE = size;
		stylE = style;
		bound = new RectangleDrawing(x_coor, y_coor, 50, 50);
	}
	
	public TextDrawing(TextDrawing td)
	{
		text = td.text;
		x_coor = td.x_coor;
		y_coor = td.y_coor;
		color = td.color;
		fonT = td.fonT;
		sizE = td.sizE;
		stylE = td.stylE;
		this.transform = new AffineTransform(td.getTransform());
		bound = new RectangleDrawing(x_coor, y_coor, 50, 50);
	}


	//*******************************************Methods ****************************************************//
	@Override
	public void draw(Graphics2D g) 
	{
		try 
		{
			Graphics2D g2 = (Graphics2D) g.create();
			
			g2.setColor(color);
			g2.setFont(new Font(fonT, stylE, sizE));
			g2.drawString(text, x_coor, y_coor);

			FontMetrics fm = g2.getFontMetrics();

			bound = new RectangleDrawing(x_coor, y_coor - (fm.getHeight() / 2), fm.stringWidth(text), fm.getHeight());
			g2.dispose();
		} 
		catch (Exception e) 
		{
			
		}

	}
	
	public AffineTransform getTransform() 
    {
        return transform;
    }

	public AffineTransform updateTransform(AffineTransform at) 
	{
		transform.concatenate(at);
		return at;
	}

	@Override
	public Rectangle getBounds() 
	{
		return bound.getBounds();
	}

	@Override
	public Rectangle2D getBounds2D()
	{
		return bound.getBounds2D();
	}

	@Override
	public boolean contains(double x, double y) 
	{
		return bound.contains(x, y);
	}

	@Override
	public boolean contains(Point2D p)
	{
		return bound.contains(p);
	}

	@Override
	public boolean intersects(double x, double y, double w, double h) 
	{
		return bound.intersects(x, y, w, h);
	}

	@Override
	public boolean intersects(Rectangle2D r) 
	{
		return bound.intersects(r);
	}

	@Override
	public boolean contains(double x, double y, double w, double h) 
	{
		return bound.contains(x, y, w, h);
	}

	@Override
	public boolean contains(Rectangle2D r) 
	{
		return bound.contains(r);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at) 
	{
		return bound.getPathIterator(at);
	}

	@Override
	public PathIterator getPathIterator(AffineTransform at, double flatness)
	{
		return bound.getPathIterator(at, flatness);
	}

	@Override
	public AffineTransform transform(AffineTransform at) 
	{
		return updateTransform(bound.transform(at));
	}

	@Override
	public AffineTransform transformOnCenter(AffineTransform at) 
	{
		return updateTransform(bound.transformOnCenter(at));
	}

	@Override
	public AffineTransform transform(AffineTransform at, double anchorx, double anchory) 
	{
		return updateTransform(bound.transform(at, anchorx, anchory));
	}

	@Override
	public AffineTransform translate(double tx, double ty) 
	{
		System.out.println("Got to translate");
		return updateTransform(bound.translate(tx, ty));
	}

	@Override
	public AffineTransform rotate(double theta) 
	{
		return transform;
	}

	@Override
	public AffineTransform rotate(double theta, double anchorx, double anchory) 
	{
		return updateTransform(bound.rotate(theta, anchorx, anchory));
	}

	@Override
	public AffineTransform rotate(double vecx, double vecy) 
	{
		return updateTransform(bound.rotate(vecx, vecy));
	}

	@Override
	public AffineTransform rotate(double vecx, double vecy, double anchorx, double anchory) 
	{
		return updateTransform(bound.rotate(vecx, vecy, anchorx, anchory));
	}

	@Override
	public AffineTransform quadrantRotate(int numquadrants) 
	{
		return updateTransform(bound.quadrantRotate(numquadrants));
	}

	@Override
	public AffineTransform quadrantRotate(int numquadrants, double anchorx, double anchory) 
	{
		return updateTransform(bound.quadrantRotate(numquadrants, anchorx, anchory));
	}

	@Override
	public AffineTransform scale(double sx, double sy) 
	{
		return scale(sx, sy, getBounds2D().getX(), getBounds2D().getY());
	}

	@Override
	public AffineTransform scale(double sx, double sy, double anchorx, double anchory) 
	{
		return updateTransform(bound.scale(sx, sy, anchorx, anchory));
	}

	@Override
	public AffineTransform scaleOnCenter(double sx, double sy) 
	{
		return updateTransform(bound.scaleOnCenter(sx, sy));
	}

	@Override
	public AffineTransform shear(double shx, double shy) 
	{
		return updateTransform(bound.shear(shx, shy));
	}

	@Override
	public AffineTransform shearOnCenter(double shx, double shy)
	{
		return updateTransform(bound.shearOnCenter(shx, shy));
	}

	@Override
	public AffineTransform shear(double shx, double shy, double anchorx, double anchory) 
	{
		return updateTransform(bound.shear(shx, shy, anchorx, anchory));
	}

	@Override
	public AffineTransform reflectHorizontal(double y)
	{
		return updateTransform(bound.reflectHorizontal(y));
	}

	@Override
	public AffineTransform reflectHorizontal() 
	{
		return updateTransform(bound.reflectHorizontal());
	}

	@Override
	public AffineTransform reflectVertical(double x) 
	{
		return updateTransform(bound.reflectVertical(x));
	}

	@Override
	public AffineTransform reflectVertical() 
	{
		return updateTransform(bound.reflectVertical());
	}
}
