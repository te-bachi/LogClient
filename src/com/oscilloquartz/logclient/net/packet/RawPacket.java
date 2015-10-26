package com.oscilloquartz.logclient.net.packet;

/**
 *
 */
public class RawPacket {

    private static final int    BUFFER_MAX_LENGTH       = 1500;

    public int     length;
    public byte[]  data;

    public RawPacket() {
        this.length = 0;
        this.data   = new byte[BUFFER_MAX_LENGTH];
    }

    public RawPacket(byte[] data, int length) {
        this.data   = data;
        this.length = length;
    }
}
