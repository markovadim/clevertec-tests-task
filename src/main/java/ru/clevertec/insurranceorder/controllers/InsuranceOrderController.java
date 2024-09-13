package ru.clevertec.insurranceorder.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.clevertec.insurranceorder.dto.InsuranceOrderDto;
import ru.clevertec.insurranceorder.services.InsuranceOrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class InsuranceOrderController {

    private final InsuranceOrderService orderService;

    @GetMapping
    public ResponseEntity<List<InsuranceOrderDto>> findAllOrders() {
        return ResponseEntity.ok()
                .body(orderService.findAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsuranceOrderDto> findOrderById(@PathVariable long id) {
        return ResponseEntity.ok()
                .body(orderService.findOrderById(id));
    }

    @PostMapping
    public ResponseEntity<InsuranceOrderDto> addNewOrder(@RequestBody InsuranceOrderDto orderDto) {
        orderService.addOrder(orderDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteOrderById(@PathVariable long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<InsuranceOrderDto> updateOrderById(@PathVariable long id, @RequestBody InsuranceOrderDto orderDto) {
        orderService.updateOrderById(id, orderDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderDto);
    }
}
