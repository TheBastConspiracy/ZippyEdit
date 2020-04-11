package com.bastisawesome.ZippyEdit.Config.PCFConversion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Contains data from the original Pronto config files
 * @author bast
 *
 */
public class PcfConfig {
	// Top-level attributes
	/**
	 * TODO: Fill this in
	 */
	private String mode = "ADVANCED";
	
	/**
	 * Version of configuration software used
	 * Currently always stores 3, but might change
	 * if user imports from older versions of 
	 * Pronto Edit (NG/original)
	 */
	private int version = 3;
	
	/**
	 * Contains all comments defined by the user
	 * in Pronto Edit
	 * TODO: Test this further to see if this is correctly
	 * defined. For all I know, it's not.
	 */
	private String userComments;
	
	/**
	 * List of all bitmaps used by the configuration
	 */
	private List<PcfBitmap> bitmaps = new ArrayList<PcfBitmap>();
	
	private List<PcfModuleExtData> modExtData = new ArrayList<PcfModuleExtData>();
	
	private List<PcfAction> pcfActions = new ArrayList<PcfAction>();
	
	private List<PcfActionList> pcfActionLists = new ArrayList<PcfActionList>();
	
	// String stuff
	private Map<Integer, String> strings = new HashMap<Integer, String>();
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUserComments() {
		return userComments;
	}

	public void setUserComments(String userComments) {
		this.userComments = userComments;
	}

	public List<PcfBitmap> getBitmaps() {
		return bitmaps;
	}

	public void addBitmap(PcfBitmap bitmap) {
		this.bitmaps.add(bitmap);
	}
	
	public List<PcfModuleExtData> getModExtData() {
		return modExtData;
	}
	
	public void addModExtData(PcfModuleExtData modExtData) {
		this.modExtData.add(modExtData);
	}
	
	public List<PcfAction> getActions() {
		return this.pcfActions;
	}
	
	public void addAction(PcfAction pcfAction) {
		this.pcfActions.add(pcfAction);
	}
	
	public PcfBitmap getLastBitmap() {
		return this.bitmaps.get(this.bitmaps.size()-1);
	}
	
	public PcfModuleExtData getLastModuleExtData() {
		return this.modExtData.get(this.modExtData.size()-1);
	}
	
	public PcfAction getLastAction() {
		return this.pcfActions.get(this.pcfActions.size()-1);
	}
	
	public List<PcfActionList> getActionLists() {
		return this.pcfActionLists;
	}
	
	public PcfActionList getLastActionList() {
		return this.pcfActionLists.get(this.pcfActionLists.size()-1);
	}
	
	public void addActionList(PcfActionList actionList) {
		this.pcfActionLists.add(actionList);
	}
	
	public Map<Integer, String> getStrings() {
		return this.strings;
	}
	
	public void addString(int id, String value) {
		this.strings.put(id, value);
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "Mode: " + this.mode;
		str += "\nVersion: " + this.version;
		str += "\nUser comments: " + this.userComments;
		
		// Populate string with elements from the bitmaps list
		for(PcfBitmap bitmap : bitmaps)
			str += "\n\t" + bitmap.toString();
		
		// Populate string with elements from the module extension data list
		for(PcfModuleExtData data : modExtData)
			str += "\n\t" + data.toString();
		
		return str;
	}
}