package com.baldgroup.addressbook;

import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.pojo.UserInfo;
import com.baldgroup.addressbook.utils.KeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
class AddressbookApplicationTests {
    @Resource
    private ModifyInfo modifyInfo;

    @Resource
    private SearchInfo searchInfo;

    @Test
    void searchInfoTest(){
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

        // List<PersonInfo> personInfos = searchInfo.queryMessage("10086", "1");
        // System.out.println(personInfos);
    }

    @Test
    void modifyInfoTest(){
        // UserInfo userInfo = new UserInfo("11111", "小qin", "11992");
        // PersonInfo personInfo = new PersonInfo("117799", "eeeee", "11111", "", "77dddd",
        //         "11111", "88787@", "src", "dd", "aa", new Date(),
        //         "1221", "jdjfjid", "1", "10086");
        // List<String> list = new ArrayList<>();
        // list.add("117799");

        // modifyInfo.insertUserInfo(userInfo);
        // modifyInfo.updateUserInfo(userInfo);
        // modifyInfo.insertPerson(personInfo);
        // modifyInfo.updatePersonInfo(personInfo);
        // modifyInfo.updatePersonCategory(list, "2");
        // modifyInfo.modifyPersonCategory("0", "1");
        // modifyInfo.deleteCategory("1");
        // modifyInfo.deletePerson("117799");
    }

}
