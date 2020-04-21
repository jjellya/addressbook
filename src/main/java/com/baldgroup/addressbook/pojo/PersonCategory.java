package com.baldgroup.addressbook.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Create By  @入门小学徒_J
 * 2020/4/5 17:47
 *
 * @version 1.0
 */

@Data
public class PersonCategory {
    //组类标识
    private String categoryId;

    // 组类名字
    private String categoryName;

    // 通讯录book_info与组类person_category的外键
    private String userId;
}
