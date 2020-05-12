package com.baldgroup.addressbook.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Create By  @江海彬
 * 2020/5/12
 * @version 1.0
 * **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMessage {
    private String messageId;

    //接收者
    private String userId;

    //发送者
    private String personMail;

    private String content;

    private Date createTime;

    private Integer state = 0;
}
