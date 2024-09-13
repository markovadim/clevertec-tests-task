package ru.clevertec.insurranceorder.exceptions;

public class InsuranceOrderBadRequestException extends RuntimeException {

    public InsuranceOrderBadRequestException() {
        super("Incorrect input data");
    }
}
