package org.demons.test;

import java.util.List;
import java.util.Set;

import org.zu.ardulink.Link;
import org.zu.ardulink.connection.usb.DigisparkUSBConnection;
import org.zu.ardulink.protocol.IProtocol;
import org.zu.ardulink.protocol.ProtocolHandler;
import org.zu.ardulink.protocol.SimpleBinaryProtocol;

public class BlinkLED {

	public static void main(String[] args) {
		try {
			Link link = Link.getDefaultInstance();
			//link = getDigisparkConnection(); // Comment this row if you use just the default connection

			List<String> portList = link.getPortList();
			if(portList != null && portList.size() > 0) {
				String port = portList.get(0);
				System.out.println("Connecting on port: " + port);
				boolean connected = link.connect(port);
				System.out.println("Connected:" + connected);
				Thread.sleep(2000);
				int power = IProtocol.HIGH;
				while(true) {
					System.out.println("Send power:" + power);
					//link.sendPowerPinSwitch(3, power);
					link.writeSerial("alp://ppsw/3/"+power);
					if(power == IProtocol.HIGH) {
						power = IProtocol.LOW;
					} else {
						power = IProtocol.HIGH;
					}
					Thread.sleep(2000);
				}
			} else {
				System.out.println("No port found!");
			}
						
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static Link getDigisparkConnection() {
		Set<String> protocolNames = ProtocolHandler.getInstalledProtocolImplementationNames();
		SimpleBinaryProtocol protocol = new SimpleBinaryProtocol();
		if(!protocolNames.contains(SimpleBinaryProtocol.NAME)) {
			ProtocolHandler.installProtocolImplementation(protocol);
		}
		return Link.createInstance("digisparkConnection", SimpleBinaryProtocol.NAME, new DigisparkUSBConnection("digisparkConnection", protocol.getIncomingMessageDivider()));
	}

}
