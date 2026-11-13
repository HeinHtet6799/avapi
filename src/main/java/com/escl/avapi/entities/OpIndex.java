package com.escl.avapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "op_index")
public class OpIndex extends AbstractEntity {

    private String name;
}
