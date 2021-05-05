/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
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
import ouhk.comps380f.exception.AttachmentNotFound;
import ouhk.comps380f.exception.CommentNotFound;
import ouhk.comps380f.exception.ItemNotFound;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Item;
import ouhk.comps380f.service.AttachmentService;
import ouhk.comps380f.service.CommentService;
import ouhk.comps380f.service.ItemService;
import ouhk.comps380f.view.DownloadingView;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AttachmentService attachmentService;
    
    @Autowired
    private CommentService commentService;

    @GetMapping({"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("itemDatabase", itemService.getItem());
        return "list";
    }
    
    @GetMapping("/create")
    public ModelAndView create() {
        return new ModelAndView("add", "itemForm", new Form());
    }

    public static class Form {

        private String itemName;
        private Double price;
        private Boolean isabailability;
        private List<MultipartFile> attachments;

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Boolean getIsabailability() {
            return isabailability;
        }

        public void setIsabailability(Boolean isabailability) {
            this.isabailability = isabailability;
        }

        public List<MultipartFile> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<MultipartFile> attachments) {
            this.attachments = attachments;
        }

        
    }

    @PostMapping("/create")
    public String create(Form form, Principal principal) throws IOException {
        long itemId = itemService.createItem( form.getItemName(), form.getPrice(),form.getIsabailability(), form.getAttachments());
        return "redirect:/item/view/" + itemId;
    }

    @GetMapping("/view/{itemId}")
    public String view(@PathVariable("itemId") long itemId,
            ModelMap model,Principal principal) {
        Item item = itemService.getItem(itemId);
        if (item == null) {
            return "redirect:/item/list";
        }
        model.addAttribute("item", item);
        /*String username = "";
        if(!principal.getName().equals(null)){
            username = principal.getName();
        }
        
        model.addAttribute("username", username);*/
        
        return "view";
    }

    @GetMapping("/{itemId}/attachment/{attachment:.+}")
    public View download(@PathVariable("itemId") long itemId,
            @PathVariable("attachment") String name) {

        Attachment attachment = attachmentService.getAttachment(itemId, name);
        if (attachment != null) {
            return new DownloadingView(attachment.getName(),
                    attachment.getMimeContentType(), attachment.getContents());
        }
        return new RedirectView("/item/list", true);
    }

    @GetMapping("/{itemId}/delete/{attachment:.+}")
    public String deleteAttachment(@PathVariable("itemId") long itemId,
            @PathVariable("attachment") String name) throws AttachmentNotFound {
        itemService.deleteAttachment(itemId, name);
        return "redirect:/item/edit/" + itemId;
    }

    @GetMapping("/edit/{itemId}")
    public ModelAndView showEdit(@PathVariable("itemId") long itemId,
            Principal principal, HttpServletRequest request) {
        Item item = itemService.getItem(itemId);
        if (item == null
                || (!request.isUserInRole("ROLE_ADMIN"))) {
            return new ModelAndView(new RedirectView("/item/list", true));
        }

        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("item", item);

        Form itemForm = new Form();
        itemForm.setItemName(item.getItemName());
        itemForm.setPrice(item.getPrice());
        itemForm.setIsabailability(item.getIsabailability());
        modelAndView.addObject("itemForm", itemForm);

        return modelAndView;
    }

    @PostMapping("/edit/{itemId}")
    public String edit(@PathVariable("itemId") long itemId, Form form,
            Principal principal, HttpServletRequest request)
            throws IOException, ItemNotFound {
        Item item = itemService.getItem(itemId);
        if (item == null
                || (!request.isUserInRole("ROLE_ADMIN"))) {
            return "redirect:/item/list";
        }

        itemService.updateItem(itemId, form.getItemName(),
                form.getPrice(),form.getIsabailability(), form.getAttachments());
        return "redirect:/item/view/" + itemId;
    }

    @GetMapping("/delete/{itemId}")
    public String deleteTicket(@PathVariable("itemId") long itemId)
            throws ItemNotFound {
        itemService.delete(itemId);
        return "redirect:/item/list";
    }
    
    public static class CM{
        String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        
        
    }
    
    @GetMapping("/addcomment/{itemId}")
    public ModelAndView addComment() {
        return new ModelAndView ("AddComment","cm",new CM());
    }
    
    @PostMapping("/addcomment/{itemId}")
    public String addingComment(@PathVariable("itemId") long itemId,Principal principal, CM cm) throws ItemNotFound{
        
        itemService.createComment(itemId, principal.getName(), cm.getMessage());
        return "redirect:/item/view/" + itemId;
    }
    
    @GetMapping("/deletecomment/{itemId}/{commentid}")
    public String deleteComment(@PathVariable("itemId") long itemId,@PathVariable("commentid") long commentid)
            throws CommentNotFound {
        itemService.deleteComment(itemId,commentid);
        return "redirect:/item/view/" + itemId;
    }
    
    @GetMapping("/editcomment/{itemId}/{commentid}")
    public ModelAndView editComment(@PathVariable("commentid") long commentid){
        CM cm=new CM();
        cm.setMessage(commentService.getComments(commentid).getComment());
        return new ModelAndView ("EditComment","cm",cm);
    }
    
    @PostMapping("/editcomment/{itemId}/{commentid}")
    public String editingComment(@PathVariable("itemId") long itemId,@PathVariable("commentid") long commentid, CM cm)
            throws CommentNotFound {
        itemService.updateComment(commentid,cm.getMessage());
        return "redirect:/item/view/" + itemId;
    }
}
