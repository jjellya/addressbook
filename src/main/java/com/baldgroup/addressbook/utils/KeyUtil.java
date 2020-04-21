package com.baldgroup.addressbook.utils;

import java.util.Random;

/**
 * Create By  @林俊杰
 * 2020/4/5 18:36
 *
 * @version 1.0
 */
public class KeyUtil {
    /*
     * 生成唯一的主键
     * 格式：时间+随机数
     *@return
     * */
    public static synchronized String genUniqueKey(){

        Random random = new Random();

        Integer number = random.nextInt(90) + 10;

        return System.currentTimeMillis() + String.valueOf(number);

    }
}
