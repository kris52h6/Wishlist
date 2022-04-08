package com.projectwishlist.controllers;

import com.projectwishlist.repositories.UserRep;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

@Controller
public class UserController
{

    //public String checkUser(@RequestParam ( value = "username", required = false), @RequestParam (value = "password", ){
    @PostMapping("/checkUser")
    public String checkUser(WebRequest data, HttpSession session){
        UserRep userRep = new UserRep();
        String username = data.getParameter("username");
        String password = data.getParameter("password");
        try{
            int userId = userRep.authenticateUser(username, password);
            if(userId > 0){
                session.setAttribute("userId",userId);
                session.setAttribute("loggedInUsername",username);
                return "redirect:/getWishlist";
            }else {
                return "index";
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/loginerror";
    }

    @PostMapping("/creatNewUser")
    public String creatNewUser(WebRequest data){
        UserRep userRep = new UserRep();

        String username = data.getParameter("username");
        String password = data.getParameter("password");
        String firstname = data.getParameter("firstname");

        userRep.addNewUser(username, password, firstname);
        return "index";
    }

    @GetMapping("/loginerror")
    public String erorpage(){
        return "login-error";
    }
}
