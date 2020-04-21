package com.baldgroup.addressbook.pojo;

import lombok.Data;
import java.util.Date;

/**
 * Create By  @林俊杰
 * 2020/4/5 17:23
 *
 * @version 1.0
 */

@Data
public class PersonInfo {
    private String personId;

    // 姓名
    private String personName;

    // 电话号码1
    private String personPhone1;

    // 电话号码2
    private String personPhone2;

    // 微信号码
    private String personWechat;

    // QQ号码
    private String personQq;

    // e-mail
    private String personMail;

    // 图像
    private String personIcon;

    // 工作单位
    private String personCompany;

    // 家庭住址
    private String personAddress;

    // 生日
    private Date personBirthday;

    // 邮编
    private String personPostcode;

    // 备注
    private String personRemark;

    // category表-personInfo外键
    private String categoryId;

    //所属用户
    private String userId;
}
