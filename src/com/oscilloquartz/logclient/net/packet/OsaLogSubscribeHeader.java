package com.oscilloquartz.logclient.net.packet;

/**
 *
 */
public class OsaLogSubscribeHeader extends Header {

    private static final int    HEADER_LENGTH           = 2;

    protected static final int  OFFSET_SUBSCRIBE_TYPE   = 0;
    protected static final int  OFFSET_DURATION         = 1;

    public static final byte    SUBSCRIBE_TYPE_REQUEST  = 0x01;
    public static final byte    SUBSCRIBE_TYPE_CANCEL   = 0x02;
    public static final byte    SUBSCRIBE_TYPE_GRANT    = 0x03;
    public static final byte    SUBSCRIBE_TYPE_REJECT   = 0x04;
    public static final byte    SUBSCRIBE_TYPE_EXPIRED  = 0x05;

    public byte subscribeType;
    public byte duration;


    public OsaLogSubscribeHeader() {
        this(null, (byte) 0, (byte) 0);
    }

    public OsaLogSubscribeHeader(Header prev) {
        this(prev, (byte) 0, (byte) 0);
    }

    public OsaLogSubscribeHeader(byte subscribeType, byte duration) {
        this(null, subscribeType, duration);
    }

    public OsaLogSubscribeHeader(Header prev, byte subscribeType, byte duration) {
        super(prev);
        this.subscribeType  = subscribeType;
        this.duration       = duration;
    }

    public static Header createLogSubscribeRequest(Header prev, byte duration) {
        return new OsaLogSubscribeHeader(prev, SUBSCRIBE_TYPE_REQUEST, duration);
    }

    @Override
    public int encode(Packet packet, RawPacket rawPacket, int offset) throws PacketException {
        packet.tail = this;

        rawPacket.data[offset + OFFSET_SUBSCRIBE_TYPE]    = subscribeType;
        rawPacket.data[offset + OFFSET_DURATION]          = duration;

        return HEADER_LENGTH;
    }

    @Override
    public Header decode(RawPacket rawPacket, int offset) throws PacketException {
        return null;
    }
}
