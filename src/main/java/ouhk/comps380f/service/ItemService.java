package ouhk.comps380f.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import ouhk.comps380f.exception.AttachmentNotFound;
import ouhk.comps380f.exception.CommentNotFound;
import ouhk.comps380f.exception.ItemNotFound;
import ouhk.comps380f.model.Item;


public interface ItemService {

    public long createItem(String itemName, Double price,
           Boolean isabailability, List<MultipartFile> attachments) throws IOException;

    public List<Item> getItem();

    public Item getItem(long id);

    public void updateItem(long id, String itemName,
            Double price,Boolean isabailability, List<MultipartFile> attachments)
            throws IOException, ItemNotFound;

    public void delete(long id) throws ItemNotFound;

    public void deleteAttachment(long itemId, String name)
            throws AttachmentNotFound;
    
    public void deleteComment(long id, long comment_id) throws CommentNotFound;
    public void createComment(long item_id,String username,String message)throws ItemNotFound;
    public void updateComment(long id,String message)throws CommentNotFound;
}

