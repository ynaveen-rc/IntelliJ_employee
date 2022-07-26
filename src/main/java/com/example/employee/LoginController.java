package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @Autowired
    UsersRepo repo;

    @RequestMapping("/")
    public ModelAndView loginmethod() {
        ModelAndView mv = new ModelAndView("loginpage.jsp");
        return mv;
    }

    @PostMapping("/home")
    public String logoutmethod() {
        return "loginpage.jsp";
    }

    @PostMapping("/newuser")
    public String createuser(Users users) {
        if (users.getUsername() != null && users.getPassword() != null) {
            repo.save(users);
        }
        return "redirect:/";
    }

    @PostMapping("/profile")
    public String getprofile(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model) {
        //ModelAndView mv = new ModelAndView("profilepage.jsp");
        Users users = repo.findById(username).orElse(null);
        if (users == null) {
            return "redirect:/";
        }
        model.addAttribute("users", users);
        if (users.getPassword().equals(password)) {
            return "profilepage.jsp";
        }
        return "redirect:/";
    }

}
