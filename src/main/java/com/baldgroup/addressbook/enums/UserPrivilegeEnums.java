package com.baldgroup.addressbook.enums;

import lombok.Getter;

/**
 * Create By  @入门小学徒_J
 * 2020/4/5 18:03
 *
 * @version 1.0
 */
@Getter
public enum UserPrivilegeEnums {
    ORDINARY_AVAILABLE(100,"普通用户可添加最大数量"),

    VIP_AVAILABLE(200,"VIP用户可添加最大数量"),

    UNACTIVATE_USER(0,"未激活用户"),

    ORDINARY_USER(1,"普通用户"),

    VIP_USER(2,"VIP用户"),
    ;


    private Integer code;

    private String message;

    UserPrivilegeEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
