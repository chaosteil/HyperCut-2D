package com.stacktraceinc.hypercut2d;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FancyCanvas extends JPanel {
	JPanel childPanel = new JPanel();
	CompEventListener compEventListener = new CompEventListener();
	Dimension d;

	public FancyCanvas(Dimension d){
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.CENTER;
		c.anchor = GridBagConstraints.CENTER;

		panelResize(d);
		childPanel.addComponentListener(compEventListener);
		add(childPanel);
	}
	
	public void panelResize(Dimension d) {
		this.d = d;
		childPanel.setPreferredSize(d);
		childPanel.setMinimumSize(null);
		childPanel.setMinimumSize(d);
		compEventListener.resize(d);
		childPanel.revalidate();
	}

	public void paint(Graphics g){
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(Color.white);
		g.fillRect(childPanel.getX(), childPanel.getY(), childPanel.getWidth(), childPanel.getHeight());
	}
}