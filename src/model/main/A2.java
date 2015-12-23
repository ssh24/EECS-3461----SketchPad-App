package model.main;

import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.basics.panes.*;

public class A2
{
	final static String[] tips = {"<html><li><font size = 4><b><i>Background Fill Color button let you choose a color to fill the paint panel background.</li></html>",
			"<html><li><font size = 4><b><i>The New button opens a new \'Untitled\' paint panel.</li></html>",
			"<html><li><font size = 4><b><i>The Open button will pop up a file chooser. It includes multiple image formats to choose from.",
			"<html><li><font size = 4><b><i>The Save button saves any changes made to the current file.",
			"<html><li><font size = 4><b><i>The Save As button will prompt you to save your current file as a new file and it includes multiple image formats to save as.",
			"<html><li><font size = 4><b><i>The Print button prints the current file. It includes the basic options to print a file.",
			"<html><li><font size = 4><b><i>The Select Pointer button let you select individual drawings or shapes.",
			"<html><li><font size = 4><b><i>The Draw button gives you two options to choose from: line drawing and free hand drawing.",
			"<html><li><font size = 4><b><i>The Add Text button let you add texts on the paint panel.",
			"<html><li><font size = 4><b><i>The Add Image button let you add images on the paint panel.",
			"<html><li><font size = 4><b><i>The Eraser button will permanently delete a shape or drawing.",
			"<html><li><font size = 4><b><i>The Shape drawing button gives 3 options to draw shapes: 1. Rectangle, 2. Ellipse, and 3. Any polygon shapes.",
			"<html><li><font size = 4><b><i>The Undo button only works when you call undo on free hand drawings, line drawings, all shapes drawings, texts and images.",
			"<html><li><font size = 4><b><i>The Redo button only works when you call redo on free hand drawings, line drawings, all shapes drawings, texts and images.",
			"<html><li><font size = 4><b><i>The Stroke size slider changes the stroke size of the lines.",
			"<html><li><font size = 4><b><i>The Background Fill Color button let you choose a color to fill the paint panel background.",
			"<html><li><font size = 4><b><i>The Shape Fill Color button let you select individual drawing or shape using the select pointer button and apply the fill color change.",
			"<html><li><font size = 4><b><i>The Rotate button let you rotate right/left by 90 degree or flip horizontally/vertically all or individual drawings and shapes.",
			"<html><li><font size = 4><b><i>The Translate button let you translate all or individual drawings and shapes from the paint panel given the x and y translating factors.",
			"<html><li><font size = 4><b><i>The Scale button let you scale all or individual drawings and shapes from the paint panel given the x and y scaling factors.",
			"<html><li><font size = 4><b><i>The Zoom slider let you zoom in and zoom out the paint panel (also available using the mouse wheel).",
			"<html><li><font size = 4><b><i>You can show/hide the toolbar using the command ctrl + T."};
    public static void main(String[] args) 
    {
    	new MainFrame("Untitled");
    	
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel label = new JLabel("<html><font size = 6><font color = blue><b>Did you know?</b></html>", JLabel.CENTER);
		
		int rand1 = new Random().nextInt(tips.length);
		int rand2 = new Random().nextInt(tips.length);
		int rand3 = new Random().nextInt(tips.length);
		
		while(rand1 == rand2 || rand1 == rand3 || rand2 == rand3)
		{
			 rand1 = new Random().nextInt(tips.length);
			 rand2 = new Random().nextInt(tips.length);
			 rand3 = new Random().nextInt(tips.length);
		}
		
		JLabel tip1, tip2, tip3;
		tip1 = new JLabel(tips[rand1]); tip2 = new JLabel(tips[rand2]); tip3 = new JLabel(tips[rand3]);

		//JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");
		
		panel.add(label);
		panel.add(tip1);
		panel.add(tip2);
		panel.add(tip3);
		JOptionPane.showMessageDialog(null, panel, "Tips of the Day" + MainFrame.company, JOptionPane.PLAIN_MESSAGE);
    }
}