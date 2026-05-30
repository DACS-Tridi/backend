package com.dacs.backend.dto;

import java.time.ZonedDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    private Long id;

    private String user; 

    private Long userId;

    private String album;

    private String albumId; 

    private String highlight;

    private String cover;

    private Double rating;

    private Stats stats; 

    private List<String> tags;

    private String tone;

    private String reviewBody;

    private ZonedDateTime postedAt;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Stats {
        private int likes;
        private int comments;
        private int shares;
    }
}
