package view.basics.menu;

import helper.extras.FileOperations;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.KeyStroke;

import view.basics.panes.MainFrame;
import controller.primary.MainControl;
import controller.secondary.drawings.Drawing;

/********The MenuBar class is a file menu that includes New, Open, Save, Save As, Print and Exit. ********/
public class MenuBar extends JMenuBar implements Printable, ActionListener
{
	/********private variables********/
	private static final long serialVersionUID = 4565655401014050528L;
	
	private JMenuItem newMenuItem, openMenuItem;

	private JMenu fileMenu;

	private JMenuItem saveMenuItem;

	private JMenuItem saveAsMenuItem;

	private JMenuItem printMenuItem;

	private JMenu toolbarMenu;

	private JMenuItem showHide;

	private JMenu fsMenu;

	private JMenu view;

	public static JMenuItem fullscreen;

	

	/********public constructor which takes as a parameter a frame ********/
	public MenuBar(JFrame frame)
	{
		// File Menu, F - Mnemonic
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		this.add(fileMenu);
		
		// File Menu, F - Mnemonic
		toolbarMenu = new JMenu("Toolbar");
		toolbarMenu.setMnemonic(KeyEvent.VK_T);
		this.add(toolbarMenu);
		

		
		
		// adding all the menu items to the file menu
		this.init_new_menu_item(frame);
		this.init_open_menu_item(frame);
		fileMenu.addSeparator();
		this.init_save_menu_item(frame);
		this.init_save_as_menu_item(frame);
		fileMenu.addSeparator();
		this.init_print_menu_item(frame);
		
		init_toolbar(frame);
		//init_fullscreen(frame);
	}
	
	
	/********private method that creates the menu item New. Takes parameter frame. ********/
	
	private void init_toolbar(JFrame frame)
	{

		
		showHide = new JMenuItem("Hide");
		showHide.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.CTRL_MASK));
		showHide.setToolTipText("Hide the toolbar.");
		//showHide.setMnemonic('H');
		showHide.setOpaque(true);
		showHide.setBackground(Color.WHITE);
		toolbarMenu.add(showHide);
		//add Listener
		showHide.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if(showHide.getText()=="Hide")
				{
					showHide.setText("Show");
					MainFrame.pblic_tb.setVisible(false);
					showHide.setToolTipText("Show the toolbar.");
				}
				else
				{
					showHide.setText("Hide");
					MainFrame.pblic_tb.setVisible(true);
					showHide.setToolTipText("Hide the toolbar.");
				}
			}	
		});
	}
	
	/*private void init_fullscreen(JFrame frame)
	{
		fullscreen = new JMenuItem("Full Screen");
		
		fullscreen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,InputEvent.CTRL_MASK+InputEvent.SHIFT_MASK));
		fullscreen.setToolTipText("Full screen the image.");
		fullscreen.setMnemonic('F');
		fullscreen.setOpaque(true);
		fullscreen.setBackground(Color.WHITE);

		//ImageIcon image = new ImageIcon("images/main-tool-bar-images/new.png");
		//newMenuItem.setIcon(image);
		view.add(fullscreen);
		//add Listener
		fullscreen.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JFrame f = new JFrame();
				
				JPanel drawPanel = new JPanel();
				System.out.println(drawPanel.getGraphics());
				
				Graphics2D g2d = (Graphics2D) MainControl.drawingArea.getGraphics();
				for (Drawing d : MainControl.drawings) 
				{
					drawPanel.paint(g2d);
					System.out.println(d);
				}
				//g2d.dispose();
				
				int numComponents = MainControl.drawingsrea.getComponentCount();
				
				for (int i=0; i<numComponents; i++) 
				{ 
					System.out.println(MainControl.drawingArea.getComponent(i));
					drawPanel.add(MainControl.drawingArea.getComponent(i)); 
					drawPanel.revalidate();
					drawPanel.repaint();
				} 
				
				
		        BufferedImage image = new BufferedImage(MainControl.drawingArea.getWidth(), MainControl.drawingArea.getHeight(), BufferedImage.TYPE_INT_RGB);
		        Graphics2D graphics2D = image.createGraphics(); 
		        drawPanel.paint(graphics2D);
		        
				f.add(drawPanel);
				
				MainControl.mode = MainControl.Mode.SELECT;
				
				f.setSize(Toolkit.getDefaultToolkit().getScreenSize());
				f.setUndecorated(true);
				f.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
				f.setVisible(true);
				
				drawPanel = new JPanel();

		        KeyAdapter listener = new KeyAdapter() {
		            @Override public void keyPressed(KeyEvent e) {
		            	
		                f.dispose();
		               
		            }
		        };

		        f.addKeyListener(listener);
			}	
		});
	}*/

	
	private void init_new_menu_item(JFrame frame)
	{
		// File->New, N - Mnemonic
		newMenuItem = new JMenuItem("New");
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		newMenuItem.setToolTipText("Create a new picture.");
		newMenuItem.setMnemonic('N');
		newMenuItem.setOpaque(true);
		newMenuItem.setBackground(Color.WHITE);

		ImageIcon image = new ImageIcon("images/main-tool-bar-images/new.png");
		newMenuItem.setIcon(image);
		fileMenu.add(newMenuItem);
		//add Listener
		newMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				FileOperations.newDrawing();
			}	
		});
	}
	
	/********private method that creates the menu item Open. Takes parameter frame. ********/
	private void init_open_menu_item(JFrame frame)
	{
		// File->Open, O - Mnemonic
		openMenuItem = new JMenuItem("Open");
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		openMenuItem.setToolTipText("Open a new picture.");
		openMenuItem.setMnemonic('O');
		openMenuItem.setOpaque(true);
		openMenuItem.setBackground(Color.WHITE);
		//add Listener
		openMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				FileOperations.openDrawing();
			}
		});
		ImageIcon image = new ImageIcon("images/main-tool-bar-images/open.png");
		openMenuItem.setIcon(image);
		fileMenu.add(openMenuItem);
	}
	
	/********private method that creates the menu item Save. Takes parameter frame. ********/
	private void init_save_menu_item(JFrame frame)
	{
		// File->Save, S - Mnemonic
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		saveMenuItem.setToolTipText("Save the current picture.");
		saveMenuItem.setMnemonic('S');
		saveMenuItem.setOpaque(true);
		saveMenuItem.setBackground(Color.WHITE);
		//add Listener
		saveMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				FileOperations.saveDrawing(false, false);
			}
		});
		ImageIcon image = new ImageIcon("images/main-tool-bar-images/save.png");
		saveMenuItem.setIcon(image);
		fileMenu.add(saveMenuItem);
	}
	
	/********private method that creates the menu item Save As. Takes parameter frame. ********/
	private void init_save_as_menu_item(JFrame frame)
	{
		// File->Save As, S - Mnemonic
		saveAsMenuItem = new JMenuItem("Save As");
		saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
		saveAsMenuItem.setToolTipText("Save the current picture as a new file.");
		saveAsMenuItem.setMnemonic('S');
		saveAsMenuItem.setOpaque(true);
		saveAsMenuItem.setBackground(Color.WHITE);
		//add Listener
		saveAsMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				FileOperations.saveDrawing(false, true);
			}
		});
		ImageIcon image = new ImageIcon("images/main-tool-bar-images/save_as.png");
		saveAsMenuItem.setIcon(image);
		fileMenu.add(saveAsMenuItem);
	}
	
	
	/********private method that creates the menu item Print. Takes parameter frame. ********/
	private void init_print_menu_item(JFrame frame)
	{
		// File->Print, P - Mnemonic
		printMenuItem = new JMenuItem("Print");
		printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		printMenuItem.setToolTipText("Print the current picture.");
		printMenuItem.setMnemonic('P');
		printMenuItem.setOpaque(true);
		printMenuItem.setBackground(Color.WHITE);
		//add Listener
		printMenuItem.addActionListener(this);
		ImageIcon image = new ImageIcon("images/main-tool-bar-images/printer.png");
		printMenuItem.setIcon(image);
		fileMenu.add(printMenuItem);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		if(arg0.getSource()==printMenuItem)
		{
			PrinterJob job = PrinterJob.getPrinterJob();
	        job.setPrintable(this);
	         boolean ok = job.printDialog();
	         if (ok) 
	         {
	             try 
	             {
	                  job.print();
	             } 
	             catch (PrinterException ex) 
	             {
	              /* The job did not successfully complete */
	             }
	         }
		}
	}
	

	/********public method to print the pages. ********/
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException 
    {
 
        if (page > 0) 
        { 
            return NO_SUCH_PAGE;
        }
 
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());
        for(Drawing d : MainControl.drawings)
        	g2d.draw(d);
 

      //  g.drawString("Hello world!", 100, 100);
 
        
        return PAGE_EXISTS;
    }
}
