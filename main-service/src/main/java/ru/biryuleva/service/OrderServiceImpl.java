package ru.biryuleva.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.biryuleva.client.Client;
import ru.biryuleva.dto.NumberDto;
import ru.biryuleva.dto.OrderDetailsDto;
import ru.biryuleva.dto.OrderDto;

import ru.biryuleva.exception.NotFoundException;
import ru.biryuleva.mapper.OrderDetailsMapper;
import ru.biryuleva.mapper.OrderMapper;
import ru.biryuleva.model.Order;
import ru.biryuleva.model.OrderDetails;
import ru.biryuleva.repository.OrderDetailsRepository;
import ru.biryuleva.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository detailsRepository;
    private final Client client;

    @Override
    public OrderDto create(OrderDto orderDto) {
        NumberDto number = client.getNumber();
        orderDto.setOrderNumber(number.getNumber());
        orderDto.setDate(LocalDate.now());
        Order order = orderRepository.save(OrderMapper.mapToOrder(orderDto));
        return OrderMapper.toOrderDto(order);
    }

    @Override
    public OrderDto getOrderByMaxAmount(LocalDate date) {
        return orderRepository.findOrderByAmount(date)
                .map(OrderMapper::toOrderDto)
                .orElseThrow(() -> new NotFoundException("Заказа на дату " + date
                + " нет в базе."));
    }

    @Override
    public List<Long> getOrders(OrderDetailsDto dto, LocalDate startDate, LocalDate endDate) {
        return orderRepository.findOrderNumbersExceptProductAndDateRange(dto.getItemName(), startDate, endDate);
    }

    @Override
    public OrderDetailsDto addOrderDetails(OrderDetailsDto detailsDto) {
        Long id = detailsDto.getId();
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Заказа c id " + id
                        + " нет в базе."));
        OrderDetails details = OrderDetailsMapper.mapToOrderDetails(detailsDto);
        details.setOrder(order);
        return OrderDetailsMapper.mapToOrderDetailsDto(detailsRepository.save(details), OrderMapper.toOrderDto(order));
    }

}
