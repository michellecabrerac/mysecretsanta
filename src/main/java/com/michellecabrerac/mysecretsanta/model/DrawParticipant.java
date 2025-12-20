package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class DrawParticipant {
    @ManyToOne
    private Draw draw;
    @ManyToOne
    private User user;
}
