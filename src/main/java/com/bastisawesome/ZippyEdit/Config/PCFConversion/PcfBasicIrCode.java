package com.bastisawesome.ZippyEdit.Config.PCFConversion;

public class PcfBasicIrCode {
	private boolean whilePressed;
	private int duration; // In milliseconds
	private String code;
	private int nameId;
	
	public boolean isWhilePressed() {
		return whilePressed;
	}
	public void setWhilePressed(boolean whilePressed) {
		this.whilePressed = whilePressed;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getNameId() {
		return nameId;
	}
	public void setNameId(int nameId) {
		this.nameId = nameId;
	}
	
	@Override
	public String toString() {
		String str = "Basic IR Code";
		str += "\n\tDuration: ";
		if(this.whilePressed)
			str += "while pressed";
		else
			str += this.duration + " milliseconds";
		str += "\n\tCode: " + this.code;
		str += "\n\tName id: " + this.nameId;
		return str;
	}
}