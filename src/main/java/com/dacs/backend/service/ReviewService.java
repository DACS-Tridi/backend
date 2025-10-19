package com.dacs.backend.service;

import com.dacs.backend.dto.ReviewDTO;
import com.dacs.backend.model.entity.Review;
import java.util.List;

public interface ReviewService extends CommonService<Review> {
    List<ReviewDTO> getTopReviewsForToday();
}
