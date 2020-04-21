package com.baldgroup.addressbook.pojo;

import lombok.Data;

/**
 * Create By  @入门小学徒_J
 * 2020/4/5 18:21
 *
 * Modified By  @江海彬
 * 2020/4/7
 *
 * @version 1.0
 */

@Data
public class UserInfo {
    private String userId;

    // 用户昵称
    private String userName;

    // 密码
    private String userPassword;

    // 尊贵度
    private Integer userLevel;

    // 当前通讯录中联系人数量
    private Integer bookNumber = 0;//默认新建为零;

    // 当前用户通讯录可用数量
    private Integer bookAvailable;

    public UserInfo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;

    }
}
