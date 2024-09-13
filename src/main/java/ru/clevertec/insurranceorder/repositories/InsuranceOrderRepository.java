package ru.clevertec.insurranceorder.repositories;

import ru.clevertec.insurranceorder.entities.InsuranceOrder;

import java.util.List;
import java.util.Optional;

public interface InsuranceOrderRepository {

    List<InsuranceOrder> findAll();

    void addOrder(InsuranceOrder order);

    Optional<InsuranceOrder> findById(long id);

    void deleteById(long id);

    void updateById(long id, InsuranceOrder order);
}
