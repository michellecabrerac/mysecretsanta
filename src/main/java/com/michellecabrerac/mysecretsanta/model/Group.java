package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter@Setter
@Entity
@Table(name = "T_GROUP")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(length = 500)
    private String description;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
