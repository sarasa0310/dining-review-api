package com.jimmy.diningreviewapi.event;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class AdminReviewAction {

    @NotNull
    private Boolean isAcceptable;

}
