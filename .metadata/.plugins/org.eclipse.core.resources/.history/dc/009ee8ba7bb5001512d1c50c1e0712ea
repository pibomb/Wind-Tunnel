package org.demons.ctrl;

import org.zu.ardulink.event.DigitalReadChangeEvent;
import org.zu.ardulink.event.DigitalReadChangeListener;
import org.zu.ardulink.protocol.IProtocol;

public class Anemometer implements DigitalReadChangeListener {
	private int pin = 7;
	
	private long a = 0, b = 0, duration = 0;
	
	public Anemometer() {
		a = System.currentTimeMillis();
		b = System.currentTimeMillis();
	}
	
	public long getDuration() {
		return duration;
	}
	
	@Override
	public int getPinListening() {
		return pin;
	}

	@Override
	public void stateChanged(DigitalReadChangeEvent e) {
		if(e.getValue() == IProtocol.HIGH) {
			b = System.currentTimeMillis();
			duration = b - a;
			a = b;
		}
	}
}
