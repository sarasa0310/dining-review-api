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

    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;
    private Double averageScore;

    protected Restaurant() {
    }

    private Restaurant(String name) {
        this.name = name;
    }

    public static Restaurant of(String name) {
        return new Restaurant(name);
    }

}
