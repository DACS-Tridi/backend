package com.dacs.backend.controller;

import com.dacs.backend.dto.ReviewDTO;
import com.dacs.backend.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/today")
    public ResponseEntity<List<ReviewDTO>> getTopReviewsForToday() {
        List<ReviewDTO> reviews = reviewService.getTopReviewsForToday();
        return ResponseEntity.ok(reviews);
    }
    
    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO review) {
        ReviewDTO created = reviewService.createReview(review);
        return ResponseEntity.ok(created);
    }

}
