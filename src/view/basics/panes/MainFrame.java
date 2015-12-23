package view.basics.panes;


import helper.extras.FileOperations;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import view.basics.menu.MenuBar;
import view.basics.toolbars.*;
import controller.primary.MainControl;

/********The MainFrame class *******/
public class MainFrame extends JFrame
{
	 /********Variables *********/
	private static final long serialVersionUID = 1L;
	public static final String company = " - SketchPad";
	public static ToolBar pblic_tb;
	public static MenuBar mb;
	public static ZoomToolBar zoom_tb;
	
	
	 /********public constructor *********/
    public MainFrame(String title)
    {
    	mb = new MenuBar(this);
    	// sets the menu bar
    	this.setJMenuBar(mb);
    	
    	// adds Listener
        addWindowListener(new WindowAdapter() 
        {
        	
            @Override
            public void windowClosing(WindowEvent e)
            {
                int n = FileOperations.saveDrawing(true, false);
                if (n == JOptionPane.YES_OPTION || n == JOptionPane.NO_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        
        
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        
        manager.addKeyEventDispatcher(new KeyEventDispatcher() 
        {
            @Override 
            public boolean dispatchKeyEvent(KeyEvent e) 
            {
                switch (e.getKeyCode()) 
                {
                    case KeyEvent.VK_ESCAPE:
                        if (MainControl.preview != null)
                        {
                            MainControl.preview = null;
                        }
                        if (MainControl.selected != null) 
                        {
                            MainControl.selected = null;
                        }
                        MainControl.drawingArea.repaint();
                        break;
                    case KeyEvent.VK_DELETE:
                        if (MainControl.mode == MainControl.Mode.SELECT && MainControl.selected != null) 
                        {
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
        //getContentPane().add(new ToolBar(this), BorderLayout.NORTH);
        pblic_tb = new ToolBar(this);
        zoom_tb = new ZoomToolBar();
        getContentPane().add(pblic_tb, BorderLayout.NORTH);
        getContentPane().add(new DrawingViewport(), BorderLayout.CENTER);
        getContentPane().add(zoom_tb, BorderLayout.SOUTH);
        
        
        
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setBounds(ge.getMaximumWindowBounds());
		
		setTitle(title + company);
		
		setIconImage(new ImageIcon("images/main_logo.png").getImage());
		
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
/*		setUndecorated(true);
		getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		mb.setVisible(false);
		pblic_tb.setVisible(false);
		zoom_tb.setVisible(false);*/
        
       /* MenuBar.fullscreen.addActionListener(new ActionListener()
        {

			@Override
			public void actionPerformed(ActionEvent e) {
				setUndecorated(true);
				getRootPane().setWindowDecorationStyle(JRootPane.NONE);
				
				mb.setVisible(false);
				pblic_tb.setVisible(false);
				zoom_tb.setVisible(false);
			}
        	
        });*/
        
        setVisible(true);
    }
  

    // private class to scroll the paint panel
    private static class DrawingViewport extends JScrollPane implements MouseWheelListener 
    {
		private static final long serialVersionUID = 1L;
		private DrawingArea drawingArea = MainControl.drawingArea;
   
		// Viewport for the paint panel
        public DrawingViewport() 
        {
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
            // adds Listener
            addMouseWheelListener(this);
        }
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) 
        {
            JSlider zslider = MainControl.zoomSlider;
            if (e.getPreciseWheelRotation() > 0) 
            {
                zslider.setValue(zslider.getValue() - 1);
            } 
            else if (e.getPreciseWheelRotation() < 0) 
            {
                zslider.setValue(zslider.getValue() + 1);
            }
        }
    } 
} 