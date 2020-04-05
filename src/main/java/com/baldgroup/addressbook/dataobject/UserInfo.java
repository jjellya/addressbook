package com.baldgroup.addressbook.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Create By  @入门小学徒_J
 * 2020/4/5 18:21
 *
 * @version 1.0
 */
@Data
@Entity
public class UserInfo {

    @Id
    private String userId;

    /**用户昵称**/
    private String userName;

    /**密码**/
    private String userPassword;

    /**尊贵度**/
    private Integer userLevel;

    /**通讯录id**/
    private String bookId;

    public UserInfo() {
    }

    public UserInfo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;

    }
}
