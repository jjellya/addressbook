package com.baldgroup.addressbook.service.impl;

import com.baldgroup.addressbook.enums.ResultEnum;
import com.baldgroup.addressbook.enums.UserPrivilegeEnums;
import com.baldgroup.addressbook.exception.UserException;
import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.pojo.UserInfo;
import com.baldgroup.addressbook.service.PersonService;
import com.baldgroup.addressbook.thread.SearchThread;
import com.baldgroup.addressbook.utils.KeyUtil;
import com.baldgroup.addressbook.utils.Pinyin4jUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        UserInfo user = searchInfo.queryUserInfo(userId);
        if(user.getBookAvailable()<=0) {
            throw new UserException(ResultEnum.USER_AVAILABLE_ERROR);
        }
        user.setBookAvailable(user.getBookAvailable()-1);
        modifyInfo.updateUserInfo(user);
        person.setUserId(userId);
        modifyInfo.insertPerson(person);

        return person;
    }

    @Override
    public void addPersonList(List<PersonInfo> personList, String userId) {
        modifyInfo.insertPersonList(personList);
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
            UserInfo user = searchInfo.queryUserInfo(userId);
            user.setBookAvailable(user.getBookAvailable()+1);
            modifyInfo.updateUserInfo(user);
        }
        else{
            throw new UserException(ResultEnum.USER_PERMISSION_ERROR);
        }
    }

    @Override
    public List<PersonInfo> searchPerson(String src, String userId) {
        List<PersonInfo> dstPersonList = new ArrayList<>();

        List<PersonInfo> personList = searchInfo.queryAllPersonInfos(userId);
        List<PersonInfo> personPinyinList = searchInfo.queryAllPersonInfos(userId);

        String stringResource = personList.toString();
        //TODO

        src = src.trim().toLowerCase();

        //System.out.println("stringResource: "+stringResource);

        String pinyinFirstResource = Pinyin4jUtil.getFirstPinYin(stringResource);

        //System.out.println("pinyinFirstResource:  "+pinyinFirstResource);

        Map<String,Map<String,String>> pinyinMap = new HashMap<>();

        Map<String, String> map = null;

        for (PersonInfo person:personPinyinList
             ) {
            map = new HashMap<>();

            map.put(Pinyin4jUtil.getFirstPinYin(person.getPersonName()),person.getPersonName());
            map.put(Pinyin4jUtil.getFirstPinYin(person.getPersonCompany()),person.getPersonCompany());
            map.put(Pinyin4jUtil.getFirstPinYin(person.getPersonAddress()),person.getPersonAddress());
            map.put(Pinyin4jUtil.getFirstPinYin(person.getPersonRemark()),person.getPersonRemark());


            pinyinMap.put(person.getPersonId(),map);

            //pinyinMapList.add(new HashMap<String, String>(Pinyin4jUtil.getFirstPinYin(person.getPersonName()),person.getPersonName()));
            person.setPersonName(Pinyin4jUtil.getFirstPinYin(person.getPersonName()));
            person.setPersonCompany(Pinyin4jUtil.getFirstPinYin(person.getPersonCompany()));
            person.setPersonAddress(Pinyin4jUtil.getFirstPinYin(person.getPersonAddress()));
            person.setPersonRemark(Pinyin4jUtil.getFirstPinYin(person.getPersonRemark()));

        }



    try {

        SearchThread searchThread1= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(1));
        SearchThread searchThread2= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(2));
        SearchThread searchThread3= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(3));
        SearchThread searchThread4= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(4));
        SearchThread searchThread5= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(5));
        SearchThread searchThread6= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(6));
        SearchThread searchThread7= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(7));
        SearchThread searchThread8= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(8));
        SearchThread searchThread9= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(9));
        SearchThread searchThreadA= new SearchThread(personList,personPinyinList,pinyinMap,src,Integer.valueOf(10));

        searchThread1.run();
        searchThread2.run();
        searchThread3.run();
        searchThread4.run();
        searchThread5.run();
        searchThread6.run();
        searchThread7.run();
        searchThread8.run();
        searchThread9.run();
        searchThreadA.run();

        if(searchThread1.getDstList()!=null)
            dstPersonList.addAll(searchThread1.getDstList());
        if(searchThread2.getDstList()!=null)
            //dstPersonList.addAll(searchThread2.getDstList());
            dstPersonList = Stream.of(dstPersonList,searchThread2.getDstList()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        if(searchThread3.getDstList()!=null)
            dstPersonList = Stream.of(dstPersonList,searchThread3.getDstList()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        if(searchThread4.getDstList()!=null)
            dstPersonList = Stream.of(dstPersonList,searchThread4.getDstList()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        if(searchThread5.getDstList()!=null)
            dstPersonList = Stream.of(dstPersonList,searchThread5.getDstList()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        if(searchThread6.getDstList()!=null)
            dstPersonList = Stream.of(dstPersonList,searchThread6.getDstList()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        if(searchThread7.getDstList()!=null)
            dstPersonList = Stream.of(dstPersonList,searchThread7.getDstList()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        if(searchThread8.getDstList()!=null)
            dstPersonList = Stream.of(dstPersonList,searchThread8.getDstList()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        if(searchThread9.getDstList()!=null)
            dstPersonList = Stream.of(dstPersonList,searchThread9.getDstList()).flatMap(Collection::stream).distinct().collect(Collectors.toList());
        if(searchThreadA.getDstList()!=null)
            dstPersonList = Stream.of(dstPersonList,searchThreadA.getDstList()).flatMap(Collection::stream).distinct().collect(Collectors.toList());

    }catch (NullPointerException e){
        System.out.println(e.getMessage());
    }

        List<String> idList = new ArrayList<>();
        for (PersonInfo person:dstPersonList
             ) {
            idList.add(person.getPersonId());
        }

        dstPersonList = personList.stream().filter((PersonInfo p)->idList.contains(p.getPersonId())).collect(Collectors.toList());

        return dstPersonList;
    }
}
