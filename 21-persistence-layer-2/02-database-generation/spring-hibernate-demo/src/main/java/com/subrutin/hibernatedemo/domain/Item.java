package com.subrutin.hibernatedemo.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "item")
public class Item {

    @Id
    private Long id;
    
    private String name;
    
    private BigDecimal productPrice;
}
