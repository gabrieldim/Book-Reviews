package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Reviewer;
import mk.ukim.finki.booksreviews.model.request.ReviewerRequest;

import java.util.List;
import java.util.Optional;

public interface ReviewerService {

    List<Reviewer> findAll();

    List<Reviewer> findAllByReview(Long reviewId);

    Optional<Reviewer> findById(Long id);

    Optional<Reviewer> registerReviewer(ReviewerRequest reviewerRequest);

    Optional<Reviewer> loginReviewer(ReviewerRequest reviewerRequest);

    void addReviewToReviewer(Long id, Long reviewId);
}
