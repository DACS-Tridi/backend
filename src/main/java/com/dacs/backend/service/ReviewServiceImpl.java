package com.dacs.backend.service;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacs.backend.dto.ReviewDTO;
import com.dacs.backend.dto.ReviewDTO.Stats;
import com.dacs.backend.model.entity.Review;
import com.dacs.backend.model.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Optional<Review> getById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> getAll() {
        return reviewRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        getById(id).ifPresent(reviewRepository::delete);
    }

    @Override
    public Review save(Review entity) {
        return reviewRepository.save(entity);
    }

    @Override
    public List<Review> find(Map<String, Object> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Review getBy(Map<String, Object> filter) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean existById(Long id) {
        return reviewRepository.existsById(id);
    }

    @Override
    public List<ReviewDTO> getTopReviewsForToday() {
        return reviewRepository.findAllOrderByPostedAtDesc()
                .stream()
                .map(r -> {
                    Stats stats = new Stats(r.getLikes(), r.getComments(), r.getShares());

                    ReviewDTO dto = new ReviewDTO();
                    dto.setId(r.getId());
                    dto.setUser(null); 
                    dto.setUserId(r.getUserId());
                    dto.setAlbum(r.getAlbum());
                    dto.setAlbumId(r.getAlbumId());
                    dto.setHighlight(r.getHighlight());
                    dto.setCover(r.getImageURL());
                    dto.setRating(r.getRating());
                    dto.setStats(stats);
                    dto.setTags(r.getTags());
                    dto.setTone(r.getTone());
                    dto.setPostedAt(r.getPostedAt().atZone(ZoneId.systemDefault()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setUserId(reviewDTO.getUserId());
        review.setAlbumId(reviewDTO.getAlbumId());
        review.setAlbum(reviewDTO.getAlbum());
        review.setHighlight(reviewDTO.getHighlight());
        review.setImageURL(reviewDTO.getCover());
        review.setRating(reviewDTO.getRating());
        review.setTone(reviewDTO.getTone());
        review.setTags(reviewDTO.getTags());
        review.setPostedAt(java.time.LocalDateTime.now());
        review.setLikes(0);
        review.setComments(0);
        review.setShares(0);

        Review saved = reviewRepository.save(review);

        Stats stats = new Stats(saved.getLikes(), saved.getComments(), saved.getShares());

        ReviewDTO dto = new ReviewDTO();
        dto.setId(saved.getId());
        dto.setUser(null);
        dto.setUserId(saved.getUserId());
        dto.setAlbum(saved.getAlbum());
        dto.setAlbumId(saved.getAlbumId());
        dto.setHighlight(saved.getHighlight());
        dto.setCover(saved.getImageURL());
        dto.setRating(saved.getRating());
        dto.setStats(stats);
        dto.setTags(saved.getTags());
        dto.setTone(saved.getTone());
        dto.setPostedAt(saved.getPostedAt().atZone(ZoneId.systemDefault()));

        return dto;
    }
}
