package helper.extras;


import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.colorchooser.*;
import javax.swing.event.*;

/**
 * This class provides a modified ColorChooser.
 * It uses the JColorChooser available in JDK 7 and
 * modifies the UI components within the chooser.
 * This is a hack-ish implementation.
 *
 * TODO: Create a copy of the package javax.swing.colorchooser
 * and modify the ColorChooserPanel directly to suit our needs.
 */
public class ColorPicker
    extends JPanel
    implements ActionListener {

    protected final GridBagConstraints GBC = new GridBagConstraints();
    protected JColorChooser chooser;
    protected ColorSelectionModel csmodel;
    protected JToggleButton noColor;
    protected JPanel boxer;

    private AbstractColorChooserPanel[] panels = {
        new AbstractColorChooserPanel() {
            private AbstractColorChooserPanel hsv;
            @Override protected void buildChooser() {
                hsv.getComponent(0).setVisible(false);
                setLayout(new BorderLayout());
                add(hsv, BorderLayout.CENTER);
            }
            @Override public void updateChooser() {hsv.updateChooser();}
            @Override public String getDisplayName() {return "HSV-1";}
            @Override public Icon getSmallDisplayIcon() {return hsv.getSmallDisplayIcon();}
            @Override public Icon getLargeDisplayIcon() {return hsv.getLargeDisplayIcon();}
            @Override public void installChooserPanel(JColorChooser enclosingChooser) {
                for (AbstractColorChooserPanel p : ColorChooserComponentFactory.getDefaultChooserPanels()) {
                    if ("HSV".equals(p.getDisplayName())) {hsv = p;}
                }
                hsv.installChooserPanel(enclosingChooser);
                buildChooser();
            }
            @Override public void uninstallChooserPanel(JColorChooser enclosingChooser) {
                hsv.uninstallChooserPanel(enclosingChooser);
            }
        },
        new AbstractColorChooserPanel() {
            private AbstractColorChooserPanel hsv;
            @Override protected void buildChooser() {
                for (Component c : ((JPanel)hsv.getComponent(0)).getComponents()) {
                    if (c instanceof JRadioButton && "Value".equals(((JRadioButton) c).getText())) {
                        ((JRadioButton) c).doClick();
                    }
                }
                hsv.getComponent(0).setVisible(false);
                setLayout(new BorderLayout());
                add(hsv, BorderLayout.CENTER);
            }
            @Override public void updateChooser() {hsv.updateChooser();}
            @Override public String getDisplayName() {return "HSV-2";}
            @Override public Icon getSmallDisplayIcon() {return hsv.getSmallDisplayIcon();}
            @Override public Icon getLargeDisplayIcon() {return hsv.getLargeDisplayIcon();}
            @Override public void installChooserPanel(JColorChooser enclosingChooser) {
                for (AbstractColorChooserPanel p : ColorChooserComponentFactory.getDefaultChooserPanels()) {
                    if ("HSV".equals(p.getDisplayName())) {hsv = p;}
                }
                hsv.installChooserPanel(enclosingChooser);
                buildChooser();
            }
            @Override public void uninstallChooserPanel(JColorChooser enclosingChooser) {
                hsv.uninstallChooserPanel(enclosingChooser);
            }
        },
        new AbstractColorChooserPanel() {
            private AbstractColorChooserPanel rgb;
            @Override protected void buildChooser() {
                rgb.getComponent(3).setVisible(false);
                rgb.getComponent(4).setVisible(false);
                reformatComponents();
                customHexCodeField();
                setLayout(new BorderLayout());
                add(rgb, BorderLayout.CENTER);
            }
            private void reformatComponents() {
                int i = 0, j = 0;
                Color[] colors = { Color.RED, Color.GREEN, Color.BLUE };
                Container cont = ((Container)rgb.getComponent(0));
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.gridx = 1;
                gbc.insets.top = 5;
                for (Component c : cont.getComponents()) {
                    if (c instanceof JRadioButton) {
                        JLabel label = new JLabel(((JRadioButton)c).getText());
                        label.setHorizontalAlignment(SwingConstants.RIGHT);
                        cont.remove(c);
                        cont.add(label, gbc, i++);
                    } else if (c instanceof JSlider) {
                        Dimension d = new Dimension(130, 20);
                        c.setPreferredSize(d);
                        c.setMaximumSize(d);
                        c.setMinimumSize(d);
                        if (j < colors.length) {
                            customSlider((JSlider)c, colors[j++]);
                        }
                    }
                } // foreach
            }
            private void customSlider(JSlider slider, final Color col) {
                UIDefaults cust = new UIDefaults();
                cust.putAll(UIManager.getLookAndFeelDefaults());
                cust.put("Slider:SliderTrack[Enabled].backgroundPainter", new Painter<JComponent>(){
                    @Override public void paint(Graphics2D g, JComponent comp, int w, int h) {
                        GradientPaint gradient = new GradientPaint((float)w/2,  6.f, Color.BLACK, (float)w/2, 12.f, col.darker());
                        GradientPaint border   = new GradientPaint((float)w/2, 10.f, Color.BLACK, (float)w/2, 12.f, Color.DARK_GRAY.brighter());
                        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                        g.setPaint(gradient);
                        g.fillRoundRect(0, 6, w - 1, 6, 8, 8);
                        g.setPaint(border);
                        g.setStroke(new BasicStroke(0.5f));
                        g.drawRoundRect(0, 6, w - 1, 6, 8, 8);
                    }
                });
                slider.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
                slider.putClientProperty("Nimbus.Overrides", cust);
            }
            private void customHexCodeField() {
                final Component hexCode = rgb.getComponent(2);
                hexCode.setBackground(chooser.getColor());
                hexCode.setForeground(UIToolbox.getContrastYIQ(chooser.getColor()));
                getSelectionModel().addChangeListener(new ChangeListener() {
                    @Override public void stateChanged(ChangeEvent e) {
                        hexCode.setBackground(chooser.getColor());
                        hexCode.setForeground(UIToolbox.getContrastYIQ(chooser.getColor()));
                    }
                });
            }
            @Override public void updateChooser() {rgb.updateChooser();}
            @Override public String getDisplayName() {return "RGB";}
            @Override public Icon getSmallDisplayIcon() {return rgb.getSmallDisplayIcon();}
            @Override public Icon getLargeDisplayIcon() {return rgb.getLargeDisplayIcon();}
            @Override public void installChooserPanel(JColorChooser enclosingChooser) {
                for (AbstractColorChooserPanel p : ColorChooserComponentFactory.getDefaultChooserPanels()) {
                    if ("RGB".equals(p.getDisplayName())) {rgb = p;}
                }
                rgb.installChooserPanel(enclosingChooser);
                buildChooser();
            }
            @Override public void uninstallChooserPanel(JColorChooser enclosingChooser) {
                rgb.uninstallChooserPanel(enclosingChooser);
            }
        }
    };

    // ----- Public methods -----

    /**
     * Construct a new color picker.
     * Set the initial value to the given color.
     *
     * @param color     the initial value
     */
    public ColorPicker(Color color) {
        chooser = new JColorChooser(color == null ? Color.white : color);
        csmodel = chooser.getSelectionModel();
        chooser.setChooserPanels(panels);
        chooser.setPreviewPanel(new JPanel());
        setLayout(new GridBagLayout());
            GBC.insets    = new Insets(1, 3, 0, 3);
            GBC.weightx   = 1.0;
            GBC.fill      = GridBagConstraints.HORIZONTAL;
            GBC.gridwidth = GridBagConstraints.REMAINDER;
        boxer = new JPanel();
        noColor = new JToggleButton("No Color");
            noColor.addActionListener(this);
            noColor.setMargin(new Insets(5, 5, 5, 5));
            noColor.setSelected(color == null);
            noColor.doClick();
            noColor.doClick();
        UIToolbox.setSize(noColor, new Dimension(275, 30));
        UIToolbox.setSize(chooser, new Dimension(275, 275));
        add(noColor, GBC);
        add(UIToolbox.box(boxer, chooser), GBC);
    }

    /**
     * Gets the current color value from the color chooser.
     * By default, this delegates to the model.
     *
     * @return the current color value of the color chooser
     */
    public Color getColor() {
        if (this.noColor.isSelected()) {
            return null;
        }
        return this.chooser.getColor();
    }

    @Override public void actionPerformed(ActionEvent e) {
        setEnabled(!noColor.isSelected());
        chooser.setVisible(isEnabled());
        if (getParent() == null) {return;}
        getParent().revalidate();
    }

    /**
     * Returns the data model that handles color selections.
     *
     * @return a <code>ColorSelectionModel</code> object
     */
    public ColorSelectionModel getSelectionModel() {
        return this.csmodel;
    }

    /**
     * Sets whether or not this component is enabled.
     * A component that is enabled may respond to user input,
     * while a component that is not enabled cannot respond to
     * user input.  Some components may alter their visual
     * representation when they are disabled in order to
     * provide feedback to the user that they cannot take input.
     * <p>Note: Disabling a component does not disable its children.
     *
     * <p>Note: Disabling a lightweight component does not prevent it from
     * receiving MouseEvents.
     *
     * @param enabled true if this component should be enabled, false otherwise
     * @see java.awt.Component#isEnabled
     * @see java.awt.Component#isLightweight
     *
     * @beaninfo
     *    preferred: true
     *        bound: true
     *    attribute: visualUpdate true
     *  description: The enabled state of the component.
     */
    @Override public void setEnabled(boolean enabled) {
        this.chooser.setEnabled(enabled);
    }

    /**
     * Determines whether this component is enabled. An enabled component
     * can respond to user input and generate events. Components are
     * enabled initially by default. A component may be enabled or disabled by
     * calling its <code>setEnabled</code> method.
     *
     * @return <code>true</code> if the component is enabled,
     *          <code>false</code> otherwise
     * @see #setEnabled
     */
    @Override public boolean isEnabled() {
        return this.chooser.isEnabled();
    }

    /**
     * Sets the tab placement for the tabbed pane of the color chooser.
     * Possible values are:<ul>
     * <li><code>JTabbedPane.TOP</code>
     * <li><code>JTabbedPane.BOTTOM</code>
     * <li><code>JTabbedPane.LEFT</code>
     * <li><code>JTabbedPane.RIGHT</code>
     * </ul>
     * The default value, if not set, is <code>SwingConstants.TOP</code>.
     *
     * @param tabPlacement the placement for the tabs relative to the content
     * @exception IllegalArgumentException if tab placement value isn't one
     *                          of the above valid values
     *
     * @beaninfo
     *    preferred: true
     *        bound: true
     *    attribute: visualUpdate true
     *         enum: TOP JTabbedPane.TOP
     *               LEFT JTabbedPane.LEFT
     *               BOTTOM JTabbedPane.BOTTOM
     *               RIGHT JTabbedPane.RIGHT
     *  description: The tabbedpane's tab placement.
     *
     */
    public void setTabPlacement(int tabPlacement) {
        JTabbedPane tabbedPane = (JTabbedPane)this.chooser.getComponent(0);
        tabbedPane.setTabPlacement(tabPlacement);
    }

} // ColorPicker
