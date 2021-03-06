package ouhk.comps380f.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class OrderHistory implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long order_id; //ORDER_ID
    
    @Column(name = "username")
    private String username;
    
    @Column(name = "item_name")
    private String item_name;
    
    @Column(name = "quantity")
    private int quantity;
    
    @Column(name = "datetime")
    private String datetime;
    
    public OrderHistory() {
    }
    
    public OrderHistory(String username, String item_name, int quantity, String datetime){
        this.username = username;
        this.item_name = item_name;
        this.quantity = quantity;
        this.datetime = datetime;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
    
    
}
