package com.aykj.loglink;

import java.util.Random;

/**
 * a simple random id generator
 */
public class RequestIdGenerator {
    private final static String charMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static String randomString(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(charMap.charAt(random.nextInt(charMap.length())));
        }
        return stringBuilder.toString();
    }

    /**
     * 生成一个请求id
     *
     * @return
     */
    public static String nextId() {
        String timeHex = String.format("%x", System.currentTimeMillis() - 1513728000000L);
        return randomString(6) + timeHex;
    }
}
