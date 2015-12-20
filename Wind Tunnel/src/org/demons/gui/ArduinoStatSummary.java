package org.demons.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class ArduinoStatSummary extends JPanel {
	private static final long serialVersionUID = -2739228960818599179L;

	public ArduinoStatSummary(int width, int height) {
		super();
		
		setLayout(null);
		setSize(width, height);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
