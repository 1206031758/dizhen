package com.bunfly.dizhen.util;

public class NumberFormatUtil {

    public static int format(String msg, int def){
        int i;
        if(msg==null || "".equals(msg)){
            return def;
        }
        try {
            i = Integer.parseInt(msg);
        } catch (Exception e) {
            return def;
        }
        return i;
    }
}
