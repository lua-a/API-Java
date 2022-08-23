package com.api.servicecontrol.controllers;

import com.api.servicecontrol.dtos.OrderDto;
import com.api.servicecontrol.models.OrderModel;
import com.api.servicecontrol.services.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/orders")
public class OrderController {

    final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Object> saveOrder(@RequestBody @Valid OrderDto orderDto) {
        var orderModel = new OrderModel();
        BeanUtils.copyProperties(orderDto, orderModel);
        orderModel.setDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.save(orderModel));
    }

    @GetMapping
    public ResponseEntity<List<OrderModel>> getAllOrder(){
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneOrder(@PathVariable(value = "id") UUID id){
        Optional<OrderModel> orderModelOptional = orderService.findById(id);
        if (!orderModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("order nao encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(orderModelOptional.get());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable(value = "id") UUID id){
        Optional<OrderModel> orderModelOptional = orderService.findById(id);
        if (!orderModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("item nao encontrado.");
        }
        orderService.delete(orderModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable(value = "id") UUID id,
                                             @RequestBody @Valid OrderDto orderDto){
        Optional<OrderModel> orderModelOptional = orderService.findById(id);
        if (!orderModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item nao encontrado.");
        }
        var orderModel = new OrderModel();
        BeanUtils.copyProperties(orderDto, orderModel);
        orderModel.setId(orderModelOptional.get().getId());
        orderModel.setDate(orderModelOptional.get().getDate());
        return ResponseEntity.status(HttpStatus.OK).body(orderService.save(orderModel));
    }
}
