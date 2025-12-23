package com.michellecabrerac.mysecretsanta.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

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
    private DrawStatus status = DrawStatus.DRAFT;
    @OneToOne
    private DrawConfiguration configuration;
    @OneToMany
    private Set<DrawParticipant> participants;
    @OneToMany
    private Set<Assignment> assignments;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
enum DrawStatus{
    DRAFT,
    READY,
    EXECUTED,
    CANCELLED
}
