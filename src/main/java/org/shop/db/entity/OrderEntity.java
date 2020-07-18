package org.shop.db.entity;


import org.shop.dto.OrderDetailDto;
import org.shop.dto.OrderDto;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = true)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "client", nullable = false)
    private String client;

    @JoinColumn(name = "order_id")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderDetailEntity> orderDetailEntities;

    public OrderEntity() {

    }

    public OrderEntity(String name, String client, List<OrderDetailEntity> orderDetailEntities) {
        this.name = name;
        this.client = client;
        this.orderDetailEntities = orderDetailEntities;
    }

    public OrderEntity(long id, String name, String client, List<OrderDetailEntity> orderDetailEntities) {
        this.id = id;
        this.name = name;
        this.client = client;
        this.orderDetailEntities = orderDetailEntities;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getClient() {
        return client;
    }

    public List<OrderDetailEntity> getOrderDetailEntities() {
        return orderDetailEntities;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setOrderDetailEntities(List<OrderDetailEntity> orderDetailEntities) {
        this.orderDetailEntities = orderDetailEntities;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", client='" + client + '\'' +
                ", orderDetailEntities=" + orderDetailEntities +
                '}';
    }

    public OrderDto toDto() {
        List<OrderDetailDto> orderDetailDtos = this.orderDetailEntities == null
                ? null
                : this.orderDetailEntities.stream().map(OrderDetailEntity::toDto).collect(toList());
        return new OrderDto(this.id, this.name,this.client, orderDetailDtos);
    }
    public static OrderEntity toEntity(OrderDto dto) {

        List<OrderDetailEntity> orderDetailEntities =
                dto.getOrderDetails()
                        .stream()
                        .map(elements -> new OrderDetailEntity(elements.getId(),elements.getName(),elements.getPrice()))
                        .collect(toList());
        return new OrderEntity(dto.getId(), dto.getName(),dto.getClient(), orderDetailEntities);
    }

}
