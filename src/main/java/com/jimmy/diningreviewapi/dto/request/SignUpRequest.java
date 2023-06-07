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

    private Boolean hasPeanutAllergies;
    private Boolean hasEggAllergies;
    private Boolean hasDairyAllergies;

    public Member toEntity() {
        return Member.of(name, state, city, zipCode, hasPeanutAllergies, hasEggAllergies, hasDairyAllergies);
    }

}
