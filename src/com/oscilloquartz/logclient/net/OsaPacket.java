package com.oscilloquartz.logclient.net;

import java.net.DatagramPacket;
import java.net.InetAddress;

/**
 *
 */
public class OsaPacket {

    private static final int    BUFFER_MAX_LENGTH       = 1500;

    protected static final int  OFFSET_VERSION          = 0;
    protected static final int  OFFSET_OPERATION        = 1;
    protected static final int  OFFSET_TYPE             = 2;

    protected static final byte VERSION_V1              = 0x01;

    public static final byte    OPERATION_GET           = 0x01;
    public static final byte    OPERATION_SET           = 0x02;
    public static final byte    OPERATION_LOG           = 0x03;
    public static final byte    OPERATION_LOG_SUBSCRIBE = 0x04;

    public static final byte    TYPE_REQUEST            = 0x01;
    public static final byte    TYPE_RESPONSE           = 0x02;
    public static final byte    TYPE_ASYNC              = 0x03;

    protected byte[]            data;
    protected int               length;

    public OsaPacket() {
        this.data = new byte[BUFFER_MAX_LENGTH];
    }

    public byte[] getData() {
        return this.data;
    }

    public int getLength() {
        return this.length;
    }

}
