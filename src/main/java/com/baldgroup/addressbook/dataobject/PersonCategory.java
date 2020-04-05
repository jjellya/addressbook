package com.baldgroup.addressbook.dataobject;

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
@Entity
public class PersonCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String categoryId;

    /**组类名字**/
    private String categoryName;

    /**组类编号**/
    private Integer categoryType;

    public PersonCategory() {
    }

    public PersonCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }


}
