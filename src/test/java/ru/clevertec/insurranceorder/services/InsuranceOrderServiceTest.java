package ru.clevertec.insurranceorder.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.insurranceorder.dto.InsuranceOrderDto;
import ru.clevertec.insurranceorder.exceptions.InsuranceOrderNotFoundException;
import ru.clevertec.insurranceorder.mappers.InsuranceOrderMapper;
import ru.clevertec.insurranceorder.repositories.InsuranceOrderRepository;
import ru.clevertec.insurranceorder.util.MockUtil;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class InsuranceOrderServiceTest {

    @Mock
    private InsuranceOrderRepository repository;
    @Mock
    private InsuranceOrderMapper mapper;

    @InjectMocks
    private InsuranceOrderService service;


    @Test
    @DisplayName("Find all orders")
    void shouldGetList_whenFindAllOrders() {
        // given
        when(repository.findAll()).thenReturn(MockUtil.getOrders());
        when(mapper.toListOrderDto(MockUtil.getOrders())).thenReturn(MockUtil.getDtoOrders());

        // when
        List<InsuranceOrderDto> list = service.findAllOrders();

        // then
        assertFalse(list.isEmpty());
    }

    @Test
    @DisplayName("Check list size")
    void shouldGetListSize_whenFindAllOrders() {
        // given
        when(repository.findAll()).thenReturn(MockUtil.getOrders());
        when(mapper.toListOrderDto(MockUtil.getOrders())).thenReturn(MockUtil.getDtoOrders());

        // when
        List<InsuranceOrderDto> list = service.findAllOrders();

        // then
        assertEquals(5, list.size());
    }

    @Test
    @DisplayName("Check order numbers by id")
    void shouldReturnAnEqualsNumber_whenFindOrderById() {
        // given
        when(repository.findById(1L)).thenReturn(MockUtil.getOrders().stream().findFirst());
        when(mapper.toDto(MockUtil.getOrders().stream().findFirst().get())).thenReturn(MockUtil.getDtoOrders().stream().findFirst().get());

        // when
        InsuranceOrderDto dto = service.findOrderById(1L);

        // then
        assertEquals("vgcf-vgr5-bhr5-bh65-yy98", dto.getNumber());
    }

    @Test
    @DisplayName("Find by id - exception")
    void shouldThrowException_whenFindOrderById() {
        // given
        when(repository.findById(11L)).thenReturn(Optional.empty());

        // when

        // then
        assertThrows(InsuranceOrderNotFoundException.class, () -> service.findOrderById(11L));
    }

    @Test
    @DisplayName("Verify create method")
    void shouldVerifyMethod_whenAddOrder() {
        // given
        doNothing().when(repository).addOrder(MockUtil.getOrders().get(0));
        when(mapper.toEntity(MockUtil.getDtoOrders().get(0))).thenReturn(MockUtil.getOrders().get(0));

        // when
        service.addOrder(MockUtil.getDtoOrders().get(0));

        // then
        verify(repository).addOrder(MockUtil.getOrders().get(0));
    }

    @Test
    @DisplayName("Delete by id - exception")
    void shouldThrowException_whenDeleteOrderById() {
        // given
        when(repository.findById(111L)).thenThrow(InsuranceOrderNotFoundException.class);

        // when

        // then
        assertThrows(InsuranceOrderNotFoundException.class, () -> service.deleteOrderById(111L));
    }

    @Test
    @DisplayName("Delete by id")
    void shouldVerifyMethod_whenDeleteOrderById() {
        // given
        when(repository.findById(111L)).thenReturn(MockUtil.getOrders().stream().findFirst());
        doNothing().when(repository).deleteById(111L);

        // when
        service.deleteOrderById(111L);

        // then
        verify(repository, times(1)).deleteById(111L);
        verify(repository, times(1)).findById(111L);
    }

    @Test
    @DisplayName("Update by id - exception")
    void shouldThrowException_whenUpdateOrderById() {
        // given
        when(repository.findById(777L)).thenReturn(MockUtil.getOrders().stream().findFirst());
        when(mapper.toEntity(MockUtil.getDtoOrders().get(0))).thenReturn(MockUtil.getOrders().get(0));
        doNothing().when(repository).updateById(777L, MockUtil.getOrders().get(0));

        // when

        // then
        assertDoesNotThrow(() -> service.updateOrderById(777L, MockUtil.getDtoOrders().get(0)));
    }

    @Test
    @DisplayName("Update by id - verify")
    void shouldVerifyMethod_whenUpdateOrderById() {
        // given
        when(repository.findById(777L)).thenReturn(MockUtil.getOrders().stream().findFirst());
        when(mapper.toEntity(MockUtil.getDtoOrders().get(0))).thenReturn(MockUtil.getOrders().get(0));
        doNothing().when(repository).updateById(777L, MockUtil.getOrders().get(0));

        // when
        service.updateOrderById(777L, MockUtil.getDtoOrders().get(0));

        // then
        verify(repository, times(1)).findById(777L);
        verify(repository, times(1)).updateById(777L, MockUtil.getOrders().get(0));
    }
}
