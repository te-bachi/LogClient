package com.oscilloquartz.logclient.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 *
 */
public class DatagramSocketClientTest {
    public static void main(String[] args) throws SocketException, IOException {

        InetAddress address;
        int         port;

        if (args.length < 2) {
            System.out.println(" <hostname> <port>");
            System.exit(-1);
        }

        address = InetAddress.getByName(args[0]);
        port    = Integer.parseInt(args[1]);

        byte[] sendData;
        byte[] receiveData = new byte[1500];
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket send;
        DatagramPacket receive = new DatagramPacket(receiveData, receiveData.length);

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter msg: ");
        sendData = stdin.readLine().getBytes(StandardCharsets.US_ASCII);

        System.out.println("address=" + address + ":" + port);
        send = new DatagramPacket(sendData, sendData.length, address, port);

        socket.send(send);
        socket.receive(receive);
        System.out.println("data=" + new String(receive.getData(), 0, receive.getLength(), StandardCharsets.US_ASCII));
    }
}
