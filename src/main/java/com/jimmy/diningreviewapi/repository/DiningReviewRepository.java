package com.jimmy.diningreviewapi.repository;

import com.jimmy.diningreviewapi.domain.DiningReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiningReviewRepository extends JpaRepository<DiningReview, Long> {
}
