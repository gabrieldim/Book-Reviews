package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Review;
import mk.ukim.finki.booksreviews.model.request.ReviewRequest;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> findAll();

    List<Review> findAllByBook(Long bookId);

    Optional<Review> findById(Long id);

    Optional<Review> createReview(ReviewRequest reviewRequest);

    Optional<Review> updateReview(Long id, ReviewRequest reviewRequest);
}
