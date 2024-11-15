package com.ecommerce.WebEcommerce.service;

import com.ecommerce.WebEcommerce.domain.OrderStatus;
import com.ecommerce.WebEcommerce.model.*;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Set<Order> createOrder (User user, Address shippingAddress, Cart cart);
    Order findOrderById(long id) throws Exception;
    List<Order> userOrderHistory(Long userId);
    List<Order> sellerOrder(Long sellerId);
    Order updateOrderStatus(Long idOrder, OrderStatus orderStatus) throws Exception;
    Order cancelOrder(Long orderId, User user) throws Exception;
    OrderItem getItemById(Long id) throws Exception;
}
