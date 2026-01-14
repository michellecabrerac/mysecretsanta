package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "T_ASSIGNMENT")
public class Assignment {
/*Resultado del sorteo*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "draw_fk", nullable = false)
    private Draw draw;
    @ManyToOne
    @JoinColumn(name = "gift_giver_fk", nullable = false)
    private User giftGiver; //quien hace el regalo
    @ManyToOne
    @JoinColumn(name = "gift_receiver_fk", nullable = false)
    private User giftReceiver; // quien recibe el regalo
}
