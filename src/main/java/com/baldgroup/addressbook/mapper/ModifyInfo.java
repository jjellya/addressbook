package com.baldgroup.addressbook.mapper;

import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.pojo.UserInfo;

public interface ModifyInfo {
    //注册(单表)
    int insertUserInfo(UserInfo userInfo);

    //修改个人资料(单表)
    int updateUserInfo(UserInfo userInfo);

    //修改联系人资料(单表)
    int updatePersonInfo(PersonInfo personInfo);

    //修改分组(单表)
    int updateCategory(String personId, String newCategoryId);

    //删除联系人(单表)
    int deletePerson(String userId, String personId);

    //删除联系组(要先修改组中所有人的组号)(单表)
    int deleteCategory(String categoryId);

    //添加联系人(单表)
    int insertPerson(PersonInfo personInfo);
}
