package com.baldgroup.addressbook.enums;

import lombok.Getter;

/**
 * Create By  @林俊杰
 * 2020/4/25 17:11
 * Modified By @江海彬
 * @version 1.0
 */
@Getter
public enum ResultEnum {

    USER_PERMISSION_ERROR(500,"该用户没有此权限"),

    UNGROUP_CANNOT_DELETE(101,"不能删除\"未分组\""),

    USER_FILE_ERROR(150, "文件不合法（非空字段为空或格式不正确）"),

    USER_NULL_EXPORT(151,"联系人为空,无法导出"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
