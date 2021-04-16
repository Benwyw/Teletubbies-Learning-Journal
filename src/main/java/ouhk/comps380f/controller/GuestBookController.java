package ouhk.comps380f.controller;

import ouhk.comps380f.model.GuestBookEntry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/guestbook")
public class GuestBookController  {
    
    private volatile int commentIdSequence = 1;
    private Map<Integer, GuestBookEntry> entries = new Hashtable<>();
        
    private synchronized int getNextCommentId() {
        return this.commentIdSequence++;
    }
     
    @GetMapping({"", "view"})
    public String index(ModelMap model) {
        model.addAttribute("entries", entries);
        return "GuestBook";
    }
    
    @GetMapping("/add")
    public ModelAndView addCommentForm() {
        return new ModelAndView("AddComment", "command", new GuestBookEntry());
    }
    
    @PostMapping("/add")
    public View addCommentHandle(GuestBookEntry entry) {
        entry.setId(getNextCommentId());
        entry.setDate(new Date());
        entries.put(entry.getId(), entry);
        return new RedirectView("/guestbook/view", true);
    }
    
    @GetMapping("/edit")
    public String editCommentForm(@RequestParam("id") Integer entryId, ModelMap model) {
        GuestBookEntry entry = entries.get(entryId);
        model.addAttribute("entry", entry);
        return "EditComment";
    }
    
    @PostMapping("/edit")
    public View editCommentHandle(GuestBookEntry entry) {
        entry.setDate(new Date());
        entries.put(entry.getId(), entry);
        return new RedirectView("/guestbook/view", true);
    }
    
}
