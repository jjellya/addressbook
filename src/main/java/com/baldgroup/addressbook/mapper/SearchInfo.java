package com.baldgroup.addressbook.mapper;

import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.pojo.UserInfo;
import com.baldgroup.addressbook.pojo.UserMessage;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Create By  @江海彬
 * Modified By @林俊杰
 * 2020/4/20
 * @version 1.2
 */

@Mapper
@Repository
public interface SearchInfo {
    // 查看个人资料、登陆(单表)
    UserInfo queryUserInfo(@Param("id") String userId);

    //
    UserInfo queryUserInfoByPassword(@Param("password") String password);

    //查询某个联系人(单表)
    PersonInfo queryPersonInfo(@Param("id") String userId, @Param("name") String personName);

    PersonCategory queryPersonCategoryByName(@Param("id") String userId,@Param("name") String categoryName);

    PersonInfo queryPersonInfoById(@Param("personId") String personId, @Param("userId") String userId);

    //查询分组(单表)
    List<PersonCategory> queryCategories(@Param("id") String userId);

    //查询某个分组中的联系人(单表)
    List<PersonInfo> queryPersonInfos(@Param("id") String categoryId);

    //查询所有联系人
    List<PersonInfo> queryAllPersonInfos(@Param("id") String UserId);

    //模糊搜索(单表)
    List<PersonInfo> FuzzySearch(@Param("id") String UserId, @Param("message") String message);

    //查询用户所有留言
    List<UserMessage> queryMessage(@Param("id") String userId);

    //查询所有未读留言(state==0)
    List<UserMessage> queryUnreadMessage();
}
