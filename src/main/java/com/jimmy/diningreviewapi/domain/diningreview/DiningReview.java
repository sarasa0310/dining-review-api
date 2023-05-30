package com.jimmy.diningreviewapi.diningreview;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class DiningReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;

    private String comment;

    private Restaurant restaurant;

    private Member member;

    protected DiningReview() {
    }

    private DiningReview(Integer peanutScore, Integer eggScore, Integer dairyScore, String comment, Restaurant restaurant, Member member) {
        this.peanutScore = peanutScore;
        this.eggScore = eggScore;
        this.dairyScore = dairyScore;
        this.comment = comment;
        this.restaurant = restaurant;
        this.member = member;
    }

    public static DiningReview of(Integer peanutScore, Integer eggScore, Integer dairyScore, String comment, Restaurant restaurant, Member member) {
        return new DiningReview(peanutScore, eggScore, dairyScore, comment, restaurant, member);
    }

}
