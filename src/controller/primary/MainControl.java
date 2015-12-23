package controller.primary;

import java.awt.*
;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JSlider;

import view.basics.panes.*;
import controller.secondary.drawings.Drawing;

/********The MainControl class ********/
public class MainControl 
{
    
    public static enum Mode 
    {
        SELECT, ROTATE, ERASER,
        LINE, RECTANGLE, OVAL,
        FREEHAND, POLYGON, IMAGE, TEXT, FULLSCREEN
    }

    /********Variables*******/
    public static Mode mode = Mode.SELECT;
    public static Color fill = null;
    public static Color line = Color.BLACK;
    public static int stroke = 5; 
    public static double zoom = 1.0;
    public static Dimension size = new Dimension(1200, 650);
    public static List<Drawing> drawings = new ArrayList<Drawing>();
    public static Drawing preview = null;
    public static Drawing selected = null;
    public static File imagePath = null;
    public static DrawingArea drawingArea = new DrawingArea();
    public static File file = null;
    public static String ext = null;
    public static JSlider zoomSlider = null;

} 