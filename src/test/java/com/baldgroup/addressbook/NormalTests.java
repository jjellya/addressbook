package com.baldgroup.addressbook;


import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.service.impl.FileServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * Create By  @江海彬
 * 2020/4/15
 *
 * @version 1.0
 */

@SpringBootTest
class NormalTests {
    @Resource
    private ModifyInfo modifyInfo;

    @Resource
    private SearchInfo searchInfo;

    @Test
    void searchInfoTest() {
        // UserInfo userInfo = searchInfo.queryUserInfo("10086");
        // System.out.println(userInfo);

        // PersonInfo personInfo = searchInfo.queryPersonInfo("10086", "小黄");
        // System.out.println(personInfo);

        // List<PersonCategory> personCategories = searchInfo.queryCategories("10086");
        // System.out.println(personCategories);

        // List<PersonInfo> personInfos = searchInfo.queryPersonInfos("1");
        // System.out.println(personInfos);

        // List<PersonInfo> personInfos = searchInfo.queryAllPersonInfos("10086");
        // System.out.println(personInfos);

        PersonCategory personCategory = searchInfo.queryPersonCategoryByName("10086", "未分组");
        System.out.println(personCategory.getCategoryId());

        // List<PersonInfo> personInfos = searchInfo.queryMessage("10086", "1");
        // System.out.println(personInfos);
    }

    @Test
    void modifyInfoTest() {
        // UserInfo userInfo = new UserInfo("11111", "小qin", "11444");
        // PersonInfo personInfo = new PersonInfo("117799", "eeeee", "11111", "", "77dddd",
        //         "11111", "88787@", "src", "dd", "aa", new Date(),
        //         "1221", "jdjfjid", "1", "10086");
        // List<String> list = new ArrayList<>();
        // list.add("10017");
        // list.add("10010");

        // modifyInfo.insertUserInfo(userInfo);
        // modifyInfo.updateUserInfo(userInfo);
        // modifyInfo.insertPerson(personInfo);
        // modifyInfo.updatePersonInfo(personInfo);
        // modifyInfo.updatePersonCategory(list, "0");
        // modifyInfo.modifyPersonCategory("0", "1");
        // modifyInfo.deleteCategory("1");
        // modifyInfo.deletePerson("117799");
    }

    @Test
    void patternTest() {
        // String text = "小刚,13266855599,55554454545,aajj,88989999,88989999@qq.com,加载,哈哈哈哈,2005/3/6,18884,朋友";
        // String pattern = "^([a-zA-Z0-9\\u4e00-\\u9fa5]*),(\\d*),(\\d*),([a-zA-Z0-9]*),(\\d*),([a-zA-Z0-9@.]*),([a-zA-Z0-9\\u4e00-\\u9fa5]*),([a-zA-Z0-9\\u4e00-\\u9fa5]*),([0-9/]*),(\\d*),([a-zA-Z0-9\\u4e00-\\u9fa5]*)$";
        // Pattern compile = Pattern.compile(pattern);
        // Matcher matcher = compile.matcher(text);
        // if (matcher.find()) {
        //     System.out.println(matcher.group(1));
        //     System.out.println(matcher.group(2));
        //     System.out.println(matcher.group(3));
        //     System.out.println(matcher.group(4));
        //     System.out.println(matcher.group(5));
        //     System.out.println(matcher.group(6));
        //     System.out.println(matcher.group(7));
        //     System.out.println(matcher.group(8));
        //     System.out.println(matcher.group(9));
        //     System.out.println(matcher.group(10));
        //     System.out.println(matcher.group(11));
        // }
        // // System.out.println(matcher.find());
        // File file = new File("F:\\test.csv");
        // FileServiceImpl fileService = new FileServiceImpl();
        // List<PersonInfo> personInfos = fileService.transformPersonInfo(file, "10086");
        // if (!personInfos.isEmpty()) {
        //     for (PersonInfo personInfo : personInfos) {
        //         System.out.println(personInfo);
        //     }
        // }
        // else
        //     System.out.println("false");
    }
}
