package mk.ukim.finki.booksreviews.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Reviewer;
import mk.ukim.finki.booksreviews.model.request.ReviewerRequest;
import mk.ukim.finki.booksreviews.service.ReviewerService;
import mk.ukim.finki.booksreviews.util.ApiController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiController
@AllArgsConstructor
@RequestMapping("/api/reviewer")
public class ReviewerController {

    private final ReviewerService reviewerService;

    @GetMapping
    public List<Reviewer> getAllReviewers() {
        return reviewerService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reviewer> getById(@PathVariable Long id) {
        return reviewerService.findById(id)
                .map(reviewer -> ResponseEntity.ok().body(reviewer))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<Reviewer> registerReviewer(@RequestBody @Valid ReviewerRequest reviewerRequest) {
        return reviewerService.registerReviewer(reviewerRequest)
                .map(reviewer -> ResponseEntity.ok().body(reviewer))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Reviewer> loginReviewer(@RequestBody ReviewerRequest reviewerRequest) {
        return reviewerService.loginReviewer(reviewerRequest)
                .map(reviewer -> ResponseEntity.ok().body(reviewer))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/page")
    public Page<Reviewer> getAllReviewersPageable(Pageable pageable) {
        return reviewerService.findAllPageable(pageable);
    }

    @GetMapping("/review/{reviewId}")
    public List<Reviewer> getAllReviewersByReview(@PathVariable Long reviewId) {
        return reviewerService.findAllByReview(reviewId);
    }
}
