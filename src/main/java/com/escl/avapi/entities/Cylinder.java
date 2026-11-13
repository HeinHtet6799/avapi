package com.escl.avapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "cylinder")
public class Cylinder extends AbstractEntity{

    private Double value;
}
