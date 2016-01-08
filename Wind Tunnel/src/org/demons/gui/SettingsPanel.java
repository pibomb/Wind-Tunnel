package org.demons.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.demons.ctrl.FanControl;
import org.demons.ctrl.ServoControl;
import org.zu.ardulink.Link;

class SettingsPanel extends JPanel implements ChangeListener {
	private static final long serialVersionUID = -2739228960818599179L;
	private Font airfoilFont, sliderFont;
	private JButton airfoil1, airfoil2, airfoil3, airfoil4, exit, setWindspeed, setAngle;
	private SpinnerNumberModel windspeed, angle;
	private JSlider windspeedSlider, angleSlider;
	private JLabel airspeedLabel, angleLabel;
	
	private ServoControl servoCtrl;
	private FanControl fanCtrl;
	
	private WindTunnelGraphicsDisplay wtgd = null;
	
	public SettingsPanel(int width, int height) {
		super();
		
		setLayout(new BorderLayout());
		setSize(width, height);
		
		airfoilFont = new Font("Century Gothic", Font.PLAIN, 25);
		
		JPanel north = new JPanel();
		JPanel center = new JPanel();
		JLabel south = new JLabel("MSB Levitate 5000 - by Maxwell's Demons");
		south.setFont(new Font("Century Gothic", Font.PLAIN, 48));
		south.setHorizontalAlignment(JLabel.CENTER);
		south.setForeground(Color.BLUE);
		
		north.setOpaque(false);
		center.setOpaque(false);
		south.setOpaque(false);
		
		exit = new JButton("EXIT");
		exit.setFont(new Font("Courier New", Font.BOLD, 30));
		exit.setBackground(new Color(128, 0, 64));
		exit.setForeground(Color.WHITE);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		airfoil1 = new JButton("Airfoil 1");
		airfoil2 = new JButton("Airfoil 2");
		airfoil3 = new JButton("Airfoil 3");
		airfoil4 = new JButton("Airfoil 4");

		airfoil1.setFont(airfoilFont);
		airfoil2.setFont(airfoilFont);
		airfoil3.setFont(airfoilFont);
		airfoil4.setFont(airfoilFont);
		
		airfoil1.setBackground(new Color(0, 83, 83));
		airfoil2.setBackground(new Color(0, 83, 83));
		airfoil3.setBackground(new Color(0, 83, 83));
		airfoil4.setBackground(new Color(0, 83, 83));
		
		airfoil1.setForeground(Color.WHITE);
		airfoil2.setForeground(Color.WHITE);
		airfoil3.setForeground(Color.WHITE);
		airfoil4.setForeground(Color.WHITE);
		
		setWindspeed = new JButton("Set Windspeed");
		setAngle = new JButton("Set Angle of Attack");
		
		setWindspeed.setFont(new Font("Arial", Font.PLAIN, 20));
		setAngle.setFont(new Font("Arial", Font.PLAIN, 20));
		
		setWindspeed.setBackground(new Color(0, 64, 0));
		setAngle.setBackground(new Color(0, 64, 0));
		
		setWindspeed.setForeground(Color.WHITE);
		setAngle.setForeground(Color.WHITE);
		
		windspeed = new SpinnerNumberModel(0, 0, 255, 1);
		angle = new SpinnerNumberModel(0, -90, 90, 1);
		
		sliderFont = new Font("Century Gothic", Font.PLAIN, 16);
		
		windspeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
		windspeedSlider.setMajorTickSpacing(25);
		windspeedSlider.setMinorTickSpacing(5);
		windspeedSlider.setPaintTicks(true);
		windspeedSlider.setPaintLabels(true);
		windspeedSlider.setFont(sliderFont);
		windspeedSlider.addChangeListener(this);
		
		angleSlider = new JSlider(JSlider.HORIZONTAL, -90, 90, 0);
		angleSlider.setMajorTickSpacing(45);
		angleSlider.setMinorTickSpacing(15);
		angleSlider.setPaintTicks(true);
		angleSlider.setPaintLabels(true);
		angleSlider.setFont(sliderFont);
		angleSlider.addChangeListener(this);
		
		airspeedLabel= new JLabel("Wind Speed: ");
		airspeedLabel.setFont(airfoilFont);
		
		angleLabel= new JLabel("Angle of Attack: ");
		angleLabel.setFont(airfoilFont);
		
		north.add(exit);
		north.add(airfoil1);
		north.add(airfoil2);
		north.add(airfoil3);
		north.add(airfoil4);
		//add(setWindspeed);
		//add(new JSpinner (windspeed));
		//add(setAngle);
		//add(new JSpinner(angle));
		center.add(airspeedLabel);
		center.add(windspeedSlider);
		center.add(angleLabel);
		center.add(angleSlider);
		
		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		repaint();
	}
	
	public void setGraphicsDisplay(WindTunnelGraphicsDisplay wTunnelGraphicsDisplay) {
		wtgd = wTunnelGraphicsDisplay;
		
		airfoil1.setActionCommand("A1");
		airfoil1.addActionListener(wtgd);
		
		airfoil2.setActionCommand("A2");
		airfoil2.addActionListener(wtgd);
		
		airfoil3.setActionCommand("A3");
		airfoil3.addActionListener(wtgd);
		
		airfoil4.setActionCommand("A4");
		airfoil4.addActionListener(wtgd);
	}
	
	public void addServoControl(ServoControl servo) {
		servoCtrl = servo;
	}
	
	public void addFanControl(FanControl fan) {
		fanCtrl = fan;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider)e.getSource();
		
		if(!source.getValueIsAdjusting()) {
			if(source.equals(angleSlider)) {
				System.out.print("Servo: ");
				
				int angle = (int) source.getValue();
				System.out.println(angle);
				
				servoCtrl.setAngle(angle);
			} else if(source.equals(windspeedSlider)) {
				System.out.print("Fan: ");
				
				int power = (int) source.getValue();
				System.out.println(power);
				
				fanCtrl.setPWM(power);
			}
		}
	}
}
