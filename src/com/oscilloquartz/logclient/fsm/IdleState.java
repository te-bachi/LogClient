package com.oscilloquartz.logclient.fsm;

import com.oscilloquartz.logclient.gui.MainFrame;
import com.oscilloquartz.logclient.net.Network;
import com.oscilloquartz.logclient.net.packet.*;

import javax.swing.*;
import java.net.DatagramPacket;

/**
 *
 */
public class IdleState extends AbstractState {
    public IdleState(StateMachine stateMachine) {
        super(stateMachine);
    }

    @Override
    public String getStateName() {
        return "Idle";
    }

    @Override
    public void connect() {
        MainFrame   frame       = stateMachine.mainFrame;
        Network     network     = frame.getNetwork();
        String      hostname    = frame.getHostname();

        RawPacket   rawPacket           = new RawPacket();
        Header      osa                 = OsaHeader.createLogSubscribeRequest();
        Header      logSubscribe        = OsaLogSubscribeHeader.createLogSubscribeRequest(osa, (byte) 60);
        Packet      packet              = new Packet(osa);

        try {
            if (packet.encode(rawPacket)) {
                network.connect(hostname);
                network.send(rawPacket);
                stateMachine.setNextState(stateMachine.connectState);
            } else {
                System.out.printf("error");
            }
        } catch (Exception e) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }
}
