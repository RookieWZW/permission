package com.mmall.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * Created by RookieWangZhiWei on 2018/11/5.
 */
@Slf4j
public class MD5Util {

    public final static String encrypt(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();

            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            mdInst.update(btInput);

            byte[] md = mdInst.digest();

            int j = md.length;

            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error("generate md5 error :{}", s, e);

            return null;
        }
    }
}
