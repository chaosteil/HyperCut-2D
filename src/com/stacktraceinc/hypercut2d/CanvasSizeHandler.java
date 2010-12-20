package com.stacktraceinc.hypercut2d;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class CanvasSizeHandler extends AbstractModelObject {
	private List<Dimension> sizes = new ArrayList<Dimension>();
	
	public List<Dimension> getSizes() {
		return sizes;
	}
	
	public CanvasSizeHandler() {
		addSize(new Dimension(1200, 2500));
		addSize(new Dimension(1200, 3050));
	}
	
	public void eraseSize(int index) {
		List<Dimension> oldValue = sizes;
		sizes = new ArrayList<Dimension>(sizes);
		sizes.remove(index);
		firePropertyChange("sizes", oldValue, sizes);
	}
	
	public void addSize(Dimension size) {
		List<Dimension> oldValue = sizes;
		sizes = new ArrayList<Dimension>(sizes);
		sizes.add(size);
		firePropertyChange("sizes", oldValue, sizes);
	}
}