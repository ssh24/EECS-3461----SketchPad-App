package helper.extras;

import java.io.*;

/**
 * General utility methods useful
 * for working with byte arrays
 */
public class FileIO {

    private static FileIO self = new FileIO();

    private FileIO() { } // Utility class

    /**
     * Read the given file into an array of bytes
     *
     * @param   filename    path of the file to read
     * @return              array consisting of the file's content
     */
    public static byte[] fileToBytes(String filename) throws Exception {
        FileInputStream       in  = new FileInputStream(filename);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (;;) {
            int c = in.read();
            if (c == -1) {break;}
            out.write(c);
        }
        in.close();
        return out.toByteArray();
    }

    /**
     * Create a file with the given name and
     * write the given array of bytes to it
     *
     * @param   data        the data to write
     * @param   filename    path of the file to write to
     */
    public static void bytesToFile(byte[] data, String filename) throws Exception {
        FileOutputStream out = new FileOutputStream(filename);
        out.write(data);
        out.close();
    }

    /**
     * Reads HTML document at the given address and
     * returns its content as a string.
     *
     * @param address   of the HTML document to read
     * @return          the content of the HTML document
     */
    public static String getHTML(String address) {
        InputStream fis = null;
        BufferedReader reader = null;
        String html = "";
        try {
            fis = self.getClass().getResourceAsStream(address);
            reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            for (;;) {
                line = reader.readLine();
                if (line == null) {break;}
                html += line;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } finally {
            try {
                reader.close();
                fis.close();
            } catch (IOException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return html;
    }

} // FileIO
