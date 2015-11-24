package view.basics.panes;

import helper.extras.*;


import java.awt.*;
import java.util.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

/**
 * AccordionPanel is a custom UI component that provides collapsible panels
 * which gives hierarchy to information, and presents only the relevant
 * information to the users on a need to know basis. Enables grouping of
 * items in a long list, sections in a large documents or controls
 * in a settings panel.
 */
public class AccordionPanel extends JPanel {

    protected final Map<AccordionButton, Component> PANELS = new HashMap<AccordionButton, Component>();
    protected final GridBagConstraints GBC = new GridBagConstraints();
    protected final Set<Component> showingPanel = new HashSet<Component>();

    protected ButtonGroup buttonGroup;
    protected int panelWidth = 0;

    /**
     * Constructs a new Accordion Panel.
     * Links the tabs as grouped if given flag is true, otherwise ungouped.
     * If grouped, only one tab can be open at any given time.
     *
     * @param group     true if grouped, else false
     */
    public AccordionPanel(boolean group) {
        buttonGroup = group ? new ButtonGroup() : null;
        setLayout(new GridBagLayout());
            GBC.insets    = new Insets(1, 3, 0, 3);
            GBC.weightx   = 1.0;
            GBC.fill      = GridBagConstraints.HORIZONTAL;
            GBC.gridwidth = GridBagConstraints.REMAINDER;
        super.addImpl(new JPanel(), GBC, 0); // padding
    }

    /**
     * Constructs a new ungrouped Accordion Panel.
     */
    public AccordionPanel() {
        this(false);
    }

    /**
     * Hide and show the contents of the collapsible sections.
     *
     * @param button    the button associated with the toggled panel
     * @param show      flag to move visible (true) or hide (false).
     */
    private void togglePanelVisibility(AccordionButton button, boolean show) {
        Component panel = PANELS.get(button);
        if (panel.isShowing() == show) {return;}
        if (show) {
            showingPanel.add(panel);
        } else {
            showingPanel.remove(panel);
        }
        panel.setVisible(show);
        button.getParent().validate();
        getParent().revalidate();
    }

    /**
     * {@inheritDoc}
     */
    @Override protected void addImpl(Component panel, Object constraint, int idx) {
        if (idx < 0) {
            idx = PANELS.size();
        }
        String label = constraint == null ? "Panel " + (idx + 1) :
                            constraint.toString();
        AccordionButton ab = new AccordionButton(label);
        PANELS.put(ab, panel);
        // add containment hierarchy
        super.addImpl(ab, GBC, idx * 2);
        super.addImpl(panel, GBC, idx * 2 + 1);
        // add to button group
        if (buttonGroup != null) {
            buttonGroup.add(ab);
        }
        // update width
        int width = panel.getPreferredSize().width;
        if (width > panelWidth) {
            panelWidth = width;
            for (AccordionButton button : PANELS.keySet()) {
                button.setPreferredSize(new Dimension(width,
                        button.getPreferredSize().height));
            }
        }
        if (panel instanceof JPanel) {
            ((JPanel)panel).setBorder(new EtchedBorder());
        }
        panel.setVisible(false); // init closed
    }

    /**
     * Returns the panel associated with the given button (key).
     *
     * @param key       the button to lookup
     * @return          the panel associated with the key,
     *                  else null if not found
     */
    public Component getComponent(Object key) {
        return PANELS.get(key);
    }

    /**
     * Returns the number of panels in the accordion.
     *
     * @return the number of panels in the accordion.
     */
    public int getPanelCount() {
        return PANELS.size();
    }

    /**
     * Returns a set of the currently visible panels.
     *
     * @return a set of the currently visible panels.
     */
    public Set<Component> getVisiblePanels() {
        return showingPanel;
    }

    /**
     * Enable the grouping of the buttons.
     * Links the tabs as grouped if given flag is true, otherwise ungouped.
     * If grouped, only one tab can be open at any given time.
     *
     * @param group     true if grouped, else false
     */
    public void setButtonGroup(boolean group) {
        if (group == (buttonGroup != null)) {return;}
        if (group) {
            buttonGroup = new ButtonGroup();
            for (AccordionButton ab : PANELS.keySet()) {
                buttonGroup.add(ab);
            }
        } else {
            for (AccordionButton ab : PANELS.keySet()) {
                buttonGroup.remove(ab);
            }
            buttonGroup = null;
        }
    }

    /**
     * Attach the given EventListener to the buttons.
     *
     * @param listener      the EventListener to attach.
     */
    public void addButtonEvent(EventListener listener) {
        if (listener == null) {
            return;
        }
        for (AccordionButton ab : PANELS.keySet()) {
            if (listener instanceof ActionListener) {
                ab.addActionListener((ActionListener)listener);
            } else if (listener instanceof MouseListener) {
                ab.addMouseListener((MouseListener)listener);
            } else if (listener instanceof ItemListener) {
                ab.addItemListener((ItemListener)listener);
            }
        } // foreach button
    }

    @Override public void revalidate() {
        super.revalidate();
        if (getParent() == null) {return;}
        getParent().revalidate();
    }

    /**
     * Class definition of a accordion button.
     * Button enables to expanding and collapsing of panels in the
     * Accordion Panel.
     */
    private class AccordionButton extends JToggleButton implements ItemListener {

        private String label;

        /**
         * Construct a new accordion button given label.
         *
         * @param label     to display on the button.
         */
        public AccordionButton(String label) {
            this.label = label;
            setText(label);
            setHorizontalAlignment(LEFT);
            addMouseListener(new MouseAdapter() {
                private AccordionButton ab = null;
                @Override public void mousePressed(MouseEvent e) {
                    if (buttonGroup != null && isSelected()) {
                        buttonGroup.clearSelection();
                        showingPanel.clear();
                        ab = (AccordionButton)e.getSource();
                    }
                } // mousePressed
                @Override public void mouseReleased(MouseEvent e) {
                    if (ab == e.getSource()) {
                        buttonGroup.clearSelection();
                        showingPanel.clear();
                        ab = null;
                    }
                } // mousePressed
            }); // addMouseListener
            addItemListener(this);
            setRequestFocusEnabled(true);
        }

        /**
         * Set the text of the button.
         * Appends an arrow icon to the left of the left.
         * Arrow points right when button unselected.
         * Arrow points down when button is selected.
         *
         * @param label     the display on the button.
         */
        @Override public void setText(String label) {
            final String template = "<html><body style=\"text-align: left;\">"
                + "<span style=\"font-family: Canvas;\">{ICON}</span>"
                + "<span>&nbsp;&nbsp;&nbsp;&nbsp;</span>"
                + "<span>{LABEL}</span>"
                + "</body></html>";
            String icon = MyFont.ICONS.get("BIG-ARROW-" + (isSelected() ? "DOWN" : "RIGHT"));
            super.setText(template
                .replace("{ICON}", icon)
                .replace("{LABEL}", label));
        }

        /**
         * {@inheritDoc}
         */
        @Override public void itemStateChanged(ItemEvent e) {
            AccordionButton button = (AccordionButton)e.getSource();
            switch (e.getStateChange()) {
                case ItemEvent.SELECTED:
                    togglePanelVisibility(button, true);
                    setText(this.label);
                    break;
                case ItemEvent.DESELECTED:
                    togglePanelVisibility(button, false);
                    setText(this.label);
                    break;
            }
        }

    } // AccordionButton

} // AccordionPanel
