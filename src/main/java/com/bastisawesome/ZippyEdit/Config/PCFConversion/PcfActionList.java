package com.bastisawesome.ZippyEdit.Config.PCFConversion;

import java.util.ArrayList;
import java.util.List;

public class PcfActionList {
	private int id;
	
	private List<Integer> actionIDs = new ArrayList<Integer>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getActionIDs() {
		return actionIDs;
	}

	public void setActionIDs(List<Integer> actionIDs) {
		this.actionIDs = actionIDs;
	}
	
	public void addActionID(int actionID) {
		actionIDs.add(actionID);
	}
	
	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("ActionList");
		string.append("\n\tid: " + this.id);
		
		for(int i : this.actionIDs) {
			string.append("\n\taction id: " + i);
		}
		
		return string.toString();
	}
}