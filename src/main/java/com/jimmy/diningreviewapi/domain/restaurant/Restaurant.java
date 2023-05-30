package com.jimmy.diningreviewapi.domain.restaurant;

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

    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;
    private Double averageScore;

    protected Restaurant() {
    }

    private Restaurant(String name) {
        this.name = name;
        this.peanutScore = 0;
        this.eggScore = 0;
        this.dairyScore = 0;
        this.averageScore = 0.0;
    }

    public static Restaurant of(String name) {
        return new Restaurant(name);
    }

}
