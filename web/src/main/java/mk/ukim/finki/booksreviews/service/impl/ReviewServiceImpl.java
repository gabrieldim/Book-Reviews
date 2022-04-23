package mk.ukim.finki.booksreviews.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Review;
import mk.ukim.finki.booksreviews.model.request.ReviewRequest;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.repository.ReviewRepository;
import mk.ukim.finki.booksreviews.repository.ReviewerRepository;
import mk.ukim.finki.booksreviews.service.ReviewService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private BookRepository bookRepository;
    private ReviewerRepository reviewerRepository;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findAllByBook(Long bookId) {
        return reviewRepository.findAllByBookId(bookId);
    }

    @Override
    public Optional<Review> findById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public Optional<Review> createReview(ReviewRequest reviewRequest) {
        Review review = reviewRepository.save(Review.of(reviewRequest.getTitle(), reviewRequest.getDescription(),
                reviewRequest.getRating(), reviewRequest.getBookId(), LocalDateTime.now()));
        bookRepository.findById(reviewRequest.getBookId()).ifPresent(book -> {
            book.addReview(review);
            bookRepository.save(book);
        });
        reviewerRepository.findById(reviewRequest.getReviewerId()).ifPresent(reviewer -> {
            reviewer.addReview(review);
            reviewerRepository.save(reviewer);
        });

        return Optional.of(review);
    }

    @Override
    public Optional<Review> updateReview(Long id, ReviewRequest reviewRequest) {
        return findById(id).map(review -> {
            review.setDescription(StringUtils.isNotBlank(reviewRequest.getDescription())
                    ? reviewRequest.getDescription() : review.getDescription());
            review.setTitle(StringUtils.isNotBlank(reviewRequest.getTitle())
                    ? reviewRequest.getTitle() : review.getTitle());
            review.setBookId(Objects.nonNull(reviewRequest.getBookId())
                    ? reviewRequest.getBookId() : review.getBookId());
            review.setRating(Objects.nonNull(reviewRequest.getRating())
                    ? reviewRequest.getRating() : review.getRating());
            return reviewRepository.save(review);
        });
    }
}
