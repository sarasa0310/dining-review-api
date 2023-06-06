package com.jimmy.diningreviewapi.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiningReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer flavorScore;
    private Integer priceScore;
    private Integer serviceScore;

    private String comment;

    // todo: enum 대기중(waiting), 승인됨(approved), 거절됨(denied) 상태 갖도록 변경
    private boolean approved;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Member member;

    private DiningReview(Integer flavorScore, Integer priceScore, Integer serviceScore, String comment, Restaurant restaurant, Member member) {
        this.flavorScore = flavorScore;
        this.priceScore = priceScore;
        this.serviceScore = serviceScore;
        this.comment = comment;
        this.restaurant = restaurant;
        this.member = member;
    }

    public static DiningReview of(Integer flavorScore, Integer priceScore, Integer serviceScore, String comment, Restaurant restaurant, Member member) {
        return new DiningReview(flavorScore, priceScore, serviceScore, comment, restaurant, member);
    }

    public void approve() {
        this.approved = true;
    }

}
