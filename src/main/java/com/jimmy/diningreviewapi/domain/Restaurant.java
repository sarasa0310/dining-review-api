package com.jimmy.diningreviewapi.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer zipCode;

    private int peanutScore;
    private int eggScore;
    private int dairyScore;
    private double averageScore;

    protected Restaurant() {
    }

    private Restaurant(String name, Integer zipCode) {
        this.name = name;
        this.zipCode = zipCode;
    }

    public static Restaurant of(String name, Integer zipCode) {
        return new Restaurant(name, zipCode);
    }

    public void updateScore(DiningReview review) {
        if (review.getPeanutScore() != null) peanutScore += review.getPeanutScore();
        if (review.getEggScore() != null) eggScore += review.getEggScore();
        if (review.getDairyScore() != null) dairyScore += review.getDairyScore();

        this.averageScore = calculateAverageScore();
    }

    private double calculateAverageScore() {
        double average = (double) (peanutScore + eggScore + dairyScore) / 3;

        return (double) Math.round(average * 100) / 100;
    }

}
