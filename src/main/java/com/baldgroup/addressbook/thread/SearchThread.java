package com.baldgroup.addressbook.thread;

import com.baldgroup.addressbook.pojo.PersonInfo;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create By  @林俊杰
 * 2020/5/14 16:01
 *
 * @version 1.0
 */
@Data
public class SearchThread extends Thread {

    private List<PersonInfo> sourceList;

    private List<PersonInfo> pinyinSourceList;

    private List<PersonInfo> dstList;

    private List<PersonInfo> subList1=new ArrayList<>();

    private List<PersonInfo> subList2=new ArrayList<>();

    private Map<String,Map<String,String>> pinyinMap = new HashMap<>();

    private String targetStr;

    private Integer code;

    public SearchThread(List<PersonInfo> sourceList, List<PersonInfo> pinyinSourceList, Map<String,Map<String,String>> pinyinMap, String targetStr, Integer code) {
        this.sourceList = sourceList;
        this.pinyinSourceList = pinyinSourceList;
        this.pinyinMap = pinyinMap;
       /* this.dstList = dstList;*/
        this.targetStr = targetStr;
        this.code = code;
    }

    @Override
    public void run() {

        //TODO
        //if(subList1==null&&subList2==null) return;
        try {

            switch (code){
                case 1:
                    subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> p.getPersonName().contains(targetStr)).collect(Collectors.toList());
                        if(!CollectionUtils.isEmpty(subList1))
                            for (PersonInfo person:subList1
                            ) {
                                    person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonName()));
                                    person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonCompany()));
                                    person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonAddress()));
                                    person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonRemark()));

                            }
                    subList2 = sourceList.stream().filter((PersonInfo p) -> p.getPersonName().contains(targetStr)).collect(Collectors.toList());
                    break;
                case 2:
                    //subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> targetStr.contains(p.getPersonPhone1())).collect(Collectors.toList());
                    subList2 = sourceList.stream().filter((PersonInfo p) ->p.getPersonPhone1().contains(targetStr)).collect(Collectors.toList());
                    break;
                case 3:
                    //subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> targetStr.contains(p.getPersonPhone2())).collect(Collectors.toList());
                    subList2 = sourceList.stream().filter((PersonInfo p) -> p.getPersonPhone2().contains(targetStr)).collect(Collectors.toList());
                    break;
                case 4:
                    //subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> targetStr.contains(p.getPersonWechat())).collect(Collectors.toList());
                    subList2 = sourceList.stream().filter((PersonInfo p) -> p.getPersonWechat().contains(targetStr)).collect(Collectors.toList());
                    break;
                case 5:
                    //subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> targetStr.contains(p.getPersonQq())).collect(Collectors.toList());
                    subList2 = sourceList.stream().filter((PersonInfo p) -> p.getPersonQq().contains(targetStr)).collect(Collectors.toList());
                    break;
                case 6:
                    //subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> targetStr.contains(p.getPersonMail())).collect(Collectors.toList());
                    subList2 = sourceList.stream().filter((PersonInfo p) -> p.getPersonMail().contains(targetStr)).collect(Collectors.toList());
                    break;
                case 7:
                    subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> p.getPersonCompany().contains(targetStr)).collect(Collectors.toList());
                    if(!CollectionUtils.isEmpty(subList1))
                        for (PersonInfo person:subList1
                        ) {
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonName()));
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonCompany()));
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonAddress()));
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonRemark()));
                        }
                    subList2 = sourceList.stream().filter((PersonInfo p) -> p.getPersonCompany().contains(targetStr)).collect(Collectors.toList());
                    break;
                case 8:
                    subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> p.getPersonAddress().contains(targetStr)).collect(Collectors.toList());
                    if(!CollectionUtils.isEmpty(subList1))
                        for (PersonInfo person:subList1
                        ) {
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonName()));
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonCompany()));
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonAddress()));
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonRemark()));
                        }
                    subList2 = sourceList.stream().filter((PersonInfo p) -> p.getPersonAddress().contains(targetStr)).collect(Collectors.toList());
                    break;
                case 9:
                    //subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> targetStr.contains(p.getPersonPostcode())).collect(Collectors.toList());
                    subList2 = sourceList.stream().filter((PersonInfo p) -> p.getPersonPostcode().contains(targetStr)).collect(Collectors.toList());
                    break;
                case 10:
                    subList1 = pinyinSourceList.stream().filter((PersonInfo p) -> p.getPersonRemark().contains(targetStr)).collect(Collectors.toList());
                    if(!CollectionUtils.isEmpty(subList1))
                        for (PersonInfo person:subList1
                        ) {
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonName()));
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonCompany()));
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonAddress()));
                            person.setPersonName(pinyinMap.get(person.getPersonId()).get(person.getPersonRemark()));
                        }
                    subList2 = sourceList.stream().filter((PersonInfo p) -> p.getPersonRemark().contains(targetStr)).collect(Collectors.toList());
                    break;
                default:break;
            }
            if(subList1==null&&subList2!=null){
                dstList=subList2;
            }
            else if (subList1!=null&&subList2==null){
                dstList=subList1;
            }
            else if(subList1!=null&&subList2!=null){
                dstList = Stream.of(subList1,subList2).flatMap(Collection::stream).distinct().collect(Collectors.toList());
            }


        }catch (NullPointerException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
