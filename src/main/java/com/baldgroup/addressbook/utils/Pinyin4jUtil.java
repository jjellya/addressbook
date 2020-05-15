package com.baldgroup.addressbook.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Create By  @林俊杰
 * 2020/5/11 22:45
 *
 * @version 1.0
 */
public class Pinyin4jUtil {

    public static String getFirstPinYin(String chinese) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuilder firstPinyin = new StringBuilder();
        if(chinese!=null) {
            char[] chineseArr = chinese.trim().toCharArray();
            try {
                for (int i = 0, len = chineseArr.length; i < len; i++) {
                    if (Character.toString(chineseArr[i]).matches("[\\u4E00-\\u9FA5]+")) {
                        String[] pys = PinyinHelper.toHanyuPinyinStringArray(chineseArr[i], format);
                        firstPinyin.append(pys[0].charAt(0));
                    } else {
                        firstPinyin.append(chineseArr[i]);
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                badHanyuPinyinOutputFormatCombination.printStackTrace();
            }
        }
        return firstPinyin.toString();
    }
}
