package com.cafeteria.cafeteria.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cafeteria.cafeteria.DbModels.OrderItem;
import com.cafeteria.cafeteria.ViewModels.CreateOrderItem;
import com.cafeteria.cafeteria.ViewModels.ViewOrderItem;
import com.cafeteria.cafeteria.repository.OrderItemRepository;
import com.cafeteria.cafeteria.service.MenuItemService;
import com.cafeteria.cafeteria.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository  orderItemRepository;
    private final MenuItemService  menuItemService;

    public OrderItemServiceImpl(
        OrderItemRepository orderItemRepository,
        MenuItemService menuItemService
    ) {
        this.orderItemRepository = orderItemRepository;
        this.menuItemService = menuItemService;
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem findById(Long order_item_id) {
        return orderItemRepository.findById(order_item_id).orElse(null);
    }

    @Override
    public OrderItem save(OrderItem order_item) {
        return orderItemRepository.save(order_item);
    }

    @Override
    public void deleteById(Long order_item_id) {
        orderItemRepository.deleteById(order_item_id);
    }

    @Override
    public List<OrderItem> findByOrderId(Long order_id) {
        return orderItemRepository.findByOrderId(order_id);
    }



    @Override
    public List<ViewOrderItem> getAllOrderItems() {
        var orderItems = findAll();
        var orderItemsView = new ArrayList<ViewOrderItem>();

        for (OrderItem orderItem : orderItems) {
            var orderItemView = new ViewOrderItem();
            var menuItem = menuItemService.getMenuItemById(orderItem.getMenuItemId());
            orderItemView.name = menuItem.getName();
            orderItemView.description = menuItem.getDescription();
            orderItemView.quantity = String.valueOf(orderItem.getQuantity());
            orderItemView.price = String.valueOf(orderItem.getPrice());
            orderItemView.menuItemId = menuItem.getId();
            orderItemView.specialInstructions = orderItem.getSpecialInstructions();
            orderItemsView.add(orderItemView);
        }

        return orderItemsView;
    }

    @Override
    public List<ViewOrderItem> getAllOrderItemsByOrderId(Long order_id) {
        var orderItems = findByOrderId(order_id);
        var orderItemsView = new ArrayList<ViewOrderItem>();

        for (OrderItem orderItem : orderItems) {
            var orderItemView = new ViewOrderItem();
            var menuItem = menuItemService.getMenuItemById(orderItem.getMenuItemId());
            orderItemView.name = menuItem.getName();
            orderItemView.description = menuItem.getDescription();
            orderItemView.quantity = String.valueOf(orderItem.getQuantity());
            orderItemView.price = String.valueOf(orderItem.getPrice());
            orderItemView.menuItemId = menuItem.getId();
            orderItemView.specialInstructions = orderItem.getSpecialInstructions();
            orderItemsView.add(orderItemView);
        }

        return orderItemsView;
    }

        @Override 
    public void createOrderItem(CreateOrderItem CreateOrderItem, Long order_id) {
        var orderItem = new OrderItem();
        orderItem.setMenuItemId(CreateOrderItem.menuItemId);
        orderItem.setOrderId(order_id);
        orderItem.setQuantity(CreateOrderItem.quantity);
        orderItem.setSpecialInstructions(CreateOrderItem.specialInstructions);
        orderItem.setPrice((int)menuItemService.getMenuItemById(order_id).getPrice());
        orderItemRepository.save(orderItem);
    }
}
