package helper.extras;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/********The ImageFileChooser class *******/
public class ImageFileChooser extends JFileChooser 
{
	/********private variable *********/
	private static final long serialVersionUID = 1L;

	/********public constructor *********/
	public ImageFileChooser() 
	{
		setAcceptAllFileFilterUsed(false);
		configExtensions();
	}

	/********private method to choose filename extension *********/
	private void configExtensions() 
	{
		this.addChoosableFileFilter(new FileNameExtensionFilter("JPEG (*.jpg;*.jpeg;*.jpe;*.jfif)", "jpg", "jpeg", "jpe", "jfif"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("PNG (*.png)", "png"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("GIF (*.gif)", "gif"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("TIFF (*.tif;*.tiff)", "tif", "tiff"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("Monochrome Bitmap (*.bmp;*.dib)", "bmp", "dib"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("16 Color Bitmap (*.bmp;*.dib)", "bmp", "dib"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("256 Color Bitmap (*.bmp;*.dib)", "bmp", "dib"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("24-bit Bitmap (*.bmp;*.dib)", "bmp", "dib"));

	}


	/********public method to choose filename extension *********/
	public File getSelectedFileWithExtension() 
	{
		File file = super.getSelectedFile();
		if (getFileFilter() instanceof FileNameExtensionFilter) 
		{
			String[] exts = ((FileNameExtensionFilter) getFileFilter()).getExtensions();
			String nameLower = file.getName().toLowerCase();
			for (String ext : exts) 
			{ 
				if (nameLower.endsWith('.' + ext.toLowerCase()))
				{
					return file; 
				}
			}
			
			file = new File(file.toString() + '.' + exts[0]);
		}
		return file;
	}
}