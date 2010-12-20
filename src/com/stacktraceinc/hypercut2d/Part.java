package com.stacktraceinc.hypercut2d;

import java.awt.Color;

public class Part extends AbstractModelObject {
	private String name;
	private String form;
	private int firstValue = 0;
	private int secondValue = 0;
	private boolean rotated = false;
	private Coordinate coord = new Coordinate(-1, -1);
	private Color color = Color.WHITE;

	public String getForm() { return form; }
	public String getName() { return name; }
	public int getFirstValue() { return firstValue; }
	public int getSecondValue() { return secondValue; }
	public Color getColor() { return color; }
	public Coordinate getCoord(){ return this.coord; }
	public int getX() { return this.coord.getX(); }
	public int getY() { return this.coord.getY(); }
	public int getArea() { return firstValue * secondValue; }
	
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
	public void setColor(Color color) {
		Color oldColor = this.color;
		this.color = color;
		firePropertyChange("color", oldColor, color);
	}	
	public void setCoordinate(Coordinate coord) {
		Coordinate oldCoord = this.coord;
		this.coord = coord;
		firePropertyChange("coordinate", oldCoord, coord);
	}	

	public void rotate(){
		if(this.rotated == false){
			this.rotated = true;
		}else{
			this.rotated = false;
		}
	}
	
	public boolean rotation(){ return rotated; }
	
	public Part clone()
	{
		Part newPart = new Part(this.name, this.form, this.firstValue, this.secondValue, this.color);
		newPart.setCoordinate(this.coord);
		
		if(this.rotated){
			newPart.rotate();
		}
		
		return newPart;
		
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
	
	public Part(String name, String form, int firstValue, int secondValue, Color color) {
		this(name, form, firstValue, secondValue);
		this.color = color;
	}
}
