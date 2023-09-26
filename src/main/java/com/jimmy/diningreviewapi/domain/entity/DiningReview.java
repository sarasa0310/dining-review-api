package com.jimmy.diningreviewapi.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class DiningReview {

    @Column(name = "dining_review_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer flavorScore;
    private Integer priceScore;
    private Integer serviceScore;

    private String comment;

    @Enumerated(EnumType.STRING)
    private Status status = Status.WAITING;

    public enum Status {
        WAITING, APPROVED, DENIED
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public DiningReview(Integer flavorScore, Integer priceScore, Integer serviceScore, String comment, Restaurant restaurant, Member member) {
        this.flavorScore = flavorScore;
        this.priceScore = priceScore;
        this.serviceScore = serviceScore;
        this.comment = comment;
        this.restaurant = restaurant;
        this.member = member;
    }

    public void approve() {
        this.status = Status.APPROVED;
    }

    public void deny() {
        this.status = Status.DENIED;
    }

}
