package view.basics.toolbars;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.primary.MainControl;
import controller.secondary.drawings.Drawing;



public class RotateToolBar extends JToolBar {

	private final Color BACKGROUND = new Color(230, 230, 230);
	private JButton rotate_right;
	private JButton rotate_left;
	private JButton flip_vertical;
	private JButton flip_horizontal;

	public RotateToolBar(JButton invoker) {

		setFloatable(false);
		setLayout(new GridLayout(4, 1, 3, 3));

		init_all();

		this.setBackground(BACKGROUND);
		this.setFloatable(false);
		this.setRollover(true);

	}

	private void init_all() {
		init_rotate_right();
		init_rotate_left();
		init_flip_horizontal();
		init_flip_vertical();

	}

	private void init_rotate_right() {
		rotate_right = new JButton();
		rotate_right
				.setText("<html><font size = 4><b>Rotate right by 90\u00B0</b></html>");

		rotate_right.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (MainControl.drawings.contains(MainControl.selected))
						MainControl.selected.rotate(Math.PI / 2);
					else {
						for (Drawing d : MainControl.drawings) {
							d.rotate(Math.PI / 2);
						}
					}
					MainControl.drawingArea.repaint();
				} catch (Exception e1) {
				}
			}

		});

		add(rotate_right);
	}

	private void init_rotate_left() {
		rotate_left = new JButton();
		rotate_left
				.setText("<html><font size = 4><b>Rotate left by 90\u00B0</b></html>");

		rotate_left.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (MainControl.drawings.contains(MainControl.selected))
						MainControl.selected.rotate(-Math.PI / 2);
					else {
						for (Drawing d : MainControl.drawings) {
							d.rotate(-Math.PI / 2);
						}
					}
					MainControl.drawingArea.repaint();

				} catch (Exception e1) {
				}
			}

		});

		add(rotate_left);
	}

	private void init_flip_horizontal() {
		flip_horizontal = new JButton();
		flip_horizontal
				.setText("<html><font size = 4><b>Flip horizontally</b></html>");

		flip_horizontal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					if (MainControl.drawings.contains(MainControl.selected))
						MainControl.selected.reflectHorizontal();
					else {
						for (Drawing d : MainControl.drawings) {
							d.reflectHorizontal();
						}
					}
					MainControl.drawingArea.repaint();
				}
				catch(Exception e1){}
			

			}

		});

		add(flip_horizontal);
	}

	private void init_flip_vertical() {
		flip_vertical = new JButton();
		flip_vertical
				.setText("<html><font size = 4><b>Flip vertically</b></html>");

		flip_vertical.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try
				{
					if (MainControl.drawings.contains(MainControl.selected))
						MainControl.selected.reflectVertical();
					else {
						for (Drawing d : MainControl.drawings) {
							d.reflectVertical();
						}
					}
					MainControl.drawingArea.repaint();
				}
				catch(Exception e1){}


			}

		});

		add(flip_vertical);
	}
}