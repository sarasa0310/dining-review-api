package com.jimmy.diningreviewapi.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static lombok.AccessLevel.NONE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor(access = PROTECTED)
public class Member {

    @Setter(NONE)
    @Column(name = "member_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter(NONE)
    @Column(nullable = false, unique = true, updatable = false)
    private String name;

    private String state;
    private String city;
    private Integer zipCode;

    private Boolean peanutAllergy;
    private Boolean eggAllergy;
    private Boolean dairyAllergy;

    public Member(String name, String state, String city, Integer zipCode, Boolean peanutAllergy, Boolean eggAllergy, Boolean dairyAllergy) {
        this.name = name;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.peanutAllergy = peanutAllergy;
        this.eggAllergy = eggAllergy;
        this.dairyAllergy = dairyAllergy;
    }

    public Boolean hasPeanutAllergy() {
        return peanutAllergy;
    }

    public Boolean hasEggAllergy() {
        return eggAllergy;
    }

    public Boolean hasDairyAllergy() {
        return dairyAllergy;
    }

}
