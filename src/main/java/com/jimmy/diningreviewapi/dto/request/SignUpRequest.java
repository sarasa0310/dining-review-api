package com.jimmy.diningreviewapi.dto.request;

import com.jimmy.diningreviewapi.domain.entity.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public final class SignUpRequest {

    @NotBlank
    private String name;

    private String state;
    private String city;
    private Integer zipCode;

    private Boolean hasPeanutAllergy;
    private Boolean hasEggAllergy;
    private Boolean hasDairyAllergy;

    public Member toEntity() {
        return new Member(name, state, city, zipCode, hasPeanutAllergy, hasEggAllergy, hasDairyAllergy);
    }

}
