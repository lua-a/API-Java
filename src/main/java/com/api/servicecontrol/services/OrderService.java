package com.api.servicecontrol.services;

import com.api.servicecontrol.models.OrderModel;
import com.api.servicecontrol.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {

    final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public OrderModel save(OrderModel orderModel) {
        return orderRepository.save(orderModel);
    }

    public Optional<OrderModel> findById(UUID id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public void delete(OrderModel orderModel) {
        orderRepository.delete(orderModel);
    }


    public List<OrderModel> findAll() {
        return orderRepository.findAll();
    }
}
