package com.escl.avapi.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgenerator")
//    // The initial value is to account for data.sql demo data ids
//    @SequenceGenerator(name = "idgenerator", initialValue = 1)

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractEntity that)) {
            return false; // null or not an AbstractEntity class
        }
        if (getId() != null) {
            return getId().equals(that.getId());
        }
        return super.equals(that);
    }

    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Rangoon")
    private LocalDateTime createdAt;

    @Column(name = "UPDATED_AT",nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Rangoon")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now(); // uses system default timezone
        this.createdAt = now;
        this.updatedAt = now;

        // default value when first created
        if (this.isDeleted == null) {
            this.isDeleted = false;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    @Column(name="IS_DELETED",nullable = false)
    private Boolean isDeleted;
}
