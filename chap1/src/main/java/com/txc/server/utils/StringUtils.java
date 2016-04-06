package com.txc.server.utils;

/**
 * Created by tanxiaocan on 2016/4/5.
 */
public class StringUtils {
    public static boolean  isEmpty(String str){
        if(str == null || str.equals("")){
            return true;
        }
        return false;
    }
}
