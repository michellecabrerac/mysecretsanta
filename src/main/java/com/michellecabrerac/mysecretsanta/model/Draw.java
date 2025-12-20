package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Draw {
    /*Participación sorteo*/
    private String status;
    @OneToOne
    private DrawConfiguration configuration;
}
