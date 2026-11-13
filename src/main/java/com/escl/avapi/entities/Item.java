package com.escl.avapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "item")
public class Item extends AbstractEntity {
    private String name;
}