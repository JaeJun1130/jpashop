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

    @ManyToOne(fetch = FetchType.LAZY) // N : 1
    @JoinColumn(name="member_id")
    private Member member;

    //cascade = CascadeType.ALL
    //데이터 Order 데이터를 저장하면 orderItems도 같이저장되게끔 해줌 (전파)
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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


    //==연관관계 메서드==//
    //연관관계에서 양쪽 데이터를 셋팅할때 편의성을 제공
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }

    public void addOrderItem(OrderItem orderItem){
        this.orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
