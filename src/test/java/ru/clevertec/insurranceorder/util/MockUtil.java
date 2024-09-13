package ru.clevertec.insurranceorder.util;

import ru.clevertec.insurranceorder.dto.InsuranceOrderDto;
import ru.clevertec.insurranceorder.entities.InsuranceOrder;

import java.util.List;

public class MockUtil {

    public static List<InsuranceOrder> getOrders() {
        return List.of(
                new InsuranceOrder(1, "uye4-97tf-jnu4-4we3-qw23", InsuranceOrder.Type.CANCELLATION_ORDER, InsuranceOrder.Status.PENDING),
                new InsuranceOrder(2, "yte6-09ii-76tt-09po-0oi6", InsuranceOrder.Type.CLAIM_ORDER, InsuranceOrder.Status.APPROVED),
                new InsuranceOrder(3, "24rt-7654-jn7h-09ok-76yt", InsuranceOrder.Type.POLICE_ORDER, InsuranceOrder.Status.APPROVED),
                new InsuranceOrder(4, "078b-nj76-0o3h-4km5-6yh5", InsuranceOrder.Type.POLICE_ORDER, InsuranceOrder.Status.IN_PROCESS),
                new InsuranceOrder(5, "hb65-0ol9-766h-bht5-bh5h", InsuranceOrder.Type.CLAIM_ORDER, InsuranceOrder.Status.IN_PROCESS)
        );
    }

    public static List<InsuranceOrderDto> getDtoOrders() {
        return List.of(
                new InsuranceOrderDto("vgcf-vgr5-bhr5-bh65-yy98", "ANCELLATION_ORDER", "REJECTED"),
                new InsuranceOrderDto("vg5r-kmhu-cfrd-4523-qw23", "CANCELLATION_ORDER", "APPROVED"),
                new InsuranceOrderDto("fd54-gfsx-87t5-nhvf-3fr5", "CLAIM_ORDER", "REJECTED"),
                new InsuranceOrderDto("vf34-mj78-mjbg-cd4d-cd23", "POLICE_ORDER", "IN_PROCESS"),
                new InsuranceOrderDto("sxc3-cd45-cd42-nh56-bg67", "RENEWAL_ORDER", "IN_PROCESS")
        );
    }
}
