package com.jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
//상속관계 전략 싱클테이블 전략
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//싱글테이블 이기떄문에 저장할때 구분을 해줘야한다
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;
    private int stockQuantity;
}
