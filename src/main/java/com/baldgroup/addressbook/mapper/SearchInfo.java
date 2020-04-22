package com.baldgroup.addressbook.mapper;

import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface SearchInfo {
    // 查看个人资料、登陆(单表)
    UserInfo queryUserInfo(@Param("id") String userId);

    //
    UserInfo queryUserInfoByPassword(@Param("password") String password);

    //查询某个联系人(单表)
    PersonInfo queryPersonInfo(@Param("id") String userId, @Param("name") String personName);

    //查询分组(单表)
    List<PersonCategory> queryCategories(@Param("id") String userId);

    //查询某个分组中的联系人(单表)
    List<PersonInfo> queryPersonInfos(@Param("id") String categoryId);

    //查询所有联系人
    List<PersonInfo> queryAllPersonInfos(@Param("id") String UserId);

    //模糊搜索(单表)
    List<PersonInfo> queryMessage(@Param("id") String UserId, @Param("message") String message);
}
