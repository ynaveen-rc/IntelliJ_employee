package com.example.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.ws.rs.PathParam;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UsersRepo repo;
    @Autowired
    EmployeesRepo emprepo;

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
        if (!users.getPassword().equals(password)) {
            return "redirect:/";
        }
        //model.addAttribute("users", users);
        List<Employees> list = emprepo.findByManager(username);
        model.addAttribute("list", list);
        //System.out.println(list);
        return "profilepage.jsp";
    }

    @GetMapping("/newemployeeform")
    public String showemployeeform() {
        return "newemployee.jsp";
    }

    private String myfunc(String manager, Model model) {
        String username = manager;
        Users user = repo.findById(username).orElse(null);
        List<Employees> list = emprepo.findByManager(username);
        model.addAttribute("list", list);
        return "profilepage.jsp";
    }

    @PostMapping("/addemployee")
    public String addemployee(@ModelAttribute("employee") EmployeeDto empdto, Model model) {
        Employees employee = new Employees();
        employee.setEmpname(empdto.getEmpname());
        employee.setEmpmail(empdto.getEmpmail());
        employee.setDepartment(empdto.getDepartment());
        employee.setManager(empdto.getManager());
        emprepo.save(employee);
        return myfunc(employee.getManager(), model);
    }

    @GetMapping("/updateemployeeform")
    public String updateemployeeform(@RequestParam Integer empid, Model model) {
        Employees employee = emprepo.findById(empid).get();
        model.addAttribute("employee", employee);
        return "updateemployee.jsp";
    }

    @GetMapping("/updateemployee")
    public String updateemployee(@RequestParam Integer empid,
                                 @ModelAttribute("employee") EmployeeDto empdto,
                                 Model model) {
        Employees updateemp = emprepo.findById(empid).orElse(null);
        updateemp.setEmpname(empdto.getEmpname());
        updateemp.setEmpmail(empdto.getEmpmail());
        updateemp.setDepartment(empdto.getDepartment());
        updateemp.setManager(empdto.getManager());
        emprepo.save(updateemp);
        return myfunc(updateemp.getManager(), model);
    }

    @GetMapping("/deleteemployee")
    public String deleteemployee(@RequestParam Integer empid, Model model) {
//        Employees employee = emprepo.findById(empid).orElse(null);
//        String username = employee.getManager();
//        emprepo.delete(employee);
        emprepo.deleteById(empid);
//        return myfunc(username, model);
        return "profilepage.jsp";
    }

}
