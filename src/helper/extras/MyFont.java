package helper.extras;

import java.awt.*;
import java.util.*;

/**
 * This class provides access to custom font faces.
 * Custom font faces include:
 *  - Open Sans Regular
 *  - Open Sans Light
 *  - Open Sans Semibold
 *  - Open Sans Bold
 *  - Canvas
 */
public class MyFont {

    private static final MyFont my;                                                         // Reference to singleton instance
    private static final String FONT_PATH           = "/assets/fonts";                      // Folder path to fonts files
    private static final String REGULAR_FONT_URI    = FONT_PATH + "/OpenSans-Regular.ttf";  // TrueType fonts: OpenSans Regular
    private static final String LIGHT_FONT_URI      = FONT_PATH + "/OpenSans-Light.ttf";    // TrueType fonts: OpenSans Light
    private static final String SEMIBOLD_FONT_URI   = FONT_PATH + "/OpenSans-Semibold.ttf"; // TrueType fonts: OpenSans Semibold
    private static final String BOLD_FONT_URI       = FONT_PATH + "/OpenSans-Bold.ttf";     // TrueType fonts: OpenSans Bold
    private static final String ICON_FONT_URI       = FONT_PATH + "/Canvas.ttf";            // TrueType fonts: Canvas

    // Setup the fonts families
    public static final Font REGULAR_FONT;
    public static final Font LIGHT_FONT;
    public static final Font SEMIBOLD_FONT;
    public static final Font BOLD_FONT;
    public static final Font ICON_FONT;
    static {
        my = new MyFont();
        REGULAR_FONT  = my.makeFont(REGULAR_FONT_URI);
        LIGHT_FONT    = my.makeFont(LIGHT_FONT_URI);
        SEMIBOLD_FONT = my.makeFont(SEMIBOLD_FONT_URI);
        BOLD_FONT     = my.makeFont(BOLD_FONT_URI);
        ICON_FONT     = my.makeFont(ICON_FONT_URI);
    }

    // A mapping of special icon names to the icon symbols (unicode)
    public static final Map<String, String> ICONS = new HashMap<String, String>();
    static {
        ICONS.put("NEW",             "\ue000");
        ICONS.put("OPEN",            "\ue001");
        ICONS.put("SAVE",            "\ue002");
        ICONS.put("PRINT",           "\ue003");
        ICONS.put("UNDO",            "\ue004");
        ICONS.put("REDO",            "\ue005");
        ICONS.put("ZOOM-IN",         "\ue006");
        ICONS.put("ZOOM-OUT",        "\ue007");
        ICONS.put("RULER",           "\ue008");
        ICONS.put("LIBRARY",         "\ue009");
        ICONS.put("GEAR",            "\ue00a");
        ICONS.put("SELECT",          "\ue00b");
        ICONS.put("EDIT",            "\ue00c");
        ICONS.put("REMOVE",          "\ue00d");
        ICONS.put("GESTURE",         "\ue00e");
        ICONS.put("PENCIL",          "\ue00f");
        ICONS.put("PALETTE",         "\ue010");
        ICONS.put("BRUSH",           "\ue011");
        ICONS.put("MARKER",          "\ue012");
        ICONS.put("EYEDROPPER",      "\ue013");
        ICONS.put("BRUSH2",          "\ue014");
        ICONS.put("PAINT",           "\ue015");
        ICONS.put("WAND",            "\ue016");
        ICONS.put("BUCKET",          "\ue017");
        ICONS.put("ERASER",          "\ue018");
        ICONS.put("PEN",             "\ue019");
        ICONS.put("WAND2",           "\ue01a");
        ICONS.put("SHAPES",          "\ue01b");
        ICONS.put("IMAGE",           "\ue01c");
        ICONS.put("TEXT",            "\ue01d");
        ICONS.put("LINE",            "\ue01e");
        ICONS.put("FREEHAND",        "\ue01f");
        ICONS.put("RECTANGLE",       "\ue020");
        ICONS.put("ROUNDRECT",       "\ue021");
        ICONS.put("OVAL",            "\ue022");
        ICONS.put("POLYGON",         "\ue023");
        ICONS.put("CURVE",           "\ue024");
        ICONS.put("PAN",             "\ue025");
        ICONS.put("ROTATE-LEFT",     "\ue026");
        ICONS.put("ROTATE-RIGHT",    "\ue027");
        ICONS.put("RESIZE",          "\ue028");
        ICONS.put("FLIP-VERT",       "\ue029");
        ICONS.put("FLIP-HORIZ",      "\ue02a");
        ICONS.put("CROP",            "\ue02b");
        ICONS.put("FONTSIZE",        "\ue02c");
        ICONS.put("BOLD",            "\ue02d");
        ICONS.put("ITALIC",          "\ue02e");
        ICONS.put("UNDERLINE",       "\ue02f");
        ICONS.put("STRIKETHROUGH",   "\ue030");
        ICONS.put("OMEGA",           "\ue031");
        ICONS.put("SUBSCRIPT",       "\ue032");
        ICONS.put("SUPERSCRIPT",     "\ue033");
        ICONS.put("UNLINK",          "\ue034");
        ICONS.put("LINK",            "\ue035");
        ICONS.put("LAYERS",          "\ue036");
        ICONS.put("BRING-FORW",      "\ue037");
        ICONS.put("BRING-FRONT",     "\ue038");
        ICONS.put("SEND-BACKW",      "\ue039");
        ICONS.put("SEND-BACK",       "\ue03a");
        ICONS.put("ADDSHAPE",        "\ue03b");
        ICONS.put("SUBTRACTSHAPE",   "\ue03c");
        ICONS.put("INTERSECTSHAPE",  "\ue03d");
        ICONS.put("EXCLUDESHAPE",    "\ue03e");
        ICONS.put("MERGESHAPES",     "\ue03f");
        ICONS.put("ROTATE-CW",       "\ue040");
        ICONS.put("ROTATE-CCW",      "\ue041");
        ICONS.put("RESIZEH",         "\ue042");
        ICONS.put("RESIZEV",         "\ue043");
        ICONS.put("LASSO",           "\ue044");
        ICONS.put("TRANSFORM",       "\ue045");
        ICONS.put("BACK",            "\ue046");
        ICONS.put("SEARCH",          "\ue047");
        ICONS.put("LOADING",         "\ue048");
        ICONS.put("ARROW-DOWN",      "\ue049");
        ICONS.put("ARROW-DOWN",      "\ue049");
        ICONS.put("ARROW-RIGHT",     "\ue04a");
        ICONS.put("BIG-ARROW-RIGHT", "\ue04b");
        ICONS.put("BIG-ARROW-DOWN",  "\ue04c");
        ICONS.put("HELP",            "\ue04d");
        ICONS.put("FONT",            "\ue04e");
        ICONS.put("GROUP",           "\ue04f");
        ICONS.put("UNGROUP",         "\ue050");
        ICONS.put("CHECKED",         "\ue600");
        ICONS.put("UNCHECKED",       "\ue601");
    }

    // Load and create font objects
    private MyFont() { }
    private Font makeFont(String fontURI) {
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, this.getClass().getResourceAsStream(fontURI));
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(font);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
        return font;
    }

}
