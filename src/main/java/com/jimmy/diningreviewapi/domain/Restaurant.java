package com.jimmy.diningreviewapi.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(indexes = {
        @Index(columnList = "name"),
        @Index(columnList = "zipCode")
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

    private int flavorScore;
    private int priceScore;
    private int serviceScore;
    private double averageScore;

    private Restaurant(String name, Integer zipCode) {
        this.name = name;
        this.zipCode = zipCode;
    }

    public static Restaurant of(String name, Integer zipCode) {
        return new Restaurant(name, zipCode);
    }

    public void updateScore(DiningReview review) {
        if (review.getFlavorScore() != null) flavorScore += review.getFlavorScore();
        if (review.getPriceScore() != null) priceScore += review.getPriceScore();
        if (review.getServiceScore() != null) serviceScore += review.getServiceScore();

        averageScore = calculateAverageScore();
    }

    private double calculateAverageScore() {
        double average = (double) (flavorScore + priceScore + serviceScore) / 3;

        return (double) Math.round(average * 100) / 100;
    }

}
