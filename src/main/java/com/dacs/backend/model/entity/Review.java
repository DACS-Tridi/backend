package com.dacs.backend.model.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reviews")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(length = 10000)
    private String reviewBody;

    @ElementCollection
    private List<String> tags;
}
