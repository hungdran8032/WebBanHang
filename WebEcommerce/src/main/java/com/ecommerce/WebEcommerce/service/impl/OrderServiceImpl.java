package com.ecommerce.WebEcommerce.service.impl;

import com.ecommerce.WebEcommerce.domain.OrderStatus;
import com.ecommerce.WebEcommerce.domain.PaymentStatus;
import com.ecommerce.WebEcommerce.model.*;
import com.ecommerce.WebEcommerce.repository.AddressRepository;
import com.ecommerce.WebEcommerce.repository.OrderItemRepository;
import com.ecommerce.WebEcommerce.repository.OrderRepository;
import com.ecommerce.WebEcommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {
        if(!user.getAddresses().contains(shippingAddress)){
            user.getAddresses().add(shippingAddress);
        }

        Address address = addressRepository.save(shippingAddress);

        Map<Long, List<CartItem>> itemsBySeller = cart.getCartItems().stream()
                .collect(Collectors.groupingBy(item -> item.getProduct().getSeller().getId()));
        Set<Order> orders = new HashSet<>();

        for (Map.Entry<Long, List<CartItem>> entry : itemsBySeller.entrySet()){
            Long sellerId = entry.getKey();
            List<CartItem> items = entry.getValue();
            int totalOrderPrice = items.stream().mapToInt(
                    CartItem::getSellingPrice
            ).sum();
            int totalItem = items.stream().mapToInt(CartItem::getQuantity).sum();
            Order createOrders = new Order();
            createOrders.setUser(user);
            createOrders.setSellerId(sellerId);
            createOrders.setTotalMrpPrice(totalOrderPrice);
            createOrders.setTotalSellingPrice(totalOrderPrice);
            createOrders.setTotalPrice(totalItem);

            createOrders.setShippingAddress(address);
            createOrders.setOrderStatus(OrderStatus.PENDING);
            createOrders.getPaymentDetails().setStatus(PaymentStatus.PENDING);

            Order saveOrder = orderRepository.save(createOrders);
            orders.add(saveOrder);

            List<OrderItem> orderItems = new ArrayList<>();
            for(CartItem item :items){
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder(saveOrder);
                orderItem.setMrpPrice(item.getMrpPrice());
                orderItem.setProduct(item.getProduct());
                orderItem.setQuantity(item.getQuantity());
                orderItem.setSize(item.getSize());
                orderItem.setUserId(item.getUserId());
                orderItem.setSellingPrice(item.getSellingPrice());

                saveOrder.getOrderItems().add(orderItem);

                OrderItem saveOrderItem = orderItemRepository.save(orderItem);
                orderItems.add(saveOrderItem);
            }
        }
        return orders;
    }

    @Override
    public Order findOrderById(long id) throws Exception {
        return orderRepository.findById(id).orElseThrow(()-> new Exception("Khong tim thay"));
    }

    @Override
    public List<Order> userOrderHistory(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public List<Order> sellerOrder(Long sellerId) {
        return orderRepository.findBySellerId(sellerId);
    }

    @Override
    public Order updateOrderStatus(Long idOrder, OrderStatus orderStatus) throws Exception {
        Order order = findOrderById(idOrder);
        order.setOrderStatus(orderStatus);
        return orderRepository.save(order);
    }

    @Override
    public Order cancelOrder(Long orderId, User user) throws Exception {
        Order order = findOrderById(orderId);
        if(!user.getId().equals(order.getUser().getId())){
            throw new Exception("Khong tim thay nguoi nay");
        }
        order.setOrderStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Override
    public OrderItem getItemById(Long id) throws Exception {

        return orderItemRepository.findById(id).orElseThrow(()-> new Exception("Khong tim thay"));
    }
}
