package com.oscilloquartz.logclient.net;

import com.oscilloquartz.logclient.gui.MainFrame;
import com.oscilloquartz.logclient.net.packet.OsaHeader;
import com.oscilloquartz.logclient.net.packet.RawPacket;

import java.io.IOException;
import java.net.*;

/**
 *
 */
public class Network {

    private static final int    PORT_OSA_GENERAL        = 9000;

    private MainFrame           frame;

    private DatagramSocket      serverSocket;
    private ReceiveThread       serverReceiveThread;

    private boolean             isConnected;
    private DatagramSocket      clientSocket;
    private InetAddress         remoteAddress;
    private ReceiveThread       clientReceiveThread;


    //private TransmitThread      clientTransmitThread;

    public Network(MainFrame frame) throws SocketException {
        this.frame          = frame;
        this.serverSocket   = new DatagramSocket(PORT_OSA_GENERAL);
        this.isConnected    = false;
    }

    public void connect(String hostname) throws SocketException, UnknownHostException {
        clientSocket            = new DatagramSocket();
        clientReceiveThread     = new ReceiveThread(this.frame, clientSocket);
        remoteAddress           = InetAddress.getByName(hostname);
        isConnected             = true;
    }

    public boolean send(RawPacket rawPacket) throws IOException {
        DatagramPacket  transmitPacket;

        if (isConnected) {
            transmitPacket = new DatagramPacket(rawPacket.data, rawPacket.length, remoteAddress, PORT_OSA_GENERAL);
            clientSocket.send(transmitPacket);
        } else {
            return false;
        }

        return true;
    }
}
