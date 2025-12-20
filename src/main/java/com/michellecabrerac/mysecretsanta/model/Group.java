package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class Group {
    private Long id;
    private String name;
    private String description;
}
