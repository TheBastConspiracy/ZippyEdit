package com.bastisawesome.ZippyEdit.Config.PCFConversion;

public class PcfSpecificDBCode {
	private boolean whilePressed;
	
	private int udpFunctionId;
	private int moduleExtDataId;
	private int duration; // In milliseconds
	
	public boolean isWhilePressed() {
		return whilePressed;
	}
	
	public void setWhilePressed(boolean whilePressed) {
		this.whilePressed = whilePressed;
	}
	
	public int getUdpFunctionId() {
		return udpFunctionId;
	}
	
	public void setUdpFunctionId(int udpFunctionId) {
		this.udpFunctionId = udpFunctionId;
	}
	
	public int getModuleExtData() {
		return moduleExtDataId;
	}
	
	public void setModuleExtData(int moduleExtData) {
		this.moduleExtDataId = moduleExtData;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Override
	public String toString() {
		String str = "Specific DB Code";
		str += "\n\tDuration: ";
		if(this.whilePressed)
			str += "while pressed";
		else
			str += this.duration + " milliseconds";
		str += "\n\tUDP Function ID: " + this.udpFunctionId;
		str += "\n\tModule extension data ID: " + this.moduleExtDataId;
		return str;
	}
}