package org.demons.test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.zu.ardulink.gui.SerialConnectionPanel;

public class SerialConnectionJDialog extends JDialog implements ActionListener {
    private static final long serialVersionUID = -2475762691171319559L;
	
    // Ardulink swing component for serial connection
    private final SerialConnectionPanel serialConnectionPanel;
    // Serial COM port connected to Arduino
    public String port = null;

    public SerialConnectionJDialog(JFrame parent, String title, String message) {
        super(parent, title, true);

        // message panel
        JPanel messagePane = new JPanel();
        messagePane.add(new JLabel(message));
        getContentPane().add(messagePane, BorderLayout.NORTH);
        JPanel buttonPane = new JPanel();
        
        // show the Ardulink swing component for serial connection
        serialConnectionPanel = new SerialConnectionPanel();
        // hide the baud rate setting
        serialConnectionPanel.setBaudRateVisible(true);
        getContentPane().add(serialConnectionPanel, BorderLayout.CENTER);

        // add dialog button
        JButton button = new JButton("Connect");
        button.addActionListener(this);
        buttonPane.add(button);
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        // display at the centre of the screen
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        // find the COM port 
        port = serialConnectionPanel.getConnectionPort();
        setVisible(false);
        dispose();
    }
}
