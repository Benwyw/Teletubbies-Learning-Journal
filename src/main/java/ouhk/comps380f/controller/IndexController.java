package ouhk.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
        System.out.println("Here is a test message on console.");
        return "index";
        //return "redirect:/ticket/list";
    }
<<<<<<< HEAD
    
    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
    @GetMapping(value = "/logout")
    public String logout() {
        return "logout";
    }
    
=======
>>>>>>> parent of 393b34c (login卡關 (有bug))
}