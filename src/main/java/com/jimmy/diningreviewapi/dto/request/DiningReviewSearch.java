package com.jimmy.diningreviewapi.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DiningReviewSearch {

    private String reviewStatus;
    private String memberName;
    private String restaurantName;

}
