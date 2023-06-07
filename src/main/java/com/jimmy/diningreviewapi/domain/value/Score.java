package com.jimmy.diningreviewapi.domain.value;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
public class Score {

    private int flavor;
    private int price;
    private int service;

}
