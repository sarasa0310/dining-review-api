package com.jimmy.diningreviewapi.dto.request;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public final class MemberUpdate {

    private String state;
    private String city;
    @Positive
    private Integer zipCode;

    private Boolean hasPeanutAllergies;
    private Boolean hasEggAllergies;
    private Boolean hasDairyAllergies;

}
