package com.jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded //내장타입
    private Address address;

    //mappedBy 나는 연관관계의 주인이 아니고
    //Order member 필드로 인해 나는 매핑이 된거임
    //맵핑된 거울일 뿐
    @OneToMany(mappedBy = "member") //1 : N
    private List<Order> orders = new ArrayList<>();
}














