package view.basics.toolbars;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.primary.MainControl;

public class ShapesToolBar extends JToolBar {
	
	private final Color BACKGROUND = new Color(230, 230, 230);
	private JButton rectangle;
	private JButton round_rectangle;
	private JButton triangle;
	private JButton circle;
	private JButton customized;
	private JButton polygon;
	
	public ShapesToolBar(JButton invoker) {

		setFloatable(false);
		setLayout(new GridLayout(1, 3, 3, 3));

		init_all();

		this.setBackground(BACKGROUND);
		this.setFloatable(false);
		this.setRollover(true);

	}
	
	private void init_all() {
		init_rectangle();
		init_circle();
		init_polygon();
		
	}
	
	private void init_rectangle()
	{
		rectangle = new JButton();
		rectangle.setIcon(new ImageIcon("images/shape-tool-bar-images/rectangle.png"));
		rectangle.setToolTipText("Draw a rectangle.");
		
		rectangle.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				MainControl.drawingArea.connect_drag();
				getParent().setVisible(false);
				MainControl.mode = MainControl.Mode.RECTANGLE;
				ToolBar.change_shapes_image(new ImageIcon(
						"images/shape-tool-bar-images/rectangle.png"));
				ToolBar.change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
			}
		});
		
		add(rectangle);
	}
	
	private void init_polygon()
	{
		polygon = new JButton();
		polygon.setIcon(new ImageIcon("images/shape-tool-bar-images/polygon.png"));
		polygon.setToolTipText("Draw a custom shape.");
		
		polygon.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				MainControl.drawingArea.connect_drag();
				getParent().setVisible(false);
				MainControl.mode = MainControl.Mode.POLYGON;
				ToolBar.change_shapes_image(new ImageIcon(
						"images/shape-tool-bar-images/polygon.png"));
				ToolBar.change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
			}
		});
		
		add(polygon);
	}
	
	private void init_circle()
	{
		circle = new JButton();
		circle.setIcon(new ImageIcon("images/shape-tool-bar-images/circle.png"));
		circle.setToolTipText("Draw a circle.");
		
		circle.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent me)
			{
				MainControl.drawingArea.connect_drag();
				getParent().setVisible(false);
				MainControl.mode = MainControl.Mode.OVAL;
				ToolBar.change_shapes_image(new ImageIcon(
						"images/shape-tool-bar-images/circle.png"));
				ToolBar.change_pencil_image(new ImageIcon("images/main-tool-bar-images/pencil.png"));
			}
		});
		
		add(circle);
	}
}
