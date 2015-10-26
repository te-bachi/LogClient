package com.oscilloquartz.logclient.net.packet;

/**
 *
 */
public class OsaHeader extends Header {

    protected static final int  HEADER_LENGTH           = 3;

    protected static final int  OFFSET_VERSION          = 0;
    protected static final int  OFFSET_OPERATION        = 1;
    protected static final int  OFFSET_TYPE             = 2;

    public static final byte    VERSION_V1              = 0x01;

    public static final byte    OPERATION_GET           = 0x01;
    public static final byte    OPERATION_SET           = 0x02;
    public static final byte    OPERATION_LOG           = 0x03;
    public static final byte    OPERATION_LOG_SUBSCRIBE = 0x04;

    public static final byte    TYPE_REQUEST            = 0x01;
    public static final byte    TYPE_RESPONSE           = 0x02;
    public static final byte    TYPE_ASYNC              = 0x03;

    public byte version;
    public byte operation;
    public byte type;

    public OsaHeader() {
        this(null, (byte) 0, (byte) 0, (byte) 0);
    }

    public OsaHeader(Header prev) {
        this(prev, (byte) 0, (byte) 0, (byte) 0);
    }

    public OsaHeader(byte version, byte operation, byte type) {
        this(null, version, operation, type);
    }

    public OsaHeader(Header prev, byte version, byte operation, byte type) {
        super(prev);
        this.version    = version;
        this.operation  = operation;
        this.type       = type;
    }

    public static Header createLogSubscribeRequest() {
        return new OsaHeader(OsaHeader.VERSION_V1, OsaHeader.OPERATION_LOG_SUBSCRIBE, OsaHeader.TYPE_REQUEST);
    }

    @Override
    public int encode(Packet packet, RawPacket rawPacket, int offset) throws PacketException {
        int length = 0;

        packet.tail = this;

        rawPacket.data[offset + OFFSET_VERSION]     = version;
        rawPacket.data[offset + OFFSET_OPERATION]   = operation;
        rawPacket.data[offset + OFFSET_TYPE]        = type;

        length = next.encode(packet, rawPacket, offset + HEADER_LENGTH);

        if (length == 0) {
            return 0;
        }

        length += HEADER_LENGTH;

        return length;
    }

    @Override
    public Header decode(RawPacket rawPacket, int offset) throws PacketException {

        if (rawPacket.length < offset + HEADER_LENGTH) {
            throw new PacketException("decode OSA header: size too small (present=" + (rawPacket.length - offset) + ", required=" + HEADER_LENGTH + ")");
        }

        version     = rawPacket.data[offset + OFFSET_VERSION];
        operation   = rawPacket.data[offset + OFFSET_OPERATION];
        type        = rawPacket.data[offset + OFFSET_TYPE];

        switch (operation) {
            case OPERATION_GET:
            case OPERATION_SET:             new OsaGetSetHeader(this);       break;
            case OPERATION_LOG:             new OsaLogHeader(this);          break;
            case OPERATION_LOG_SUBSCRIBE:   new OsaLogSubscribeHeader(this); break;
            default:                        return null;
        }

        next.decode(rawPacket, offset + HEADER_LENGTH);

        if (next == null) {
            return null;
        }

        return this;
    }
}


