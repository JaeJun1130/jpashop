package com.jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

//값 타입은 변경 불가능하게 설계해야 한다.
@Embeddable //내장타입
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;

    //JPA는 기본 생성자가 필
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
