package view.basics.panes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;


public class TextPane extends JFrame implements ActionListener, ItemListener {
	
	static final long serialVersionUID = 42L;

	final int DEFAULT_STYLE = Font.PLAIN;
	final int DEFAULT_SIZE = 22;
	final int DEFAULT_SIZE_INDEX = 3;
	final String[] SZ = { "10", "14", "18", "22", "26", "32", "38", "48" };

	private JTextField message;
	private JButton exitButton;
	private JButton restoreButton;
	private JCheckBox italicCheckBox;
	private JCheckBox boldCheckBox;
	private JComboBox<String> sizeCombo;
	private JComboBox<String> fontCombo;

	private String fontFamily;
	private int fontSize;
	private int fontStyle;
	private String[] fontList;
	private String defaultFamily;
	private int defaultFamilyIndex;
	
	public static String messageText;
	public static String font;
	public static int size, style;
	

	private Color c;

	private JPanel panel;

	private JTextField entry;

	public static Color color;
	
	public String getMessage()
	{
		return entry.getText();
	}
	
	public Color getcolor()
	{
		return c;
	}
	
	public String getfont()
	{
		return (String) fontCombo.getSelectedItem();
	}
	
	public int getsize()
	{
		return Integer.parseInt((String) sizeCombo.getSelectedItem());
	}
	
	public int getstyle()
	{
		if(italicCheckBox.isSelected() && !boldCheckBox.isSelected())
			return Font.ITALIC;
		else if(boldCheckBox.isSelected() && !italicCheckBox.isSelected())
			return Font.BOLD;
		else if(italicCheckBox.isSelected() && boldCheckBox.isSelected())
			return Font.ITALIC + Font.BOLD;
		else
			return Font.PLAIN;
	}
	
	public TextPane()
	{


		// configurations
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fontList = ge.getAvailableFontFamilyNames();
		
		defaultFamilyIndex = -1;
		for (int i = 0; i < fontList.length; ++i)
		{
			if (fontList[i].toLowerCase().equals("serif"))
			{
				defaultFamilyIndex = i;
				break;
			}
		}

		if (defaultFamilyIndex == -1) // not found!
		{
			JOptionPane.showMessageDialog(this, "Default font family ('serif') not found!\n" + "Will use '"
					+ fontList[0] + "' as default.", "Information Message", JOptionPane.INFORMATION_MESSAGE);
			defaultFamilyIndex = 0;
		}
		defaultFamily = fontList[defaultFamilyIndex];
		
		fontFamily = defaultFamily;
		fontStyle = DEFAULT_STYLE;
		fontSize = DEFAULT_SIZE;

		// ----------------------------------
		// construct and configure components
		// ----------------------------------
		
		panel = new JPanel(new BorderLayout());
		
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		
		JLabel label = new JLabel("Enter text: ");
		north.add(label);
		
		entry = new JTextField(30);
		entry.addCaretListener(new CaretListener()
		{

			@Override
			public void caretUpdate(CaretEvent e) {
				message.setText(entry.getText());
			}
			
		});

		
		north.add(entry);

		message = new JTextField("");
		message.setFont(new Font(defaultFamily, DEFAULT_STYLE, DEFAULT_SIZE));
		message.setEditable(false);

		message.setBackground(Color.WHITE);
		message.setHorizontalAlignment(SwingConstants.CENTER);

		
		JScrollPane sp = new JScrollPane(message);
		sp.setPreferredSize(new Dimension(600, 150));
		center.add(sp);
		
		italicCheckBox = new JCheckBox("Italic");
		boldCheckBox = new JCheckBox("Bold");

		sizeCombo = new JComboBox<String>(SZ);
		sizeCombo.setEditable(true);

		((JLabel) sizeCombo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JTextField) sizeCombo.getEditor().getEditorComponent()).setHorizontalAlignment(JTextField.CENTER);

		sizeCombo.setSelectedIndex(DEFAULT_SIZE_INDEX);
		sizeCombo.setPreferredSize(new Dimension(75, 25));

		fontCombo = new JComboBox<String>(fontList);
		fontCombo.setRenderer(new CustomRenderer());
		fontCombo.setSelectedIndex(defaultFamilyIndex);
		fontCombo.setPreferredSize(new Dimension(150, 25));
		
		italicCheckBox.addItemListener(this);
		boldCheckBox.addItemListener(this);
		sizeCombo.addActionListener(this);
		fontCombo.addActionListener(this);
		
		JPanel sizePanel = new JPanel();
		sizePanel.setLayout(new GridLayout(2, 1));
		sizePanel.add(sizeCombo);
		sizePanel.setBorder(new TitledBorder(new EtchedBorder(), "Size"));
		//sizePanel.setPreferredSize(d);

		JPanel stylePanel = new JPanel();
		stylePanel.setLayout(new GridLayout(2, 1));
		stylePanel.add(italicCheckBox);
		stylePanel.add(boldCheckBox);
		stylePanel.setBorder(new TitledBorder(new EtchedBorder(), "Style"));
		//stylePanel.setPreferredSize(d);

		JPanel fontPanel = new JPanel();
		fontPanel.setLayout(new GridLayout(2, 1));
		fontPanel.add(fontCombo);
		fontPanel.setBorder(new TitledBorder(new EtchedBorder(), "Font"));
		//fontPanel.setPreferredSize(new Dimension(d.width * 2, d.height));
		
		JButton colorButton = new JButton("Choose text color");
		colorButton.addActionListener(new ActionListener()
		{
			


			@Override
			public void actionPerformed(ActionEvent e) {
				c = JColorChooser.showDialog(null, "Text color chooser", Color.BLACK);
				message.setForeground(c);
			}
			
			
		});
		
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new GridLayout(1, 1));
		colorPanel.add(colorButton);
		colorPanel.setBorder(new TitledBorder(new EtchedBorder(), "Text Color"));
		
		JPanel proceedPanel = new JPanel();
		
		JButton proceed = new JButton("Proceed ->");
		
		proceed.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				messageText = getMessage();
				color = getcolor();
				font = getfont();
				size = getsize();
				style = getstyle();
				setVisible(false);
			}
			
		});
		proceedPanel.add(proceed);

		// arrange panels

		JPanel southPanel = new JPanel();
		southPanel.add(sizePanel);
		southPanel.add(stylePanel);
		southPanel.add(fontPanel);
		southPanel.add(colorPanel);
		southPanel.add(proceedPanel);

		// add panels to content pane
		
		panel.add(north, BorderLayout.NORTH);
		panel.add(center,BorderLayout.CENTER);
		panel.add(southPanel, BorderLayout.SOUTH);
		
		add(panel);
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Text Prompt" + MainFrame.company);
		pack();

		// put the frame in the middle of the display
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

		setVisible(true);
	}


	// -------------------------------
	// implement ActionListener method
	// -------------------------------

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		Object source = ae.getSource();

		// 'Command' - check if a command button was pressed
		if (source == restoreButton)
		{
			fontFamily = fontList[defaultFamilyIndex];
			fontStyle = DEFAULT_STYLE;
			fontSize = DEFAULT_SIZE;
			sizeCombo.setSelectedIndex(DEFAULT_SIZE_INDEX);
			italicCheckBox.setSelected(false);
			boldCheckBox.setSelected(false);
			fontCombo.setSelectedIndex(defaultFamilyIndex);
		} else if (source == exitButton)
			System.exit(0);

		// 'Size' - check if the font size was changed via the combobox
		else if (source == sizeCombo)
		{
			int tmp = this.getFontSize(sizeCombo);
			if (tmp == -1)
				return;
			else
				fontSize = tmp;
		}

		// 'Font' - check if the font family was changed via the combobox
		else if (source == fontCombo)
			fontFamily = fontList[fontCombo.getSelectedIndex()];

		// update message font, size, and style
		message.setFont(new Font(fontFamily, fontStyle, fontSize));
	}

	// -----------------------------
	// implement ItemListener method
	// -----------------------------

	@Override
	public void itemStateChanged(ItemEvent ie)
	{
		Object source = ie.getSource();

		// 'Style' - check if the font style was change via a checkbox
		if (source == italicCheckBox)
		{
			if (italicCheckBox.isSelected())
				fontStyle = fontStyle | Font.ITALIC; // turn italic on
			else
				fontStyle = fontStyle & ~Font.ITALIC; // turn italic off
		} else if (source == boldCheckBox)
		{
			if (boldCheckBox.isSelected())
				fontStyle = fontStyle | Font.BOLD; // turn bold on
			else
				fontStyle = fontStyle & ~Font.BOLD; // turn bold off
		}

		// update message font, size, and style
		message.setFont(new Font(fontFamily, fontStyle, fontSize));
	}

	// -------------
	// other methods
	// -------------

	/**
	 * Get the size of the font from the combo box.
	 * 
	 * @param cb
	 *            the combo box
	 * @return a int equal to the font size, or -1 if invalid. Note a valid font size is an integer in the range 1-500
	 */
	private int getFontSize(JComboBox<String> cb)
	{
		String userInput = (String) cb.getSelectedItem();
		boolean ok = true;
		int fntSize = -1;
		try
		{
			fntSize = Integer.parseInt(userInput);
		} catch (NumberFormatException nfe)
		{
			ok = false;
		}

		if (fntSize < 1 || fntSize > 500)
		{
			ok = false;
			fntSize = -1; // indicates invalid
			cb.setSelectedItem("" + fontSize);
		}

		if (!ok)
		{
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(this, "Please enter an integer in the range 1-500!", "Invalid Input",
					JOptionPane.ERROR_MESSAGE);

			// keep focus on combobox editor until input is corrected
			cb.setSelectedItem("" + fontSize);
		}
		return fntSize; // returns -1 if invalid
	}

	// -----------
	// inner class
	// -----------

	class CustomRenderer extends JTextField implements ListCellRenderer
	{
		// the following avoids a "warning" with Java 1.5.0 complier (?)
		static final long serialVersionUID = 42L;

		public CustomRenderer()
		{
			this.setOpaque(true);
			this.setHorizontalAlignment(LEFT);
			this.setBorder(null);
		}

		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
				boolean cellHasFocus)
		{
			if (isSelected)
			{
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else
			{
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}

			String fontFamily = (String) value;
			this.setText(fontFamily);

			// The following line is the 'clincher'. This will cause
			// the font family name to be rendered in the named font.

			this.setFont(new Font(fontFamily, Font.PLAIN, 14));

			return this;
		}
	}
	
/*	public static void main(String[] args)
	{
		// use look and feel for my system (Win32)
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e)
		{
		}

		TextPane frame = new TextPane();
	}*/
	
	

}
