package com.dacs.backend.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long userId;
    private String albumId;
    private String album;
    private String highlight;
    private String imageURL;
    private Double rating;
    private String tone;
    private LocalDateTime postedAt;
    private Integer likes;
    private Integer comments;
    private Integer shares;
    private List<String> tags;
}
