package com.jimmy.diningreviewapi.dto;

import com.jimmy.diningreviewapi.domain.Member;
import lombok.Getter;

@Getter
public final class MemberDto {

    private final String name;

    private final String state;
    private final String city;
    private final Integer zipCode;

    private final Boolean peanutAllergies;
    private final Boolean eggAllergies;
    private final Boolean dairyAllergies;

    private MemberDto(String name, String state, String city, Integer zipCode, Boolean peanutAllergies, Boolean eggAllergies, Boolean dairyAllergies) {
        this.name = name;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.peanutAllergies = peanutAllergies;
        this.eggAllergies = eggAllergies;
        this.dairyAllergies = dairyAllergies;
    }

    public static MemberDto of(String name, String state, String city, Integer zipCode, Boolean peanutAllergies, Boolean eggAllergies, Boolean dairyAllergies) {
        return new MemberDto(name, state, city, zipCode, peanutAllergies, eggAllergies, dairyAllergies);
    }

    public static MemberDto from(Member member) {
        return MemberDto.of(
                member.getName(),
                member.getState(),
                member.getCity(),
                member.getZipCode(),
                member.getPeanutAllergies(),
                member.getEggAllergies(),
                member.getDairyAllergies());
    }

    public Member toEntity() {
        return Member.of(name, state, city, zipCode, peanutAllergies, eggAllergies, dairyAllergies);
    }

}
