package org.demons.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class SettingsPanel extends JPanel {
	private static final long serialVersionUID = -2739228960818599179L;

	public SettingsPanel(int width, int height) {
		super();
		
		setLayout(null);
		setSize(width, height);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
