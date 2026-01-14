package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter @Setter
@Entity
@Table(name = "T_DRAW_CONFIGURATION")
public class DrawConfiguration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Boolean allowRepeatedPairsLastYear;
    @Column
    private Float minGiftPrice;
    @Column
    private Float maxGiftPrice;
    @Column(nullable = false)
    private Date drawDate;
    @OneToOne(mappedBy = "configuration" , fetch = FetchType.LAZY)
    private Draw draw;
    @Column
    private LocalDate eventDate;
}
