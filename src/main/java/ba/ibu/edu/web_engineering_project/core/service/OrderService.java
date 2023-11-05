package ba.ibu.edu.web_engineering_project.core.service;

import ba.ibu.edu.web_engineering_project.core.exceptions.repository.ResourceNotFoundException;
import ba.ibu.edu.web_engineering_project.core.model.Order;
import ba.ibu.edu.web_engineering_project.core.repository.OrderRepository;
import ba.ibu.edu.web_engineering_project.rest.dto.OrderRequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(String id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()){
            throw new ResourceNotFoundException("The order with the provided ID does not exist.");
        }
        return order.get();
    }

    public Order addOrder(OrderRequestDTO payload){
        return orderRepository.save(payload.toEntity());
    }

    public Order updateOrder(String id, OrderRequestDTO payload){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isEmpty()){
            throw new ResourceNotFoundException("The order with the provided ID does not exist.");
        }
        Order updatedOrder = payload.toEntity();
        updatedOrder.setId(order.get().getId());
        updatedOrder = orderRepository.save(updatedOrder);
        return updatedOrder;
    }

    public void deleteOrder(String id){
        Optional<Order> order = orderRepository.findById(id);
        order.ifPresent(orderRepository::delete);
    }
}
