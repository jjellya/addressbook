package com.baldgroup.addressbook.service;

import com.baldgroup.addressbook.pojo.PersonInfo;

import java.io.File;
import java.util.List;

/**
 * Create By  @林俊杰
 * 2020/4/26 23:50
 *
 * @version 1.0
 */
public interface FileService {
    List<PersonInfo> transformPersonInfo(File file);
}
