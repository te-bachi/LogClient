package com.oscilloquartz.logclient.net;

import java.io.IOException;
import java.net.*;

/**
 *
 */
public class LogClientSocket implements Runnable {

    private static final int    PORT_OSA_GENERAL        = 9000;

    private DatagramSocket      socket;
    private DatagramPacket      receivePacket;
    private DatagramPacket      transmitPacket;




    public LogClientSocket() {
//        socket= new DatagramSocket();
//        receivePacket = new DatagramPacket()
    }

    public boolean connect(String hostname) throws UnknownHostException, IOException {
        InetAddress     remoteAddress;
        DatagramSocket  socket;
        byte[]          data;

        data            = new byte[1500];
        remoteAddress   = InetAddress.getByName(hostname);
//        socket.se
//
//                packet    = new DatagramPacket(this.data, this.data.length, remoteAddress, PORT_OSA_GENERAL);
//        private DatagramPacket  packet;

        return true;
    }

    @Override
    public void run() {

    }
}
