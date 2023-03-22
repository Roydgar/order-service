package com.example.productservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;


@MappedSuperclass
public class AuditableEntity {
    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    private Instant updatedAt;
    @Version
    @Column(name = "version")
    private int version;
}
