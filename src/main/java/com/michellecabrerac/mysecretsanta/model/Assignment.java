package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Assignment {
/*Resultado del sorteo*/
    @ManyToOne
    private Draw draw;
    @ManyToOne
    private User giftGiver; //quien hace el regalo
    @ManyToOne
    private User giftReceiver; // quien recibe el regalo
}
