package com.baldgroup.addressbook.mapper;

import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.pojo.UserInfo;

import java.util.List;

public interface SearchInfo {
    // 查看个人资料、登陆(单表)
    UserInfo queryUserInfo(String userId);

    //查询某个联系人(单表)
    PersonInfo queryPersonInfo(String userId, String personName);

    //查询分组(单表)
    List<PersonCategory> queryCategories(String userId);

    //查询某个分组中的联系人(单表)
    List<PersonInfo> queryPersonInfos(String categoryId);

    //查询所有联系人
    List<PersonInfo> queryAllPersonInfos(String UserId);

    //模糊搜索(单表)
    List<PersonInfo> queryMessage(String UserId, String message);
}
