package view.basics.panes;


import helper.extras.FileOperations;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import view.basics.menu.MenuBar;
import view.basics.toolbars.*;
import controller.primary.MainControl;

public class MainFrame extends JFrame {
	
	public static final String company = " - SketchPad";
	
    public MainFrame(String title) {
        
    	// set menu bar
    	this.setJMenuBar(new MenuBar(this));
    	
    	//  save drawing window listener
        addWindowListener(new WindowAdapter() {
        	
            @Override
            public void windowClosing(WindowEvent e)
            {
                int n = FileOperations.saveDrawing(true, false);
                if (n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        
        manager.addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override 
            public boolean dispatchKeyEvent(KeyEvent e) 
            {
                switch (e.getKeyCode()) 
                {
                    case KeyEvent.VK_ESCAPE:
                        if (MainControl.preview != null) {
                            MainControl.preview = null;
                        }
                        if (MainControl.selected != null) {
                            MainControl.selected = null;
                        }
                        MainControl.drawingArea.repaint();
                        break;
                    case KeyEvent.VK_DELETE:
                        if (MainControl.mode == MainControl.Mode.SELECT && MainControl.selected != null) {
                            MainControl.drawings.remove(MainControl.selected);
                            MainControl.selected = null;
                        }
                        MainControl.drawingArea.repaint();
                        break;
                }
                
                if (e.isControlDown()) 
                {
                    switch (e.getKeyCode()) 
                    {
                        case KeyEvent.VK_N: 
                        	//FileOperations.newDrawing(); 
                        	break;
                        case KeyEvent.VK_O: 
                        	//FileOperations.openDrawing(); 
                        	break;
                        case KeyEvent.VK_S:
                        	//FileOperations.saveDrawing(false, false); 
                        	break;
                    }
                }
                return false;
            }
        });
        getContentPane().add(new ToolBar(this), BorderLayout.NORTH);
        getContentPane().add(new DrawingViewport(), BorderLayout.CENTER);
        getContentPane().add(new ZoomToolBar(), BorderLayout.SOUTH);
        
        
        
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		setBounds(ge.getMaximumWindowBounds());
		
		setTitle(title + company);
		
		setIconImage(new ImageIcon("images/main_logo.png").getImage());
		
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        setVisible(true);
    }
  

    /**
     * This class implements a viewpoint for the
     * drawing area. Allows the user to pan, scroll
     * and zoom in or out of the drawing.
     */
    private static class DrawingViewport extends JScrollPane implements MouseWheelListener {
        private DrawingArea drawingArea = MainControl.drawingArea;
        /**
         * Creates a <code>JScrollPane</code> that displays the
         * contents of the specified
         * component, where both horizontal and vertical scrollbars appear
         * whenever the component's contents are larger than the view.
         *
         * @see #setViewportView
         * @param pane the component to display in the scrollpane's viewport
         */
        public DrawingViewport() {
            Dimension d = drawingArea.getPreferredSize();
            drawingArea.setBackground(Color.WHITE);
            setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            getVerticalScrollBar().setUnitIncrement(25);
            getHorizontalScrollBar().setUnitIncrement(25);
                JPanel inner = new JPanel();
                inner.setLayout(new GridBagLayout());
                inner.setPreferredSize(new Dimension(d.width + 50, d.height + 50));
                inner.add(drawingArea);
            setViewportView(inner);
            addMouseWheelListener(this);
        }
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            JSlider zslider = MainControl.zoomSlider;
            if (e.getPreciseWheelRotation() > 0) {
                zslider.setValue(zslider.getValue() - 1);
            } else if (e.getPreciseWheelRotation() < 0) {
                zslider.setValue(zslider.getValue() + 1);
            }
        }
    } // DrawingViewport

} // AppWindow