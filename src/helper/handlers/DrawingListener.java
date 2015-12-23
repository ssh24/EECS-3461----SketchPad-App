package helper.handlers;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import controller.primary.MainControl;


public class DrawingListener extends MouseAdapter 
{
	 /********private variables *********/
    private DrawShapeListener dsl;
    private SelectionListener sel;
    private DrawPolygonListener dpl;
    private ImageListener imgl;
    private TextListener tl;
    
    
    /********public constructor *********/
    public DrawingListener(Component comp)
    {
        this.dsl = new DrawShapeListener(comp);
        this.sel = new SelectionListener(comp);
        this.dpl = new DrawPolygonListener(comp);
        this.imgl = new ImageListener(comp);
        tl = new TextListener(comp);
    }
    
    // implements MouseAdapter methods
    @Override
    public void mouseClicked(MouseEvent e)
    {
    	
        switch (MainControl.mode)
        {
            case FREEHAND:
            case LINE:
            case OVAL:
            case RECTANGLE:
                dsl.mouseClicked(e);
                break;
            case POLYGON:
                dpl.mouseClicked(e);
                break;
            case ROTATE:
                break;
            case IMAGE:
                imgl.mouseClicked(e);
                break;
            case SELECT:
                sel.mouseClicked(e);
                break;
            case TEXT:
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
        switch (MainControl.mode) {
            case FREEHAND:
            case LINE:
            case OVAL:
            case RECTANGLE:
                dsl.mousePressed(e);
                break;
            case POLYGON:
                dpl.mousePressed(e);
                break;
            case ROTATE:
                break;
            case IMAGE:
                imgl.mousePressed(e);
                break;
            case SELECT:
                sel.mousePressed(e);
                break;
            case TEXT:
            	tl.mousePressed(e);
                break;
            default:
                break;
        } 
    }
    
    @Override
    public void mouseDragged(MouseEvent e) 
    {
        switch (MainControl.mode) {
            case FREEHAND:
            case LINE:
            case OVAL:
            case RECTANGLE:
                dsl.mouseDragged(e);
                break;
            case POLYGON:
                dpl.mouseDragged(e);
                break;
            case ROTATE:
                break;
            case IMAGE:
                imgl.mouseDragged(e);
                break;
            case SELECT:
                sel.mouseDragged(e);
                break;
            case TEXT:
                break;
            default:
                break;
        } 
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
        switch (MainControl.mode) {
        
            case FREEHAND:
            case LINE:
            case OVAL:
            case RECTANGLE:
                dsl.mouseReleased(e);
                break;
            case POLYGON:
                dpl.mouseReleased(e);
                break;
            case ROTATE:
                break;
            case IMAGE:
                imgl.mouseReleased(e);
                break;
            case SELECT:
                sel.mouseReleased(e);
                break;
            case TEXT:
            	tl.mouseReleased(e);
                break;
            default:
                break;
        } 
    }
}
