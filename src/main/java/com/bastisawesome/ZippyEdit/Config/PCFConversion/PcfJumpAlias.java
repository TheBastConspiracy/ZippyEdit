package com.bastisawesome.ZippyEdit.Config.PCFConversion;

public class PcfJumpAlias {
	private int moduleRef;
	private int pageRef;
	
	public int getModuleRef() {
		return moduleRef;
	}
	public void setModuleRef(int moduleRef) {
		this.moduleRef = moduleRef;
	}
	public int getPageRef() {
		return pageRef;
	}
	public void setPageRef(int pageRef) {
		this.pageRef = pageRef;
	}
	
	@Override
	public String toString() {
		String str = "Jump Alias";
		str += "\n\tModule ref: " + this.moduleRef;
		str += "\n\tPage ref: " + this.pageRef;
		return str;
	}
}