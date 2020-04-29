package com.baldgroup.addressbook.service.impl;

import com.baldgroup.addressbook.enums.ResultEnum;
import com.baldgroup.addressbook.exception.UserException;
import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.service.PersonService;
import com.baldgroup.addressbook.utils.KeyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Create By  @林俊杰
 * 2020/4/25 13:26
 * Modified By @江海彬
 * @version 1.0
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private ModifyInfo modifyInfo;
    @Resource
    private SearchInfo searchInfo;

    @Override
    public PersonInfo addPerson(PersonInfo person, String userId) {
        person.setPersonId(KeyUtil.genUniqueKey());
        if(person.getCategoryId()==null)
            person.setCategoryId(searchInfo.queryCategories(userId).get(0).getCategoryId());
        person.setUserId(userId);
        modifyInfo.insertPerson(person);
        return person;
    }

    @Override
    public void save(PersonInfo person, String userId) {

        if(person.getUserId().equals(userId)){
           modifyInfo.updatePersonInfo(person);
        }
        else{
            throw new UserException(ResultEnum.USER_PERMISSION_ERROR);
        }
    }

    @Override
    public void delete(String personId, String userId) {
        if(searchInfo.queryPersonInfoById(personId, userId)!=null){
            modifyInfo.deletePerson(personId);
        }
        else{
            throw new UserException(ResultEnum.USER_PERMISSION_ERROR);
        }
    }
}
