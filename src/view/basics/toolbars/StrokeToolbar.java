package view.basics.toolbars;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.primary.MainControl;
import controller.secondary.drawings.ShapeDrawing;

/********The StrokeToolBar class *******/
public class StrokeToolbar extends JToolBar 
{
	/********private variables *********/
	private static final long serialVersionUID = 1L;
	private final Color BACKGROUND = new Color(230, 230, 230);
	private static JSlider strokeSlider;
	private JPanel p;
	
	
	 /********public constructor *********/
	public StrokeToolbar() 
	{

		setFloatable(false);
		setLayout(new BorderLayout());
		setOpaque(true);
		
		p = new JPanel();

		add(p, BorderLayout.CENTER);
		init_slider();

		this.setBackground(BACKGROUND);
		this.setFloatable(false);
		this.setRollover(true);
	}

	/********private method that shows the slider label for the stroke size ********/
	private static void showLabel(JSlider slider, Map<Integer, Integer> values) 
	{
		Hashtable<Integer, JLabel> label = new Hashtable<Integer, JLabel>();
		JLabel jLabel = new JLabel(values.get(slider.getValue()).toString());
		label.put(slider.getValue(), jLabel);
		slider.setLabelTable(label);
		slider.setPaintLabels(true);
	}
	
	public static void resetSlider()
	{
		strokeSlider.setValue(0);
	}
	
	/********private method that creates the slider for the stroke size ********/
	private void init_slider()
	{
		strokeSlider = new JSlider(JSlider.HORIZONTAL, 0, 19, 3);
		strokeSlider.setBackground(BACKGROUND);
		
		final Map<Integer, Integer> zoomValues = new HashMap<Integer, Integer>();
		zoomValues.put(0, 1);
		zoomValues.put(1, 2);
		zoomValues.put(2, 3);
		zoomValues.put(3, 4);
		zoomValues.put(4, 5);
		zoomValues.put(5, 6);
		zoomValues.put(6, 7);
		zoomValues.put(7, 8);
		zoomValues.put(8, 9);
		zoomValues.put(9, 10);
		
		zoomValues.put(10, 11);
		zoomValues.put(11, 12);
		zoomValues.put(12, 13);
		zoomValues.put(13, 14);
		zoomValues.put(14, 15);
		zoomValues.put(15, 16);
		zoomValues.put(16, 17);
		zoomValues.put(17, 18);
		zoomValues.put(18, 19);
		zoomValues.put(19, 20);
		
		strokeSlider.setValue(9);


		strokeSlider.setMajorTickSpacing(1);
		strokeSlider.setPaintTicks(true);
		strokeSlider.setSnapToTicks(true);
		
		showLabel(strokeSlider, zoomValues);
		// add Listener
		strokeSlider.addChangeListener(new ChangeListener() 
		{
			@Override
			public void stateChanged(ChangeEvent arg0) 
			{
				showLabel(strokeSlider, zoomValues);
				MainControl.stroke = zoomValues.get(strokeSlider.getValue());
				if (MainControl.selected != null && MainControl.selected instanceof ShapeDrawing) 
				{
					ShapeDrawing shape = (ShapeDrawing) MainControl.selected;
					shape.getStyle().setStroke(new BasicStroke(MainControl.stroke));
					MainControl.drawingArea.repaint();
				}
			}
		});
		
		p.setBorder(new TitledBorder("Stroke size"));
		p.add(strokeSlider);
	}
}
