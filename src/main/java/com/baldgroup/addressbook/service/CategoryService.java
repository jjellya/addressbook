package com.baldgroup.addressbook.service;

import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;

/**
 * Create By  @林俊杰
 * 2020/4/27 19:12
 *
 * @version 1.0
 */
public interface CategoryService {
    void move(String personId,String categoryId,String userId);

    void addCategory(String categoryName,String userId);

    void save(PersonInfo person,String userId);

    void deletePerson(String personId,String userId);
}
