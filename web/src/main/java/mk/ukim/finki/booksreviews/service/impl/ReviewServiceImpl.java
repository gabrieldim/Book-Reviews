package mk.ukim.finki.booksreviews.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Review;
import mk.ukim.finki.booksreviews.model.request.ReviewRequest;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.repository.ReviewRepository;
import mk.ukim.finki.booksreviews.repository.ReviewerRepository;
import mk.ukim.finki.booksreviews.service.ReviewService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final ReviewerRepository reviewerRepository;

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Page<Review> findAllPageable(Pageable pageable) {
        return reviewRepository.findAll(pageable);
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
    public Optional<Review> createReview(ReviewRequest reviewRequest, String sentiment) {
        Long rating = reviewRequest.getRating();
        if (Objects.isNull(rating) || rating < 1 || rating > 5) {
            return Optional.empty();
        }
        Review review = reviewRepository.save(Review.of(reviewRequest.getTitle(), reviewRequest.getDescription(),
                reviewRequest.getRating(), reviewRequest.getBookId(), LocalDateTime.now(), sentiment));
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
}
