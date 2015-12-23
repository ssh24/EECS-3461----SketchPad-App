package view.basics.toolbars;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.primary.MainControl;


/********The DrawingToolBar class *******/
public class DrawingToolBar extends JToolBar 
{
	 /********Variables *********/
	private static final long serialVersionUID = 1L;
	public static JButton draw_line;
	private final Color BACKGROUND = new Color(230, 230, 230);
	public static JButton free_hand;

	
	 /********public constructor *********/
	public DrawingToolBar(JButton invoker) 
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
		init_draw_line();
		init_free_hand_draw();
	}

	private void init_draw_line() 
	{
		draw_line = new JButton();
		draw_line.setIcon(new ImageIcon("images/draw-tool-bar-images/draw_line.png"));
		draw_line.setBackground(Color.WHITE);
		draw_line.setToolTipText("Draw a line.");
		// adds Listener
		draw_line.addMouseListener(new MouseAdapter() 
		{

			@Override
			public void mouseClicked(MouseEvent me) 
			{
				if(EraserToolbar.bool)
				{
					MainControl.line = EraserToolbar.prev_color;
					MainControl.drawingArea.connect_drag();
					getParent().setVisible(false);
					MainControl.mode = MainControl.Mode.LINE;
					
					ToolBar.change_pencil_image(new ImageIcon("images/draw-tool-bar-images/draw_line.png"));
					ToolBar.change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
					//EraserToolbar.bool = false;
				}
				else
				{
					MainControl.drawingArea.connect_drag();
					getParent().setVisible(false);
					MainControl.mode = MainControl.Mode.LINE;
					ToolBar.change_pencil_image(new ImageIcon("images/draw-tool-bar-images/draw_line.png"));
					ToolBar.change_shapes_image(new ImageIcon("images/main-tool-bar-images/shapes.png"));
				}
				
			}
		});

		add(draw_line);
	}

	private void init_free_hand_draw() 
	{
		free_hand = new JButton(new ImageIcon("images/draw-tool-bar-images/freehand_draw.png"));
		free_hand.setBackground(Color.WHITE);
		free_hand.setToolTipText("Free hand drawing.");
		// adds Listener
		free_hand.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent me) 
			{
				if(EraserToolbar.bool)
				{
					MainControl.line = EraserToolbar.prev_color;
					MainControl.drawingArea.connect_drag();
					getParent().setVisible(false);
					MainControl.mode = MainControl.Mode.FREEHAND;
					ToolBar.change_pencil_image(new ImageIcon("images/draw-tool-bar-images/freehand_draw.png"));
					//EraserToolbar.bool = false;
				}
				else
				{
					MainControl.drawingArea.connect_drag();
					getParent().setVisible(false);
					MainControl.mode = MainControl.Mode.FREEHAND;
					ToolBar.change_pencil_image(new ImageIcon("images/draw-tool-bar-images/freehand_draw.png"));
				}
				
			}

		});
		add(free_hand);
	}
}
