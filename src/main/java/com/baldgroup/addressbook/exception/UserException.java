package com.baldgroup.addressbook.exception;

import com.baldgroup.addressbook.enums.ResultEnum;

/**
 * Create By  @林俊杰
 * 2020/4/25 17:09
 *
 * @version 1.0
 */
public class UserException extends RuntimeException{

    private Integer code;

    public UserException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code=resultEnum.getCode();
    }

    public UserException(Integer code,String message) {
        super(message);

        this.code = code;
    }
}
