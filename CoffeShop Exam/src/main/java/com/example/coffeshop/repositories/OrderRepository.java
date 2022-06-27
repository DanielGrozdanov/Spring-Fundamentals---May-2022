package com.example.coffeshop.repositories;

import com.example.coffeshop.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    Optional<Order> findByName(String name);

    List<Order> findAllByOrderByPriceDesc();

}
