package helper.extras;

import java.awt.*;


import javax.swing.*;

import view.basics.panes.*;


/********The UIToolbox class creates a toolbar for a button. *******/
public class UIPopUp
{
    public static JPopupMenu attachPopupPane(AbstractButton invoker, Component popupPanel, ButtonGroup bgroup, int orientation, boolean deselectOnClose) {
        PopupPane popup = new PopupPane(popupPanel);
        popup.configInvoker(invoker, bgroup, deselectOnClose, orientation);
        return popup;
    }
} 
