package com.dacs.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dacs.backend.dto.ReviewDTO;
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
        Optional<Review> review = getById(id);
        review.ifPresent(reviewRepository::delete);
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
                .map(r -> new ReviewDTO(
                        r.getId(),
                        r.getUserId(),
                        r.getAlbumId(),
                        r.getAlbum(),
                        r.getHighlight(),
                        r.getImageURL(),
                        r.getRating(),
                        r.getTone(),
                        r.getPostedAt(),
                        r.getLikes(),
                        r.getComments(),
                        r.getShares(),
                        r.getTags()
                ))
                .collect(Collectors.toList());
    }
}
