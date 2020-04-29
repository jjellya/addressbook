package com.baldgroup.addressbook.service.impl;

import com.baldgroup.addressbook.enums.ResultEnum;
import com.baldgroup.addressbook.exception.UserException;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.service.FileService;
import com.baldgroup.addressbook.utils.KeyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Create By  @江海彬
 * 2020/4/26 23:53
 *
 * @version 1.0
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private SearchInfo searchInfo;

    @Override
    public List<PersonInfo> transformPersonInfo(File file, String userId) {
        List<PersonInfo> personInfoList = new ArrayList<>();

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");) {
            BufferedReader bfr = new BufferedReader(isr);
            String line = null;
            String pattern = "^([a-zA-Z0-9\\u4e00-\\u9fa5]*),(\\d*),(\\d*),([a-zA-Z0-9]*),(\\d*),([a-zA-Z0-9@.]*),([a-zA-Z0-9\\u4e00-\\u9fa5]*),([a-zA-Z0-9\\u4e00-\\u9fa5]*),([0-9/]*),(\\d*),([a-zA-Z0-9\\u4e00-\\u9fa5]*)$";
            Pattern compile = Pattern.compile(pattern);
            int i = 0;
            while ((line = bfr.readLine()) != null) {
                Matcher matcher = compile.matcher(line);
                if (matcher.find()) {
                    if (matcher.group(2).equals("") && matcher.group(2).equals(""))
                        throw new UserException(ResultEnum.USER_FILE_ERROR);
                    PersonInfo personInfo = new PersonInfo();
                    //非空
                    personInfo.setPersonName(matcher.group(1));
                    personInfo.setPersonPhone1(matcher.group(2));
                    personInfo.setPersonId(KeyUtil.genUniqueKey());
                    personInfo.setUserId(userId);
                    personInfo.setCategoryId(searchInfo.queryPersonCategoryByName(userId, "未分组").getCategoryId());
                    //可空
                    personInfo.setPersonPhone2(matcher.group(3).equals("") ? null : matcher.group(3));
                    personInfo.setPersonWechat(matcher.group(4).equals("") ? null : matcher.group(4));
                    personInfo.setPersonQq(matcher.group(5).equals("") ? null : matcher.group(5));
                    personInfo.setPersonMail(matcher.group(6).equals("") ? null : matcher.group(6));
                    personInfo.setPersonCompany(matcher.group(7).equals("") ? null : matcher.group(7));
                    personInfo.setPersonAddress(matcher.group(8).equals("") ? null : matcher.group(8));
                    personInfo.setPersonBirthday(matcher.group(9).equals("") ? null : new SimpleDateFormat("yyyy-MM-dd").parse(matcher.group(9)));
                    personInfo.setPersonPostcode(matcher.group(10).equals("") ? null : matcher.group(10));
                    personInfo.setPersonRemark(matcher.group(11).equals("") ? null : matcher.group(11));

                    personInfoList.add(personInfo);
                    i++;
                }
            }
            if (!personInfoList.isEmpty())
                return personInfoList;
        } catch (FileNotFoundException e) {
            throw new NullPointerException();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
