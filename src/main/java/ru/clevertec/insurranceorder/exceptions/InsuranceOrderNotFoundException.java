package ru.clevertec.insurranceorder.exceptions;

public class InsuranceOrderNotFoundException extends RuntimeException {

    public InsuranceOrderNotFoundException() {
        super("Order not wound");
    }

    public InsuranceOrderNotFoundException(long id) {
        super(String.format("Order with id:%d not found", id));
    }
}
