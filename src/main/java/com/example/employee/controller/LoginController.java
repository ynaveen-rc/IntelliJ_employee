package com.example.employee.controller;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.entity.Employees;
import com.example.employee.entity.Users;
import com.example.employee.service.EmployeeService;
import com.example.employee.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UsersService usersservice;
    @Autowired
    EmployeeService empservice;

    @RequestMapping("/")
    public ModelAndView loginmethod() {
        ModelAndView mv = new ModelAndView("loginpage.jsp");
        return mv;
    }

    @PostMapping("/home")
    public String logoutmethod() {
        return "redirect:/";
    }

    @PostMapping("/newuser")
    public String createuser(Users users) {
        if (users.getUsername() != null && users.getPassword() != null) {
            usersservice.saveUser(users);
        }
        return "redirect:/";
    }

    @PostMapping("/profile")
    public String getprofile(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             Model model) {
        Users users = usersservice.getUserByUsername(username);
        if (users == null) {
            return "redirect:/";
        }
        if (!users.getPassword().equals(password)) {
            return "redirect:/";
        }
        List<Employees> list = empservice.getEmployee(username);
        model.addAttribute("list", list);
        return "profilepage.jsp";
    }

    @GetMapping("/newemployeeform")
    public String showemployeeform() {
        return "newemployee.jsp";
    }

    private String myfunc(String username, Model model) {
        List<Employees> list = empservice.getEmployee(username);
        model.addAttribute("list", list);
        return "profilepage.jsp";
    }

    @PostMapping("/addemployee")
    public String addemployee(@ModelAttribute EmployeeDto empdto, Model model) {
        Employees employee = new Employees();
        employee.setEmpname(empdto.getEmpname());
        employee.setEmpmail(empdto.getEmpmail());
        employee.setDepartment(empdto.getDepartment());
        employee.setManager(empdto.getManager());
        empservice.saveEmployee(employee);
        return myfunc(employee.getManager(), model);
    }

    @GetMapping("/updateemployeeform")
    public String updateemployeeform(@RequestParam Integer empid, Model model) {
        Employees employee = empservice.getEmployeeById(empid);
        model.addAttribute("employee", employee);
        return "updateemployee.jsp";
    }

    @GetMapping("/updateemployee")
    public String updateemployee(@RequestParam Integer empid,
                                 @ModelAttribute("employee") EmployeeDto empdto,
                                 Model model) {
        Employees updateemp = empservice.getEmployeeById(empid);
        updateemp.setEmpname(empdto.getEmpname());
        updateemp.setEmpmail(empdto.getEmpmail());
        updateemp.setDepartment(empdto.getDepartment());
        updateemp.setManager(empdto.getManager());
        empservice.saveEmployee(updateemp);
        return myfunc(updateemp.getManager(), model);
    }

    @GetMapping("/deleteemployee")
    public String deleteemployee(@RequestParam Integer empid, Model model) {
        String username = empservice.getEmployeeById(empid).getManager();
        empservice.deleteEmployeeById(empid);
        return myfunc(username, model);
    }

}
