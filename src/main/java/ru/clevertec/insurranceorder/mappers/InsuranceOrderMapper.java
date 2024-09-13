package ru.clevertec.insurranceorder.mappers;

import org.mapstruct.Mapper;
import ru.clevertec.insurranceorder.dto.InsuranceOrderDto;
import ru.clevertec.insurranceorder.entities.InsuranceOrder;

import java.util.List;

@Mapper
public interface InsuranceOrderMapper {

    List<InsuranceOrderDto> toListOrderDto(List<InsuranceOrder> orders);

    InsuranceOrderDto toDto(InsuranceOrder entity);

    InsuranceOrder toEntity(InsuranceOrderDto dto);
}
