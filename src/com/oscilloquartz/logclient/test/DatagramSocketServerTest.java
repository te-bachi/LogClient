package com.oscilloquartz.logclient.test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.charset.Charset;

/**
 *
 */
public class DatagramSocketServerTest {
    public static void main(String[] args) throws SocketException, IOException {
        byte[] data = new byte[1500];
        DatagramSocket socket = new DatagramSocket(1234);
        DatagramPacket receive = new DatagramPacket(data, data.length);
        DatagramPacket send;
        socket.receive(receive);

        System.out.println("data=" + new String(receive.getData(), 0, receive.getLength(), Charset.defaultCharset()));
        System.out.println("address=" + receive.getAddress().getHostAddress() + ":" + receive.getPort());
        send = new DatagramPacket(receive.getData(), receive.getLength(), receive.getAddress(), receive.getPort());
        socket.send(send);
    }
}
