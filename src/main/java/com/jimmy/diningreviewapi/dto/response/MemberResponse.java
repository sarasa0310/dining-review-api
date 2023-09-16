package com.jimmy.diningreviewapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmy.diningreviewapi.domain.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

    private final Long memberId;
    private final String name;

    private final String state;
    private final String city;
    private final Integer zipCode;

    private final Boolean hasPeanutAllergy;
    private final Boolean hasEggAllergy;
    private final Boolean hasDairyAllergy;

    @QueryProjection
    public MemberResponse(Long memberId, String name, String state, String city, Integer zipCode, Boolean hasPeanutAllergy, Boolean hasEggAllergy, Boolean hasDairyAllergy) {
        this.memberId = memberId;
        this.name = name;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.hasPeanutAllergy = hasPeanutAllergy;
        this.hasEggAllergy = hasEggAllergy;
        this.hasDairyAllergy = hasDairyAllergy;
    }

    public static MemberResponse toResponse(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getState(),
                member.getCity(),
                member.getZipCode(),
                member.hasPeanutAllergy(),
                member.hasEggAllergy(),
                member.hasDairyAllergy()
        );
    }

}
