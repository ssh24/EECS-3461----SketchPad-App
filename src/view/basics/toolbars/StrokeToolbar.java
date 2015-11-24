package view.basics.toolbars;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.primary.MainControl;

public class StrokeToolbar extends JToolBar {
	
	private final Color BACKGROUND = new Color(230, 230, 230);
	private JSlider strokeSlider;
	private JPanel p;
	
	public StrokeToolbar() {

		setFloatable(false);
		setLayout(new BorderLayout());
		
		p = new JPanel();

		add(p, BorderLayout.CENTER);
		init_slider();

		this.setBackground(BACKGROUND);
		this.setFloatable(false);
		this.setRollover(true);
	}

	private static void showLabel(JSlider slider, Map<Integer, Integer> values) {

		Hashtable<Integer, JLabel> label = new Hashtable<Integer, JLabel>();
		JLabel jLabel = new JLabel(values.get(slider.getValue()).toString());
		label.put(slider.getValue(), jLabel);
		slider.setLabelTable(label);
		slider.setPaintLabels(true);
	}
	
	private void init_slider()
	{
		strokeSlider = new JSlider(JSlider.HORIZONTAL, 0, 5, 3);
		strokeSlider.setBackground(BACKGROUND);
		
		final Map<Integer, Integer> zoomValues = new HashMap<Integer, Integer>();
		zoomValues.put(0, 1);
		zoomValues.put(1, 2);
		zoomValues.put(2, 3);
		zoomValues.put(3, 4);
		zoomValues.put(4, 5);
		zoomValues.put(5, 6);
		
		strokeSlider.setValue(0);


		strokeSlider.setMajorTickSpacing(1);
		strokeSlider.setPaintTicks(true);
		strokeSlider.setSnapToTicks(true);
		
		showLabel(strokeSlider, zoomValues);
		
		strokeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				showLabel(strokeSlider, zoomValues);
				MainControl.stroke = zoomValues.get(strokeSlider.getValue());
			}
		});
		
		p.setBorder(new TitledBorder("Stroke size"));
		p.add(strokeSlider);
	}
	
	
}
