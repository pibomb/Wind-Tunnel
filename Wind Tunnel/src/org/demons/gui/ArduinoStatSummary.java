package org.demons.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.Timer;

import org.demons.test.PinListings;
import org.demons.utils.MegaConstants;
import org.jfree.ui.about.SystemPropertiesTableModel;
import org.zu.ardulink.Link;
import org.zu.ardulink.event.AnalogReadChangeEvent;
import org.zu.ardulink.event.AnalogReadChangeListener;
import org.zu.ardulink.event.DigitalReadChangeEvent;
import org.zu.ardulink.event.DigitalReadChangeListener;
import org.zu.ardulink.protocol.IProtocol;

import com.sun.xml.internal.bind.v2.runtime.Name;


public class ArduinoStatSummary extends JPanel {
	private static final long serialVersionUID = -2739228960818599179L;
	
	private Link link = null;
	
	private final int PADDING = 25;
	
	private Font titleFont, regularFont, tableFont;
	
	private JLabel title, stat;
	private JPanel descBar;
	private JPanel list;
	private JScrollPane scrollPane;
	
	private final int TOTAL_PINS = MegaConstants.DIGITAL_INPUT_PIN_MAX + MegaConstants.ANALOG_INPUT_PIN_MAX + 2;
	
	class Listing extends JPanel {
		JLabel nameLabel, valLabel;
		
		Listing(String name, String val) {
			super();
			
			setSize(list.getWidth(), 100);
			setLayout(new GridLayout(1, 2));
			
			nameLabel = new JLabel(name);
			valLabel = new JLabel(val);
			
			nameLabel.setFont(tableFont);
			valLabel.setFont(tableFont);
			
			add(nameLabel);
			add(new JSeparator(JSeparator.VERTICAL));
			add(valLabel);
		}
		
		protected JLabel getNameLabel() {
			return nameLabel;
		}
		
		protected JLabel getValLabel() {
			return valLabel;
		}
	}
	
	private Listing[] pinListings;
	
	public ArduinoStatSummary(int width, int height) {
		super();
		
		setLayout(null);
		setSize(width, height);
		
		titleFont = new Font("Century", Font.BOLD, 14);
		regularFont = new Font("Dialog", Font.BOLD, 14);
		tableFont = new Font("Dialog", Font.BOLD, 12);
		
		title = new JLabel("ARDUINO STAT SUMMARY", JLabel.CENTER);
		title.setFont(titleFont);
		title.setBounds(PADDING, PADDING, width-2*PADDING, 15);
		title.setForeground(Color.BLUE);
		
		stat = new JLabel("Port: ");
		stat.setFont(regularFont);
		stat.setBounds(PADDING, PADDING+title.getHeight(), width-2*PADDING, regularFont.getSize()+PADDING);
		
		descBar = new JPanel();
		descBar.setLayout(new GridLayout(1, 3));
		JLabel left = new JLabel("Arduino Pin");
		left.setFont(tableFont);
		JLabel right = new JLabel("Value");
		right.setFont(tableFont);
		descBar.add(left);
		descBar.add(new JSeparator(JSeparator.VERTICAL));
		descBar.add(right);
		descBar.setBounds(PADDING, PADDING+title.getHeight()+stat.getHeight(), width-2*PADDING, tableFont.getSize()+2);
		
		pinListings = new Listing[TOTAL_PINS];
		
		list = new JPanel();
		list.setLayout(new GridLayout(pinListings.length, 1));
		
		for(int i = 0; i < pinListings.length; i++) {
			if(i <= MegaConstants.DIGITAL_INPUT_PIN_MAX) {
				pinListings[i] = new Listing("DIGITAL " + i, "LOW");
			} else {
				int pin = i - MegaConstants.DIGITAL_INPUT_PIN_MAX - 1;
				pinListings[i] = new Listing("ANALOG " + pin, "0");
			}
			pinListings[i].setBounds(0, i*20, width, 20);
			list.add(pinListings[i]);
		}
		
		scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(PADDING, 2*PADDING+title.getHeight()+stat.getHeight()+descBar.getHeight(), width-2*PADDING, height-3*PADDING-title.getHeight()-stat.getHeight()-descBar.getHeight());
		
		add(title);
		add(stat);
		add(descBar);
		add(scrollPane);
		
		repaint();
	}
	
	public ArduinoStatSummary(int width, int height, Font f) {
		this(width, height);
		titleFont = f;
		title.setFont(f);
		title.setBounds(PADDING, PADDING, width-2*PADDING, f.getSize());
		stat.setBounds(PADDING, PADDING+title.getHeight(), width-2*PADDING, regularFont.getSize()+PADDING);
		descBar.setBounds(PADDING, PADDING+title.getHeight()+stat.getHeight(), width-2*PADDING, tableFont.getSize()+2);
		scrollPane.setBounds(PADDING, 2*PADDING+title.getHeight()+stat.getHeight()+descBar.getHeight(), width-2*PADDING, height-3*PADDING-title.getHeight()-stat.getHeight()-descBar.getHeight());
		
		repaint();
	}
	
	public void setPortStatus(String msg) {
		stat.setText("Port: " + msg);
	}
	
	public void setInfo(int key, int val) {
		if(key <= MegaConstants.DIGITAL_INPUT_PIN_MAX) {
			pinListings[key].getValLabel().setText(val == IProtocol.HIGH ? "HIGH" : "LOW");
		} else {
			pinListings[key].getValLabel().setText(((Integer)val).toString());
		}
		repaint();
	}
	
	public String getInfo(boolean digital, int pin) {
		int key = (digital) ? pin : MegaConstants.DIGITAL_INPUT_PIN_MAX + pin + 1;
		
		return pinListings[key].getValLabel().getText();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(128, 128, 255));
		g.fillRect(0, 0, getWidth(), getHeight());
	}
}
