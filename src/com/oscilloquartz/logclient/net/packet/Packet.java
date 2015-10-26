package com.oscilloquartz.logclient.net.packet;

/**
 *
 */
public class Packet {

    protected Header  head;
    protected Header  tail;

    public Packet() {
        this(null);
    }

    public Packet(Header head) {
        this.head = head;
        this.tail = null;
    }

    public Header getHead() {
        return head;
    }

    public void setHead(Header head) {
        this.head = head;
    }

    public Header getTail() {
        return tail;
    }

    public void setTail(Header tail) {
        this.tail = tail;
    }

    public boolean encode(RawPacket rawPacket) throws PacketException {
        rawPacket.length = 0;

        if (head != null) {
            rawPacket.length = head.encode(this, rawPacket, 0);
        }

        if (rawPacket.length == 0) {
            return false;
        }

        return true;
    }

    public static Packet decode(RawPacket rawPacket) throws PacketException {
        Packet packet = new Packet();
        packet.head = new OsaHeader().decode(rawPacket, 0);

        return packet;
    }
}
