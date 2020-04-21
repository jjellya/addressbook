package com.baldgroup.addressbook.utils;

import java.util.UUID;

/**
 * Create By  @入门小学徒_J
 * 2020/4/5 18:36
 *
 * @version 1.0
 */
public class KeyUtil {
    /*
     * 生成唯一的主键
     *@Modified by 江海彬
     * */
    public static String genUniqueKey() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
