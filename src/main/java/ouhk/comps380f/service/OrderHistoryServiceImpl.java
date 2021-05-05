package ouhk.comps380f.service;

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
    public OrderHistory getOrderHistory(String name) {
        return orderhistoryRepo.findByUsername(name);
    }
}
