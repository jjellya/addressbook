package com.baldgroup.addressbook.service;

import com.baldgroup.addressbook.pojo.UserInfo;

/**
 * Create By  @林俊杰
 * 2020/4/21 20:24
 *
 * @version 1.0
 */
public interface UserService {

    UserInfo fineOne(String id);

    UserInfo save(UserInfo user);

    UserInfo activate(String code);

    void addUser(String userName,String password,String email,String Code);
}
