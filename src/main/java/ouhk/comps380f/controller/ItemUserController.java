/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.controller;

import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.dao.ItemUserRepository;
import ouhk.comps380f.model.ItemUser;


@Controller
@RequestMapping("/user")
public class ItemUserController {
     @Resource
    ItemUserRepository itemUserRepo;

    @GetMapping({"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("itemUsers", itemUserRepo.findAll());
        return "listUser";
    }

    public static class Form {
        private String username;
        private String password;
        private String[] roles;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String[] getRoles() {
            return roles;
        }

        public void setRoles(String[] roles) {
            this.roles = roles;
        } 
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "itemUser", new Form());
    }

    @PostMapping("/create")
    public View create(Form form) throws IOException {
        ItemUser user = new ItemUser(form.getUsername(),
                form.getPassword(), form.getRoles()
        );
        itemUserRepo.save(user);
        return new RedirectView("/user/list", true);
    }

    @GetMapping("/delete/{username}")
    public View deleteTicket(@PathVariable("username") String username) {
        itemUserRepo.delete(itemUserRepo.findById(username).orElse(null));
        return new RedirectView("/user/list", true);
    }
}
