package view.basics.panes;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageFileChooser extends JFileChooser {
	public ImageFileChooser() {
		setAcceptAllFileFilterUsed(false);
		configExtensions();
	}

	private void configExtensions() {
		this.addChoosableFileFilter(new FileNameExtensionFilter(
				"JPEG (*.jpg;*.jpeg;*.jpe;*.jfif)", "jpg", "jpeg", "jpe",
				"jfif"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("PNG (*.png)",
				"png"));
		this.addChoosableFileFilter(new FileNameExtensionFilter("GIF (*.gif)",
				"gif"));
		this.addChoosableFileFilter(new FileNameExtensionFilter(
				"TIFF (*.tif;*.tiff)", "tif", "tiff"));
		this.addChoosableFileFilter(new FileNameExtensionFilter(
				"Monochrome Bitmap (*.bmp;*.dib)", "bmp", "dib"));
		this.addChoosableFileFilter(new FileNameExtensionFilter(
				"16 Color Bitmap (*.bmp;*.dib)", "bmp", "dib"));
		this.addChoosableFileFilter(new FileNameExtensionFilter(
				"256 Color Bitmap (*.bmp;*.dib)", "bmp", "dib"));
		this.addChoosableFileFilter(new FileNameExtensionFilter(
				"24-bit Bitmap (*.bmp;*.dib)", "bmp", "dib"));

	}

	/**
	 * Works around a JFileChooser limitation, that the selected file when
	 * saving is returned exactly as typed and doesn't take into account the
	 * selected file filter.
	 */
	public File getSelectedFileWithExtension() {
		File file = super.getSelectedFile();
		if (getFileFilter() instanceof FileNameExtensionFilter) 
		{
			String[] exts = ((FileNameExtensionFilter) getFileFilter())
					.getExtensions();
			String nameLower = file.getName().toLowerCase();
			for (String ext : exts) { // check if it already has a valid
										// extension
				if (nameLower.endsWith('.' + ext.toLowerCase())) {
					return file; // if yes, return as-is
				}
			}
			// if not, append the first one from the selected filter
			file = new File(file.toString() + '.' + exts[0]);
		}
		return file;
	}
}