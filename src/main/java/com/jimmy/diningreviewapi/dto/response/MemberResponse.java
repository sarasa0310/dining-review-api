package com.jimmy.diningreviewapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmy.diningreviewapi.domain.entity.Member;
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
