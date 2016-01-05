package org.demons;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import org.demons.gui.*;

public class Launch {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(NimbusLookAndFeel.class.getCanonicalName());
					JFrame frame = new JFrame("Maxwell's Demons");
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setUndecorated(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.add(new OperationFrame());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
