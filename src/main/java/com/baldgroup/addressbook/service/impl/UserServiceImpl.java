package com.baldgroup.addressbook.service.impl;

import com.baldgroup.addressbook.enums.UserPrivilegeEnums;
import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.UserInfo;
import com.baldgroup.addressbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

/**
 * Create By  @林俊杰
 * 2020/4/21 20:26
 *
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private ModifyInfo modifyInfo;
    @Resource
    private SearchInfo searchInfo;

    @Override
    public UserInfo fineOne(String id) {
        return searchInfo.queryUserInfo(id);
    }

    @Override
    public UserInfo save(UserInfo user) {

        modifyInfo.updateUserInfo(user);

        return user;
    }

    @Override
    public UserInfo activate(String code) {
        return searchInfo.queryUserInfo(code);

    }

    @Override
    public void addUser(String userName, String password, String email, String Code) {

        UserInfo user = new UserInfo();
        user.setUserName(userName);
        user.setUserPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setUserId(Code+email);
        user.setBookAvailable(0);
        user.setBookNumber(0);
        user.setUserLevel(UserPrivilegeEnums.UNACTIVATE_USER.getCode());
        modifyInfo.updateUserInfo(user);

    }
}