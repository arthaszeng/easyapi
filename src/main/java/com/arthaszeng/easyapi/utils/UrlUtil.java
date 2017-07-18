package com.arthaszeng.easyapi.utils;

import java.io.UnsupportedEncodingException;
/**
 * url转码、解码
 *
 * @author lifq
 * @date 2015-3-17 下午04:09:35
 */
public class UrlUtil {
    private static final String ENCODE = "UTF-8";

    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String getURLEncoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, ENCODE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}