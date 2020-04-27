package com.baldgroup.addressbook.controller;

import com.baldgroup.addressbook.pojo.UserInfo;
import com.baldgroup.addressbook.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Create By  @林俊杰
 * 2020/4/23 17:59
 *
 * @version 1.0
 */
@Controller
public class LoginController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/login")
    public String login(@RequestParam("id") String id,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){
        UserInfo user = userService.fineOne(id);
        if(user!=null && user.getUserPassword().equals(password)){
            session.setAttribute("loginUserId",id);
            session.setAttribute("loginUser",user);
            return "redirect:/user/home";
        }else {
            //TODO
            model.addAttribute("msg","用户名或者密码错误!");
            return   "index";//"redirect:/id_or_Password_is_error";
        }

    }

    @RequestMapping("user/signOut")
    public String signOut(Model model,
                          HttpSession session){
        session.removeAttribute("loginUserId");
        session.removeAttribute("loginUser");
        model.addAttribute("msg","已注销,请重新登录！");
        return "redirect:/login";
    }


}
