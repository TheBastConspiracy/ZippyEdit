package com.bastisawesome.ZippyEdit.Config.PCFConversion;

public class PcfIrCode {
	private boolean useBasicIrCode;
	private boolean useSpecificDBCode;
	
	private PcfBasicIrCode basicIrCode = new PcfBasicIrCode();
	private PcfSpecificDBCode specificDBCode = new PcfSpecificDBCode();
	
	public PcfBasicIrCode getBasicIrCode() {
		return basicIrCode;
	}
	
	public void setBasicIrCode(PcfBasicIrCode basicIrCode) {
		this.basicIrCode = basicIrCode;
	}
	
	public boolean usesBasicIrCode() {
		return this.useBasicIrCode;
	}
	
	public void setUseBasicIrCode(boolean useBasicIrCode) {
		this.useBasicIrCode = useBasicIrCode;
	}
	
	public boolean usesSpecificDBCode() {
		return this.useSpecificDBCode;
	}
	
	public void setUseSpecificDBCode(boolean useSpecificDBCode) {
		this.useSpecificDBCode = useSpecificDBCode;
	}
	
	public PcfSpecificDBCode getSpecificDBCode() {
		return this.specificDBCode;
	}
	
	public void setSpecificDBCode(PcfSpecificDBCode specificDBCode) {
		this.specificDBCode = specificDBCode;
	}
	
	@Override
	public String toString() {
		String str = "IR Code";
		if(this.useBasicIrCode)
			str += "\n\t" + this.basicIrCode;
		if(this.useSpecificDBCode)
			str += "\n\t" + this.specificDBCode;
		return str;
	}
}