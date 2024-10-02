package com.discwords.discwords.common;

public class Utils {
    public static long generateId(){
        long time = System.currentTimeMillis();
        time = time % 1000000000;
        time = time * 1000;
        time = time + (long) (Math.random() * 1000);

        return time;
    }
}
