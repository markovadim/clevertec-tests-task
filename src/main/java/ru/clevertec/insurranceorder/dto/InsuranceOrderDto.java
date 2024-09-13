package ru.clevertec.insurranceorder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class InsuranceOrderDto {

    private String number;
    private String type;
    private String status;
}
