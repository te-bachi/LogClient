package com.oscilloquartz.logclient.net;

import com.oscilloquartz.logclient.gui.MainFrame;
import com.oscilloquartz.logclient.net.packet.*;

import javax.swing.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 */
public class ReceiveThread implements Runnable {

    private MainFrame       mainFrame;
    private DatagramSocket  socket;
    private Thread          thread;

    private static final boolean USE_STATIC_PACKET = false;
    private static final byte[] STATIC_PACKET = {
            (byte) 0x01, (byte) 0x03, (byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x06, (byte) 0x01, (byte) 0x21, (byte) 0xf0, (byte) 0x00, (byte) 0x17, (byte) 0x02, (byte) 0x2d, (byte) 0x00, (byte) 0x41, (byte) 0x52, (byte) 0x50, (byte) 0x20, (byte) 0x70, (byte) 0x61, (byte) 0x63,
            (byte) 0x6b, (byte) 0x65, (byte) 0x74, (byte) 0x20, (byte) 0x66, (byte) 0x61, (byte) 0x69, (byte) 0x6c, (byte) 0x65, (byte) 0x64, (byte) 0x20, (byte) 0x74, (byte) 0x6f, (byte) 0x20, (byte) 0x70, (byte) 0x72,
            (byte) 0x6f, (byte) 0x63, (byte) 0x65, (byte) 0x73, (byte) 0x73, (byte) 0x20, (byte) 0x6f, (byte) 0x72, (byte) 0x20, (byte) 0x69, (byte) 0x73, (byte) 0x20, (byte) 0x6e, (byte) 0x6f, (byte) 0x74, (byte) 0x20,
            (byte) 0x66, (byte) 0x6f, (byte) 0x72, (byte) 0x20, (byte) 0x75, (byte) 0x73,
    };

    public ReceiveThread(MainFrame mainFrame, DatagramSocket socket) {
        this.mainFrame  = mainFrame;
        this.socket     = socket;
        this.thread     = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        byte[]          receiveData;
        DatagramPacket  receivePacket;
        RawPacket       rawPacket;
        Packet          packet;
        OsaHeader       osaHeader;
        OsaLogHeader    logHeader;
        receiveData     = new byte[1500];
        receivePacket   = new DatagramPacket(receiveData, receiveData.length);

        try {
            while (!socket.isClosed()) {
                System.out.println("Packet received!");
                if (!USE_STATIC_PACKET) {
                    socket.receive(receivePacket);
                    rawPacket = new RawPacket(receivePacket.getData(), receivePacket.getLength());
                } else {
                    Thread.sleep(2000);
                    rawPacket = new RawPacket(STATIC_PACKET, STATIC_PACKET.length);
                }
                packet = Packet.decode(rawPacket);
                if (packet != null) {
                    if (packet.getHead() instanceof OsaHeader) {
                        osaHeader = (OsaHeader) packet.getHead();
                        if (osaHeader.operation == OsaHeader.OPERATION_LOG) {
                            if (osaHeader.getNext() instanceof OsaLogHeader) {
                                logHeader = (OsaLogHeader) osaHeader.getNext();

                                Object[] data = { logHeader.getTimestampString(), logHeader.getCategoryString(), logHeader.getLevelString(), logHeader.getMessageString()};

                                SwingUtilities.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        mainFrame.getLogPanel().getDefaultTableModel().addRow(data);
                                    }
                                });
                            } else {
                                System.out.println("head.next not OsaLogHeader");
                            }
                        } else {
                            System.out.println("operation not OPERATION_LOG");
                        }
                    } else {
                        System.out.println("head not OsaHeader");
                    }
                }
            }
            System.out.println("Socket closed. Exit ReceiveThread");
        } catch (IOException e) {
            System.out.println("IOException. Exit!");
        } catch (PacketException e) {
            System.out.println("PacketException. Exit!");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException. Exit!");
        }
    }
}
