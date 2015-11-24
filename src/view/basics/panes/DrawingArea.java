package view.basics.panes;


import helper.handlers.*;
import helper.extras.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

import view.basics.toolbars.*;
import controller.primary.MainControl;
import controller.secondary.drawings.Drawing;

public class DrawingArea extends JPanel implements MouseListener,
		MouseMotionListener {

	public DrawingArea() {
		setBackground(Color.WHITE);
		setPreferredSize(MainControl.size);
		DrawingListener dl = new DrawingListener(this);
		addMouseListener(dl);
		addMouseMotionListener(dl);
	}

	public void zoom() {
		int width = (int) (MainControl.zoom * MainControl.size.width);
		int height = (int) (MainControl.zoom * MainControl.size.height);
		setPreferredSize(new Dimension(width, height));
		getParent().setPreferredSize(new Dimension(width + 50, height + 50));
		revalidate();
		repaint();
	}

	public void connect_drag() {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void paint(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).scale(MainControl.zoom, MainControl.zoom);
		paintBacking(g);
		paintDrawing(g);
		paintGrid(g);
		paintPreview(g);
		paintSelection(g);
	}

	private void paintBacking(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
/*		if (MainControl.isOpaque) {
			setBackground(Color.WHITE);
		}*/
		g2d.dispose();
	}

	private void paintDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// ToolBar.undo.setEnabled(true);
		for (Drawing d : MainControl.drawings) {
			d.draw(g2d);
		}
		g2d.dispose();
	}

	private void paintGrid(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (MainControl.isGrid) {
			UIToolbox.drawGrid(g2d);
		}
		g2d.dispose();
	}

	private void paintPreview(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (MainControl.preview != null) {
			g2d.setComposite(AlphaComposite.SrcOver.derive(0.5f));
			MainControl.preview.draw(g2d);
			g2d.setColor(Color.WHITE);
			g2d.draw(MainControl.preview.getBounds2D());
		}
		g2d.dispose();
	}

	private void paintSelection(Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		if (MainControl.selected != null) {
			Rectangle2D bound = MainControl.selected.getBounds2D();
			bound = new Rectangle2D.Double(bound.getX() - 5, bound.getY() - 5,
					bound.getWidth() + 10, bound.getHeight() + 10);
			g2d.setColor(new Color(0,163,0));
			g2d.draw(bound);
		}
		g2d.dispose();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		/*System.out.println(MainControl.drawings.isEmpty());
		if (!MainControl.drawings.isEmpty()) 
		{
			ToolBar.undo.setEnabled(true);
		} 
		else {
			ToolBar.redo.setEnabled(false);
			MainControl.drawings.clear();
			ToolBar.contents.clear();
			ToolBar.erase_list.clear();
		}*/
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		/*if (!MainControl.drawings.isEmpty() && (MainControl.mode == MainControl.Mode.LINE || MainControl.mode == MainControl.Mode.FREEHAND))
		{
			ToolBar.undo.setEnabled(true);
			ToolBar.redo.setEnabled(false);
			ToolBar.contents.clear();
		}
		else if(MainControl.mode == MainControl.Mode.LINE || MainControl.mode == MainControl.Mode.FREEHAND)
		{
				ToolBar.redo.setEnabled(false);
				MainControl.drawings.clear();
				ToolBar.contents.clear();
				ToolBar.erase_list.clear();
		}*/

	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		if (!MainControl.drawings.isEmpty() && (MainControl.mode == MainControl.Mode.LINE || MainControl.mode == MainControl.Mode.FREEHAND || MainControl.mode == 
				MainControl.mode.OVAL || MainControl.mode == 
				MainControl.mode.RECTANGLE || MainControl.mode == 
				MainControl.mode.POLYGON || MainControl.mode == MainControl.Mode.IMAGE || MainControl.mode == MainControl.Mode.TEXT))
		{
			ToolBar.undo.setEnabled(true);
			ToolBar.redo.setEnabled(false);
			ToolBar.contents.clear();
		}
		else if(MainControl.mode == MainControl.Mode.LINE || MainControl.mode == MainControl.Mode.FREEHAND || MainControl.mode == 
				MainControl.mode.OVAL || MainControl.mode == 
				MainControl.mode.RECTANGLE || MainControl.mode == 
				MainControl.mode.POLYGON || MainControl.mode == MainControl.Mode.IMAGE || MainControl.mode == MainControl.mode.TEXT)
		{
				ToolBar.redo.setEnabled(false);
				MainControl.drawings.clear();
				ToolBar.contents.clear();
				ToolBar.erase_list.clear();
		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		/*if (!MainControl.drawings.isEmpty())
			ToolBar.undo.setEnabled(true);*/

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

} // DrawingArea
