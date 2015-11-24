package helper.extras;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.w3c.dom.*;

/**
 * This utility class provides tools to read XML into
 * memory from a file, and write XML to a file.
 */
public class XML {

    private XML() { } // Utility class

    /**
     * Print the given exception to
     * the given error output.
     *
     * @param   error   the exception thrown
     */
    private static void printError(Exception error) {
        System.err.println(error.getMessage());
        error.printStackTrace(System.err);
    }

    /**
     * Returns the XML document object model for
     * given a XML source file address.
     *
     * @param   src     the XML source file
     * @return          the XML document object model
     */
    public static Document getDOM(String src) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            return docBuilder.parse(src);
        } catch (Exception e) {
            printError(e);
        }
        return null;
    }

    /**
     * Returns the XML output for the given
     * document object model.
     *
     * @param   doc     the XML document object model.
     * @return          the XML output.
     */
    public static String getXMLString(Document doc) {
        try {
            StreamResult result = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), result);
            return result.getWriter().toString();
        } catch (Exception e) {
            printError(e);
        }
        return null;
    }

    /**
     * Outputs the XML docuemnt for the given
     * document object model to the given
     * file location.
     *
     * @param   doc     the XML document object model
     * @param   file    the output file path
     * @Return          true, if successful, otherwise false.
     */
    public static boolean outputXML(Document doc, String file) {
        String xml = XML.getXMLString(doc);
        if (xml != null) {
            try {
                FileIO.bytesToFile(xml.getBytes("UTF-8"), file);
                return true;
            } catch (Exception e) {
                printError(e);
            }
        } // xml != null
        return false;
    }

} // XML
