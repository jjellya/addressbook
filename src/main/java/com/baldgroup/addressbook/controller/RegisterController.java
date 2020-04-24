package com.baldgroup.addressbook.controller;

import com.baldgroup.addressbook.async.MailTask;
import com.baldgroup.addressbook.config.EmailConfig;
import com.baldgroup.addressbook.enums.UserPrivilegeEnums;
import com.baldgroup.addressbook.pojo.UserInfo;
import com.baldgroup.addressbook.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Random;

/**
 * Create By  @林俊杰
 * 2020/4/21 21:02
 *
 * @version 1.0
 */
@Controller
public class RegisterController {
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EmailConfig emailConfig;

    @GetMapping("/register")
    public String goRegisterPage(){
        return "register";
    }


    @PostMapping("/register")
    public String index(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        @RequestParam("passwordAgain") String passwordAgain,
                        @RequestParam("email") String email,
                        Model model){
        if(userService.fineOne(email)==null) {
            if(password.equals(passwordAgain)) {
                //code = 当前时间 + 位随机数
                String activeCode = String.valueOf(new Date().getTime()) + String.valueOf(new Random().nextInt(900) + 100);

                taskExecutor.execute(new MailTask(activeCode + password, email, emailConfig.getMailMailFrom(), emailConfig.getMailDomainName(), javaMailSender));

                userService.addUser(userName, password, email, activeCode);

                return "success";
            }else {
                model.addAttribute("msg_passwordError","密码不一致!");
                return   "register";
            }

        }else {
            model.addAttribute("msg_registered","该邮箱已被注册!");
            return   "register";
        }


    }

    @GetMapping("/activate")
    public String activate(@RequestParam(value = "code") String code){

      //  if(System.currentTimeMillis()-Long.valueOf(code.substring(0,code.length()-3))/1000<3){

            UserInfo user = userService.activate(code);
            System.out.println(user);
            //如果用户不等于null，把用户状态修改status=1
            if (user !=null){
                user.setUserLevel(1);
                user.setBookAvailable(UserPrivilegeEnums.ORDINARY_AVAILABLE.getCode());
                //把code验证码清空，已经不需要了
                user.setUserPassword(code.substring(16));
                System.out.println(user);
                userService.save(user);
            }
       // }
        return "index";
    }
}
