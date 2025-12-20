package com.michellecabrerac.mysecretsanta.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
@Entity
public class DrawConfiguration {
private Boolean allowRepeatedPairsLastYear;
private float minGiftPrice;
private float maxGiftPrice;
private Date drawDate;
}
