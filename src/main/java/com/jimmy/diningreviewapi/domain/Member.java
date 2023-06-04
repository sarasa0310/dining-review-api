package com.jimmy.diningreviewapi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    @Setter
    private String state;
    @Setter
    private String city;
    @Setter
    private Integer zipCode;

    @Setter
    private Boolean peanutAllergies;
    @Setter
    private Boolean eggAllergies;
    @Setter
    private Boolean dairyAllergies;

    protected Member() {
    }

    private Member(String name, String state, String city, Integer zipCode, Boolean peanutAllergies, Boolean eggAllergies, Boolean dairyAllergies) {
        this.name = name;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.peanutAllergies = peanutAllergies;
        this.eggAllergies = eggAllergies;
        this.dairyAllergies = dairyAllergies;
    }

    public static Member of(String name, String state, String city, Integer zipCode, Boolean peanutAllergies, Boolean eggAllergies, Boolean dairyAllergies) {
        return new Member(name, state, city, zipCode, peanutAllergies, eggAllergies, dairyAllergies);
    }

}
