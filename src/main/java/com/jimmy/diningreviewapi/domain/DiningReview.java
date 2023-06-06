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

    private Integer peanutScore;
    private Integer eggScore;
    private Integer dairyScore;

    private String comment;

    // todo: enum 대기중(waiting), 승인됨(approved), 거절됨(denied) 상태 갖도록 변경
    private boolean approved;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Member member;

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

    public void approve() {
        this.approved = true;
    }

}
