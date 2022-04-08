package com.example.sfgrecipeproject.model;

import javax.persistence.Entity;

@Entity
public class Ingredient extends BaseEntity {

    private String name;
    private Long quantity;

    public Ingredient() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
