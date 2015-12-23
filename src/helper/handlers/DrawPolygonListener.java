package helper.handlers;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.primary.MainControl;
import controller.secondary.drawings.*;

/********The DrawPolygonListener class********/
public class DrawPolygonListener extends MouseAdapter 
{
	 /********private variables *********/
	private PathDrawing path;
	private double startx;
	private double starty;
	private Component comp;
	private ShapeDrawing.Style shapeStyle;
	

	 /********public constructor *********/
	public DrawPolygonListener(Component comp) 
	{
		this.comp = comp;
	}

	
	 // implements MouseAdapter methods
	@Override
	public void mousePressed(MouseEvent e) 
	{
		if (path == null) 
		{
			MainControl.preview = null;
			startx = e.getX() / MainControl.zoom;
			starty = e.getY() / MainControl.zoom;
			path = new PathDrawing();
			path.moveTo(startx, starty);
			shapeStyle = new ShapeDrawing.Style();
			shapeStyle.setLineColor(MainControl.line);
			shapeStyle.setStroke(new BasicStroke(MainControl.stroke));
			path.setStyle(shapeStyle);
		} 
		else 
		{
			path.lineTo(e.getX() / MainControl.zoom, e.getY() / MainControl.zoom);
		}
		MainControl.preview = path;
		comp.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		if (MainControl.preview == null) 
		{
			return;
		}
		PathDrawing p2 = new PathDrawing(path);
		p2.lineTo(e.getX() / MainControl.zoom, e.getY() / MainControl.zoom);
		p2.setStyle(shapeStyle);
		MainControl.preview = p2;
		comp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if (MainControl.preview == null) 
		{
			return;
		}
		path = (PathDrawing) MainControl.preview;
		if (Math.abs(startx - e.getX() * MainControl.zoom) < 5 && Math.abs(starty - e.getY() * MainControl.zoom) < 5) 
		{
			shapeStyle.setFillColor(MainControl.fill);
			path.closePath();
			path.setStyle(shapeStyle);
			MainControl.drawings.add(path);
			MainControl.preview = null;
			path = null;
		}
		comp.repaint();
	}
} 