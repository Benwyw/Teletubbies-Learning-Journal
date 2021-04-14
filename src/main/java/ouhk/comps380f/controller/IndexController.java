package ouhk.comps380f.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping
    public String index() {
        System.out.println("Here is a test message on console.");
        return "index";
        //return "redirect:/ticket/list";
    }
}