package ru.clevertec.insurranceorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.clevertec.insurranceorder.dto.InsuranceOrderDto;
import ru.clevertec.insurranceorder.exceptions.InsuranceOrderNotFoundException;
import ru.clevertec.insurranceorder.mappers.InsuranceOrderMapper;
import ru.clevertec.insurranceorder.repositories.InsuranceOrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InsuranceOrderService {

    private final InsuranceOrderRepository repository;
    private final InsuranceOrderMapper orderMapper;


    public List<InsuranceOrderDto> findAllOrders() {
        return orderMapper.toListOrderDto(repository.findAll());
    }

    public InsuranceOrderDto findOrderById(long id) {
        return orderMapper.toDto(repository.findById(id).orElseThrow(() -> new InsuranceOrderNotFoundException(id)));
    }

    public void addOrder(InsuranceOrderDto orderDto) {
        repository.addOrder(orderMapper.toEntity(orderDto));
    }

    public void deleteOrderById(long id) {
        repository.findById(id).orElseThrow(() -> new InsuranceOrderNotFoundException(id));
        repository.deleteById(id);
    }

    public void updateOrderById(long id, InsuranceOrderDto orderDto) {
        repository.findById(id).orElseThrow(() -> new InsuranceOrderNotFoundException(id));
        repository.updateById(id, orderMapper.toEntity(orderDto));
    }
}
