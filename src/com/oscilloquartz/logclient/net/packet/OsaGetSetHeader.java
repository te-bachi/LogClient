package com.oscilloquartz.logclient.net.packet;

/**
 *
 */
public class OsaGetSetHeader extends Header {

    public OsaGetSetHeader() {
        super();
    }

    public OsaGetSetHeader(Header prev) {
        super(prev);
    }

    @Override
    public int encode(Packet packet, RawPacket rawPacket, int offset) throws PacketException {
        return 0;
    }

    @Override
    public Header decode(RawPacket rawPacket, int offset) throws PacketException {
        return null;
    }
}
