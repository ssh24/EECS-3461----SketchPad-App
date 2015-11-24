package view.basics.menu;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;


public class MenuBar extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4565655401014050528L;
	
	private JMenuItem newMenuItem, openMenuItem;

	private JMenu fileMenu;

	private JMenuItem saveMenuItem;

	private JMenuItem saveAsMenuItem;

	private JMenuItem printMenuItem;

	private JMenuItem exitMenuItem;

	public MenuBar(JFrame frame) {
		
		// File Menu, F - Mnemonic
		fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		this.add(fileMenu);
		
		this.init_new_menu_item(frame);
		this.init_open_menu_item(frame);
		
		fileMenu.addSeparator();
		
		this.init_save_menu_item(frame);
		this.init_save_as_menu_item(frame);
		
		fileMenu.addSeparator();
		
		this.init_print_menu_item(frame);
		
		fileMenu.addSeparator();
		
		this.init_exit_menu_item(frame);
		
		
		
		
		
	}
	
	private void init_new_menu_item(JFrame frame)
	{
		// File->New, N - Mnemonic
		newMenuItem = new JMenuItem("New");
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_MASK));
		newMenuItem.setToolTipText("Create a new picture.");
		newMenuItem.setMnemonic('N');
		newMenuItem.setOpaque(true);
		newMenuItem.setBackground(Color.WHITE);

		ImageIcon image = new ImageIcon("images/main-tool-bar-images/new.png");
		newMenuItem.setIcon(image);
		fileMenu.add(newMenuItem);
		
		newMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//FileOperations.newDrawing();
			}	
		});
	}
	
	private void init_open_menu_item(JFrame frame)
	{
		// File->Open, O - Mnemonic
		openMenuItem = new JMenuItem("Open");
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		openMenuItem.setToolTipText("Open a new picture.");
		openMenuItem.setMnemonic('O');
		openMenuItem.setOpaque(true);
		openMenuItem.setBackground(Color.WHITE);
		openMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//FileOperations.openDrawing();
			}
		});
		ImageIcon image = new ImageIcon("images/main-tool-bar-images/open.png");
		openMenuItem.setIcon(image);
		fileMenu.add(openMenuItem);
	}
	
	private void init_save_menu_item(JFrame frame)
	{
		// File->Save, S - Mnemonic
		saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		saveMenuItem.setToolTipText("Save the current picture.");
		saveMenuItem.setMnemonic('S');
		saveMenuItem.setOpaque(true);
		saveMenuItem.setBackground(Color.WHITE);
		saveMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//FileOperations.saveDrawing(false, false);
			}
		});
		ImageIcon image = new ImageIcon("images/main-tool-bar-images/save.png");
		saveMenuItem.setIcon(image);
		fileMenu.add(saveMenuItem);
	}
	
	private void init_save_as_menu_item(JFrame frame)
	{
		// File->Save As, S - Mnemonic
		saveAsMenuItem = new JMenuItem("Save As");
		saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK + InputEvent.SHIFT_MASK));
		saveAsMenuItem.setToolTipText("Save the current picture as a new file.");
		saveAsMenuItem.setMnemonic('S');
		saveAsMenuItem.setOpaque(true);
		saveAsMenuItem.setBackground(Color.WHITE);
		saveAsMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//FileOperations.saveDrawing(false, true);
			}
		});
		ImageIcon image = new ImageIcon("images/main-tool-bar-images/save_as.png");
		saveAsMenuItem.setIcon(image);
		fileMenu.add(saveAsMenuItem);
	}
	
	private void init_print_menu_item(JFrame frame)
	{
		// File->Save As, P - Mnemonic
		printMenuItem = new JMenuItem("Print");
		printMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
		printMenuItem.setToolTipText("Print the current picture.");
		printMenuItem.setMnemonic('P');
		printMenuItem.setOpaque(true);
		printMenuItem.setBackground(Color.WHITE);
		printMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		ImageIcon image = new ImageIcon("images/main-tool-bar-images/printer.png");
		printMenuItem.setIcon(image);
		fileMenu.add(printMenuItem);
	}
	
	private void init_exit_menu_item(JFrame frame)
	{
		// File->Save As, E - Mnemonic
		exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
		exitMenuItem.setToolTipText("Exit from paint.");
		exitMenuItem.setMnemonic('E');
		exitMenuItem.setOpaque(true);
		exitMenuItem.setBackground(Color.WHITE);
		exitMenuItem.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {	
			}
		});
		ImageIcon image = new ImageIcon("images/main-tool-bar-images/exit.png");
		exitMenuItem.setIcon(image);
		fileMenu.add(exitMenuItem);
	}
}
