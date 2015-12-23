package helper.extras;

import java.io.*;

/********The FileIO class *******/
public class FileIO 
{

    private static FileIO self = new FileIO();

    /********private constructor *********/
    private FileIO() 
    { 
    	
    } 

  
    /********public method that reads the given file into an array of bytes *********/
    public static byte[] fileToBytes(String filename) throws Exception
    {
        FileInputStream       in  = new FileInputStream(filename);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        for (;;) 
        {
            int c = in.read();
            if (c == -1) 
            {
            	break;
            }
            out.write(c);
        }
        in.close();
        return out.toByteArray();
    }


    /********public method that create a file with the given name and write the given array of bytes *********/
    public static void bytesToFile(byte[] data, String filename) throws Exception 
    {
        FileOutputStream out = new FileOutputStream(filename);
        out.write(data);
        out.close();
    }

    
    /********public method that reads HTML document at the given address *********/
    public static String getHTML(String address) 
    {
        InputStream fis = null;
        BufferedReader reader = null;
        String html = "";
        try 
        {
            fis = self.getClass().getResourceAsStream(address);
            reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            for (;;) 
            {
                line = reader.readLine();
                if (line == null) 
                {
                	break;
                }
                html += line;
            }
        }
        catch (FileNotFoundException e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } 
        catch (IOException e) 
        {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        } 
        finally 
        {
            try 
            {
                reader.close();
                fis.close();
            } 
            catch (IOException e) 
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }
        return html;
    }
} 
