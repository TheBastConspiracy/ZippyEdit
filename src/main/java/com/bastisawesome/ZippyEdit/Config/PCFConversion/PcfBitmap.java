package com.bastisawesome.ZippyEdit.Config.PCFConversion;

public class PcfBitmap {
	/**
	 * Unique identifier for the bitmap
	 * Used to determine which bitmap is being
	 * used as the image for the button
	 */
	private int id;
	
	/**
	 * Name of the bitmap file to load
	 */
	private String fileName;
	
	/**
	 * Type of transparency the bitmap uses
	 */
	private PcfTransparency transparency = PcfTransparency.NONE;

	/**
	 * Returns the unique ID of the bitmap
	 * @return Unique identifier for the bitmap
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Defines the unique ID
	 * @param id Unique identifier for the bitmap
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the name of the bitmap file to load
	 * @return Name of the bitmap file to load
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Sets the name of the bitmap file to load
	 * @param fileName Name of the bitmap file to load
	 */
	public void setFileName(String fileName) {
		// Fix paths to follow standard Unix styles
		String fixedFileName = fileName.replace('\\', '/');
		
		this.fileName = fixedFileName;
	}
	
	/**
	 * Returns the transparency setting for the bitmap
	 * @return Transparency setting for the bitmap
	 */
	public PcfTransparency getTransparency() {
		return transparency;
	}

	/**
	 * Sets the transparency setting for the bitmap
	 * @param transparency Transparency setting for the bitmap
	 */
	public void setTransparency(PcfTransparency transparency) {
		this.transparency = transparency;
	}
	
	@Override
	public String toString() {
		String str = "Bitmap:";
		str += "\n\tID: " + this.id;
		str += "\n\tFile name: " + this.fileName;
		str += "\n\tTransparency: " + this.transparency.toString();
		
		return str;
	}
}