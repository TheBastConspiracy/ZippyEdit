package com.bastisawesome.ZippyEdit.Config.PCFConversion;

public class PcfAction {
	// Enabled features for action
	private boolean hasJumpAlias;
	private boolean hasIrCode;
	private boolean hasKeyAlias;
	private boolean hasButtonAlias;
	private boolean doesBeep;
	
	private int id;
	// Jump alias
	private PcfJumpAlias pcfJumpAlias = new PcfJumpAlias();
	
	// IR Codes
	private PcfIrCode irCode = new PcfIrCode();
	
	// Key alias
	private int moduleRef;
	private int keyId;
	
	// Button alias
	private int buttonRef;
	
	// Delay
	private int delay; // In milliseconds
	
	// BEEP BEEP MOTHERFUCKER!
	private int duration; // In milliseconds
	private int frequency; // In hertz
	private int volume;	// In decibels
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public PcfJumpAlias getJumpAlias() {
		return pcfJumpAlias;
	}
	public void setJumpAlias(PcfJumpAlias pcfJumpAlias) {
		this.pcfJumpAlias = pcfJumpAlias;
	}
	
	public PcfIrCode getIrCode() {
		return irCode;
	}
	
	public void setIrCode(PcfIrCode irCode) {
		this.irCode = irCode;
	}
	
	public int getModuleRef() {
		return this.moduleRef;
	}
	
	public void setModuleRef(int modRef) {
		this.moduleRef = modRef;
	}
	
	public int getKeyId() {
		return this.keyId;
	}
	
	public void setKeyId(int keyId) {
		this.keyId = keyId;
	}
	
	public int getButtonRef() {
		return this.buttonRef;
	}
	
	public void setButtonRef(int buttonRef) {
		this.buttonRef = buttonRef;
	}
	
	public boolean isJumpAlias() {
		return hasJumpAlias;
	}
	
	public void setJumpAlias(boolean hasJumpAlias) {
		this.hasJumpAlias = hasJumpAlias;
	}
	
	public boolean isIrCode() {
		return hasIrCode;
	}
	
	public void setIrCode(boolean hasIrCode) {
		this.hasIrCode = hasIrCode;
	}
	
	public boolean isKeyAlias() {
		return hasKeyAlias;
	}
	
	public void setKeyAlias(boolean hasKeyAlias) {
		this.hasKeyAlias = hasKeyAlias;
	}
	
	public boolean isButtonAlias() {
		return this.hasButtonAlias;
	}
	
	public void setButtonAlias(boolean hasButtonAlias) {
		this.hasButtonAlias = hasButtonAlias;
	}
	
	public int getDelay() {
		return this.delay;
	}
	
	public void setDelay(int delay) {
		this.delay = delay;
	}
	
	public boolean getDoesBeep() {
		return this.doesBeep;
	}
	
	public void setDoesBeep(boolean doesBeep) {
		this.doesBeep = doesBeep;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getFrequency() {
		return this.frequency;
	}
	
	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	
	public int getVolume() {
		return this.volume;
	}
	
	public void setVolume(int volume) {
		this.volume = volume;
	}
	
	public void setBeep(int duration, int frequency, int volume) {
		this.duration = duration;
		this.frequency = frequency;
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		String str = "Action";
		str += "\n\tid: " + this.id;
		
		if(this.hasJumpAlias)
			str += "\n\t" + this.pcfJumpAlias;
		
		if(this.hasIrCode)
			// Only print if there is something to print
			str += "\n\t" + this.irCode;
		
		if(this.hasKeyAlias) {
			str += "\n\tKey Alias:";
			str += "\n\t\tModule reference: " + this.moduleRef;
			str += "\n\t\tKey ID: " + this.keyId;
		}
		
		if(this.hasButtonAlias) {
			str += "\n\tButton Alias:";
			str += "\n\t\tModule reference: " + this.moduleRef;
			str += "\n\t\tButton ID: " + this.buttonRef;
		}
		
		if(this.delay > 0) {
			str += "\n\tDelay: " + this.delay;
		}
		
		return str;
	}
}