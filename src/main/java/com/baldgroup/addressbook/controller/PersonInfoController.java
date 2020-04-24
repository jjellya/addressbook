package com.baldgroup.addressbook.controller;

import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * Create By  @林俊杰
 * 2020/4/24 16:11
 *
 * @version 1.0
 */
@Controller
public class PersonInfoController {

    @Resource
    private ModifyInfo modifyInfo;
    @Resource
    private SearchInfo searchInfo;

    @RequestMapping("/user/list")
    public String list(Model model,
                       HttpSession session){
        String id =String.valueOf(session.getAttribute("loginUserId"));
        List<PersonInfo> personInfoList = searchInfo.queryAllPersonInfos(id);
        List<PersonCategory> categoryList = searchInfo.queryCategories(id);
        model.addAttribute("personInfoList",personInfoList);
        model.addAttribute("categoryList",categoryList);
        return "person/list";
    }

    @RequestMapping("/user/toAdd")
    public String toAddPage(){
        return "person/add";
    }
}
