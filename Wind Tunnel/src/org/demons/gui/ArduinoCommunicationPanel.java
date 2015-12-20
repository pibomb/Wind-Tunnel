package org.demons.gui;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

class ArduinoCommunicationPanel extends JPanel {
	private static final long serialVersionUID = -2739228960818599179L;

	ArduinoCommunicationPanel(int width, int height) {
		super();
		
		setLayout(null);
		setSize(width, height);
		repaint();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
