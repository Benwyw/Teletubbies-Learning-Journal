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
@RequestMapping("/item")
public class ItemController {

    private volatile long TICKET_ID_SEQUENCE = 1;
    private Map<Long, Item> itemDatabase = new Hashtable<>();

    @GetMapping(value = {"", "/list"})
    public String list(ModelMap model) {
        model.addAttribute("itemDatabase", itemDatabase);
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
    public View create(Form form) throws IOException {
        Item item = new Item();
        item.setId(this.getNextItemId());
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setIsabailability(form.getIsabailability());
        for (MultipartFile filePart : form.getAttachments()) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null && attachment.getContents().length > 0) {
                item.addAttachment(attachment);
            }
        }
        this.itemDatabase.put(item.getId(), item);
        return new RedirectView("/item/view/" + item.getId(), true);
    }

    private synchronized long getNextItemId() {
        return this.TICKET_ID_SEQUENCE++;
    }

    @GetMapping("/view/{itemId}")
    public String view(@PathVariable("itemId") long itemId,
            ModelMap model) {
        Item item = this.itemDatabase.get(itemId);
        if (item == null) {
            return "redirect:/item/list";
        }
        model.addAttribute("itemId", itemId);
        model.addAttribute("item", item);
        return "view";
    }

    @GetMapping("/{itemId}/attachment/{attachment:.+}")
    public View download(@PathVariable("itemId") long itemId,
            @PathVariable("attachment") String name) {
        Item item = this.itemDatabase.get(itemId);
        if (item != null) {
            Attachment attachment = item.getAttachment(name);
            if (attachment != null) {
                return new DownloadingView(attachment.getName(),
                        attachment.getMimeContentType(), attachment.getContents());
            }
        }
        return new RedirectView("/item/list", true);
    }
    
    @GetMapping("/{itemId}/delete/{attachment:.+}")
    public String deleteAttachment(@PathVariable("itemId") long itemId,
            @PathVariable("attachment") String name) {
        Item item = this.itemDatabase.get(itemId);
        if (item != null) {
            if (item.hasAttachment(name)) {
                item.deleteAttachment(name);
            }
        }
        return "redirect:/item/edit/" + itemId;
    }

    @GetMapping("/edit/{itemId}")
    public ModelAndView showEdit(@PathVariable("itemId") long itemId, Principal principal, HttpServletRequest request) {
        Item item = this.itemDatabase.get(itemId);
        if (item == null
                || (!request.isUserInRole("ROLE_ADMIN"))) {
            return new ModelAndView(new RedirectView("/item/list", true));
        }
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("itemId", Long.toString(itemId));
        modelAndView.addObject("item", item);

        Form itemForm = new Form();
        itemForm.setPrice(item.getPrice());
        itemForm.setIsabailability(item.getIsabailability());
        modelAndView.addObject("itemForm", itemForm);

        return modelAndView;
    }

    @PostMapping("/edit/{itemId}")
    public String edit(@PathVariable("itemId") long itemId, Form form, Principal principal, HttpServletRequest request)
            throws IOException {
        Item item = this.itemDatabase.get(itemId);
        if (item == null || (!request.isUserInRole("ROLE_ADMIN"))) {
            return "redirect:/ticket/list";
        }

        item.setPrice(item.getPrice());
        item.setIsabailability(item.getIsabailability());

        for (MultipartFile filePart : form.getAttachments()) {
            Attachment attachment = new Attachment();
            attachment.setName(filePart.getOriginalFilename());
            attachment.setMimeContentType(filePart.getContentType());
            attachment.setContents(filePart.getBytes());
            if (attachment.getName() != null && attachment.getName().length() > 0
                    && attachment.getContents() != null && attachment.getContents().length > 0) {
                item.addAttachment(attachment);
            }
        }
        this.itemDatabase.put(item.getId(), item);
        return "redirect:/item/view/" + item.getId();
    }

    @GetMapping("/delete/{itemId}")
    public String deleteItem(@PathVariable("itemId") long itemId) {
        if (this.itemDatabase.containsKey(itemId)) {
            this.itemDatabase.remove(itemId);
        }
        return "redirect:/item/list";
    }

   
}
