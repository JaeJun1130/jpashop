package com.jpabook.jpashop.domain;

import com.jpabook.jpashop.domain.status.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery",fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    //READY, COMP
    //enum 타입은 Enumerated을 넣어줘야함
    //EnumType.ORDINAL default 숫자로 들어감 (쓰면 안좋음 순서가 밀리면 장애남)
    //EnumType.STRING
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
