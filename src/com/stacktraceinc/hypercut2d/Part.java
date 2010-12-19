package com.stacktraceinc.hypercut2d;

public class Part extends AbstractModelObject {
	private String name;
	private String form;
	private int firstValue = 0;
	private int secondValue = 0;
	// TODO: Coordinates + Rotation

	public String getForm() { return form; }
	public String getName() { return name; }
	public int getFirstValue() { return firstValue; }
	public int getSecondValue() { return secondValue; }
	
	public void setForm(String form) {
		String oldValue = this.form;
		this.form = form;
		firePropertyChange("form", oldValue, form);
	}
	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		firePropertyChange("name", oldValue, name);
	}
	public void setFirstValue(int firstValue) {
		int oldValue = this.firstValue;
		this.firstValue = firstValue;
		firePropertyChange("firstValue", oldValue, firstValue);
	}
	public void setSecondValue(int secondValue) {
		int oldValue = this.secondValue;
		this.secondValue = secondValue;
		firePropertyChange("secondValue", oldValue, secondValue);
	}

	public Part(String name, String form) {
		this.name = name;
		this.form = form;
	}
	
	public Part(String name, String form, int firstValue) {
		this(name, form);
		this.firstValue = firstValue;
	}
	
	public Part(String name, String form, int firstValue, int secondValue) {
		this(name, form, firstValue);
		this.secondValue = secondValue;
	}
}
