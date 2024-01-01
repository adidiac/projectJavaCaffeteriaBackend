package com.cafeteria.cafeteria.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cafeteria.cafeteria.Aspects.ValidateTokenAdmin;
import com.cafeteria.cafeteria.Aspects.ValidateTokenUser;
import com.cafeteria.cafeteria.ViewModels.CreateOrder;
import com.cafeteria.cafeteria.ViewModels.ViewOrder;
import com.cafeteria.cafeteria.service.OrderService;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;



@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @ValidateTokenAdmin
    public List<ViewOrder> getAllOrders(@RequestHeader("Authorization") String token) {
        return orderService.getAllOrders();
    }
    

    @PostMapping
    @ValidateTokenUser
    @Valid
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrder createOrder, @RequestHeader("Authorization") String token) {
        try {
            orderService.createOrder(createOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.noContent().build();
    }

}
