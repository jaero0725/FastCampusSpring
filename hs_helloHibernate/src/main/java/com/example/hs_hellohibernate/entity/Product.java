package com.example.hs_hellohibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/*
Entity Class = Java Class that is mapped to a db table

@Table

 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="product")
public class Product {

    @Id                         // id로 사용. primary key
    @GeneratedValue             // 키를 생성할때는, 자동으로 생성한다.
    @Column(name="product_id")	// 컬럼 내용을 지정해줄 수 있다.  - 만약에 name을 지정하지 않는다면, field 이름과 같아 진다.
    private int id;

    /*
     @Id, @GeneratedValue, @Column
     */
    private String name;

    private int price;

    private String description;
}
