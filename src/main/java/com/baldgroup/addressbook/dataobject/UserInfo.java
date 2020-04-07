package com.baldgroup.addressbook.dataobject;

import com.baldgroup.addressbook.enums.UserPrivilegeEnums;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

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

    /**当前通讯录中联系人数量**/
    private Integer bookNumber = 0;//默认新建为零;

    /**当前用户通讯录可用数量**/
    private Integer bookAvailable = UserPrivilegeEnums.ORDINARY_AVAILABLE.getCode();

    public UserInfo(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;

    }
}
