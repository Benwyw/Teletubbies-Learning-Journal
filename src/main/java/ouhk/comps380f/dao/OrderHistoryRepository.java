package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.OrderHistory;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {

    public OrderHistory findByUsername(String name);
}