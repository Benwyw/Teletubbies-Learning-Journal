/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import ouhk.comps380f.model.UserRole;
import ouhk.comps380f.service.ItemUserService;


@Controller
@RequestMapping("/user")
public class ItemUserController {
     
    @Autowired
    private ItemUserService itemUserService;
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
        private String fullname;
        private String phone;
        private String address;
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

        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("addUser", "itemUser", new Form());
    }

    @PostMapping("/create")
    public View create(Form form) throws IOException {
        ItemUser user = new ItemUser(form.getUsername(),
               form.getPassword(), form.getRoles(),form.getFullname(),form.getPhone(),form.getAddress()
        );
        itemUserRepo.save(user);
        return new RedirectView("/user/list", true);
    }
    @GetMapping("/register")
    public ModelAndView register() {
        return new ModelAndView("registerUser", "reitemUser", new Form());
    }

    @PostMapping("/register")
    public View register(Form form) throws IOException {
        String[] role={"ROLE_USER"};
        ItemUser user = new ItemUser(form.getUsername(),
                form.getPassword(),role,form.getFullname(),form.getPhone(),form.getAddress()
        );
        itemUserRepo.save(user);
        return new RedirectView("/login", true);
    }

    @GetMapping("/delete/{username}")
    public View deleteTicket(@PathVariable("username") String username) {
        itemUserRepo.delete(itemUserRepo.findById(username).orElse(null));
        return new RedirectView("/user/list", true);
    }
    
    @GetMapping({"/editUser"})
    public View editUser(Principal principal) {
        String path = "/user/editUser/"+principal.getName();
        return new RedirectView(path, true);
    }
    
     @GetMapping("/editUser/{username}")
    public ModelAndView showEdit(@PathVariable("username")  String username,
            Principal principal, HttpServletRequest request) {
        //Security: If role is not admin, then check if the user to be edited equals current user
        if (!principal.toString().split("Granted Authorities")[1].contains("ROLE_ADMIN")){
            if (!principal.getName().equals(username)){
                return new ModelAndView("list"); //No permission to access
            }
        }
        ItemUser user = itemUserService.getItemUser(username);
        if (user == null ) {
            return new ModelAndView(new RedirectView("/item/list", true));
        }
        
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        //String[] rolelist = authorities.toArray(new String[0]);
        ModelAndView modelAndView;
        if (!principal.toString().split("Granted Authorities")[1].contains("ROLE_ADMIN")){
            modelAndView = new ModelAndView("editUser2"); //user view
        }
        else{
            modelAndView = new ModelAndView("editUser"); //admin view
        }
        modelAndView.addObject("user", user);

        Form edituserForm = new Form();
        edituserForm.setUsername(user.getUsername());
        edituserForm.setPassword(user.getPassword());
        edituserForm.setFullname(user.getFullname());
        edituserForm.setPhone(user.getPhone());
        edituserForm.setAddress(user.getAddress());
       // edituserForm.setRoles(rolelist);
        modelAndView.addObject("edituserForm", edituserForm);

        return modelAndView;
    }
}
