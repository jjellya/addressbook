package com.baldgroup.addressbook.dataobject;

import com.baldgroup.addressbook.enums.UserPrivilegeEnums;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Create By  @入门小学徒_J
 * 2020/4/5 17:54
 *
 * @version 1.0
 */
@Data
@Entity
public class BookInfo {

    @Id
    private String bookId;

    /**当前通讯录中联系人数量**/
    private Integer bookNumber = 0;//默认新建为零;

    /**当前用户通讯录可用数量**/
    private Integer bookAvailable = UserPrivilegeEnums.ORDINARY_AVAILABLE.getCode();

}
