package com.mtrp.hibp;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Util {

    public static String getSHA1(String text){
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance( "SHA-1" );
            byte[] bytes = text.getBytes();
            digest.update(bytes, 0, bytes.length);
            return bytesToHex(digest.digest());

        } catch (NoSuchAlgorithmException e) {
            Log.e("UTIL", "SHA-1 Instantiation failed", e);
            throw new RuntimeException("SHA-1 Instantiation failed", e);
        }
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
