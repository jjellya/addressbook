package com.baldgroup.addressbook.service;

import com.baldgroup.addressbook.pojo.PersonInfo;

/**
 * Create By  @林俊杰
 * 2020/4/25 13:24
 *
 * @version 1.0
 */
public interface PersonService {
    PersonInfo addPerson(PersonInfo person,String userId);

    void save(PersonInfo person,String userId);

    void delete(String personId,String userId);
}
