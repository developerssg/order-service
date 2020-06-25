package com.cts.milestone.order.repository;

import com.cts.milestone.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,String> {

    List<OrderEntity> findByProductId(String productId);
}
