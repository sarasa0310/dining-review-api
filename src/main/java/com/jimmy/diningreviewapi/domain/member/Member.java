package com.jimmy.diningreviewapi.member;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String state;
    private String city;
    private Integer zipCode;

    private Boolean peanutAllergies;
    private Boolean eggAllergies;
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
