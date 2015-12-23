package helper.handlers;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import controller.primary.MainControl;
import controller.secondary.drawings.ImageDrawing;

/********The ImageListener class********/
public class ImageListener extends MouseAdapter 
{
	 /********private variables *********/
    private double startx;
    private double starty;
    private Component comp;
    
    public ImageListener(Component comp) 
    {
        this.comp = comp;
    }
    
    
    // implements MouseAdapter methods
    @Override
    public void mousePressed(MouseEvent e) 
    {
        startx = e.getX();
        starty = e.getY();
        ImageDrawing image = new ImageDrawing(MainControl.imagePath, startx, starty);
        MainControl.preview = image;
        comp.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) 
    {
        double endx = e.getX();
        double endy = e.getY();
        double w = endx - startx; if (w == 0) {w = 0.1f;}
        double h = endy - starty; if (h == 0) {h = 0.1f;}
        Rectangle2D bound = MainControl.preview.getBounds2D();
        MainControl.preview.scale(w/bound.getWidth(), h/bound.getHeight());
        comp.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        MainControl.drawings.add(MainControl.preview);
        MainControl.preview = null;
        comp.repaint();
    }


}
