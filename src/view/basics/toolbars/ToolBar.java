package view.basics.toolbars;

import helper.extras.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import view.basics.panes.*;
import controller.primary.MainControl;
import controller.secondary.drawings.*;


/********The ToolBar class creates the main toolbar for the paint program. *******/
public class ToolBar extends JToolBar 
{
	/********Variables *********/
	private static final long serialVersionUID = 1L;

	private JButton new_shortcut, open_shortcut, save_shortcut, save_as_shortcut;

	private JPanel shortcut_menu;

	private final Font FONT = new Font("Calibri", Font.PLAIN, 20);
	
	private final Color BACKGROUND = new Color(230, 230, 230);

	public static ArrayList<Drawing> contents = new ArrayList<Drawing>();
	
	private boolean choice = false;



	private JButton pointer;

	public static JButton undo;

	public static JButton redo;

	private JPanel edit;

	private JButton rotate;

	private static JButton draw;

	public static JButton del;

	private JPanel extras;

	private JButton fill_color;

	private JButton line_color;

	private ButtonGroup modeGroup = new ButtonGroup();

	private JButton text;

	private static JButton shapes;


	private JButton images;

	private JButton translate;

	private JButton scale;

	private JButton fill_background;

	private JFrame frame;

	private FileOperations fo;

	private JButton stroke;

	private boolean choice_line_color = false;

	private boolean choice_translate = false;

	private boolean choice_scale = false;

	private boolean choice_image = false;

	public static Color backgroundColor;
	
	/********public constructor which takes as a parameter a frame ********/
	public ToolBar(JFrame main) 
	{
		setLayout(new GridLayout(1, 3));

		shortcut_menu = new JPanel();
		shortcut_menu.setLayout(new GridLayout(2, 2, 5, 5));
		shortcut_menu.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "<html><font size = 6>Shortcut Menu</html>", TitledBorder.CENTER, 0));
		shortcut_menu.setBackground(BACKGROUND);

		edit = new JPanel();
		edit.setLayout(new GridLayout(3, 3, 5, 5));
		edit.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "<html><font size = 6>Tools<html>", TitledBorder.CENTER, 0));
		edit.setBackground(BACKGROUND);

		extras = new JPanel();
		extras.setLayout(new GridLayout(2, 3, 5, 5));
		extras.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(), "<html><font size = 6>Attributes</html>", TitledBorder.CENTER, 0));
		extras.setBackground(BACKGROUND);

		init_all();

		add(shortcut_menu);
		add(edit);
		add(extras);

		//Toolbars for drawing, shapes, rotate and stroke
		JToolBar drawingModePane = new DrawingToolBar(draw);
		JToolBar shapesModePane = new ShapesToolBar();
		JToolBar rotateModePane = new RotateToolBar();
		JToolBar sliderModePane = new StrokeToolbar();
	
		//JToolBar eraserModePane= new EraserToolbar();

		UIPopUp.attachPopupPane(draw, drawingModePane, null, PopupPane.CENTER, false);

		UIPopUp.attachPopupPane(shapes, shapesModePane, null, PopupPane.CENTER, false);

		UIPopUp.attachPopupPane(rotate, rotateModePane, null, PopupPane.CENTER, false);

		UIPopUp.attachPopupPane(stroke, sliderModePane, null, PopupPane.CENTER, false);
		
		//UIPopUp.attachPopupPane(del, eraserModePane, null, PopupPane.CENTER, false);

		this.setBackground(BACKGROUND);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setFloatable(false);
		this.setRollover(true);
	}

	/********private method that creates the buttons for the toolbar ********/
	private void init_all() 
	{
		init_new_shortchut();
		init_open_shortchut();
		init_save_shortchut();
		init_save_as_shortchut();

		init_pointer();
		init_draw();
		init_delete();

		init_text();
		init_shapes();
		init_image();

		init_stroke();
		init_undo();
		init_redo();

		init_fill_background();
		init_line();
		init_fill_shapes();

		init_rotate();
		init_translate();
		init_scale();
	}

	
	/********private method that adds an image ********/
	private void init_image() 
	{
		images = new JButton(new ImageIcon("images/main-tool-bar-images/insert_image.png"));
		images.setBackground(Color.WHITE);
		images.setToolTipText("Insert an image.");
		images.setFont(FONT);
		//add Listener
		images.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				
				
				MainControl.drawingArea.connect_drag();
				MainControl.mode = MainControl.Mode.IMAGE;
				ImageFileChooser imgChooser = new ImageFileChooser();
				int returnVal = imgChooser.showOpenDialog((Component) e.getSource());

				if (returnVal == ImageFileChooser.APPROVE_OPTION) 
				{
					MainControl.preview = null;
					MainControl.imagePath = imgChooser.getSelectedFileWithExtension();
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

					JLabel label = new JLabel("<html><font size = 5><b><u>Tip</u>: "
							+ "Click anywhere on the drawing panel to Add Image.<b></html>");

					JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");

					if (choice_image)
						;
					else 
					{
						panel.add(label);
						panel.add(box);

						JOptionPane.showMessageDialog(null, panel, "", JOptionPane.PLAIN_MESSAGE);

						if (box.isSelected())
							choice_image = true;
						else
							choice_image = false;
					}

					
				}
				//EraserToolbar.bool = false;
				change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
				change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
			}
		});

		edit.add(images);
	}

	/********private method that fills the background color ********/
	private void init_fill_background() 
	{
		fill_background = new JButton(new ImageIcon("images/main-tool-bar-images/fill_background.png"));

		fill_background.setBackground(Color.WHITE);
		fill_background.setToolTipText("Choose Background Color.");
		fill_background.setFont(FONT);
		//add Listener
		fill_background.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Color newColor = JColorChooser.showDialog(null, "Choose background color.", Color.WHITE);

				if (newColor != null)
				{
					backgroundColor = newColor;
					MainControl.drawingArea.setBackground(newColor);
					MainControl.drawingArea.repaint();
				}
				else
					;
			}
		});

		extras.add(fill_background);
	}

	/********private method that changes the stroke size ********/
	private void init_stroke() 
	{
		stroke = new JButton(new ImageIcon("images/main-tool-bar-images/stroke.png"));
		stroke.setBackground(Color.WHITE);
		stroke.setToolTipText("Change stroke size.");
		stroke.setFont(FONT);
		//add Listener
		stroke.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
				change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
			}
		});

		edit.add(stroke);
	}

	/********private method that translates the drawing objects ********/
	private void init_translate() 
	{
		translate = new JButton(new ImageIcon("images/main-tool-bar-images/translate.png"));
		translate.setBackground(Color.WHITE);
		translate.setToolTipText("Translate a drawing with X and Y factor.");
		translate.setFont(FONT);
		//add Listener
		translate.addActionListener(new ActionListener()
		{


			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (!MainControl.drawings.isEmpty())
				{
					try
					{
						JPanel panel = new JPanel();
						panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

						JLabel label = new JLabel("<html><font size = 5><b><u>Tip</u>: This option let you translate every drawing and shape given the x and y translating factors. <br>You can also select "
								+ "individual drawing or shape using the <u><i>select pointer</i></u> and apply the translation.</br></html>");

						JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");

						
						if (choice_translate)
							;
						else 
							
						{
							panel.add(label);
							panel.add(box);

							JOptionPane.showMessageDialog(null, panel, "", JOptionPane.PLAIN_MESSAGE);

							if (box.isSelected())
								choice_translate = true;
							else
								choice_translate = false;
						}
						
						String x_s = (String) JOptionPane.showInputDialog(null, 
								"<html>Please enter a valid translate factor for <b><font color= blue>x-axis</b></font> between <b><font color= blue>-100 to 100</b></font>:</html>", "Prompt", JOptionPane.PLAIN_MESSAGE);
						
						
						double x = Double.parseDouble(x_s);
						
						while(x<-100 || x>100)
						{
							x_s = (String) JOptionPane.showInputDialog(null, 
									"<html>Please enter a valid translate factor for <b><font color= blue>x-axis</b></font> between <b><font color= blue>-100 to 100</b></font>:</html>", "Prompt", JOptionPane.PLAIN_MESSAGE);
							x = Double.parseDouble(x_s);
						}

						if (!(x < -100 || x > 100)) 
						{
							String y_s = (String) JOptionPane.showInputDialog(null, 
									"<html>Please enter a valid translate factor for <html><b><font color= blue>y-axis</b></font> between <b><font color= blue>-100 to 100</b></font>:</html>", "Prompt", JOptionPane.PLAIN_MESSAGE);
							double y = Double.parseDouble(y_s);
							
							while(y<-100 || y>100)
							{
								y_s = (String) JOptionPane.showInputDialog(null, 
										"<html>Please enter a valid translate factor <html><b><font color= blue>for y-axis</b></font> between <b><font color= blue>-100 to 100</b></font>:</html>", "Prompt", JOptionPane.PLAIN_MESSAGE);
								y = Double.parseDouble(y_s);
							}

							if (!(y < -100 || y > 100)) 
							{
								if (MainControl.drawings.contains(MainControl.selected))
									MainControl.selected.translate(x, y);
								else
									for (Drawing d : MainControl.drawings)
										d.translate(x, y);
								MainControl.drawingArea.repaint();
							}
						}
					} 
					catch (Exception ne) 
					{
						
					}
				}
				else
					JOptionPane.showMessageDialog(null, new JLabel("<html><font size = 5>Could not apply translation on an empty drawing. "
							+ "<br><br> Please draw something in the drawing panel to use this option.</html>")
							, "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		extras.add(translate);
	}

	/********private method that scales the drawing objects ********/
	private void init_scale() 
	{
		scale = new JButton(new ImageIcon("images/main-tool-bar-images/scale.png"));
		scale.setBackground(Color.WHITE);
		scale.setToolTipText("Scale a drawing with X and Y factor.");
		scale.setFont(FONT);
		//add Listener
		scale.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{

				if (!MainControl.drawings.isEmpty()) 
				{
					try 
					{
						JPanel panel = new JPanel();
						panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

						JLabel label = new JLabel("<html><font size = 5><b><u>Tip</u>: This option let you scale every drawing and shape given the x and y scaling factors. <br>You can also select "
								+ "individual drawing or shape using the <u><i>select pointer</i></u> and apply the scaling.</br></html>");

						JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");

						
						if (choice_scale )
							;
						else 
							
						{
							panel.add(label);
							panel.add(box);

							JOptionPane.showMessageDialog(null, panel, "", JOptionPane.PLAIN_MESSAGE);

							if (box.isSelected())
								choice_scale = true;
							else
								choice_scale = false;
						}
						
						String x_s = (String) JOptionPane.showInputDialog(null, 
								"Enter scale factor for x-axis [0-5]:", "Prompt", JOptionPane.PLAIN_MESSAGE);
						
						double x = Double.parseDouble(x_s);

						if (!(x < 0 || x > 5))
						{
							String y_s = (String) JOptionPane.showInputDialog(null, 
									"Enter scale factor for y-axis [0-5]:", "Prompt", JOptionPane.PLAIN_MESSAGE);
							double y = Double.parseDouble(y_s);

							if (!(y < 0 || y > 5)) {
								if (MainControl.drawings.contains(MainControl.selected))
									MainControl.selected.scale(x, y);
								else 
								{
									for (Drawing d : MainControl.drawings) 
									{
										d.scale(x, y);
									}
								}
								MainControl.drawingArea.repaint();
							}
						}
					} 
					catch (Exception ne) 
					{
						
					}
				}

				else
					JOptionPane.showMessageDialog(null,
							new JLabel("<html><font size = 5>Could not apply scaling on an empty drawing. "
									+ "<br><br> Please draw something in the drawing panel to use this option.</html>"), "Error", JOptionPane.ERROR_MESSAGE);
			}
		});

		extras.add(scale);
	}

	
	/********private method that creates a new picture ********/
	private void init_new_shortchut() 
	{
		new_shortcut = new JButton(new ImageIcon("images/main-tool-bar-images/new.png"));
		new_shortcut.setBackground(Color.WHITE);
		new_shortcut.setToolTipText("Create a new picture.");
		new_shortcut.setFont(FONT);
		//add Listener
		new_shortcut.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				FileOperations.newDrawing();
			}
		});

		shortcut_menu.add(new_shortcut);
	}
	
	/********private method that opens a new picture ********/
	private void init_open_shortchut() 
	{
		open_shortcut = new JButton(new ImageIcon("images/main-tool-bar-images/open.png"));
		open_shortcut.setBackground(Color.WHITE);
		open_shortcut.setToolTipText("Open a new picture.");
		open_shortcut.setFont(FONT);
		//add Listener
		open_shortcut.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				FileOperations.openDrawing();
			}
		});

		shortcut_menu.add(open_shortcut);
	}

	/********private method that saves the current picture ********/
	private void init_save_shortchut() 
	{
		save_shortcut = new JButton(new ImageIcon("images/main-tool-bar-images/save.png"));
		save_shortcut.setBackground(Color.WHITE);
		save_shortcut.setToolTipText("Save the current picture.");
		save_shortcut.setFont(FONT);
		//add Listener
		save_shortcut.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				FileOperations.saveDrawing(false, false);
			}
		});

		shortcut_menu.add(save_shortcut);
	}

	/********private method that saves the current picture as a new file ********/
	private void init_save_as_shortchut() 
	{
		save_as_shortcut = new JButton(new ImageIcon("images/main-tool-bar-images/save_as.png"));
		save_as_shortcut.setBackground(Color.WHITE);
		save_as_shortcut.setToolTipText("Save the current picture as a new file.");
		save_as_shortcut.setFont(FONT);
		//add Listener
		save_as_shortcut.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				FileOperations.saveDrawing(false, true);
			}
		});

		shortcut_menu.add(save_as_shortcut);
	}

	/********private method that selects a drawing object ********/
	private void init_pointer() 
	{
		pointer = new JButton(new ImageIcon("images/main-tool-bar-images/pointer.png"));
		pointer.setBackground(Color.WHITE);
		pointer.setToolTipText("Select drawing.");
		pointer.setFont(FONT);
		//add Listener
		pointer.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				//EraserToolbar.bool = false;
				MainControl.mode = MainControl.Mode.SELECT;
				draw.setIcon(new ImageIcon("images/main-tool-bar-images/pencil.png"));
				shapes.setIcon(new ImageIcon("images/main-tool-bar-images/shapes.png"));
			}
		});

		modeGroup.add(pointer);
		edit.add(pointer);
	}

	/********private method to draw line and free hand drawings ********/
	private void init_draw()
	{
		draw = new JButton(new ImageIcon("images/main-tool-bar-images/pencil.png"));
		draw.setBackground(Color.WHITE);
		draw.setToolTipText("Draw.");
		draw.setFont(FONT);

		// modeGroup.add(draw);
		edit.add(draw);
	}

	/********private method to add text ********/
	private void init_text() 
	{
		text = new JButton(new ImageIcon("images/main-tool-bar-images/text.png"));
		text.setBackground(Color.WHITE);
		text.setToolTipText("Add a text.");
		text.setFont(FONT);
		//add Listener
		text.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{//EraserToolbar.bool = false;
				MainControl.drawingArea.connect_drag();
				MainControl.mode = MainControl.Mode.TEXT;
				new TextPane();
				change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
				change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
			}
		});

		edit.add(text);
	}

	/********private method to draw shape objects ********/
	private void init_shapes() 
	{
		shapes = new JButton(new ImageIcon("images/main-tool-bar-images/shapes.png"));
		shapes.setBackground(Color.WHITE);
		shapes.setToolTipText("Draw a shape.");
		shapes.setFont(FONT);

		edit.add(shapes);
	}

	/********private method that rotates the drawing objects ********/
	private void init_rotate() 
	{
		rotate = new JButton(new ImageIcon("images/main-tool-bar-images/rotate.png"));
		rotate.setBackground(Color.WHITE);
		rotate.setToolTipText("Rotate drawing.");
		rotate.setFont(FONT);

		extras.add(rotate);
	}

	/********private method delete a drawing object ********/
	private void init_delete() 
	{
		del = new JButton(new ImageIcon("images/main-tool-bar-images/eraser.png"));
		del.setBackground(Color.WHITE);
		del.setToolTipText("Erase drawing.");
		del.setFont(FONT);
		//add Listener
		del.addMouseListener(new MouseAdapter() 
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
						undo.setEnabled(false);
					}

					MainControl.mode = MainControl.mode.SELECT;
					MainControl.selected = null;

					MainControl.drawingArea.repaint();

				} 
				else
				{
					JOptionPane.showMessageDialog(null, "<html><font size = 5>Please select a drawing or shape using the <u><i>select pointer</i></u> in order to delete.</html>", 
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		edit.add(del);
	}

	/********private method to undo the last command ********/
	private void init_undo() 
	{
		undo = new JButton(new ImageIcon("images/main-tool-bar-images/undo.png"));
		undo.setBackground(Color.WHITE);
		undo.setToolTipText("Undo the last command.");
		undo.setFont(FONT);

		undo.setEnabled(false);
		//add Listener
		undo.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{

				if (MainControl.drawings.isEmpty()) 														
				{
					undo.setEnabled(false);
					redo.setEnabled(false);
				}

				else 
				{
					if (MainControl.drawings.size() == 1) 
					{
						contents.add(MainControl.drawings.get(MainControl.drawings.size() - 1));
						MainControl.drawings.remove(MainControl.drawings.size() - 1);
						MainControl.drawingArea.repaint();

						redo.setEnabled(true);
						undo.setEnabled(false);
					} 
					else
					{
						contents.add(MainControl.drawings
								.get(MainControl.drawings.size() - 1));
						MainControl.drawings.remove(MainControl.drawings.size() - 1);
						MainControl.drawingArea.repaint();

						redo.setEnabled(true);
						undo.setEnabled(true);
					}
				}
			}
		});

		edit.add(undo);
	}

	/********private method to redo the last command ********/
	private void init_redo() 
	{
		redo = new JButton(new ImageIcon("images/main-tool-bar-images/redo.png"));
		redo.setBackground(Color.WHITE);
		redo.setToolTipText("Redo the last command.");
		redo.setFont(FONT);

		redo.setEnabled(false);
		//add Listener
		redo.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{

				if (contents.isEmpty()) // && toggle == 1
				{
					redo.setEnabled(false);
				} 
				else 
				{
					if (contents.size() == 1) 
					{
						MainControl.drawings.add(contents.get(contents.size() - 1));
						contents.remove(contents.size() - 1);
						MainControl.drawingArea.repaint();

						redo.setEnabled(false);

						undo.requestFocus();
						undo.setEnabled(true);
					}
					else 
					{
						MainControl.drawings.add(contents.get(contents.size() - 1));
						contents.remove(contents.size() - 1);
						MainControl.drawingArea.repaint();

						redo.setEnabled(true);
						undo.setEnabled(true);
					}
				}
			}
		});

		edit.add(redo);
	}

	/********private method that changes the fill color of a shape object ********/
	private void init_fill_shapes()
	{
		fill_color = new JButton(new ImageIcon("images/main-tool-bar-images/fill_color.png"));
		fill_color.setBackground(Color.WHITE);
		fill_color.setToolTipText("Choose Fill color.");
		fill_color.setFont(FONT);
		//add Listener
		fill_color.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{

				if (MainControl.selected == null)
					JOptionPane.showMessageDialog(null, "<html><font size = 5>Please select a drawing or shape to change the fill color.</html>",
							"Error", JOptionPane.ERROR_MESSAGE);
				else 
				{
					Color newColor = JColorChooser.showDialog(null, "Choose fill color for drawings and shapes.", null);

					MainControl.fill = newColor;

					if (MainControl.selected != null && MainControl.selected instanceof ShapeDrawing) 
					{
						ShapeDrawing shape = (ShapeDrawing) MainControl.selected;

						shape.setStyle(MainControl.fill, shape.getStyle().getLineColor(), new BasicStroke(MainControl.stroke));
						MainControl.drawingArea.repaint();
						MainControl.fill = Color.WHITE;
					}
				}
			}
		});

		extras.add(fill_color);
	}

	/********private method that changes the line color of a shape object ********/
	private void init_line() 
	{
		line_color = new JButton(new ImageIcon("images/main-tool-bar-images/line_color.png"));
		line_color.setBackground(Color.WHITE);
		line_color.setToolTipText("Choose Line color.");
		line_color.setFont(FONT);
		//add Listener
		line_color.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent ae) 
			{
/*				MainControl.mode = MainControl.Mode.SELECT;
				line_color.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));*/
				
				
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

				JLabel label = new JLabel("<html><font size = 5><b><u>Tip</u>: This option let you change the line color for new drawings and shapes. You can also select "
						+ "<br>individual drawing or shape using the <u><i>select pointer</i></u> and apply the line color change.</br></html>");

				JCheckBox box = new JCheckBox("<html><font size = 5> <font color = Blue>Remember my decision.</html>");

				
				if (choice_line_color);
				else 
					
				{
					panel.add(label);
					panel.add(box);

					JOptionPane.showMessageDialog(null, panel, "", JOptionPane.PLAIN_MESSAGE);

					if (box.isSelected())
						choice_line_color = true;
					else
						choice_line_color = false;
				}
				Color newColor = JColorChooser.showDialog(null, "Choose a line color.", null);

				MainControl.line = newColor;

				if (MainControl.selected != null && MainControl.selected instanceof ShapeDrawing) 
				{
					ShapeDrawing shape = (ShapeDrawing) MainControl.selected;
					shape.setStyle(shape.getStyle().getFillColor(), MainControl.line, new BasicStroke(MainControl.stroke));
					MainControl.drawingArea.repaint();
				}
			}
		});

		extras.add(line_color);
	}

	/********public method that sets the image icon********/
	public static void change_pencil_image(ImageIcon img) 
	{
		draw.setIcon(img);
	}

	/********public method that sets the image icon********/
	public static void change_shapes_image(ImageIcon img) 
	{
		shapes.setIcon(img);
	}
}