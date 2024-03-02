package ru.biryuleva.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.biryuleva.dto.OrderDetailsDto;
import ru.biryuleva.dto.OrderDto;
import ru.biryuleva.service.OrderService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@Validated
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/order")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createNewOrder(@Valid @RequestBody OrderDto orderDto) {
        log.info("Получен POST-запрос на добавление заказа");
        return orderService.create(orderDto);
    }

    @PostMapping("/details")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDetailsDto createOrderDetails(@Valid @RequestBody OrderDetailsDto detailsDto) {
        log.info("Получен POST-запрос на добавление деталей к заказу");
        return orderService.addOrderDetails(detailsDto);
    }

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderByMaxAmount(@DateTimeFormat(pattern = "yyyy-MM-dd")
                                        @RequestParam(required = false) LocalDate date) {
        log.info("Получен GET-запрос на получение заказа в заданную дату {}", date);
        return orderService.getOrderByMaxAmount(date);
    }

    @GetMapping("/order")
    @ResponseStatus(HttpStatus.OK)
    public List<Long> getOrders(@Valid @RequestBody OrderDetailsDto dto,
                                @DateTimeFormat(pattern = "yyyy-MM-dd")
                                @RequestParam(required = false) LocalDate startDate,
                                @DateTimeFormat(pattern = "yyyy-MM-dd")
                                @RequestParam(required = false) LocalDate endDate) {
        log.info("Получен GET-запрос на получение списка id заказов, не содержащих товар - {} и поступивших " +
                "в период с {} по {}", dto, startDate, endDate);
        return orderService.getOrders(dto, startDate, endDate);
    }

}
