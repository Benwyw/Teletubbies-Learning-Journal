package ouhk.comps380f.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.OrderHistoryRepository;
import ouhk.comps380f.model.OrderHistory;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService{
    
    @Resource
    private OrderHistoryRepository orderhistoryRepo;
    
    @Override
    @Transactional
    public void createOrderHistory(String username, int item_id, int quantity, String datetime){
        OrderHistory orderhistory = new OrderHistory();
        orderhistory.setUsername(username);
        orderhistory.setItem_id(item_id);
        orderhistory.setQuantity(quantity);
        orderhistory.setDatetime(datetime);
        orderhistoryRepo.save(orderhistory);
    }
    
    @Override
    @Transactional
    public List<OrderHistory> getOrderHistory(String name) {
        List<OrderHistory> orderhistory = orderhistoryRepo.findAll();
        List<OrderHistory> orderhistory_return = new ArrayList<>();
        
        for(OrderHistory oh : orderhistory){
            if(oh.getUsername().equals(name)){
                orderhistory_return.add(oh);
            }
        }
        return orderhistory_return;
    }
}