package com.baldgroup.addressbook.mapper;

import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Create By  @江海彬
 * Modified by @林俊杰
 * 2020/4/25
 * @version 1.3
 * PS:每次都得改改 DAO层，我很难受啊
 */

@Mapper
public interface ModifyInfo {
    //注册(单表)
    int insertUserInfo(@Param("user") UserInfo userInfo);

    //添加联系人(单表)
    int insertPerson(@Param("person") PersonInfo personInfo);

    //添加分组(单表)
    int insertCategory(@Param("personCategory")PersonCategory personCategory);

    //修改个人资料(单表)
    int updateUserInfo(@Param("user") UserInfo userInfo);

    //修改联系人资料(单表)
    int updatePersonInfo(@Param("person") PersonInfo personInfo);



    //修改用户分组(单表),支持批处理
    int updatePersonCategory(@Param("list") List<String> personId, @Param("cid") String newCategoryId);

    //与删除联系组配合操作
    int modifyPersonCategory(@Param("oid") String oldCategoryId, @Param("nid") String newCategoryId);

    //删除联系人(单表)
    int deletePerson(@Param("id") String personId);

    //删除联系组(要先修改组中所有人的组号modifyPersonCategory)(单表)
    int deleteCategory(@Param("id") String categoryId);
}
