package com.jimmy.diningreviewapi.domain.entity;

import com.jimmy.diningreviewapi.domain.value.Score;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "zipCode"),
        @Index(columnList = "averageScore")
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer zipCode;

    @Embedded
    private Score score;
    private double averageScore;

    private Restaurant(String name, Integer zipCode) {
        this.name = name;
        this.zipCode = zipCode;
        this.score = new Score();
    }

    public static Restaurant of(String name, Integer zipCode) {
        return new Restaurant(name, zipCode);
    }

    public void updateScore(DiningReview review) {
        if (review.getFlavorScore() != null) {
            score.setFlavor(score.getFlavor() + review.getFlavorScore());
        }
        if (review.getPriceScore() != null) {
            score.setPrice(score.getPrice() + review.getPriceScore());
        }
        if (review.getServiceScore() != null) {
            score.setService(score.getService() + review.getServiceScore());
        }

        averageScore = calculateAverageScore();
    }

    private double calculateAverageScore() {
        double average = (double) (score.getFlavor() + score.getPrice() + score.getService()) / 3;

        return (double) Math.round(average * 100) / 100;
    }

}
