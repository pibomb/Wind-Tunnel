package org.demons.ctrl;

import org.zu.ardulink.Link;

public class FanControl {
	private Link link = null;
	
	private final int[] FAN_PINS = {8, 9, 10, 11};
	private final int FAN_COUNT = 4;
	
	private int pwm = 0;
	
	public FanControl(Link link) {
		this.link = link;
	}
	
	public int getPWM() {
		return pwm;
	}
	
	public void setPWM(int value) {
		pwm = value;
		for(int i = 0; i < FAN_COUNT; i++) {
			link.sendPowerPinIntensity(FAN_PINS[i], pwm);
		}
	}
}
