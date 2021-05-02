package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.ItemUser;


public interface ItemUserRepository extends JpaRepository<ItemUser, String> {
}

