package helper.extras;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import view.basics.panes.*;
import controller.primary.MainControl;
import controller.secondary.drawings.Drawing;
import controller.secondary.drawings.ImageDrawing;

public class FileOperations {

	private static JFrame my_frame;

	public FileOperations(JFrame frame)
	{
		my_frame = frame;
	}
    public int newDrawing()
    {
        if (saveDrawing(true, false) == JOptionPane.CANCEL_OPTION)
        {
        	System.out.println("First if");
            return JOptionPane.CANCEL_OPTION;
        }
        MainControl.file = null;
        if (!MainControl.drawings.isEmpty()) 
        {
        	MainControl.drawings.clear();
            MainControl.preview = null;
            MainControl.selected = null;
            MainControl.drawingArea.repaint();
        	my_frame.dispose();
        	new MainFrame("Untitled");
/*            MainControl.drawings.clear();
            MainControl.preview = null;
            MainControl.selected = null;
            MainControl.drawingArea.repaint();*/
        }
        return JOptionPane.YES_OPTION;
    }

    public  int openDrawing() {
        if (newDrawing() == JOptionPane.CANCEL_OPTION) 
        {
            return JOptionPane.CANCEL_OPTION;
        }
        ImageFileChooser imgChooser = new ImageFileChooser();
        int returnVal = imgChooser.showOpenDialog(null);
        if (returnVal == ImageFileChooser.APPROVE_OPTION) {
            MainControl.drawings.add(new ImageDrawing(imgChooser.getSelectedFileWithExtension(), 0, 0));
            MainControl.file = imgChooser.getSelectedFileWithExtension();
            String[] parts = MainControl.file.getName().split("[.]");
            MainControl.ext = parts[parts.length - 1];
            
            MainControl.preview = null;
            MainControl.selected = null;
            MainControl.drawingArea.repaint();
        	my_frame.dispose();
        	new MainFrame(MainControl.file.getName());
            return JOptionPane.YES_OPTION;
        }
        return JOptionPane.CANCEL_OPTION;
    }
    
    private static int saveDrawing(File saveFile, String ext) {
        JPanel drawPanel = MainControl.drawingArea;
        BufferedImage image = new BufferedImage(drawPanel.getWidth(), drawPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = image.createGraphics(); 
        boolean showGrid = MainControl.isGrid;
        Drawing selected = MainControl.selected;
        MainControl.isGrid = false;
        MainControl.selected = null;
        drawPanel.paint(graphics2D);
        MainControl.isGrid = showGrid;
        MainControl.selected = selected;
        try {
            ImageIO.write(image, ext, saveFile);
        } catch(Exception ex){
            ex.printStackTrace();
            return JOptionPane.NO_OPTION;
        }
    	my_frame.dispose();
    	new MainFrame(MainControl.file.getName());
        return JOptionPane.YES_OPTION;
    }

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
        } else {
            return saveDrawing(MainControl.file, MainControl.ext);
        }
    	my_frame.dispose();
    	new MainFrame(MainControl.file.getName());
        return JOptionPane.CANCEL_OPTION;
    }

}
