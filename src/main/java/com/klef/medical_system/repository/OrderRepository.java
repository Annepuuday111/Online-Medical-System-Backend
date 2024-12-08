package com.klef.medical_system.repository;

import com.klef.medical_system.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    // JpaRepository already provides a count() method
}
