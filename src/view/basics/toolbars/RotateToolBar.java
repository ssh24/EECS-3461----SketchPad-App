package view.basics.toolbars;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import controller.primary.MainControl;
import controller.secondary.drawings.Drawing;

/********The RotateToolBar class *******/
public class RotateToolBar extends JToolBar 
{
	 /********private variables *********/
	private static final long serialVersionUID = 1L;
	private final Color BACKGROUND = new Color(230, 230, 230);
	private JButton rotate_right;
	private JButton rotate_left;
	private JButton flip_vertical;
	private JButton flip_horizontal;
	private boolean choice_rotate_right = false;
	private boolean choice_rotate_left = false;
	private boolean choice_horizontal_flip = false;
	private boolean choice_vertical_flip = false;

	
	 /********public constructor *********/
	public RotateToolBar() 
	{
		setFloatable(false);
		setLayout(new GridLayout(4, 1, 3, 3));

		init_all();

		this.setBackground(BACKGROUND);
		this.setFloatable(false);
		this.setRollover(true);
	}

	/********private method that creates the buttons for the rotation toolbar ********/
	private void init_all() 
	{
		init_rotate_right();
		init_rotate_left();
		init_flip_horizontal();
		init_flip_vertical();

	}

	 /********private method to rotate the drawing objects to the right *********/
	private void init_rotate_right() 
	{
		rotate_right = new JButton();
		rotate_right.setText("<html><font size = 4><b>Rotate right by 90\u00B0</b></html>");
		// adds Listener
		rotate_right.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{

					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

					JLabel label = new JLabel("<html><font size = 5><b><u>Tip</u>: This option let you rotate every drawing and shape to the right by 90\u00B0. <br>You can also select "
							+ "individual drawing or shape using the <u><i>select pointer</i></u> and apply the rotation.</br></html>");

					JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");

					
					if (choice_rotate_right)
						;
					else 
						
					{
						panel.add(label);
						panel.add(box);

						JOptionPane.showMessageDialog(null, panel, "", JOptionPane.PLAIN_MESSAGE);

						if (box.isSelected())
							choice_rotate_right = true;
						else
							choice_rotate_right = false;
					}
					if (MainControl.drawings.contains(MainControl.selected))
						MainControl.selected.rotate(Math.PI / 2);
					else 
					{
						for (Drawing d : MainControl.drawings) 
						{
							d.rotate(Math.PI / 2);
						}
					}
					MainControl.drawingArea.repaint();
				} 
				catch (Exception e1) 
				{
					
				}
			}
		});

		add(rotate_right);
	}

	 /********private method to rotate the drawing objects to the left *********/
	private void init_rotate_left() 
	{
		rotate_left = new JButton();
		rotate_left.setText("<html><font size = 4><b>Rotate left by 90\u00B0</b></html>");
		// adds Listener
		rotate_left.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{

					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

					JLabel label = new JLabel("<html><font size = 5><b><u>Tip</u>: This option let you rotate every drawing and shape to the left by 90\u00B0. <br>You can also select "
							+ "individual drawing or shape using the <u><i>select pointer</i></u> and apply the rotation.</br></html>");

					JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");

					
					if (choice_rotate_left)
						;
					else 
						
					{
						panel.add(label);
						panel.add(box);

						JOptionPane.showMessageDialog(null, panel, "", JOptionPane.PLAIN_MESSAGE);

						if (box.isSelected())
							choice_rotate_left = true;
						else
							choice_rotate_left = false;
					}
					if (MainControl.drawings.contains(MainControl.selected))
						MainControl.selected.rotate(-Math.PI / 2);
					else 
					{
						for (Drawing d : MainControl.drawings) 
						{
							d.rotate(-Math.PI / 2);
						}
					}
					MainControl.drawingArea.repaint();

				} 
				catch (Exception e1) 
				{
					
				}
			}
		});

		add(rotate_left);
	}

	 /********private method to flip horizontally the drawing objects *********/
	private void init_flip_horizontal() 
	{
		flip_horizontal = new JButton();
		flip_horizontal.setText("<html><font size = 4><b>Flip horizontally</b></html>");
		// adds Listener
		flip_horizontal.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

					JLabel label = new JLabel("<html><font size = 5><b><u>Tip</u>: This option let you flip horizontally every drawing and shape. <br>You can also select "
							+ "individual drawing or shape using the <u><i>select pointer</i></u> and apply the horizontal flip.</br></html>");

					JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");

					
					if (choice_horizontal_flip)
						;
					else 
						
					{
						panel.add(label);
						panel.add(box);

						JOptionPane.showMessageDialog(null, panel, "", JOptionPane.PLAIN_MESSAGE);

						if (box.isSelected())
							choice_horizontal_flip = true;
						else
							choice_horizontal_flip = false;
					}
					if (MainControl.drawings.contains(MainControl.selected))
						MainControl.selected.reflectHorizontal();
					else 
					{
						for (Drawing d : MainControl.drawings)
						{
							d.reflectHorizontal();
						}
					}
					MainControl.drawingArea.repaint();
				}
				catch(Exception e1)
				{
					
				}
			}
		});

		add(flip_horizontal);
	}


	 /********private method to flip vertically the drawing objects *********/
	private void init_flip_vertical() 
	{
		flip_vertical = new JButton();
		flip_vertical.setText("<html><font size = 4><b>Flip vertically</b></html>");
		// adds Listener
		flip_vertical.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

					JLabel label = new JLabel("<html><font size = 5><b><u>Tip</u>: This option let you flip vertically every drawing and shape. <br>You can also select "
							+ "individual drawing or shape using the <u><i>select pointer</i></u> and apply the vertical flip.</br></html>");

					JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");

					
					if (choice_vertical_flip)
						;
					else 
						
					{
						panel.add(label);
						panel.add(box);

						JOptionPane.showMessageDialog(null, panel, "", JOptionPane.PLAIN_MESSAGE);

						if (box.isSelected())
							choice_vertical_flip = true;
						else
							choice_vertical_flip = false;
					}
					if (MainControl.drawings.contains(MainControl.selected))
						MainControl.selected.reflectHorizontal();
					else 
					{
						for (Drawing d : MainControl.drawings)
						{
							d.reflectHorizontal();
						}
					}
					if (MainControl.drawings.contains(MainControl.selected))
						MainControl.selected.reflectVertical();
					else 
					{
						for (Drawing d : MainControl.drawings) 
						{
							d.reflectVertical();
						}
					}
					MainControl.drawingArea.repaint();
				}
				catch(Exception e1)
				{
					
				}
			}
		});

		add(flip_vertical);
	}
}