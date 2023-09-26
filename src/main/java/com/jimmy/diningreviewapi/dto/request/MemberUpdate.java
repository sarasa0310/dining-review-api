package com.jimmy.diningreviewapi.dto.request;

import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
public class MemberUpdate {

    private String state;
    private String city;
    @Positive
    private Integer zipCode;

    private Boolean hasPeanutAllergy;
    private Boolean hasEggAllergy;
    private Boolean hasDairyAllergy;

}
