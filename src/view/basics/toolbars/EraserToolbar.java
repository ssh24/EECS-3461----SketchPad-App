package view.basics.toolbars;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.primary.MainControl;


/********The DrawingToolBar class *******/
public class EraserToolbar extends JToolBar 
{
	 /********Variables *********/
	private static final long serialVersionUID = 1L;
	public static JButton eraser_line;
	private final Color BACKGROUND = new Color(230, 230, 230);
	public static JButton eraser_selection;
	
	private boolean choice = false;
	public static Color prev_color;
	public static boolean bool = false;

	
	 /********public constructor *********/
	public EraserToolbar() 
	{

		setFloatable(false);
		setLayout(new GridLayout(1, 2, 3, 3));

		init_all();

		this.setBackground(BACKGROUND);
		this.setFloatable(false);
		this.setRollover(true);

	}

	/********private method that creates the buttons for the drawing toolbar ********/
	private void init_all() 
	{
		//init_eraser_line();
		//init_eraser_selection();
	}

/*	private void init_eraser_line()
	{
		eraser_line = new JButton();
		eraser_line.setIcon(new ImageIcon("images/erase-tool-bar-images/eraser1.png"));
		eraser_line.setBackground(Color.WHITE);
		eraser_line.setToolTipText("Eraser.");
		// adds Listener
		eraser_line.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me) 
			{
				ToolBar.change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
				ToolBar.change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
				MainControl.mode = MainControl.Mode.SELECT;
				
				if(!MainControl.drawings.isEmpty())
				{
					bool = true;
					prev_color = MainControl.line;
					MainControl.mode = MainControl.Mode.FREEHAND;
					MainControl.line = Color.WHITE;
					getParent().setVisible(false);
				}
			}
		});

		add(eraser_line);
	}

	private void init_eraser_selection()
	{
		eraser_selection = new JButton(new ImageIcon("images/erase-tool-bar-images/selection_erasing.png"));
		eraser_selection.setBackground(Color.WHITE);
		eraser_selection.setToolTipText("Selection Eraser.");
		eraser_selection.addMouseListener(new MouseAdapter() 
		{

			@Override
			public void mouseClicked(MouseEvent me) 
			{

				if (MainControl.mode == MainControl.Mode.SELECT && MainControl.selected != null) 
				{
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

					JLabel label = new JLabel("<html><font size = 5><b><u>Tip</u>: Using an eraser to delete drawing is permanent. "
							+ "<br>Cannot revert back once the drawing is deleted.<b></html>");

					JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");

					if (choice)
						;
					else 
					{
						panel.add(label);
						panel.add(box);

						JOptionPane.showMessageDialog(null, panel, "", JOptionPane.PLAIN_MESSAGE);

						if (box.isSelected())
							choice = true;
						else
							choice = false;
					}

					MainControl.drawings.remove(MainControl.selected);
					
					if(MainControl.drawings.isEmpty())
					{
						ToolBar.undo.setEnabled(false);
					}
					//if(MainControl.dr)

					MainControl.mode = MainControl.mode.SELECT;
					MainControl.selected = null;
					getParent().setVisible(false);

					MainControl.drawingArea.repaint();

				} 
				else
				{
					JOptionPane.showMessageDialog(null, "<html><font size = 5>Please select a drawing or shape using the <u><i>select pointer</i></u> in order to delete.</html>", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(eraser_selection);
	}*/
}
