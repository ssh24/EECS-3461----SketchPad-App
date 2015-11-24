package view.basics.toolbars;

import helper.extras.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.basics.panes.*;
import controller.primary.MainControl;
import controller.secondary.drawings.*;

/**
 * This class constructs the main toolbar for the application window.
 */
public class ToolBar extends JToolBar {

	private Map<String, AbstractButton> buttons = new HashMap<String, AbstractButton>();

	private JButton new_shortcut, open_shortcut, save_shortcut,
			save_as_shortcut;

	private JPanel shortcut_menu;

	private final Font FONT = new Font("Calibri", Font.PLAIN, 20);
	private final Color BACKGROUND = new Color(230, 230, 230);

	public static ArrayList<Drawing> contents = new ArrayList<Drawing>();
	public static ArrayList<Drawing> erase_list = new ArrayList<Drawing>();

	private ArrayList<Integer> index_list = new ArrayList<Integer>();
	private int toggle = 0;


	private boolean choice = false;

	private int mode;

	private JButton pointer;

	public static JButton undo;

	public static JButton redo;

	private JPanel edit;

	private JButton rotate;

	private static JButton draw;

	public static JButton del;

	private JPanel extras_menu;

	private JPanel extras;

	private JButton fill_color;

	private JButton line_color;

	private JButton zoom_out;

	private JButton zoom_in;

	private ButtonGroup modeGroup = new ButtonGroup();

	private JScrollBar scrollbar;

	private JTextField scrollbarValue;

	private JPanel extras_north;

	private JPanel extras_south;

	private JLabel label;

	private int count;

	private JButton text;

	private static JButton shapes;

	private JButton images;

	private JButton zoomIn;

	private JButton zoomOut;

	private JSlider zoomSlider;

	private int MAIN_INDEX;

	private JButton crop;

	private JButton translate;

	private JButton scale;

	private JPanel extras_center;

	private JButton fill_background;

	private JFrame frame;

	private FileOperations fo;

	private JButton stroke;

	public ToolBar(JFrame main) {

		frame = main;
		 fo = new FileOperations(frame);
		setFloatable(false);
		setLayout(new GridLayout(1, 3));

		shortcut_menu = new JPanel();
		shortcut_menu.setLayout(new GridLayout(2, 2, 5, 5));
		shortcut_menu.setBorder(BorderFactory.createTitledBorder(
				new EtchedBorder(),
				"<html><font size = 6>Shortcut Menu</html>",
				TitledBorder.CENTER, 0));
		shortcut_menu.setBackground(BACKGROUND);

		edit = new JPanel();
		edit.setLayout(new GridLayout(3, 3, 5, 5));
		edit.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(),
				"<html><font size = 6>Tools<html>", TitledBorder.CENTER, 0));
		edit.setBackground(BACKGROUND);

		extras = new JPanel();
		extras.setLayout(new GridLayout(2, 3, 5, 5));
		extras.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(),
				"<html><font size = 6>Extras</html>", TitledBorder.CENTER, 0));
		extras.setBackground(BACKGROUND);

		init_all();

		add(shortcut_menu);
		add(edit);
		add(extras);

		// popups
		JToolBar drawingModePane = new DrawingToolBar(draw);
		JToolBar shapesModePane = new ShapesToolBar(shapes);
		JToolBar rotateModePane = new RotateToolBar(shapes);
		JToolBar sliderModePane = new StrokeToolbar();

		UIToolbox.attachPopupPane(draw, drawingModePane, null,
				PopupPane.CENTER, false);

		UIToolbox.attachPopupPane(shapes, shapesModePane, null,
				PopupPane.CENTER, false);

		UIToolbox.attachPopupPane(rotate, rotateModePane, null,
				PopupPane.CENTER, false);
		
		UIToolbox.attachPopupPane(stroke, sliderModePane, null,
				PopupPane.CENTER, false);

		this.setBackground(BACKGROUND);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setFloatable(false);
		this.setRollover(true);
	}

	private void init_all() {
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

		/*
		 * extras.add(extras_north, BorderLayout.NORTH);
		 * extras.add(extras_south, BorderLayout.SOUTH);
		 */
		// init_zoom_out();
	}

	private void init_fill_background() {
		fill_background = new JButton(new ImageIcon(
				"images/main-tool-bar-images/fill_background.png"));

		fill_background.setBackground(Color.WHITE);
		fill_background.setToolTipText("Choose Background Color.");
		fill_background.setFont(FONT);

		fill_background.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Color newColor = JColorChooser.showDialog(null,
						"Choose background color.", Color.WHITE);

				if (newColor != null)
					MainControl.drawingArea.setBackground(newColor);
				else;
			}

		});

		extras.add(fill_background);
	}

	private void init_stroke() {
		stroke = new JButton(
				new ImageIcon("images/main-tool-bar-images/stroke.png"));
		stroke.setBackground(Color.WHITE);
		stroke.setToolTipText("Change stroke width.");
		stroke.setFont(FONT);

		stroke.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
				change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
			}
			
		});
		
		edit.add(stroke);
	}

	private void init_translate() {
		translate = new JButton(new ImageIcon(
				"images/main-tool-bar-images/translate.png"));
		translate.setBackground(Color.WHITE);
		translate.setToolTipText("Translate a drawing with X and Y factor.");
		translate.setFont(FONT);

		translate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!MainControl.drawings.isEmpty()) {
					try {
						String x_s = (String) JOptionPane
								.showInputDialog(
										null,
										"Enter translate factor for x-axis [-100 to 100]:",
										"Prompt", JOptionPane.PLAIN_MESSAGE);
						double x = Double.parseDouble(x_s);

						if (!(x < -100 || x > 100)) {
							String y_s = (String) JOptionPane
									.showInputDialog(
											null,
											"Enter translate factor for y-axis [-100 to 100]:",
											"Prompt", JOptionPane.PLAIN_MESSAGE);
							double y = Double.parseDouble(y_s);

							if (!(y < -100 || y > 100)) {
								if (MainControl.drawings
										.contains(MainControl.selected))
									MainControl.selected.translate(x, y);
								else
									for (Drawing d : MainControl.drawings)
										d.translate(x, y);
								MainControl.drawingArea.repaint();
							}
						}
					} catch (Exception ne) {
					}
				}

				else
					JOptionPane.showMessageDialog(null,
							"Could not translate a proper image.", "Error",
							JOptionPane.ERROR_MESSAGE);

			}

		});

		extras.add(translate);
	}

	private void init_scale() {
		scale = new JButton(new ImageIcon(
				"images/main-tool-bar-images/scale.png"));
		scale.setBackground(Color.WHITE);
		scale.setToolTipText("Scale a drawing with X and Y factor.");
		scale.setFont(FONT);

		scale.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!MainControl.drawings.isEmpty()) {

					try {
						String x_s = (String) JOptionPane.showInputDialog(null,
								"Enter scale factor for x-axis [0-5]:",
								"Prompt", JOptionPane.PLAIN_MESSAGE);
						double x = Double.parseDouble(x_s);

						if (!(x < 0 || x > 5)) {
							String y_s = (String) JOptionPane.showInputDialog(
									null,
									"Enter scale factor for y-axis [0-5]:",
									"Prompt", JOptionPane.PLAIN_MESSAGE);
							double y = Double.parseDouble(y_s);

							if (!(y < 0 || y > 5)) {
								if (MainControl.drawings
										.contains(MainControl.selected))
									MainControl.selected.scale(x, y);
								else {
									for (Drawing d : MainControl.drawings) {
										d.scale(x, y);
									}
								}
								MainControl.drawingArea.repaint();
							}
						}
					} catch (Exception ne) {
					}
				}

				else
					JOptionPane.showMessageDialog(null,
							"Could not scale a proper image.", "Error",
							JOptionPane.ERROR_MESSAGE);

			}

		});

		extras.add(scale);
	}

	private void init_new_shortchut() {
		new_shortcut = new JButton(new ImageIcon(
				"images/main-tool-bar-images/new.png"));
		new_shortcut.setBackground(Color.WHITE);
		new_shortcut.setToolTipText("Create a new picture.");
		new_shortcut.setFont(FONT);

		new_shortcut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fo.newDrawing();
				//FileOperations.newDrawing();
			}
		});

		shortcut_menu.add(new_shortcut);
	}

	private void init_open_shortchut() {
		open_shortcut = new JButton(new ImageIcon(
				"images/main-tool-bar-images/open.png"));
		open_shortcut.setBackground(Color.WHITE);
		open_shortcut.setToolTipText("Open a new picture.");
		open_shortcut.setFont(FONT);

		open_shortcut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fo.openDrawing();
				//FileOperations.openDrawing();
			}
		});

		shortcut_menu.add(open_shortcut);
	}

	private void init_save_shortchut() {
		save_shortcut = new JButton(new ImageIcon(
				"images/main-tool-bar-images/save.png"));
		save_shortcut.setBackground(Color.WHITE);
		save_shortcut.setToolTipText("Save the current picture.");
		save_shortcut.setFont(FONT);

		save_shortcut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileOperations.saveDrawing(false, false);
			}
		});

		shortcut_menu.add(save_shortcut);
	}

	private void init_save_as_shortchut() {
		save_as_shortcut = new JButton(new ImageIcon(
				"images/main-tool-bar-images/save_as.png"));
		save_as_shortcut.setBackground(Color.WHITE);
		save_as_shortcut
				.setToolTipText("Save the current picture as a new file.");
		save_as_shortcut.setFont(FONT);

		save_as_shortcut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileOperations.saveDrawing(false, true);
			}
		});

		shortcut_menu.add(save_as_shortcut);
	}

	private void init_pointer() {
		pointer = new JButton(new ImageIcon(
				"images/main-tool-bar-images/pointer.png"));
		pointer.setBackground(Color.WHITE);
		pointer.setToolTipText("Select drawing.");
		pointer.setFont(FONT);

		pointer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				MainControl.mode = MainControl.Mode.SELECT;
				draw.setIcon(new ImageIcon(
						"images/main-tool-bar-images/pencil.png"));
				shapes.setIcon(new ImageIcon(
						"images/main-tool-bar-images/shapes.png"));
			}
		});

		modeGroup.add(pointer);
		edit.add(pointer);
	}

	private void init_draw() {
		draw = new JButton(new ImageIcon(
				"images/main-tool-bar-images/pencil.png"));
		draw.setBackground(Color.WHITE);
		draw.setToolTipText("Draw.");
		draw.setFont(FONT);

		// modeGroup.add(draw);
		edit.add(draw);
	}

	private void init_text() {
		text = new JButton(
				new ImageIcon("images/main-tool-bar-images/text.png"));
		text.setBackground(Color.WHITE);
		text.setToolTipText("Add a text.");
		text.setFont(FONT);
		
		text.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e) {
				MainControl.drawingArea.connect_drag();
				MainControl.mode = MainControl.Mode.TEXT;
				new TextPane();
						change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
						change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
			}
			
		});

		edit.add(text);
	}

	private void init_shapes() {
		shapes = new JButton(new ImageIcon(
				"images/main-tool-bar-images/shapes.png"));
		shapes.setBackground(Color.WHITE);
		shapes.setToolTipText("Draw a shape.");
		shapes.setFont(FONT);

		edit.add(shapes);
	}

	private void init_image() {
		images = new JButton(new ImageIcon(
				"images/main-tool-bar-images/insert_image.png"));
		images.setBackground(Color.WHITE);
		images.setToolTipText("Insert an image.");
		images.setFont(FONT);

		images.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * if (!BUTTONS.get("IMAGE").isSelected()) { return; }
				 */
				MainControl.drawingArea.connect_drag();
				MainControl.mode = MainControl.Mode.IMAGE;
				ImageFileChooser imgChooser = new ImageFileChooser();
				// getParent().setVisible(false);
				int returnVal = imgChooser.showOpenDialog((Component) e
						.getSource());

				if (returnVal == ImageFileChooser.APPROVE_OPTION) 
				{
					MainControl.preview = null;
					MainControl.imagePath = imgChooser
							.getSelectedFileWithExtension();

				}
				change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
				change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
			}

		});

		edit.add(images);
	}

	private void init_rotate() {
		rotate = new JButton(new ImageIcon(
				"images/main-tool-bar-images/rotate.png"));
		rotate.setBackground(Color.WHITE);
		rotate.setToolTipText("Rotate drawing.");
		rotate.setFont(FONT);

		extras.add(rotate);
	}

	private void init_delete() {
		del = new JButton(new ImageIcon(
				"images/main-tool-bar-images/eraser.png"));
		del.setBackground(Color.WHITE);
		del.setToolTipText("Erase drawing.");
		del.setFont(FONT);

		del.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {

				if (MainControl.mode == MainControl.Mode.SELECT
						&& MainControl.selected != null) {
					JPanel panel = new JPanel();
					panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

					JLabel label = new JLabel(
							"<html><font size = 5><b>Tip: Using an eraser to delete drawing is permanent. Cannot revert back once the drawing is deleted.<b></html>");

					JCheckBox box = new JCheckBox(
							"<html><font size = 4>Remember my decision.</html>");

					if (choice)
						;
					else {
						panel.add(label);
						panel.add(box);

						JOptionPane.showMessageDialog(null, panel, "",
								JOptionPane.PLAIN_MESSAGE);

						if (box.isSelected())
							choice = true;
						else
							choice = false;
					}

					MainControl.drawings.remove(MainControl.selected);

					MainControl.mode = MainControl.mode.SELECT;
					MainControl.selected = null;

					MainControl.drawingArea.repaint();

				} else {
					JOptionPane.showMessageDialog(null,
							"Please select a drawing inorder to delete.",
							"Error", JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		edit.add(del);
	}

	private void init_undo() {
		undo = new JButton(
				new ImageIcon("images/main-tool-bar-images/undo.png"));
		undo.setBackground(Color.WHITE);
		undo.setToolTipText("Undo the last command.");
		undo.setFont(FONT);

		undo.setEnabled(false);

		undo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (MainControl.drawings.isEmpty()) // do nothing--------- use &&
													// erase_list.isEmpty()
				{
					undo.setEnabled(false);
					redo.setEnabled(false);
				}

				else {
					if (MainControl.drawings.size() == 1) {
						contents.add(MainControl.drawings.get(MainControl.drawings
								.size() - 1));
						MainControl.drawings.remove(MainControl.drawings.size() - 1);
						MainControl.drawingArea.repaint();

						redo.setEnabled(true);
						undo.setEnabled(false);
					} else {
						contents.add(MainControl.drawings.get(MainControl.drawings
								.size() - 1));
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

	private void init_redo() {
		redo = new JButton(
				new ImageIcon("images/main-tool-bar-images/redo.png"));
		redo.setBackground(Color.WHITE);
		redo.setToolTipText("Redo the last command.");
		redo.setFont(FONT);

		redo.setEnabled(false);

		redo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (contents.isEmpty()) // && toggle == 1
				{
					redo.setEnabled(false);
				} else {
					if (contents.size() == 1) {
						MainControl.drawings.add(contents.get(contents.size() - 1));
						contents.remove(contents.size() - 1);
						MainControl.drawingArea.repaint();

						redo.setEnabled(false);

						undo.requestFocus();
						undo.setEnabled(true);
					}
					/*
					 * else if(toggle == 0 && contents.isEmpty()) {
					 * erase_list.add(MainControl.drawings.get(MAIN_INDEX));
					 * index_list.add(MAIN_INDEX);
					 * 
					 * MainControl.drawings.remove(MAIN_INDEX);
					 * 
					 * toggle = 1;
					 * 
					 * MainControl.drawingArea.repaint(); }
					 */
					else {
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

	private void init_fill_shapes() {
		fill_color = new JButton(new ImageIcon(
				"images/main-tool-bar-images/fill_color.png"));
		fill_color.setBackground(Color.WHITE);
		fill_color.setToolTipText("Choose Fill color.");
		fill_color.setFont(FONT);

		fill_color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {

				if (MainControl.selected == null)
					JOptionPane.showMessageDialog(null,
							"Must select a shape to change fill color.",
							"Error", JOptionPane.ERROR_MESSAGE);
				else {
					Color newColor = JColorChooser.showDialog(null,
							"Choose fill color for shapes.", null);

					MainControl.fill = newColor;

					if (MainControl.selected != null
							&& MainControl.selected instanceof ShapeDrawing) {
						ShapeDrawing shape = (ShapeDrawing) MainControl.selected;

						shape.setStyle(MainControl.fill, shape.getStyle()
								.getLineColor(), new BasicStroke(
								MainControl.stroke));
						MainControl.drawingArea.repaint();
						MainControl.fill = Color.WHITE;
					}
				}

			}
		});

		extras.add(fill_color);
	}

	private void init_line() {
		line_color = new JButton(new ImageIcon(
				"images/main-tool-bar-images/line_color.png"));
		line_color.setBackground(Color.WHITE);
		line_color.setToolTipText("Choose Line color.");
		line_color.setFont(FONT);

		line_color.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				Color newColor = JColorChooser.showDialog(null,
						"Choose a line color.", null);

				MainControl.line = newColor;

				if (MainControl.selected != null
						&& MainControl.selected instanceof ShapeDrawing) {
					ShapeDrawing shape = (ShapeDrawing) MainControl.selected;
					shape.setStyle(shape.getStyle().getFillColor(),
							MainControl.line, new BasicStroke(MainControl.stroke));
					MainControl.drawingArea.repaint();
				}

			}
		});

		extras.add(line_color);
	}

	public static void change_pencil_image(ImageIcon img) {
		draw.setIcon(img);
	}

	public static void change_shapes_image(ImageIcon img) {
		shapes.setIcon(img);
	}
}