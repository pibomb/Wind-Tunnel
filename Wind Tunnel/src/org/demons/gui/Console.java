package org.demons.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.omg.CORBA.PRIVATE_MEMBER;

class Console extends JPanel {
	private static final long serialVersionUID = -2739228960818599179L;
	private JTextArea consoleTextArea;
	private JScrollPane consoleScroll;
	private String consoleText = "Morris and Sheila are poopyfaces";

	public Console(int width, int height) {
		super();
		setSize(width, height);

		consoleTextArea = new JTextArea(16,102);
		consoleTextArea.setEditable(false);
		consoleTextArea.setOpaque(false);
		consoleTextArea.setText(consoleText);
		consoleTextArea.setForeground(Color.GREEN);
		consoleTextArea.setBackground(Color.BLACK);
		consoleTextArea.setFont(new Font("Courier New", Font.PLAIN, 18));
		
		consoleScroll = new JScrollPane(consoleTextArea);
		consoleScroll.setOpaque(false);
		
		add(consoleScroll);
		repaint();
	}
	
	public void setConsoleText(String message)
	{
		consoleText += message + "\n";
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
