package com.bastisawesome.ZippyEdit.Config.PCFConversion;

public class PcfModuleExtData {
	private int id;
	
	private int rcsDeviceId;
	
	private int rcsCodeSetId;
	
	private int rcsBrandId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRcsDeviceId() {
		return rcsDeviceId;
	}

	public void setRcsDeviceId(int rcsDeviceId) {
		this.rcsDeviceId = rcsDeviceId;
	}

	public int getRcsCodeSetId() {
		return rcsCodeSetId;
	}

	public void setRcsCodeSetId(int rcsCodeSetId) {
		this.rcsCodeSetId = rcsCodeSetId;
	}

	public int getRcsBrandId() {
		return rcsBrandId;
	}

	public void setRcsBrandId(int rcsBrandId) {
		this.rcsBrandId = rcsBrandId;
	}
	
	@Override
	public String toString() {
		String str = "Module Ext Data:";
		str += "\n\tID: " + this.id;
		str += "\n\tRCS Device ID: " + this.rcsDeviceId;
		str += "\n\tRCS Code Set ID: " + this.rcsCodeSetId;
		str += "\n\tRCS Brand ID: " + this.rcsBrandId;
		
		return str;
	}
}
