package com.oscilloquartz.logclient.net.packet;

import java.nio.charset.StandardCharsets;

public class OsaLogHeader extends Header {

    private class CategoryString {
        public int category;
        public String str;
        public CategoryString(int category, String str) {
            this.category = category;
            this.str = str;
        }
    }

    private final CategoryString[] CATEGORY_LIST = {
            new CategoryString(0x00, "ENVIRONMENT"),
            new CategoryString(0x01, "LOG"),
            new CategoryString(0x02, "HAL ENET"),
            new CategoryString(0x03, "HAL TIMER"),
            new CategoryString(0x04, "HAL PTP TIME"),
            new CategoryString(0x05, "HAL SLAVEMGM"),
            new CategoryString(0x06, "HAL SYSCONF"),
            new CategoryString(0x07, "HAL TOD"),
            new CategoryString(0x08, "HAL ESMC"),
            new CategoryString(0x09, "HAL PLL"),
            new CategoryString(0x0a, "FSM"),
            new CategoryString(0x0b, "NETWORK"),
            new CategoryString(0x0c, "TASK"),
            new CategoryString(0x0d, "TASK QUEUE"),
            new CategoryString(0x0e, "PACKET ETHERNET"),
            new CategoryString(0x0f, "PACKET ARP"),
            new CategoryString(0x10, "PACKET IPv4"),
            new CategoryString(0x11, "PACKET IPv6"),
            new CategoryString(0x12, "PACKET ICMP"),
            new CategoryString(0x13, "PACKET UDP"),
            new CategoryString(0x14, "PACKET PTP2"),
            new CategoryString(0x15, "PACKET PTP2 SIGNALING"),
            new CategoryString(0x16, "PACKET PTP2 ANNOUNCE"),
            new CategoryString(0x17, "PACKET ANALYZER"),
            new CategoryString(0x18, "PACKET ASSEMBLER"),
            new CategoryString(0x19, "PING TABLE"),
            new CategoryString(0x1a, "ARP TABLE"),
            new CategoryString(0x1b, "NODE TABLE"),
            new CategoryString(0x1c, "SHADOW TABLE"),
            new CategoryString(0x1d, "PTP PROFILE"),
            new CategoryString(0x1e, "BMCA"),
            new CategoryString(0x1f, "TOD"),
            new CategoryString(0x20, "DATASET DEFAULT"),
            new CategoryString(0x21, "DATASET CURRENT"),
            new CategoryString(0x22, "DATASET PARENT"),
            new CategoryString(0x23, "DATASET TIMEPROP"),
            new CategoryString(0x24, "DATASET PORT"),
            new CategoryString(0x25, "DATASET FOREIGN MASTER"),
            new CategoryString(0x26, "SPI MASTER"),
            new CategoryString(0x27, "SPI SLAVE"),
            new CategoryString(0x28, "PPS PLL"),
            new CategoryString(0x29, "GRANDMASTER"),
            new CategoryString(0x2a, "NETWORK INTERFACE"),
            new CategoryString(0x2b, "HAL ITF TABLE"),
            new CategoryString(0x2c, "HOST BOOT"),
    };

    private String[] LEVEL_LIST = {
        "ERROR",
        "WARN",
        "INFO",
        "DEBUG",
        "VERBOSE"
    };

    public static final int HEADER_LENGTH_MIN           = 12;
    public static final int MESSAGE_LENGTH_MAX          = 256;

    public static final int OFFSET_TIMESTAMP_YEAR       = 0;
    public static final int OFFSET_TIMESTAMP_MONTH      = 1;
    public static final int OFFSET_TIMESTAMP_DAY        = 2;
    public static final int OFFSET_TIMESTAMP_HOUR       = 3;
    public static final int OFFSET_TIMESTAMP_MINUTE     = 4;
    public static final int OFFSET_TIMESTAMP_SECOND     = 5;
    public static final int OFFSET_TIMESTAMP_MILISECOND = 6;
    public static final int OFFSET_CATEGORY             = 8;
    public static final int OFFSET_LEVEL                = 9;
    public static final int OFFSET_MESSAGE_LENGTH       = 10;
    public static final int OFFSET_MESSAGE              = 12;

    public byte      year;
    public byte      month;
    public byte      day;
    public byte      hour;
    public byte      minute;
    public byte      second;
    public short     msec;
    public byte      category;
    public byte      level;
    public short     messageLength;
    public byte[]    message;

    public OsaLogHeader() {
        super();
    }

    public OsaLogHeader(Header prev) {
        super(prev);
    }

    @Override
    public int encode(Packet packet, RawPacket rawPacket, int offset) throws PacketException {
        return 0;
    }

    @Override
    public Header decode(RawPacket rawPacket, int offset) throws PacketException {

        if (rawPacket.length < offset + HEADER_LENGTH_MIN) {
            throw new PacketException("decode OSA log header: size too small (present=" + (rawPacket.length - offset) + ", required=" + HEADER_LENGTH_MIN + ")");
        }

        year            = rawPacket.data[offset + OFFSET_TIMESTAMP_YEAR];
        month           = rawPacket.data[offset + OFFSET_TIMESTAMP_MONTH];
        day             = rawPacket.data[offset + OFFSET_TIMESTAMP_DAY];
        hour            = rawPacket.data[offset + OFFSET_TIMESTAMP_HOUR];
        minute          = rawPacket.data[offset + OFFSET_TIMESTAMP_MINUTE];
        second          = rawPacket.data[offset + OFFSET_TIMESTAMP_SECOND];
        msec            = rawPacket.data[offset + OFFSET_TIMESTAMP_MILISECOND];
        category        = rawPacket.data[offset + OFFSET_CATEGORY];
        level           = rawPacket.data[offset + OFFSET_LEVEL];
        messageLength   = rawPacket.data[offset + OFFSET_MESSAGE_LENGTH];

        if (messageLength > MESSAGE_LENGTH_MAX) {
            message = new byte[MESSAGE_LENGTH_MAX];
        } else {
            message = new byte[messageLength];
        }

        for (int i = 0; i < message.length; i++) {
            message[i] = rawPacket.data[offset + OFFSET_MESSAGE + i];
        }

        return this;
    }

    public String getTimestampString() {
        return String.format("%02d.%02d.%04d %02d:%02d:%02d.%03d", day, month, year, hour, minute, second, msec);
        //return day + "." + month + "." + year + " " + hour + ":" + minute + ":" + second + "." + msec;
    }

    public String getCategoryString() {
        System.out.println("category=" + category + " (" + String.format("0x%02x", category) + ")");
        return CATEGORY_LIST[category].str;
    }

    public String getLevelString() {
        System.out.println("level=" + level + " (" + String.format("0x%02x", level) + ")");
        return LEVEL_LIST[level];
    }

    public String getMessageString() {
        return new String(message, StandardCharsets.US_ASCII);
    }
}
