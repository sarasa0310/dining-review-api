package com.jimmy.diningreviewapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jimmy.diningreviewapi.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class MemberResponse {

    private final Long id;
    private final String name;

    private final String state;
    private final String city;
    private final Integer zipCode;

    private final Boolean hasPeanutAllergies;
    private final Boolean hasEggAllergies;
    private final Boolean hasDairyAllergies;

    public static MemberResponse from(Member entity) {
        return new MemberResponse(
                entity.getId(),
                entity.getName(),
                entity.getState(),
                entity.getCity(),
                entity.getZipCode(),
                entity.getHasPeanutAllergies(),
                entity.getHasEggAllergies(),
                entity.getHasDairyAllergies()
        );
    }

}
