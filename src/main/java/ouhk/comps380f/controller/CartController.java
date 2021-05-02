/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Item;
import ouhk.comps380f.view.DownloadingView;

@Controller
@RequestMapping("/cart")
public class CartController {

    private Map<Long, Item> itemDatabase = new Hashtable<>();
    private final Map<Integer, String> products = new Hashtable<>();

    @GetMapping
    public String list(ModelMap model) {
        model.addAttribute("products", this.products);
        return "viewCart";
    }
    
    @GetMapping("/add/{itemId}")
    public String view(HttpSession session, @PathVariable("itemId") long itemId,
            ModelMap model) {
        int productId;
        try {
            productId = (int)itemId;
        } catch (Exception e) {
            return "redirect:/item/list";
        }
        
        if (session.getAttribute("cart") == null) {
            session.setAttribute("cart", new Hashtable<>());
        }
        
        @SuppressWarnings("unchecked")
        Map<Integer, Integer> cart
                = (Map<Integer, Integer>) session.getAttribute("cart");
        if (!cart.containsKey(productId)) {
            cart.put(productId, 0);
        }
        cart.put(productId, cart.get(productId) + 1);

        return "redirect:/cart";
    }

}