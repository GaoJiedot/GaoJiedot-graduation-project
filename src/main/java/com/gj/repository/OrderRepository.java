package com.gj.repository;

import com.gj.pojo.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findByOrderStatus(Integer orderStatus);

    List<Order> findByShopId(Integer shopId);
}
