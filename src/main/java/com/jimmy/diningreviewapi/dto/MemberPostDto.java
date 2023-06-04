package com.jimmy.diningreviewapi.dto;

import com.jimmy.diningreviewapi.domain.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public final class MemberPostDto {

    @NotBlank
    private final String name;

    private final String state;
    private final String city;
    private final Integer zipCode;

    private final Boolean hasPeanutAllergies;
    private final Boolean hasEggAllergies;
    private final Boolean hasDairyAllergies;

    private MemberPostDto(String name, String state, String city, Integer zipCode, Boolean hasPeanutAllergies, Boolean hasEggAllergies, Boolean hasDairyAllergies) {
        this.name = name;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.hasPeanutAllergies = hasPeanutAllergies;
        this.hasEggAllergies = hasEggAllergies;
        this.hasDairyAllergies = hasDairyAllergies;
    }

    public static MemberPostDto of(String name, String state, String city, Integer zipCode, Boolean hasPeanutAllergies, Boolean hasEggAllergies, Boolean hasDairyAllergies) {
        return new MemberPostDto(name, state, city, zipCode, hasPeanutAllergies, hasEggAllergies, hasDairyAllergies);
    }

    public static MemberPostDto from(Member member) {
        return MemberPostDto.of(
                member.getName(),
                member.getState(),
                member.getCity(),
                member.getZipCode(),
                member.getHasPeanutAllergies(),
                member.getHasEggAllergies(),
                member.getHasDairyAllergies());
    }

    public Member toEntity() {
        return Member.of(name, state, city, zipCode, hasPeanutAllergies, hasEggAllergies, hasDairyAllergies);
    }

}
