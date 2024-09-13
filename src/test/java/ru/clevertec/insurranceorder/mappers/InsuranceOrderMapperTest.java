package ru.clevertec.insurranceorder.mappers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.clevertec.insurranceorder.dto.InsuranceOrderDto;
import ru.clevertec.insurranceorder.entities.InsuranceOrder;
import ru.clevertec.insurranceorder.util.MockUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class InsuranceOrderMapperTest {

    @Autowired
    private InsuranceOrderMapper mapper;

    @Test
    @DisplayName("Map null list")
    void shouldReturnNullList_whenMapNullList() {
        // given
        List<InsuranceOrder> list = null;

        // when
        List<InsuranceOrderDto> dtos = mapper.toListOrderDto(list);

        // then
        assertNull(dtos);
    }

    @ParameterizedTest
    @ValueSource(strings = {"uye4-97tf-jnu4-4we3-qw23", "CANCELLATION_ORDER", "PENDING"})
    @DisplayName("Map list entity to dto")
    void shouldEqualsOrderByParams_whenMapToDtoList(String line) {
        // given
        List<InsuranceOrder> list = MockUtil.getOrders();

        // when
        List<InsuranceOrderDto> dtos = mapper.toListOrderDto(list);

        // then
        assertTrue(dtos.get(0).toString().contains(line));
    }

    @Test
    @DisplayName("Map to dto")
    void shouldReturnNotNullObject_whenMapToDto() {
        // given
        InsuranceOrder order = new InsuranceOrder(1, "1234567890", InsuranceOrder.Type.CLAIM_ORDER, InsuranceOrder.Status.PENDING);

        // when
        InsuranceOrderDto dto = mapper.toDto(order);

        // then
        assertNotNull(dto);
    }

    @Test
    @DisplayName("Map to dto - null")
    void shouldReturnNullObject_whenMapToDto() {
        // given
        InsuranceOrder order = null;

        // when
        InsuranceOrderDto dto = mapper.toDto(order);

        // then
        assertNull(dto);
    }

    @Test
    @DisplayName("Map to entity")
    void shouldMapEqualsOrder_whenMapToEntity() {
        // given
        InsuranceOrderDto dto = new InsuranceOrderDto("1234567890", "CLAIM_ORDER", "PENDING");

        // when
        InsuranceOrder order = mapper.toEntity(dto);

        // then
        assertAll(
                () -> assertNotNull(order),
                () -> assertEquals(0, order.getId()),
                () -> assertEquals("1234567890", order.getNumber()),
                () -> assertEquals(InsuranceOrder.Status.PENDING, order.getStatus())
        );
    }

    @Test
    @DisplayName("Map to entity - null")
    void shouldReturnNull_whenMapToEntity() {
        // given
        InsuranceOrderDto dto = null;

        // when
        InsuranceOrder order = mapper.toEntity(dto);

        // then
        assertNull(order);
    }
}
