package mk.ukim.finki.booksreviews.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Review;
import mk.ukim.finki.booksreviews.model.request.ReviewRequest;
import mk.ukim.finki.booksreviews.service.ReviewService;
import mk.ukim.finki.booksreviews.util.ApiController;
import mk.ukim.finki.booksreviews.xport.client.SentimentGeneratorClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiController
@AllArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final SentimentGeneratorClient sentimentGeneratorClient;
    private final ReviewService reviewService;

    @GetMapping
    public List<Review> getAllReviews() {
        return reviewService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getById(@PathVariable Long id) {
        return reviewService.findById(id)
                .map(review -> ResponseEntity.ok().body(review))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Review> createReview(@RequestBody @Valid ReviewRequest reviewRequest) {
        String reviewSentiment = sentimentGeneratorClient.generateSentiment(reviewRequest.getDescription());

        return (StringUtils.isNotBlank(reviewSentiment)) ?
                reviewService.createReview(reviewRequest, reviewSentiment)
                .map(review -> ResponseEntity.ok().body(review))
                .orElseGet(() -> ResponseEntity.notFound().build()) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/page")
    public Page<Review> getAllReviewsPageable(Pageable pageable) {
        return reviewService.findAllPageable(pageable);
    }

    @GetMapping("/book/{bookId}")
    public List<Review> getAllReviewsByBook(@PathVariable Long bookId) {
        return reviewService.findAllByBook(bookId);
    }
}
