package com.bastisawesome.ZippyEdit.Config.PCFConversion;
import java.util.Arrays;
/*
 * TODO:
 * Make use of Stack<T>.lastElement()
 * Make use of PcfCnf.getLast[LIST_NAME]()
 */
import java.util.List;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PcfHandler extends DefaultHandler {
	private PcfConfig pcfCnf = new PcfConfig();
	
	// Containers for elements
	private Stack<String> elementStack = new Stack<String>();
	
	public PcfConfig getPcfCnf() {
		return this.pcfCnf;
	}
	
	@Override
	public void startElement(String uri, String localName,
			String qName, Attributes attributes) throws SAXException {
		// Store the parent element, if any
		String parentElement = "";
		// Obviously we can't add an element that doesn't exist
		if(this.elementStack.size() > 0) {
			// We use -1 here because the current element
			// hasn't been pushed onto the stack yet
			parentElement = this.elementStack.lastElement();
		}
		String gParentElement = "";
		if(this.elementStack.size() > 1) {
			gParentElement = this.elementStack.get(this.elementStack.size()-2);
		}
		/*
		 * Switch statement to fill all attributes for
		 * any element that contains attributes
		 */
		switch(qName.toLowerCase()) {
		case "configurationfile":
			// Set the top-level configuration information
			String mode = attributes.getValue("Mode");
			int version = Integer.parseInt(attributes.getValue("Version"));
			pcfCnf.setMode(mode);
			pcfCnf.setVersion(version);
			break;
		case "bitmap":
			// This tag is used in multiple locations
			// Using an if statement to protect against duplicate,
			// null information
			if(parentElement.equalsIgnoreCase("bitmaps")) {
				// Only add a new object to array if it is
				// a bitmap object, as defined by being stored
				// within the "bitmaps" tag
				PcfBitmap bitmap = new PcfBitmap();
				bitmap.setId(Integer.parseInt(attributes.getValue("id")));
				pcfCnf.addBitmap(bitmap);
				// Break statement here removes the need
				// for an if-else clause
				// The rest of the statements can be simple "if"s
				break;
			}
			// Break here in case of fall-through
			break;
		case "moduleextdata":
			// This tag is used in multiple locations
			// Using an if statement to protect against duplicate,
			// null information
			if(parentElement.equalsIgnoreCase("moduleextdatas")) {
				// Only add object to list if it exists within
				// the "moduleextdatas" tag
				PcfModuleExtData data = new PcfModuleExtData();
				data.setId(Integer.parseInt(attributes.getValue("id")));
				pcfCnf.addModExtData(data);
				// Break statement here removes the need for
				// an if-else clause
				break;
			}
			if(parentElement.equalsIgnoreCase("specificdbcode")) {
				PcfAction action = pcfCnf.getLastAction();
				PcfIrCode irCode = action.getIrCode();
				irCode.getSpecificDBCode().setModuleExtData(Integer.parseInt(attributes.getValue("id")));
			}
			// Breaking here in case of fall-through cases
			break;
		//------------------------
		// Action-class parsing
		//------------------------
		case "action":
			// Using an if statement to protect against duplicate,
			// null information
			if(parentElement.equalsIgnoreCase("actions")) {
				// Only add object to list if it exists within
				// the "actions" tag
				PcfAction pcfAction = new PcfAction();
				pcfAction.setId(Integer.parseInt(attributes.getValue("id")));
				pcfCnf.addAction(pcfAction);
				break;
			} else if(parentElement.equalsIgnoreCase("actionlist")) {
				// Implement Action ID for action lists
				PcfActionList actionList = pcfCnf.getLastActionList();
				actionList.addActionID(Integer.parseInt(attributes.getValue("id")));
				break;
			}
			
			// Breaking here in case of fall-through cases
			break;
		case "jumpalias":
			if(parentElement.equalsIgnoreCase(("action"))) {
				PcfAction action = pcfCnf.getLastAction();
				action.setJumpAlias(true);
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "ircode":
			if(parentElement.equalsIgnoreCase("action")) {
				PcfAction action = pcfCnf.getLastAction();
				action.setIrCode(true);
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "basicircode":
			if(gParentElement.equalsIgnoreCase("action")) {
				if(parentElement.equalsIgnoreCase("ircode")) {
					PcfAction action = pcfCnf.getLastAction();
					PcfIrCode irCode = action.getIrCode();
					irCode.setUseBasicIrCode(true);
					break;
				}
			}
			// Breaking here to prevent fall-through cases
			break;
		case "specificdbcode":
			if(gParentElement.equalsIgnoreCase("action")) {
				if(parentElement.equalsIgnoreCase("ircode")) {
					PcfAction action = pcfCnf.getLastAction();
					PcfIrCode irCode = action.getIrCode();
					irCode.setUseSpecificDBCode(true);
					break;
				}
			}
			// Break here to prevent fall-through cases
			break;
		case "keyalias":
			if(parentElement.equalsIgnoreCase("action")) {
				PcfAction action = pcfCnf.getLastAction();
				action.setKeyAlias(true);
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "moduleref":
			// Get the "grand" parent element from the stack
			gParentElement = this.elementStack.get(this.elementStack.size()-2);
			if(gParentElement.equalsIgnoreCase("action")) {
				if(parentElement.equalsIgnoreCase("jumpAlias")) {
					List<PcfAction> pcfActions = pcfCnf.getActions();
					// Find the last element in the actions list
					PcfAction lastAction = pcfActions.get(pcfActions.size()-1);
					// Get the jump alias from the last action
					PcfJumpAlias ja = lastAction.getJumpAlias();
					// Update action data
					ja.setModuleRef(Integer.parseInt(attributes.getValue("id")));
					break;
				}
				if(parentElement.equalsIgnoreCase("keyalias")) {
					PcfAction action = pcfCnf.getLastAction();
					action.setModuleRef(Integer.parseInt(attributes.getValue("id")));
					break;
				}
				if(parentElement.equalsIgnoreCase("buttonalias")) {
					PcfAction action = pcfCnf.getLastAction();
					action.setModuleRef(Integer.parseInt(attributes.getValue("id")));
					break;
				}
			}
			// Breaking here in case of fall-through cases
			break;
		case "pageref":
			// Get the "grand-parent" element
			gParentElement = this.elementStack.get(this.elementStack.size()-2);
			if(gParentElement.equalsIgnoreCase("action")) {
				if(parentElement.equalsIgnoreCase("jumpalias")) {
					List<PcfAction> pcfActions = pcfCnf.getActions();
					// Find the last element in the actions list
					PcfAction lastAction = pcfActions.get(pcfActions.size()-1);
					// Get the jump alias from the last action
					PcfJumpAlias ja = lastAction.getJumpAlias();
					// Update action data
					ja.setPageRef(Integer.parseInt(attributes.getValue("id")));
					break;
				}
			}
			// Breaking here in case of fall-through cases
			break;
		case "name":
			if(parentElement.equalsIgnoreCase("basicircode")) {
				List<PcfAction> pcfActions = pcfCnf.getActions();
				PcfAction lastAction = pcfActions.get(pcfActions.size()-1);
				PcfBasicIrCode basicIrCode = lastAction.getIrCode().getBasicIrCode();
				basicIrCode.setNameId(Integer.parseInt(attributes.getValue("id")));
				break;
			}
			// Breaking here in case of fall-through cases
			break;
		case "buttonalias":
			if(parentElement.equalsIgnoreCase("action")) {
				PcfAction action = pcfCnf.getLastAction();
				action.setButtonAlias(true);
				break;
			}
			// Breaking here in case of fall-through cases
			break;
		case "buttonref":
			if(gParentElement.equalsIgnoreCase("action")) {
				if(parentElement.equalsIgnoreCase("buttonalias")) {
					PcfAction action = pcfCnf.getLastAction();
					action.setButtonRef(Integer.parseInt(attributes.getValue("id")));
					break;
				}
			}
			// Breaking here in case  of fall-through cases
			break;
		case "whilepressed":
			// Requiring some extra effort here because fuck this XML
			if(gParentElement.equalsIgnoreCase("basicircode")) {
				if(parentElement.equalsIgnoreCase("duration")) {
					PcfAction action = pcfCnf.getLastAction();
					PcfIrCode irCode = action.getIrCode();
					irCode.getBasicIrCode().setWhilePressed(true);
					break;
				}
			}
			// Break here to catch fall-through cases
			break;
		case "actionlist":
			// Ensure we're only storing information for ActionLists
			if(parentElement.equalsIgnoreCase("actionlists")) {
				PcfActionList actionList = new PcfActionList();
				actionList.setId(Integer.parseInt(attributes.getValue("id")));
				pcfCnf.addActionList(actionList);
				break;
			}
			
			// Break here to catch fall-through cases
			break;
		}
		
		// Push the element onto the stack
		// This is used in the characters function
		// to test for parent element information
		this.elementStack.push(qName);
	}
	
	@Override
	public void endElement(String uri, String localName, String qName)
		throws SAXException {
		// Simply remove the last element in the stack
		this.elementStack.pop();
	}
	
	@Override
	public void characters(char[] ch, int start, int length)
		throws SAXException {
		// Fill elements with values from the XML file
		String value = new String(ch, start, length);
		if(value.length() == 0) return; // Ignore whitespace
		
		// Store the current element for manipulation
		String currentElement = this.elementStack.peek();
		// Store the element's parent for manipulation
		// Only contains one level above
		String currentElementParent = "";
		if(this.elementStack.size() > 1) // Checking that there are at least 2 elements
			currentElementParent = this.elementStack.get(this.elementStack.size()-2);
		String gParent = "";
		if(this.elementStack.size() > 2)
			gParent = this.elementStack.get(this.elementStack.size()-3);
		
		/*
		 * Fill in data for each element from tags
		 */
		switch(currentElement.toLowerCase()) {
		case "usercomments":
			pcfCnf.setUserComments(value);
			break;
		case "filename":
			// This element could potentially be used in multiple locations
			// Using an if statement to ensure it stores data in the correct
			// objects
			if(currentElementParent.equalsIgnoreCase("bitmap")) {
				// Store only bitmap-related information
				PcfBitmap bitmap = pcfCnf.getLastBitmap();
				bitmap.setFileName(value);
				// Break here to prevent the use of else-if clauses
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "notransparency":
			// This element could potentially be used in multiple locations
			// Using an if statement to ensure it stores data in the correct
			// objects
			if(currentElementParent.equalsIgnoreCase("bitmap")) {
				// Only store bitmap-related information
				PcfBitmap bitmap = pcfCnf.getBitmaps().get(pcfCnf.getBitmaps().size()-1);
				bitmap.setTransparency(PcfTransparency.NONE);
				// Break here to prevent else-if clauses
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "upperlefttransparency":
			// This element could potentially be used in multiple locations
			// Using an if statement to ensure it stores data in the correct
			// objects
			if(currentElementParent.equalsIgnoreCase("bitmap")) {
				// Only store bitmap-related information
				PcfBitmap bitmap = pcfCnf.getBitmaps().get(pcfCnf.getBitmaps().size()-1);
				bitmap.setTransparency(PcfTransparency.UPPERLEFT);
				// Break here to prevent else-if clauses
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "udp_rcsdeviceid":
			// This element might be used in multiple elements
			// Using an if statement to ensure data is stored
			// in the correct objects
			if(currentElementParent.equalsIgnoreCase("moduleextdata")) {
				// Only store moduleextdata-related information
				PcfModuleExtData data = pcfCnf.getModExtData().get(pcfCnf.getModExtData().size()-1);
				data.setRcsDeviceId(Integer.parseInt(value, 16));
				// Break here to prevent else-if clauses
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "udp_rcscodesetid":
			// This element might be used in multiple locations
			// Using an if statement to ensure only the correct
			// data is stored in the objects
			if(currentElementParent.equalsIgnoreCase("moduleextdata")) {
				// Store only moduleextdata-related information
				PcfModuleExtData data = pcfCnf.getModExtData().get(pcfCnf.getModExtData().size()-1);
				data.setRcsCodeSetId(Integer.parseInt(value, 16));
				// Break here to prevent else-if clauses
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "udp_rcsbrandid":
			// This element might be used in multiple locations
			// Using an if statement to ensure only the correct
			// data is stored in the objects
			if(currentElementParent.equalsIgnoreCase("moduleextdata")) {
				// Store only moduleextdata-related information
				PcfModuleExtData data = pcfCnf.getModExtData().get(pcfCnf.getModExtData().size()-1);
				data.setRcsBrandId(Integer.parseInt(value, 16));
				// Break here to prevent else-if clauses
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "duration":
			// Using an if statement to ensure only the correct
			// data is stored in the objects
			if(gParent.equalsIgnoreCase("basicircode")) {
				if(currentElementParent.equalsIgnoreCase("duration")) {
					// Only parse as an integer if it is an integer
					List<PcfAction> actionList = pcfCnf.getActions();
					PcfAction lastAction = actionList.get(actionList.size()-1);
					PcfIrCode irCode = lastAction.getIrCode();
					PcfBasicIrCode basicIrCode = irCode.getBasicIrCode();
					try {
						// Set the duration value
						basicIrCode.setDuration(Integer.parseInt(value));
					} catch(NumberFormatException e) {
						// If it isn't an integer then it must be set
						// to run while pressed
						basicIrCode.setWhilePressed(true);
					}
					break;
				}
			}
			if(gParent.equalsIgnoreCase("specificdbcode")) {
				if(currentElementParent.equalsIgnoreCase("duration")) {
					PcfAction action = pcfCnf.getLastAction();
					PcfIrCode irCode = action.getIrCode();
					try {
						// Set the duration value
						irCode.getSpecificDBCode().setDuration(Integer.parseInt(value));
					} catch(NumberFormatException e) {
						// Legacy code that proves that this shit is broken
						// This essentially shows how I initially did it, but
						// then it turned out that there are nested duration
						// tags and I wanted to scream.
						// If it isn't an integer then it must be
						// set to run while pressed
						irCode.getSpecificDBCode().setWhilePressed(true);
					}
					break;
				}
			}
			// Break here to prevent fall-through cases
			break;
		case "code":
			if(currentElementParent.equalsIgnoreCase("basicircode")) {
				List<PcfAction> actionList = pcfCnf.getActions();
				PcfAction lastAction = actionList.get(actionList.size()-1);
				PcfIrCode irCode = lastAction.getIrCode();
				PcfBasicIrCode basicIrCode = irCode.getBasicIrCode();
				basicIrCode.setCode(value);
				break;
			}
			// Break here to prevent fall-through cases
			break;
		case "keyid":
			if(gParent.equalsIgnoreCase("action")) {
				if(currentElementParent.equalsIgnoreCase("keyalias")) {
					PcfAction action = pcfCnf.getLastAction();
					action.setKeyId(Integer.parseInt(value));
					break;
				}
			}
			// Breaking here to prevent fall-through cases
			break;
		case "udp_functionid":
			if(currentElementParent.equalsIgnoreCase("specificdbcode")) {
				PcfAction action = pcfCnf.getLastAction();
				PcfIrCode irCode = action.getIrCode();
				irCode.getSpecificDBCode().setUdpFunctionId(Integer.parseInt(value));
				break;
			}
			// Breaking here to prevent fall-through cases
			break;
		case "delay":
			if(currentElementParent.equalsIgnoreCase("action")) {
				PcfAction action = pcfCnf.getLastAction();
				action.setDelay(Integer.parseInt(value));
				break;
			}
			// Breaking here to prevent fall-through cases
			break;
		case "beep":
			if(currentElementParent.equalsIgnoreCase("action")) {
				// Some Java 8 magic to convert a string array to
				// an integer array.
				// Magic!
				int[] values = Arrays.stream(value.split("\\s")).mapToInt(Integer::parseInt).toArray();
				PcfAction action = pcfCnf.getLastAction();
				action.setBeep(values[0], values[1], values[2]);
			}
			// Break here to prevent fall-through cases
			break;
		case "id":
			if(currentElementParent.equalsIgnoreCase("string")) {
				// Add a new string to the strings map
				
			}
		}
	}
}