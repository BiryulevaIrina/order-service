package ru.biryuleva.mapper;

import org.springframework.stereotype.Component;
import ru.biryuleva.dto.OrderDto;
import ru.biryuleva.model.Order;

@Component
public class OrderMapper {

    public static OrderDto toOrderDto(Order order) {
        return new OrderDto(
                order.getOrderNumber(),
                order.getAmount(),
                order.getDate()
        );
    }

    public static Order mapToOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderNumber(orderDto.getOrderNumber());
        order.setAmount(orderDto.getAmount());
        order.setDate(orderDto.getDate());
        return order;
    }
}
