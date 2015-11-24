package helper.extras;

import java.awt.*;

import javax.swing.*;
import javax.swing.UIManager.*;

/**
 * This class defines the theme colors of the
 * user interface.
 */
public class UITheme {

    private UITheme() { } // static class, no constructor
    
    /**
     * Set the look and feel of the user interface.
     * Configures the UIManager, colors and styling.
     */
    public static void setLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // Configure theming colors. 
            // {@see http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/_nimbusDefaults.html}
            
/*            UIManager.put("nimbusBase", Color.GRAY);
            UIManager.put("control", Color.GRAY);
            UIManager.put("text", Color.BLACK);
            UIManager.put("defaultFont", MyFont.REGULAR_FONT.deriveFont(12.f));
            UIManager.put("nimbusLightBackground", Color.DARK_GRAY);
            UIManager.put("nimbusFocus", new Color(255, 255, 255, 0));
            UIManager.put("nimbusSelectedText", Color.WHITE);
            UIManager.put("nimbusSelectionBackground", Color.LIGHT_GRAY);
            UIManager.put("controlLHighlight", Color.DARK_GRAY);
            UIManager.put("TabbedPane.foreground", Color.BLACK);
            UIManager.put("ToolBar.opaque", false);
            UIManager.put("ToolBarSeparator.textForeground", new Color(110, 110, 110));
            UIManager.put("ToolTip.background", Color.DARK_GRAY);
            UIManager.put("ToolTip.foreground", Color.BLACK);*/

        } catch (Exception e) {}
    }
}
