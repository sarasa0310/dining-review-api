package com.jimmy.diningreviewapi.dto;

import com.jimmy.diningreviewapi.domain.entity.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public final class MemberDto {

    @NotBlank
    private final String name;

    private final String state;
    private final String city;
    private final Integer zipCode;

    private final Boolean hasPeanutAllergies;
    private final Boolean hasEggAllergies;
    private final Boolean hasDairyAllergies;

    private MemberDto(String name, String state, String city, Integer zipCode, Boolean hasPeanutAllergies, Boolean hasEggAllergies, Boolean hasDairyAllergies) {
        this.name = name;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.hasPeanutAllergies = hasPeanutAllergies;
        this.hasEggAllergies = hasEggAllergies;
        this.hasDairyAllergies = hasDairyAllergies;
    }

    public static MemberDto of(String name, String state, String city, Integer zipCode, Boolean hasPeanutAllergies, Boolean hasEggAllergies, Boolean hasDairyAllergies) {
        return new MemberDto(name, state, city, zipCode, hasPeanutAllergies, hasEggAllergies, hasDairyAllergies);
    }

    public static MemberDto from(Member member) {
        return MemberDto.of(
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
