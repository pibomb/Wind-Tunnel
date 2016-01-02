package org.demons.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

import org.zu.ardulink.Link;

class ArduinoPinManager extends JPanel {
	private static final long serialVersionUID = -2739228960818599179L;
	
	private Link link = Link.getDefaultInstance();
	
	public ArduinoPinManager(int width, int height) {
		super();
		
		setLayout(null);
		setSize(width, height);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(255, 128, 128));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
