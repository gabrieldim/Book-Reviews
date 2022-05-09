package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.*;
import mk.ukim.finki.booksreviews.model.request.ReviewRequest;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.repository.ReviewRepository;
import mk.ukim.finki.booksreviews.repository.ReviewerRepository;
import mk.ukim.finki.booksreviews.service.impl.ReviewServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReviewServiceImplTest {

    Long bookId = 1L;
    List<Review> reviewList = List.of(
            Review.of("Review Title 1", "Review Description 1", 4L, bookId, LocalDateTime.now()),
            Review.of("Review Title 2", "Review Description 2", 8L, bookId, LocalDateTime.now()),
            Review.of("Review Title 3", "Review Description 3", 5L, bookId, LocalDateTime.now()),
            Review.of("Review Title 4", "Review Description 4", 2L, bookId, LocalDateTime.now()),
            Review.of("Review Title 5", "Review Description 5", 4L, bookId, LocalDateTime.now())
    );

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private ReviewerRepository reviewerRepository;

    private ReviewService reviewService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.reviewService = Mockito.spy(new ReviewServiceImpl(this.reviewRepository, this.bookRepository, this.reviewerRepository));
    }

    @Test
    public void testFindAll() {
        when(reviewRepository.findAll()).thenReturn(List.of()).thenReturn(reviewList);

        assertThat(reviewService.findAll()).isNotNull().isEmpty();
        assertThat(reviewService.findAll()).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(reviewList);
        verify(reviewRepository, times(2)).findAll();
    }

    @Test
    public void testFindAllPageable() {
        Pageable pageable = mock(Pageable.class);
        Page<Review> page = new PageImpl<>(reviewList);
        when(reviewRepository.findAll(pageable)).thenReturn(Page.empty()).thenReturn(page);

        assertThat(reviewService.findAllPageable(pageable)).isNotNull().isEmpty();
        assertThat(reviewService.findAllPageable(pageable)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(page);
        verify(reviewRepository, times(2)).findAll(pageable);
    }

    @Test
    public void testFindAllByBook() {
        Long bookId = 1L;
        List<Review> filteredReviews = List.of(reviewList.get(0));
        when(reviewRepository.findAllByBookId(bookId)).thenReturn(List.of()).thenReturn(filteredReviews);

        assertThat(reviewService.findAllByBook(bookId)).isNotNull().isEmpty();
        assertThat(reviewService.findAllByBook(bookId)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(1).isEqualTo(filteredReviews);
        verify(reviewRepository, times(2)).findAllByBookId(bookId);
    }

    @Test
    public void testFindById() {
        Review filteredReview = reviewList.get(0);
        when(reviewRepository.findById(bookId)).thenReturn(Optional.empty()).thenReturn(Optional.of(filteredReview));

        assertThat(reviewService.findById(bookId)).isNotNull().isNotPresent();
        assertThat(reviewService.findById(bookId)).isNotNull().isPresent().isEqualTo(Optional.of(filteredReview));
        verify(reviewRepository, times(2)).findById(bookId);
    }

    @Test
    public void testCreateReview() {
        Long reviewId = 2L;
        Long reviewerId = 3L;
        Review createdReviewValidRating = reviewList.get(0);
        Review createdReviewInvalidRating = reviewList.get(1);
        Reviewer reviewer = Reviewer.of("Reviewer first name 1", "Reviewer last name 1", "reviewer123@gmail.com", "reviewer1321@yu", "Reviewer favourite quote 1", "Reviewer bio description 1");
        Book book = Book.of("Book 1", "Description 1", "Drama", "https://google.com/book-1", "Book Quote 1", true);
        ReflectionTestUtils.setField(createdReviewValidRating, "id", reviewId);
        ReflectionTestUtils.setField(createdReviewValidRating, "id", reviewId);
        ReflectionTestUtils.setField(book, "id", bookId);
        ReflectionTestUtils.setField(reviewer, "id", reviewerId);
        ReviewRequest reviewRequestValidRating = new ReviewRequest(createdReviewValidRating.getTitle(), createdReviewValidRating.getDescription(), createdReviewValidRating.getRating(), bookId, reviewerId);
        ReviewRequest reviewRequestInvalidRating = new ReviewRequest(createdReviewInvalidRating.getTitle(), createdReviewInvalidRating.getDescription(), createdReviewInvalidRating.getRating(), bookId, reviewerId);

        when(reviewRepository.save(any(Review.class))).thenReturn(createdReviewValidRating).thenReturn(createdReviewInvalidRating);
        when(reviewerRepository.findById(reviewerId)).thenReturn(Optional.of(reviewer));
        when(reviewerRepository.save(any(Reviewer.class))).thenReturn(reviewer);
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        assertThat(reviewService.createReview(reviewRequestValidRating)).isNotNull().isPresent().isEqualTo(Optional.of(createdReviewValidRating));
        assertThat(reviewService.createReview(reviewRequestInvalidRating)).isNotNull().isNotPresent();

        verify(reviewRepository, times(1)).save(any(Review.class));
        verify(reviewerRepository, times(1)).findById(reviewerId);
        verify(reviewerRepository, times(1)).save(any(Reviewer.class));
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(bookRepository, times(1)).findById(bookId);
    }

    @Test
    public void testUpdateReview() {
        Long reviewId = 2L;
        Review createdReviewValidRating = reviewList.get(0);
        Review createdReviewInvalidRating = reviewList.get(2);
        ReviewRequest reviewRequestValidRating = new ReviewRequest("Review request title 1", "Review request description 1", 2L, 10L, 11L);
        ReviewRequest reviewRequestInvalidRating = new ReviewRequest("Review request title 2", "Review request description 2", 10L, 10L, 11L);
        Review updatedReviewValidRating = Review.of(reviewRequestValidRating.getTitle(), reviewRequestValidRating.getDescription(), createdReviewValidRating.getRating(), bookId, createdReviewValidRating.getDate());
        Review updatedReviewInvalidRating = Review.of(reviewRequestInvalidRating.getTitle(), reviewRequestInvalidRating.getDescription(), createdReviewInvalidRating.getRating(), bookId, createdReviewInvalidRating.getDate());

        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(updatedReviewValidRating)).thenReturn(Optional.of(updatedReviewInvalidRating));
        when(reviewRepository.save(any(Review.class))).thenReturn(updatedReviewValidRating).thenReturn(updatedReviewInvalidRating);

        assertThat(reviewService.updateReview(reviewId, reviewRequestValidRating)).isNotNull().isPresent().isEqualTo(Optional.of(updatedReviewValidRating));
        Optional<Review> updatedInvalidRatingReview = reviewService.updateReview(reviewId, reviewRequestInvalidRating);
        assertThat(updatedInvalidRatingReview).isNotNull().isPresent().isEqualTo(updatedInvalidRatingReview);
        assertThat(updatedInvalidRatingReview.get().getRating()).isNotNull().isNotEqualTo(Optional.of(updatedReviewInvalidRating.getRating())).isEqualTo(createdReviewInvalidRating.getRating());

        verify(reviewRepository, times(2)).findById(reviewId);
        verify(reviewRepository, times(1)).save(updatedReviewValidRating);
        verify(reviewRepository, times(1)).save(updatedReviewInvalidRating);
    }
}
