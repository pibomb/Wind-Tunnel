package org.demons.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class WindTunnelGraphicsDisplay extends JPanel {
	private static final long serialVersionUID = -2739228960818599179L;

	public WindTunnelGraphicsDisplay(int width, int height) {
		super();
		
		setLayout(null);
		setSize(width, height);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
