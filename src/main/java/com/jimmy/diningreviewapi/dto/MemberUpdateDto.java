package com.jimmy.diningreviewapi.dto;

import lombok.Getter;

@Getter
public final class MemberUpdateDto {

    private String state;
    private String city;
    private Integer zipCode;

    private Boolean hasPeanutAllergies;
    private Boolean hasEggAllergies;
    private Boolean hasDairyAllergies;

}
