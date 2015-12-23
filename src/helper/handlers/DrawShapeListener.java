package helper.handlers;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.primary.MainControl;
import controller.secondary.drawings.*;

/********The DrawShapeListener class********/
public class DrawShapeListener extends MouseAdapter
{
	 /********private variables *********/
    private PathDrawing path;
    private double startx;
    private double starty;
    private Component comp;

    
    /********public constructor *********/
    public DrawShapeListener(Component comp) 
    {
        this.comp = comp;
    }

    // implements MouseAdapter methods
    @Override
    public void mousePressed(MouseEvent e)
    {

        MainControl.preview = null;
        startx = e.getX()/MainControl.zoom;
        starty = e.getY()/MainControl.zoom;
        
        switch (MainControl.mode) 
        {
            case FREEHAND:
                path = new PathDrawing();
                path.moveTo(startx, starty);
                MainControl.preview = path;
                break;
            case LINE:      MainControl.preview =    LineDrawing.createLine(startx, starty, 0, 0);       break;
            case OVAL:      MainControl.preview =        new EllipseDrawing(startx, starty, 0, 0);       break;
            case RECTANGLE: MainControl.preview =      new RectangleDrawing(startx, starty, 0, 0);       break;
            default:
                break;
        } 
        if (MainControl.preview instanceof ShapeDrawing)
        {
            ShapeDrawing.Style shapeStyle = new ShapeDrawing.Style();
            if (MainControl.mode != MainControl.Mode.FREEHAND)
            {
                shapeStyle.setFillColor(MainControl.fill);
            }
            shapeStyle.setLineColor(MainControl.line);
            shapeStyle.setStroke(new BasicStroke(MainControl.stroke));
            ((ShapeDrawing) MainControl.preview).setStyle(shapeStyle);
        }
        comp.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        if (MainControl.preview == null) 
        {
        	return;
        }        
        double endx = e.getX()/MainControl.zoom;
        double endy = e.getY()/MainControl.zoom;
        double x = (startx > endx) ? endx : startx;
        double y = (starty > endy) ? endy : starty;
        double w = Math.abs(endx - startx);
        double h = Math.abs(endy - starty);

        switch (MainControl.mode) 
        {
            case FREEHAND:
                path.lineTo(endx, endy);
                break;
            case LINE:      MainControl.preview = new LineDrawing(startx, starty, endx, endy);           break;
            case OVAL:      MainControl.preview =        new EllipseDrawing(x, y, w, h);                 break;
            case RECTANGLE: MainControl.preview =      new RectangleDrawing(x, y, w, h);                 break;
            default:
                break;
        } 
        if (MainControl.preview instanceof ShapeDrawing) 
        {
            ShapeDrawing.Style shapeStyle = new ShapeDrawing.Style();
            if (MainControl.mode != MainControl.Mode.FREEHAND) 
            {
                shapeStyle.setFillColor(MainControl.fill);
            }
            shapeStyle.setLineColor(MainControl.line);
            shapeStyle.setStroke(new BasicStroke(MainControl.stroke));
            ((ShapeDrawing) MainControl.preview).setStyle(shapeStyle);
        }
        comp.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        if (MainControl.preview == null) 
        {
        	return;
        }
        switch (MainControl.mode) 
        {
            case FREEHAND: path = null;
            case LINE:  
            case OVAL:                  
            case RECTANGLE:                     
                MainControl.drawings.add(MainControl.preview);
                break;
            default:
                break;
        } 
        MainControl.preview = null;
        comp.repaint();
    }
} 