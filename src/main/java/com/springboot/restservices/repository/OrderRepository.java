package com.springboot.restservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.restservices.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
