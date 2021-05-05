/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ouhk.comps380f.model;

/**
 *
 * @author Ronaldtsai
 */
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Ronaldtsai
 */
@Entity
@Table(name="comment")
public class Comment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    
    @Column(name="username")
    private String username;
    
    @Column(name="comment")
    private String comment;
    
    @Column(insertable=false,updatable=false)
    private long item_id;
    
    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item_comment;
    
    public Comment(){
    }
    
    public Comment(Item item_comment, String comment, String username){
        this.item_comment=item_comment;
        this.comment=comment;
        this.username=username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Item getItem_comment() {
        return item_comment;
    }

    public void setItem_comment(Item item_comment) {
        this.item_comment = item_comment;
    }

    
    
}
