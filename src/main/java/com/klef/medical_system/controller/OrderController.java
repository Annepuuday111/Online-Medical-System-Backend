package com.klef.medical_system.controller;

import com.klef.medical_system.model.Order;
import com.klef.medical_system.service.OrderService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public Order saveOrder(@RequestBody Order order) {
        return orderService.saveOrder(order);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @GetMapping("/counts")
    public ResponseEntity<Map<String, Long>> getOrderCount() {
        Map<String, Long> countMap = new HashMap<>();
        countMap.put("totalOrders", orderService.getOrderCount());
        return ResponseEntity.ok(countMap);
    }
}
