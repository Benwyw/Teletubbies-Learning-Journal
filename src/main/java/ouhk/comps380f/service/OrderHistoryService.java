package ouhk.comps380f.service;

import java.util.List;
import ouhk.comps380f.model.OrderHistory;

public interface OrderHistoryService {
    
    public void createOrderHistory(String username, String item_name, int quantity, String datetime);
    public List<OrderHistory> getOrderHistory(String name);
}