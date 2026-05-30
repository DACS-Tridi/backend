package com.dacs.backend.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumReviewsResponseDTO {

    private String albumId;
    private Double averageRating;
    private int totalReviews;
    private List<ReviewDTO> reviews;
}
