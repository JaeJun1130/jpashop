package com.jpabook.jpashop.domain.item;

import com.jpabook.jpashop.domain.Category;
import com.jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //==비즈니스 로직==//
    //setter 를 사용하기보다는 값이 변경될 일 이 있으면 비즈니스 로직으로 인해 변경하는게 객체지향적 설계.

    /**
     * stock 증가
     */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;

        if(restStock < 0) throw new NotEnoughStockException("need more stock");

        this.stockQuantity = restStock;
    }
}
