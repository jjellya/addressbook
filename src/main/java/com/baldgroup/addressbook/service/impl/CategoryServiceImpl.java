package com.baldgroup.addressbook.service.impl;

import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.service.CategoryService;
import com.baldgroup.addressbook.utils.KeyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Create By  @林俊杰
 * 2020/4/27 19:14
 *
 * @version 1.0
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private ModifyInfo modifyInfo;
    @Resource
    private SearchInfo searchInfo;

    @Override
    public void move(String personId, String categoryId, String userId) {
        PersonInfo person = searchInfo.queryPersonInfoById(personId,userId);
        //Attention:This action maybe cause error!
        //因为我没有检查categoryId的合法性,所以有隐患;<=======By@林俊杰
        person.setCategoryId(categoryId);
        modifyInfo.updatePersonInfo(person);
    }

    @Override
    public void addCategory(String categoryName, String userId) {
        PersonCategory category = new PersonCategory();
        category.setCategoryName(categoryName);
        category.setCategoryId(KeyUtil.genUniqueKey());
        category.setUserId(userId);
        modifyInfo.insertCategory(category);
    }

    @Override
    public void save(PersonInfo person, String userId) {

    }

    @Override
    public void deletePerson(String personId, String userId) {
        String categoryName = "未分组";
        PersonCategory category = searchInfo.queryPersonCategoryByName(userId,categoryName);
        PersonInfo person = searchInfo.queryPersonInfoById(personId,userId);
        person.setCategoryId(category.getCategoryId());
        modifyInfo.updatePersonInfo(person);
    }
}
