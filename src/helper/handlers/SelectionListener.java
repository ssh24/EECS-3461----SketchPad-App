package helper.handlers;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import controller.primary.MainControl;
import controller.secondary.drawings.*;
import controller.secondary.drawings.ShapeDrawing.Style;

/********The SelectionListener class********/
public class SelectionListener extends MouseAdapter 
{
	 /********private variables *********/
    private Component comp;

    
    /********public constructor *********/
    public SelectionListener(Component comp) 
    {
        this.comp = comp;
    }

    
    // implements MouseAdapter methods
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        boolean selected = false;
        for (int i = MainControl.drawings.size() - 1; i >= 0; i--) 
        {
            Drawing d = MainControl.drawings.get(i);
            Rectangle2D bound = d.getBounds2D();
            if (bound.contains(e.getX()/MainControl.zoom, e.getY()/MainControl.zoom)) 
            {
                if (MainControl.selected == d) 
                {
                    MainControl.selected = null;
                } 
                else 
                {
                    MainControl.selected = d;
                }
                selected = true;
                break;
            }
        }
        if (!selected) 
        {
            MainControl.selected = null;
        }
        comp.repaint();
    }

    private double startx;
    private double starty;

    @Override
    public void mousePressed(MouseEvent e) 
    {
        MainControl.preview = null;
        double ptx = e.getX()/MainControl.zoom;
        double pty = e.getY()/MainControl.zoom;
        if (MainControl.selected != null && 
                MainControl.selected.getBounds2D().contains(ptx, pty))
        {
            startx = ptx;
            starty = pty;
            if (MainControl.selected instanceof ShapeDrawing)
            {
                MainControl.preview = new ShapeDrawing((ShapeDrawing)MainControl.selected);
            } 
            else if (MainControl.selected instanceof ImageDrawing)
            {
                MainControl.preview = new ImageDrawing((ImageDrawing)MainControl.selected);
            }
        }
        comp.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        double endx = e.getX()/MainControl.zoom;
        double endy = e.getY()/MainControl.zoom;
        if (MainControl.selected != null && MainControl.preview != null) 
        {
            Drawing drawing = null;
            if (MainControl.selected instanceof ShapeDrawing) 
            {
                drawing = new ShapeDrawing((ShapeDrawing)MainControl.selected);
            } 
            else if (MainControl.selected instanceof ImageDrawing) 
            {
                drawing = new ImageDrawing((ImageDrawing)MainControl.selected);
            }
            else if (MainControl.selected instanceof TextDrawing) 
            {
                drawing = (TextDrawing)MainControl.selected;
            }
            drawing.translate(endx - startx, endy - starty);
            MainControl.preview = drawing;
        }
        comp.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        double endx = e.getX()/MainControl.zoom;
        double endy = e.getY()/MainControl.zoom;
        if (MainControl.selected != null && MainControl.preview != null && MainControl.preview.getBounds2D().contains(endx, endy)) 
        {
            MainControl.selected.translate(endx - startx, endy - starty);
            MainControl.preview = null;
        }
        comp.repaint();
    }
} 