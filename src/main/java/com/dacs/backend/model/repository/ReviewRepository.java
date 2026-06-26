package com.dacs.backend.model.repository;

import com.dacs.backend.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r ORDER BY r.postedAt DESC")
    List<Review> findAllOrderByPostedAtDesc();

    List<Review> findByAlbumIdOrderByPostedAtDesc(String albumId);

    List<Review> findByUserIdOrderByPostedAtDesc(Long userId);
}
