package org.shop.service.impl;

import org.shop.db.OrdersRepository;
import org.shop.db.entity.OrderEntity;
import org.shop.dto.OrderDto;
import org.shop.service.OrdersService;

import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public List<OrderDto> findAll() {
        return ordersRepository.findAll().stream().map(OrderEntity::toDto).collect(Collectors.toList());
    }

    @Override
    public OrderDto findOrderBy(long id) {
        return null;
    }

    @Override
    public void saveOrder(OrderDto orderDto) {

    }

    @Override
    public void deleteOrder(long orderId) {

    }
}
