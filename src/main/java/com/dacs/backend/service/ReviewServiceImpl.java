package com.dacs.backend.service;

import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacs.backend.dto.AlbumReviewsResponseDTO;
import com.dacs.backend.dto.ReviewDTO;
import com.dacs.backend.dto.ReviewDTO.Stats;
import com.dacs.backend.model.entity.Review;
import com.dacs.backend.model.entity.User;
import com.dacs.backend.model.repository.ReviewRepository;
import com.dacs.backend.model.repository.UserRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

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
                .map(r -> toDTO(r))
                .collect(Collectors.toList());
    }

    @Override
    public AlbumReviewsResponseDTO getReviewsByAlbumId(String albumId) {
        List<Review> reviews = reviewRepository.findByAlbumIdOrderByPostedAtDesc(albumId);

        List<ReviewDTO> dtos = reviews.stream()
                .map(r -> toDTO(r))
                .collect(Collectors.toList());

        double avg = reviews.stream()
                .filter(r -> r.getRating() != null)
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);

        return new AlbumReviewsResponseDTO(albumId, avg, reviews.size(), dtos);
    }

    @Override
    public List<ReviewDTO> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserIdOrderByPostedAtDesc(userId)
                .stream()
                .map(r -> toDTO(r))
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
        review.setReviewBody(reviewDTO.getReviewBody());
        review.setPostedAt(java.time.LocalDateTime.now());
        review.setLikes(0);
        review.setComments(0);
        review.setShares(0);

        Review saved = reviewRepository.save(review);
        return toDTO(saved);
    }

    private ReviewDTO toDTO(Review r) {
        String username = null;
        if (r.getUserId() != null) {
            Optional<User> user = userRepository.findById(r.getUserId());
            username = user.map(User::getUserName).orElse(null);
        }

        Stats stats = new Stats(
                r.getLikes() != null ? r.getLikes() : 0,
                r.getComments() != null ? r.getComments() : 0,
                r.getShares() != null ? r.getShares() : 0
        );

        ReviewDTO dto = new ReviewDTO();
        dto.setId(r.getId());
        dto.setUser(username);
        dto.setUserId(r.getUserId());
        dto.setAlbum(r.getAlbum());
        dto.setAlbumId(r.getAlbumId());
        dto.setHighlight(r.getHighlight());
        dto.setCover(r.getImageURL());
        dto.setRating(r.getRating());
        dto.setStats(stats);
        dto.setTags(r.getTags());
        dto.setTone(r.getTone());
        dto.setReviewBody(r.getReviewBody());
        dto.setPostedAt(r.getPostedAt() != null ? r.getPostedAt().atZone(ZoneId.systemDefault()) : null);
        return dto;
    }
}
