package com.stacktraceinc.hypercut2d;

public class Rectangle extends Part {	
	public Rectangle(String name, String form, int width, int height) {
		super(name, form);
		setFirstValue(width);
		setSecondValue(height);
	}
}
