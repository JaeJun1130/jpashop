package com.jpabook.jpashop.domain;

import com.jpabook.jpashop.domain.status.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Getter @Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne // N : 1
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //주문시간
    private LocalDateTime orderDate;

    //주문상태 [ORDER, CANCEL]
    //enum 타입은 Enumerated 을 넣어줘야함
    //EnumType.ORDINAL default 숫자로 들어감 (쓰면 안좋음 순서가 밀리면 장애남)
    //EnumType.STRING
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
