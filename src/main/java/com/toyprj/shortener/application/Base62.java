package com.toyprj.shortener.application;

import java.util.Random;

public class Base62 {
    private static final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public static String encode(String originUrl) {
        char[] originArr = originUrl.toCharArray();
        int[] originIntArr = new int[originArr.length];
        String encodedUrl = "";

        for (int i = 0; i < originArr.length; i++) {
            originIntArr[i] = (int) originArr[i] % 62;
        }

        for (int i = 0; i < 7; i++) {
            encodedUrl += BASE62[originIntArr[new Random().nextInt(originIntArr.length)]];
        }
        return encodedUrl;
    }
}
