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

    public short getUInt8(int offset) {
        return (short) (((((short) data[offset + 0]) & 0xff) << 0));
    }

    public int getUInt16(int offset) {
        return (int) (((((int) data[offset + 0])     & 0xff) << 0) |
                      ((((int) data[offset + 1]) & 0xff) << 8));
    }

    public long getUInt32(int offset) {
        return (long) (((((long) data[offset + 0])     & 0xff) <<  0) |
                       ((((long) data[offset + 1]) & 0xff) <<  8) |
                       ((((long) data[offset + 2]) & 0xff) << 16) |
                       ((((long) data[offset + 3]) & 0xff) << 24));
    }
}
