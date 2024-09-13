package ru.clevertec.insurranceorder.repositories;

import org.springframework.stereotype.Repository;
import ru.clevertec.insurranceorder.entities.InsuranceOrder;
import ru.clevertec.insurranceorder.exceptions.InsuranceOrderBadRequestException;

import java.util.List;
import java.util.Optional;

@Repository
public class InsuranceOrderRepositoryImpl implements InsuranceOrderRepository {

    private static List<InsuranceOrder> orders = List.of(
            new InsuranceOrder(1, "uye4-97tf-jnu4-4we3-qw23", InsuranceOrder.Type.CANCELLATION_ORDER, InsuranceOrder.Status.PENDING),
            new InsuranceOrder(2, "yte6-09ii-76tt-09po-0oi6", InsuranceOrder.Type.CLAIM_ORDER, InsuranceOrder.Status.APPROVED),
            new InsuranceOrder(3, "24rt-7654-jn7h-09ok-76yt", InsuranceOrder.Type.POLICE_ORDER, InsuranceOrder.Status.APPROVED),
            new InsuranceOrder(4, "078b-nj76-0o3h-4km5-6yh5", InsuranceOrder.Type.POLICE_ORDER, InsuranceOrder.Status.IN_PROCESS),
            new InsuranceOrder(5, "hb65-0ol9-766h-bht5-bh5h", InsuranceOrder.Type.CLAIM_ORDER, InsuranceOrder.Status.IN_PROCESS),
            new InsuranceOrder(6, "vgcf-vgr5-bhr5-bh65-yy98", InsuranceOrder.Type.CANCELLATION_ORDER, InsuranceOrder.Status.REJECTED),
            new InsuranceOrder(7, "vg5r-kmhu-cfrd-4523-qw23", InsuranceOrder.Type.RENEWAL_ORDER, InsuranceOrder.Status.APPROVED)
    );


    @Override
    public List<InsuranceOrder> findAll() {
        return orders;
    }

    @Override
    public void addOrder(InsuranceOrder order) {
        if (order.getNumber() == null || order.getStatus() == null || order.getType() == null) {
            throw new InsuranceOrderBadRequestException();
        } else {
            orders.add(order);
        }
    }

    @Override
    public Optional<InsuranceOrder> findById(long id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst();
    }

    @Override
    public void deleteById(long id) {
        orders.removeIf(o -> o.getId() == id);
    }

    @Override
    public void updateById(long id, InsuranceOrder order) {
        if (orders.removeIf(o -> o.getId() == id)) {
            orders.add(order);
        }
    }
}
