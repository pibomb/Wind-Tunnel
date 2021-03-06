package org.demons.ctrl;

import org.zu.ardulink.Link;

public class ServoControl {
	private Link link = null;
	
	private static final int SERVO_PIN = 99;
	private final int pin = 2;
	private int angle = 90;
	
	public ServoControl(Link link) {
		this.link = link;
	}
	
	public int getAngle() {
		return angle;
	}
	
	public void setAngle(int theta) {
		angle = theta + 90;
		link.sendPowerPinIntensity(99, angle);
	}
}
