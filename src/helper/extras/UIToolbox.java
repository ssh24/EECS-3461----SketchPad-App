package helper.extras;

import java.awt.*;


import javax.swing.*;

import view.basics.panes.*;
import controller.primary.MainControl;


/**
 * This static class contains a collection of commonly
 * used methods for constructing, displaying, manipulating
 * user interface components.
 */
public class UIToolbox {

    private UIToolbox() { } // no constructor

    /**
     * Computes the constrast and returns the foreground
     * color appropriate for the given background color.
     *
     * @param bg    the background color
     * @return      the constrasting foreground color
     */
    public static Color getContrastYIQ(Color bg) {
        if (bg == null) {
            return null;
        }
        int r = bg.getRed();
        int g = bg.getGreen();
        int b = bg.getBlue();
        int yiq = ((r*299)+(g*587)+(b*114))/1000;
        if (bg.getAlpha() < 64) {
            return Color.WHITE;
        }
        return (yiq >= 128) ? Color.BLACK : Color.WHITE;
    }

    // ----- Create Icon Buttons -----

    public static final int BUTTON = 0;
    public static final int TOGGLEBUTTON = 1;
    public static final int MENUBUTTON = 2;

    /**
     * Helper method for creating buttons.
     *
     * @param   type        the type of the button.
     * @param   id          the button reference ID.
     * @param   icon        the name of the icon to place on the button
     * @param   size        the font point size
     * @param   padding     the amount of padding around the button
     * @return              the button created.
     */
    public static AbstractButton createButton(int type, String id, String icon, String tooltip, float size, int padding) {
        AbstractButton ab = null;
        switch (type) {
            case BUTTON:       ab = new JButton();       break;
            case TOGGLEBUTTON: ab = new JToggleButton(); break;
            case MENUBUTTON:   ab = new JMenu();         break;
            default:           return null;
        }
        ab.setFont(MyFont.ICON_FONT.deriveFont(size));
        ab.setText(MyFont.ICONS.get(icon));
        ab.setMargin(new Insets(padding, padding, padding, padding));
        ab.setActionCommand(id);
        ab.setToolTipText(tooltip);
        ab.setName(id);
        return ab;
    }
    public static AbstractButton createButton(int type, String id, String icon, String tooltip) {
        return createButton(type, id, icon, tooltip, 16.f, 5);
    }

    // ---- Popup Pane -----

    /**
     * Attaches a popup pane to a button.
     * Returns the created popup.
     *
     * @param invoker              the button that invokes the popup panel
     * @param popupPanel           the popup panel to attach
     * @param bgroup               the button group for which button is contained (if applicable)
     * @param orientation          the orientation of the placement of the popup.
     * @param deselectOnClose      flag for deselecting the button on close of popup
     * @return                     the popup created
     */
    public static JPopupMenu attachPopupPane(
            AbstractButton invoker,
            Component popupPanel,
            ButtonGroup bgroup,
            int orientation,
            boolean deselectOnClose) {
        PopupPane popup = new PopupPane(popupPanel);
        popup.configInvoker(invoker, bgroup, deselectOnClose, orientation);
        return popup;
    }

    /**
     * Returns the popup menu that is an ancestor of the
     * given component. If the component is not a child of a popup,
     * return null.
     *
     * @param comp      the component to get the popup of
     * @return          the popup menu or null, if none found.
     */
    public static JPopupMenu getAncestorPopup(Component comp) {
        while (comp != null) {
            if (comp instanceof JPopupMenu) {
                return (JPopupMenu)comp;
            }
            comp = comp.getParent();
        }
        return null;
    }

    /**
     * Add the panel to the outer panel and box.
     * This fixes the size of the panel, so it does not
     * expand when resized.
     *
     * @param outer     panel to be added to.
     * @param inner     panel to add to the outer.
     * @return          the outer panel
     */
    public static JPanel box(JPanel outer, Component inner) {
        Box box = Box.createVerticalBox();
        box.add(inner);
        outer.add(box);
        return outer;
    }

    /**
     * Set the size of the given of the component.
     *
     * @param comp  component to set the size of
     * @param size  width and height of the panel
     * @return      the component that was given.
     */
    public static Component setSize(Component comp, Dimension size) {
        comp.setPreferredSize(size);
        comp.setMinimumSize(size);
        comp.setMaximumSize(size);
        comp.setSize(size);
        return comp;
    }

    /**
     * Draw the grid.
     * 
     * @param g2d       the graphics context
     */
    public static void drawGrid(Graphics2D g2d) {
        int w = (int)(MainControl.zoom*MainControl.size.width);
        int h = (int)(MainControl.zoom*MainControl.size.height);
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.setStroke( new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, new float[] {1.0f}, 0.0f) );
        for (int i = 0; i < w; i++) {
            if ((i % 10) == 0) {
                g2d.drawLine(i, 0, i, h);
            }
        }
        for (int j = 0; j < h; j++) {
            if ((j % 10) == 0) {
                g2d.drawLine(0, j, w, j);
            }
        }
    }

} // UIToolbox
