package com.oscilloquartz.logclient.net;

import java.io.IOException;
import java.net.*;

/**
 *
 */
public class SocketThread implements Runnable {

    private Socket              socket;


    private static final int    PORT_OSA_GENERAL        = 9000;

    public SocketThread() {

    }

    public boolean connect(String hostname) throws UnknownHostException, IOException {
        InetAddress     remoteAddress;
        DatagramSocket  socket;
        byte[]          data;

        data            = new byte[1500];
        remoteAddress   = InetAddress.getByName(hostname);
        socket          = new DatagramSocket();
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
