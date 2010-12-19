package com.stacktraceinc.hypercut2d;

import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class CompEventListener implements ComponentListener {
	Dimension d = new Dimension(0, 0);

	public CompEventListener(){
	}
	
	public void resize(Dimension d) {
		this.d = d;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
	}

	@Override
	public void componentResized(ComponentEvent e) {
		e.getComponent().setSize(d);
	}

	@Override
	public void componentShown(ComponentEvent e) {
		e.getComponent().setSize(d);
	}
}
