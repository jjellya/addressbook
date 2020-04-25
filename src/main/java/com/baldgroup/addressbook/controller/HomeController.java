package com.baldgroup.addressbook.controller;

import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Create By  @林俊杰
 * 2020/4/25 22:19
 *
 * @version 1.0
 */
@Controller
public class HomeController {

    @Resource
    private ModifyInfo modifyInfo;
    @Resource
    private SearchInfo searchInfo;

    @GetMapping("/user/home")
    public String homePage(Model model,
                           HttpSession session){

        String id =String.valueOf(session.getAttribute("loginUserId"));

        List<PersonCategory> categoryList = searchInfo.queryCategories(id);
        List<PersonInfo> personInfoList = searchInfo.queryAllPersonInfos(id);

        model.addAttribute("personInfoList",personInfoList);
        model.addAttribute("categoryList",categoryList);
        return "home";
    }
}
