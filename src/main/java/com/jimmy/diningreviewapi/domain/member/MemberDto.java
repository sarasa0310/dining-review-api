package com.jimmy.diningreviewapi.domain.member;

import lombok.Getter;

@Getter
public final class MemberDto {

    private String name;

    private String state;
    private String city;
    private Integer zipCode;

    private Boolean peanutAllergies;
    private Boolean eggAllergies;
    private Boolean dairyAllergies;

    public Member toEntity() {
        return Member.of(name, state, city, zipCode, peanutAllergies, eggAllergies, dairyAllergies);
    }

}
