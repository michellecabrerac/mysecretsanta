package com.michellecabrerac.mysecretsanta.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class Draw {
    /*Participación sorteo*/
    @Id
    private Long id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(length = 1500)
    private String description;
    @Column(nullable = false, length = 20)
    private DrawStatus status;
    @OneToOne
    private DrawConfiguration configuration;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
enum DrawStatus{
    DRAFT,
    READY,
    EXECUTED,
    CANCELLED
}
