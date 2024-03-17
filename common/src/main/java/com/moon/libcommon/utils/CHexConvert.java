package com.moon.libcommon.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

public class CHexConvert {
    private static final String mHexStr = "0123456789ABCDEF";
    private static final char[] mChars = mHexStr.toCharArray();

    public static boolean checkHexStr(String sHex) {
        String sTmp;
        int iLen;
        if (sHex == null || (iLen = (sTmp = sHex.trim().replace(" ", "").toUpperCase(Locale.US)).length()) <= 1 || iLen % 2 != 0) {
            return false;
        }
        for (int i = 0; i < iLen; i++) {
            if (!mHexStr.contains(sTmp.substring(i, i + 1))) {
                return false;
            }
        }
        return true;
    }

    public static String str2HexStr(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        byte[] bs = null;
        try {
            bs = str.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (bs == null) {
            return "";
        }
        for (byte b : bs) {
            sb.append(mChars[(b & 255) >> 4]);
            sb.append(mChars[b & 15]);
        }
        return sb.toString().trim();
    }

    public static String hexStr2Str(String hexStr) {
        if (hexStr == null) {
            return null;
        }
        String hexStr2 = hexStr.trim().replace(" ", "").toUpperCase(Locale.US);
        char[] hexs = hexStr2.toCharArray();
        byte[] bytes = new byte[hexStr2.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) (((mHexStr.indexOf(hexs[2 * i]) << 4) | mHexStr.indexOf(hexs[(2 * i) + 1])) & 255);
        }
        String result = "";
        try {
            result = new String(bytes, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String bytesToStr(byte[] data) {
        return data == null ? "" : hexStr2Str(byte2HexStr(data, data.length));
    }

    public static String byte2HexStr(byte[] b) {
        return b == null ? "" : byte2HexStr(b, b.length);
    }

    public static String byte2HexStr(byte[] b, int iLen) {
        if (b == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < iLen; n++) {
            sb.append(mChars[(b[n] & 255) >> 4]);
            sb.append(mChars[b[n] & 15]);
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }

    public static String int2HexStr(int[] b, int iLen) {
        if (b == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < iLen; n++) {
            sb.append(mChars[(((byte) b[n]) & 255) >> 4]);
            sb.append(mChars[((byte) b[n]) & 15]);
        }
        return sb.toString().trim().toUpperCase(Locale.US);
    }

    public static String byte2String(byte[] b, int len) {
        if (b == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(String.valueOf((int) b[i]));
        }
        return sb.toString();
    }

    public static byte[] hexStr2Bytes(String src) {
        if (src == null) {
            return null;
        }
        String src2 = src.trim().replace(" ", "").toUpperCase(Locale.US);
        int iLen = src2.length() / 2;
        byte[] ret = new byte[iLen];
        for (int i = 0; i < iLen; i++) {
            int m = (i * 2) + 1;
            ret[i] = (byte) (Integer.decode("0x" + src2.substring(i * 2, m) + src2.substring(m, m + 1)).intValue() & 255);
        }
        return ret;
    }

    public static String strToUnicode(String strText) {
        if (strText == null) {
            return null;
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < strText.length(); i++) {
            char c = strText.charAt(i);
            String strHex = Integer.toHexString(c);
            if (c > 128) {
                str.append("\\u");
            } else {
                str.append("\\u00");
            }
            str.append(strHex);
        }
        return str.toString();
    }

    public static String unicodeToString(String hex) {
        if (hex == null) {
            return null;
        }
        int t = hex.length() / 6;
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < t; i++) {
            String s = hex.substring(i * 6, (i + 1) * 6);
            str.append(new String(Character.toChars((Integer.valueOf(s.substring(2, 4), 16).intValue() << 8) | Integer.valueOf(s.substring(4), 16).intValue())));
        }
        return str.toString();
    }

    public static String intToHexString(int num) {
        return String.format("%02x", Integer.valueOf(num));
    }

    public static byte intToByte(int num) {
        return (byte) num;
    }

    public static int byteToInt(byte b) {
        return b & 255;
    }

    public static String byteToHexString(byte b) {
        return intToHexString(b & 255);
    }

    public static byte[] intToBytes(int n) {
        return new byte[]{(byte) (n & 255), (byte) ((n >> 8) & 255), (byte) ((n >> 16) & 255), (byte) ((n >> 24) & 255)};
    }

    public static byte[] intToBigBytes(int n) {
        return new byte[]{(byte) ((n >> 24) & 255), (byte) ((n >> 16) & 255), (byte) ((n >> 8) & 255), (byte) (n & 255)};
    }

    public static byte[] int2byte2(int res) {
        return new byte[]{(byte) ((res >> 8) & 255), (byte) (res & 255)};
    }

    public static byte[] getBooleanArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    public static byte[] getBooleanArrayBig(byte b) {
        byte[] array = new byte[8];
        for (int i = 0; i < 8; i++) {
            array[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    public static int bytesToInt(byte h, byte l) {
        return ((255 & h) << 8) + (255 & l);
    }

    public static int bytesToInt(byte[] data, int start, int len) {
        int result = 0;
        if (len == 4) {
            byte[] bytes = new byte[4];
            System.arraycopy(data, start, bytes, 0, len);
            result = bytesToInt(bytes);
        } else if (len == 2) {
            result = bytesToInt(data[start], data[start + 1]);
        }
        return result;
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [long] */
    public static long bytesToLong(byte[] data, int start, int len) {
        char c = 0;
        int i = start;
        int index = 0;
        while (i < start + len) {
            c |= (data[i] & 255) << (((len - 1) - index) * 8);
            i++;
            index++;
        }
        return c;
    }

    public static short bytesToShort(byte h, byte l) {
        return (short) bytesToInt(h, l);
    }

    public static int bytesToInt(byte[] value) {
        if (value == null || value.length < 4) {
            return 0;
        }
        return ((value[0] & 255) << 24) | ((value[1] & 255) << 16) | ((value[2] & 255) << 8) | (value[3] & 255);
    }

    public static byte[] shortToBytes(short n) {
        return new byte[]{(byte) (n & 255), (byte) ((n >> 8) & 255)};
    }

    public static byte[] shortToBigBytes(short n) {
        return new byte[]{(byte) ((n >> 8) & 255), (byte) (n & 255)};
    }

    public static String byteToBit(byte b) {
        return "" + ((int) ((byte) ((b >> 7) & 1))) + ((int) ((byte) ((b >> 6) & 1))) + ((int) ((byte) ((b >> 5) & 1))) + ((int) ((byte) ((b >> 4) & 1))) + ((int) ((byte) ((b >> 3) & 1))) + ((int) ((byte) ((b >> 2) & 1))) + ((int) ((byte) ((b >> 1) & 1))) + ((int) ((byte) ((b >> 0) & 1)));
    }

    public static byte decodeBinaryString(String byteStr) {
        int re;
        if (null == byteStr) {
            return (byte) 0;
        }
        int len = byteStr.length();
        if (len != 4 && len != 8) {
            return (byte) 0;
        }
        if (len != 8) {
            re = Integer.parseInt(byteStr, 2);
        } else if (byteStr.charAt(0) == '0') {
            re = Integer.parseInt(byteStr, 2);
        } else {
            re = Integer.parseInt(byteStr, 2) - 256;
        }
        return (byte) re;
    }

    public static byte decodeHexChar(char hChar, char lChar) {
        String hString = hexChar2BinaryString(hChar);
        String lString = hexChar2BinaryString(lChar);
        String result = "";
        if (!TextUtils.isEmpty(hString)) {
            result = hString;
        }
        if (!TextUtils.isEmpty(lString)) {
            result = result + lString;
        }
        if (TextUtils.isEmpty(result)) {
            return (byte) 0;
        }
        return decodeBinaryString(result);
    }

    public static int getBitByPosition(int src, int pos) {
        if (pos < 0 || pos >= 32) {
            return 0;
        }
        return (src >> pos) & 1;
    }

    private static String hexChar2BinaryString(char src) {
        String result = "";
        if (src == '0') {
            result = "0000";
        } else if (src == '1') {
            result = "0001";
        } else if (src == '2') {
            result = "0010";
        } else if (src == '3') {
            result = "0011";
        } else if (src == '4') {
            result = "0100";
        } else if (src == '5') {
            result = "0101";
        } else if (src == '6') {
            result = "0110";
        } else if (src == '7') {
            result = "0111";
        } else if (src == '8') {
            result = "1000";
        } else if (src == '9') {
            result = "1001";
        } else if (src == 'A' || src == 'a') {
            result = "1010";
        } else if (src == 'B' || src == 'b') {
            result = "1011";
        } else if (src == 'C' || src == 'c') {
            result = "1100";
        } else if (src == 'D' || src == 'd') {
            result = "1101";
        } else if (src == 'E' || src == 'e') {
            result = "1110";
        } else if (src == 'F' || src == 'f') {
            result = "1111";
        }
        return result;
    }
}