package com.baldgroup.addressbook.enums;

import lombok.Getter;

/**
 * Create By  @林俊杰
 * 2020/4/25 17:11
 *
 * @version 1.0
 */
@Getter
public enum ResultEnum {

    USER_PERMISSION_ERROR(500,"该用户没有此权限"),

    UNGROUP_CANNOT_DELETE(101,"不能删除\"未分组\""),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
