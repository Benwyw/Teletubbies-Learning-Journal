package ouhk.comps380f.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {

    //@GetMapping
    //public View index() {
    //return new RedirectView("/guestbook", true);
    //}
    @GetMapping
    public String index() {
        return "redirect:/item/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
