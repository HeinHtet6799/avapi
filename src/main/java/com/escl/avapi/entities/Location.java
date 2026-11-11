package com.escl.avapi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "location")
public class Location extends AbstractEntity{

    private String name;
    private String alias;
    private String address;
    private String tel;
    private String topUp;
    private boolean autoTransfer;
    private boolean active;
}
