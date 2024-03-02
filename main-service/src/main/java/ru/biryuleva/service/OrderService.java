package ru.biryuleva.service;

import ru.biryuleva.dto.OrderDetailsDto;
import ru.biryuleva.dto.OrderDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    OrderDto create(OrderDto orderDto);

    OrderDto getOrderByMaxAmount(LocalDate date);

    List<Long> getOrders(OrderDetailsDto dto, LocalDate startDate, LocalDate endDate);

    OrderDetailsDto addOrderDetails(OrderDetailsDto detailsDto);
}
