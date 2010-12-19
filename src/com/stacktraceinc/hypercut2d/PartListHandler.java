package com.stacktraceinc.hypercut2d;

import java.util.ArrayList;
import java.util.List;

public class PartListHandler extends AbstractModelObject {
	public static final String RECTANGLE = "Sta\u010Diakampis";
	public static final String SQUARE = "Kvadratas";
	
	private List<Part> parts = new ArrayList<Part>();
	
	public List<Part> getParts() { return parts; }
	
	public PartListHandler() {
	}
	
	public void createNewPart() {
		// Automatic part creation based on previously selected?
		addPart(new Rectangle("Test", RECTANGLE, 10, 20));
	}
	
	public void clonePart(int index) {
		Part part = parts.get(index);
		addPart(new Part(part.getName(), part.getForm(), part.getFirstValue(), part.getSecondValue()));
	}
	
	public void erasePart(int index) {
		List<Part> oldValue = parts;
		parts = new ArrayList<Part>(parts);
		parts.remove(index);
		firePropertyChange("parts", oldValue, parts);
	}
	
	public void addPart(Part part) {
		List<Part> oldValue = parts;
		parts = new ArrayList<Part>(parts);
		parts.add(part);
		firePropertyChange("parts", oldValue, parts);
	}
}
