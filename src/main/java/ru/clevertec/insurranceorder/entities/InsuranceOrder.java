package ru.clevertec.insurranceorder.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceOrder {

    private long id;
    private String number;
    private Type type;
    private Status status;

    public enum Type {
        CLAIM_ORDER,
        POLICE_ORDER,
        CANCELLATION_ORDER,
        RENEWAL_ORDER
    }

    public enum Status {
        PENDING,
        APPROVED,
        REJECTED,
        IN_PROCESS
    }
}
