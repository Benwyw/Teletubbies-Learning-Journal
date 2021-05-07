/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.controller;

import java.security.Principal;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ouhk.comps380f.dao.OrderHistoryRepository;
import ouhk.comps380f.model.OrderHistory;
import ouhk.comps380f.service.ItemService;
import ouhk.comps380f.service.OrderHistoryService;

@Controller
@RequestMapping("/cart")
public class CartController {

    //private Map<Long, Item> itemDatabase = new Hashtable<>();
    //private final Map<Integer, String> products = new Hashtable<>();
    @Autowired
    private ItemService itemService;
    
    @Autowired
    private OrderHistoryService orderhistoryService;
    @Resource
    OrderHistoryRepository orderHistoryRepo;

    @GetMapping({"", "/viewCart"})
    public String list(ModelMap model) {
        model.addAttribute("itemDatabase", itemService.getItem());
        return "viewCart";
    }
    
    @GetMapping("/empty")
    private String emptyCart(HttpSession session, ModelMap model) {
        session.removeAttribute("cart");
        return "redirect:/cart";
    }
    
    @GetMapping("/orderHistory")
    private String orderhistory(HttpSession session, ModelMap model, Principal principal) {
        String name = principal.getName();
        
        List<OrderHistory> orderhistory = orderhistoryService.getOrderHistory(name);
        
        model.addAttribute("orderhistory", orderhistory);
        model.addAttribute("itemDatabase", itemService.getItem());
        
        return "orderhistory";
    }
    
    @GetMapping("/checkout")
    private String checkout(HttpSession session, ModelMap model, Principal principal) {
        String name = principal.getName();
        
        session.getAttribute("cart");
        Map<Integer, Integer> cart
                = (Map<Integer, Integer>) session.getAttribute("cart");
        System.out.println("cart: "+cart);
        
        if (cart != null){
            for (Map.Entry<Integer, Integer> entry : cart.entrySet()){
                System.out.println(entry.getKey() + "/" + entry.getValue());

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                LocalDateTime now = LocalDateTime.now();

                orderhistoryService.createOrderHistory(name, (int)entry.getKey(), (int)entry.getValue(), now.toString());
            }
        
            session.removeAttribute("cart");
            return "checkout";
        }
        else{
            return "redirect:/item/list";
        }
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
        System.out.println(cart);
        return "redirect:/cart";
    }

}