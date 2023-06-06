package com.jimmy.diningreviewapi.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Boolean hasPeanutAllergies;
    @Setter
    private Boolean hasEggAllergies;
    @Setter
    private Boolean hasDairyAllergies;

    private Member(String name, String state, String city, Integer zipCode, Boolean hasPeanutAllergies, Boolean hasEggAllergies, Boolean hasDairyAllergies) {
        this.name = name;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.hasPeanutAllergies = hasPeanutAllergies;
        this.hasEggAllergies = hasEggAllergies;
        this.hasDairyAllergies = hasDairyAllergies;
    }

    public static Member of(String name, String state, String city, Integer zipCode, Boolean hasPeanutAllergies, Boolean hasEggAllergies, Boolean hasDairyAllergies) {
        return new Member(name, state, city, zipCode, hasPeanutAllergies, hasEggAllergies, hasDairyAllergies);
    }

}
