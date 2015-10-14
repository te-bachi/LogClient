package com.oscilloquartz.logclient.net;

/**
 *
 */
public class OsaLogSubscribePacket extends OsaPacket {

    private static final int    LENGTH                  = 5;

    protected static final int  OFFSET_SUBSCRIBE_TYPE   = 3;
    protected static final int  OFFSET_DURATION         = 4;

    public static final byte    SUBSCRIBE_TYPE_REQUEST  = 0x01;
    public static final byte    SUBSCRIBE_TYPE_CANCEL   = 0x02;
    public static final byte    SUBSCRIBE_TYPE_GRANT    = 0x03;
    public static final byte    SUBSCRIBE_TYPE_REJECT   = 0x04;
    public static final byte    SUBSCRIBE_TYPE_EXPIRED  = 0x05;

    public OsaLogSubscribePacket(byte type, byte subscribeType, byte duration) {
        super();

        this.data[OFFSET_VERSION]           = VERSION_V1;
        this.data[OFFSET_OPERATION]         = OPERATION_LOG_SUBSCRIBE;
        this.data[OFFSET_TYPE]              = type;

        this.data[OFFSET_SUBSCRIBE_TYPE]    = subscribeType;
        this.data[OFFSET_DURATION]          = duration;

        this.length                         = LENGTH;
    }
}
