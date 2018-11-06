package com.mmall.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by RookieWangZhiWei on 2018/11/4.
 */
public class LevelUtil {


    public  final static String SEPARRATOR = ".";

    public  final static String ROOT = "0";


    public static String calculateLevel(String parentLevel, int parentId) {

        if (StringUtils.isBlank(parentLevel)) {
            return ROOT;
        } else {
            return StringUtils.join(parentLevel, SEPARRATOR, parentId);
        }

    }
}
