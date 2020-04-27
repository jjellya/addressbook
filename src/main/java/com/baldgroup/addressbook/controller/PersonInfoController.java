package com.baldgroup.addressbook.controller;

import com.baldgroup.addressbook.exception.UserException;
import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.service.impl.PersonServiceImpl;
import com.baldgroup.addressbook.service.impl.UserServiceImpl;
import com.baldgroup.addressbook.utils.KeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baldgroup.addressbook.mapper.ModifyInfo;
import com.baldgroup.addressbook.mapper.SearchInfo;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
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
    private SearchInfo searchInfo;

    @Autowired
    private PersonServiceImpl personService;

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

    @GetMapping("/user/addPerson")
    public String toAddPage(Model model,
                            HttpSession session){

        String userId = String.valueOf(session.getAttribute("loginUserId"));
        List<PersonCategory> categoryList = searchInfo.queryCategories(userId);
        model.addAttribute("categoryList",categoryList);

        return "person/add";
    }

    @PostMapping("/user/addPerson")
    public String addPearson(PersonInfo person,
                             BindingResult bindingResult,
                             HttpSession session){
        String userId = String.valueOf(session.getAttribute("loginUserId"));

        personService.addPerson(person,userId);
        return "redirect:/user/list";
    }

    @GetMapping("/user/toChangePerson")
    public String toChangePerson(@RequestParam("personId") String id,
                               Model model,
                               HttpSession session){
        String userId = String.valueOf(session.getAttribute("loginUserId"));
        PersonInfo personInfo = searchInfo.queryPersonInfoById(id,userId);

        List<PersonCategory> categoryList = searchInfo.queryCategories(userId);

        model.addAttribute("categoryList",categoryList);
        model.addAttribute("personInfo",personInfo);
        return "person/update";
    }

    @PostMapping("/user/changePerson")
    public String updatePerson(PersonInfo person,
                               BindingResult bindingResult,
                               HttpSession session){
        String userId = String.valueOf(session.getAttribute("loginUserId"));
        try{
            personService.save(person,userId);
        }
        catch (UserException e){
            //TODO
            System.out.println(e.getMessage());
        }
        return "redirect:/user/list";

    }


    @GetMapping("/user/toDeletePerson")
    public String toDeletePerson(@RequestParam("personId") String id,
                                 Model model,
                                 HttpSession session){


        String userId = String.valueOf(session.getAttribute("loginUserId"));
        try{
            personService.delete(id,userId);
        }catch (UserException e){
            //TODO
            System.out.println(e.getMessage());
        }

        return "redirect:/user/list";
    }


}
