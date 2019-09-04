package com.aykj.loglink;


public class Constants {
    public static String getRequestIdKey(){
        return System.getProperty("logRequestIdKey","logRequestId");
    }
}
