package com.example.employee.controller;

import com.example.employee.dto.EmployeeDto;
import com.example.employee.entity.Employees;
import com.example.employee.entity.Users;
import com.example.employee.service.EmployeeServiceImpl;
import com.example.employee.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MyController {
    @Autowired
    UsersServiceImpl usersServiceImpl;
    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/")
    public ModelAndView homemethod() {
        ModelAndView mv = new ModelAndView("loginpage.jsp");
        return mv;
    }

    @GetMapping("/loginpage")
    public ModelAndView loginmethod() {
        ModelAndView mv = new ModelAndView("loginpage.jsp");
        return mv;
    }

    @GetMapping("/logout-success")
    public ModelAndView logoutmethod() {
        ModelAndView mv = new ModelAndView("loginpage.jsp");
        return mv;
    }

    @PostMapping("/newuser")
    public String createuser(Users users) {
        if (users.getUsername() != null && users.getPassword() != null) {
            usersServiceImpl.saveUser(users);
        }
        return "loginpage.jsp";
    }

    @GetMapping("/profile")
    public String getprofile(Model model, Authentication authentication) {
        List<Employees> list = employeeServiceImpl.getEmployee(authentication.getName());
        model.addAttribute("list", list);
        return "profilepage.jsp";
    }

    @GetMapping("/newemployeeform")
    public String showemployeeform() {
        return "newemployee.jsp";
    }

    private String myfunc(String username, Model model) {
        List<Employees> list = employeeServiceImpl.getEmployee(username);
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
        employeeServiceImpl.saveEmployee(employee);
        return myfunc(employee.getManager(), model);
    }

    @GetMapping("/updateemployeeform")
    public String updateemployeeform(@RequestParam Integer empid, Model model) {
        Employees employee = employeeServiceImpl.getEmployeeById(empid);
        model.addAttribute("employee", employee);
        return "updateemployee.jsp";
    }

    @GetMapping("/updateemployee")
    public String updateemployee(@RequestParam Integer empid,
                                 @ModelAttribute("employee") EmployeeDto empdto,
                                 Model model) {
        Employees updateemp = employeeServiceImpl.getEmployeeById(empid);
        updateemp.setEmpname(empdto.getEmpname());
        updateemp.setEmpmail(empdto.getEmpmail());
        updateemp.setDepartment(empdto.getDepartment());
        updateemp.setManager(empdto.getManager());
        employeeServiceImpl.saveEmployee(updateemp);
        return myfunc(updateemp.getManager(), model);
    }

    @GetMapping("/deleteemployee")
    public String deleteemployee(@RequestParam Integer empid, Model model) {
        String username = employeeServiceImpl.getEmployeeById(empid).getManager();
        employeeServiceImpl.deleteEmployeeById(empid);
        return myfunc(username, model);
    }

}
