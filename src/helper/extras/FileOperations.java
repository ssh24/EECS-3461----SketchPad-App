package helper.extras;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.basics.panes.*;
import view.basics.toolbars.StrokeToolbar;
import view.basics.toolbars.ToolBar;
import view.basics.*;
import controller.primary.MainControl;
import controller.secondary.drawings.Drawing;
import controller.secondary.drawings.ImageDrawing;

/********The FileOperations class *******/
public class FileOperations 
{	
	/********public method to create a new drawing *********/
    public static int newDrawing()
    {
        if (saveDrawing(true, false) == JOptionPane.CANCEL_OPTION)
        {
            return JOptionPane.CANCEL_OPTION;
        }
        MainControl.file = null;
        if (!MainControl.drawings.isEmpty()) 
        {
        	MainControl.drawings.clear();
            MainControl.preview = null;
            MainControl.selected = null;
            MainControl.drawingArea.repaint();
            MainControl.mode = MainControl.Mode.SELECT;
            
            ToolBar.undo.setEnabled(false);
            ToolBar.redo.setEnabled(false);
            ToolBar.change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
            ToolBar.change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
            MainControl.drawingArea.setBackground(Color.WHITE);
            MainControl.stroke = 1;
            StrokeToolbar.resetSlider();
            MainControl.line = Color.BLACK;
            MainControl.fill = Color.WHITE;
            
            MainFrame.getFrames()[0].setTitle("Untitled" + MainFrame.company);
            
        }
        return JOptionPane.YES_OPTION;
    }

    /********public method to open a drawing file *********/
    public static  int openDrawing() 
    {
        if (newDrawing() == JOptionPane.CANCEL_OPTION) 
        {
            return JOptionPane.CANCEL_OPTION;
        }
        ImageFileChooser imgChooser = new ImageFileChooser();
        int returnVal = imgChooser.showOpenDialog(null);
        if (returnVal == ImageFileChooser.APPROVE_OPTION) 
        {
            MainControl.drawings.add(new ImageDrawing(imgChooser.getSelectedFileWithExtension(), 0, 0));
            MainControl.file = imgChooser.getSelectedFileWithExtension();
            String[] parts = MainControl.file.getName().split("[.]");
            MainControl.ext = parts[parts.length - 1];
            
            MainControl.preview = null;
            MainControl.selected = null;
            MainControl.drawingArea.repaint();
            MainFrame.getFrames()[0].setTitle(MainControl.file.getName() + MainFrame.company);
            return JOptionPane.YES_OPTION;
        }
        return JOptionPane.CANCEL_OPTION;
    }
    
    /********private method to save the drawing *********/
    private static int saveDrawing(File saveFile, String ext) 
    {
        JPanel drawPanel = MainControl.drawingArea;
        BufferedImage image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics(); 
        drawPanel.paint(graphics2D);
   
        Drawing selected = MainControl.selected;
        MainControl.selected = null;
        MainControl.selected = selected;
        try 
        {
            ImageIO.write(image, ext, saveFile);
        } 
        catch(Exception ex)
        {
            ex.printStackTrace();
            return JOptionPane.NO_OPTION;
        }
        if(MainControl.file == null)
        {
        	
        }
        else 
        {
        	 MainFrame.getFrames()[0].setTitle(MainControl.file.getName() + MainFrame.company);
        }
        return JOptionPane.YES_OPTION;
    }

    /********public method to save the drawing *********/
    public static int saveDrawing(boolean confirm, boolean saveAs)
    {
        if (MainControl.drawings.isEmpty()) 
        {
            return JOptionPane.YES_OPTION;
        }
        
        if (confirm)
        {
            int n = JOptionPane.showOptionDialog(null, "Do you to save changes to " + 
                    (MainControl.file == null ? "Untitled" : MainControl.file.getName()) + "?", "Save",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, null, null);
            
            if (n != JOptionPane.YES_OPTION) 
            {
                return n;
            }
        }
        
        if (MainControl.file == null || saveAs) 
        {
            ImageFileChooser imgChooser = new ImageFileChooser();
            int returnVal = imgChooser.showSaveDialog(null);
            if (returnVal == ImageFileChooser.APPROVE_OPTION) 
            {
                MainControl.file = imgChooser.getSelectedFileWithExtension();
                MainControl.ext = ((FileNameExtensionFilter)imgChooser.getFileFilter()).getExtensions()[0];
                return saveDrawing(MainControl.file, MainControl.ext);
            }
        } 
        else 
        {
            return saveDrawing(MainControl.file, MainControl.ext);
        }
        if(MainControl.file == null)
        {
        	
        }
        else 
        {
        	 MainFrame.getFrames()[0].setTitle(MainControl.file.getName() + MainFrame.company);
        }
       
        return JOptionPane.CANCEL_OPTION;
    }
}
