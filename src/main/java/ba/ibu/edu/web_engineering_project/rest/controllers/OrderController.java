package ba.ibu.edu.web_engineering_project.rest.controllers;

import ba.ibu.edu.web_engineering_project.core.model.Order;
import ba.ibu.edu.web_engineering_project.core.service.JwtService;
import ba.ibu.edu.web_engineering_project.core.service.OrderService;
import ba.ibu.edu.web_engineering_project.rest.dto.OrderRequestDTO;
import ba.ibu.edu.web_engineering_project.rest.dto.TicketPurchaseRequest;
import ba.ibu.edu.web_engineering_project.rest.dto.TicketPurchaseRequestDTO;
import com.google.zxing.WriterException;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;
    private final JwtService jwtService;

    public OrderController(OrderService orderService, JwtService jwtService) {
        this.orderService = orderService;
        this.jwtService = jwtService;
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

    @RequestMapping(method = RequestMethod.POST, path = "/purchase")
    @SecurityRequirement(name="Authorization")
    public ResponseEntity<Order> purchaseTickets(
            @RequestBody TicketPurchaseRequestDTO ticketPurchaseRequestDTO,
            @RequestHeader(name = HttpHeaders.AUTHORIZATION, required = false) String authorizationHeader
    ) throws IOException, WriterException {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String token = authorizationHeader.substring(7);
        String userId = jwtService.extractUserId(token);

        if (userId == null || userId.equals("Null")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        TicketPurchaseRequest tpr = ticketPurchaseRequestDTO.toEntity();
        tpr.setUserId(userId);
        return ResponseEntity.ok(orderService.purchaseTickets(tpr));
    }
}
