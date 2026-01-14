package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "T_DRAW_PARTICIPANT")
public class DrawParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "draw_fk", nullable = false)
    private Draw draw;
    @ManyToOne
    @JoinColumn(name = "user_fk", nullable = false)
    private User user;
    @CreationTimestamp
    private LocalDateTime registeredAt;
    @Column(nullable = false)
    private Boolean confirmed = false; // el usuario debe de confirmar su participación
    @Column(length = 500)
    private String preferences; //preferencias de regalo
}
