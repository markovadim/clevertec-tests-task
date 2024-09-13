package ru.clevertec.insurranceorder.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.clevertec.insurranceorder.dto.InsuranceOrderDto;
import ru.clevertec.insurranceorder.services.InsuranceOrderService;
import ru.clevertec.insurranceorder.util.MockUtil;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InsuranceOrderController.class)
class InsuranceOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private InsuranceOrderService service;

    @Test
    @DisplayName("Get list")
    void shouldReturnOkStatusAndJson_whenFindAllOrders() throws Exception {
        when(service.findAllOrders()).thenReturn(MockUtil.getDtoOrders());

        mockMvc.perform(get("/api/v1/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Get by id")
    void shouldOneOrderDto_whenFindOrderById() throws Exception {
        when(service.findOrderById(1L)).thenReturn(MockUtil.getDtoOrders().get(0));

        mockMvc.perform(get("/api/v1/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Post order")
    void shouldReturnIsCreatedStatus_whenAddNewOrder() throws Exception {
        InsuranceOrderDto dto = InsuranceOrderDto.builder().number("111").type("POLICE_ORDER").status("IN_PROCESS").build();

        doNothing().when(service).addOrder(dto);

        mockMvc.perform(post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Delete by id")
    void shouldReturnNoContentStatus_whenDeleteOrderById() throws Exception {
        doNothing().when(service).deleteOrderById(11L);

        mockMvc.perform(delete("/api/v1/orders/11"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Patch by id")
    void shouldReturnOkStatusAndJsonObject_whenUpdateOrderById() throws Exception {
        InsuranceOrderDto dto = MockUtil.getDtoOrders().get(0);

        doNothing().when(service).updateOrderById(1L, dto);

        mockMvc.perform(patch("/api/v1/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
