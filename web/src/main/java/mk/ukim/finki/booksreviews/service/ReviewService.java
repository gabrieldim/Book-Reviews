package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Review;
import mk.ukim.finki.booksreviews.model.request.ReviewRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> findAll();

    Page<Review> findAllPageable(Pageable pageable);

    List<Review> findAllByBook(Long bookId);

    Optional<Review> findById(Long id);

    Optional<Review> createReview(ReviewRequest reviewRequest, String sentiment);
}
