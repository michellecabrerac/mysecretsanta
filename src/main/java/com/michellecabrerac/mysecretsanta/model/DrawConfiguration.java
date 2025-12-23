package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
@Getter @Setter
@Entity
public class DrawConfiguration {
    @Id
    private Long id;
    @Column
    private Boolean allowRepeatedPairsLastYear;
    @Column
    private Float minGiftPrice;
    @Column
    private Float maxGiftPrice;
    @Column(nullable = false)
    private Date drawDate;
    @OneToOne(fetch = FetchType.LAZY)
    private Draw draw;
    @Column
    private LocalDate evenDate;
}
