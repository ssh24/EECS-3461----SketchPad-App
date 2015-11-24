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


public class MainControl {
    
    public static enum Mode {
        SELECT, ROTATE, ERASER,
        LINE, RECTANGLE, ROUNDRECT, OVAL,
        FREEHAND, POLYGON, IMAGE, TEXT, TRIANGLE
    }

    public static Mode mode = Mode.SELECT;
    public static Color fill = null;
    public static Color line = Color.BLACK;
    public static int stroke = 1; 
    public static boolean isOpaque = true;
    public static boolean isGrid = false;
    public static boolean isRuler = false;
    public static double zoom = 1.0;
    public static Dimension size = new Dimension(800, 600);
    public static List<Drawing> drawings = new ArrayList<Drawing>();
    public static Drawing preview = null;
    public static Drawing selected = null;
    public static File imagePath = null;
    public static AbstractButton fillButton = null;
    public static AbstractButton lineButton = null;
    public static DrawingArea drawingArea = new DrawingArea();
    public static File file = null;
    public static String ext = null;
    public static JSlider zoomSlider = null;
    public static AbstractButton zoomIn = null;
    public static AbstractButton zoomOut = null;

} 