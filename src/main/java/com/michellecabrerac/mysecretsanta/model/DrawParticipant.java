package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
public class DrawParticipant {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Draw draw;
    @ManyToOne
    private User user;
    @CreationTimestamp
    private LocalDateTime registeredAt;
    @Column(nullable = false)
    private Boolean confirmed = false; // el usuario debe de confirmar su participación
    @Column(length = 500)
    private String preferences; //preferencias de regalo
}
