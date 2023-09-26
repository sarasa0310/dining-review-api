package com.jimmy.diningreviewapi.domain.entity;

import com.jimmy.diningreviewapi.domain.value.Score;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "zipCode"),
        @Index(columnList = "averageScore")
})
@NoArgsConstructor(access = PROTECTED)
public class Restaurant {

    @Column(name = "restaurant_id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer zipCode;

    @Embedded
    private Score score;
    private double averageScore;

    public Restaurant(String name, Integer zipCode) {
        this.name = name;
        this.zipCode = zipCode;
        this.score = new Score();
    }

    public void updateScore(DiningReview review) {
        if (review.getFlavorScore() != null) {
            score.setFlavorScore(score.getFlavorScore() + review.getFlavorScore());
        }
        if (review.getPriceScore() != null) {
            score.setPriceScore(score.getPriceScore() + review.getPriceScore());
        }
        if (review.getServiceScore() != null) {
            score.setServiceScore(score.getServiceScore() + review.getServiceScore());
        }

        averageScore = calculateAverageScore();
    }

    private double calculateAverageScore() {
        double average = (double) (score.getFlavorScore() + score.getPriceScore() + score.getServiceScore()) / 3;

        return (double) Math.round(average * 100) / 100;
    }

}
