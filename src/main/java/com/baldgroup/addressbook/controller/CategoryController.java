package com.baldgroup.addressbook.controller;

import com.baldgroup.addressbook.exception.UserException;
import com.baldgroup.addressbook.mapper.SearchInfo;
import com.baldgroup.addressbook.pojo.PersonCategory;
import com.baldgroup.addressbook.pojo.PersonInfo;
import com.baldgroup.addressbook.service.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Create By  @林俊杰
 * 2020/4/27 19:03
 *
 * @version 1.0
 */
@Controller
public class CategoryController {

    @Resource
    private SearchInfo searchInfo;

    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/user/toDeleteCategoryPerson")
    public String toDeletePerson(@RequestParam("personId") String id,
                                 Model model,
                                 HttpSession session){


        String userId = String.valueOf(session.getAttribute("loginUserId"));
        try{
            categoryService.deletePerson(id,userId);
        }catch (UserException e){
            //TODO
            System.out.println(e.getMessage());
        }

        return "redirect:/user/home";
    }

    @GetMapping("/user/movePerson")
    public String movePerson(@RequestParam("personId") String personId,
                             @RequestParam("categoryId") String categoryId,
                                 Model model,
                                 HttpSession session){


        String userId = String.valueOf(session.getAttribute("loginUserId"));
        try{
            categoryService.move(personId,categoryId,userId);
        }catch (UserException e){
            //TODO
            System.out.println(e.getMessage());
        }

        return "redirect:/user/home";
    }


    @PostMapping("/user/addCategory")
    public String addCategory(@RequestParam("categoryName") String categoryName,
                             HttpSession session){
        String userId = String.valueOf(session.getAttribute("loginUserId"));

        categoryService.addCategory(categoryName,userId);
        return "redirect:/user/home";
    }

    @PostMapping("/user/deleteCategory")
    public String deleteCategory(@RequestParam("categoryId") String categoryId,
                             HttpSession session){
        String userId = String.valueOf(session.getAttribute("loginUserId"));

        //categoryService.addCategory(categoryName,userId);
        try{
            categoryService.deleteCategoy(categoryId,userId);
        }catch (UserException e){
            //TODO
            System.out.println(e.getMessage());
        }
        return "redirect:/user/home";
    }

}
