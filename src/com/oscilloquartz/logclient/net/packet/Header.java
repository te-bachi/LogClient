package com.oscilloquartz.logclient.net.packet;

/**
 *
 */
public abstract class Header {
    protected Header    prev;
    protected Header    next;

    public Header() {
        this(null);
    }

    public Header(Header prev) {
        this.prev           = prev;
        this.next           = null;

        if (prev != null) {
            prev.next = this;
        }
    }

    public Header getPrev() {
        return prev;
    }

    public Header getNext() {
        return next;
    }

    public abstract int encode(Packet packet, RawPacket rawPacket, int offset) throws PacketException;
    public abstract Header decode(RawPacket rawPacket, int offset) throws PacketException;
}
