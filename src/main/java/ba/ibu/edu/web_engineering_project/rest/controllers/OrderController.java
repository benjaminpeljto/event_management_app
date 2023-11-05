package ba.ibu.edu.web_engineering_project.rest.controllers;

import ba.ibu.edu.web_engineering_project.core.model.Order;
import ba.ibu.edu.web_engineering_project.core.service.OrderService;
import ba.ibu.edu.web_engineering_project.rest.dto.OrderRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/")
    public ResponseEntity<List<Order>> getOrders(){
        return ResponseEntity.ok(orderService.getOrders());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/")
    public ResponseEntity<Order> addOrder(@RequestBody OrderRequestDTO payload){
        return ResponseEntity.ok(orderService.addOrder(payload));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable String id, @RequestBody OrderRequestDTO payload){
        return ResponseEntity.ok(orderService.updateOrder(id, payload));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id){
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
