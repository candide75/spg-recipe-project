package com.example.sfgrecipeproject.domain;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class UnitOfMeasure extends BaseEntity {

    private String description;
}