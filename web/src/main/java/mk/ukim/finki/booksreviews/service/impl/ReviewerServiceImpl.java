package mk.ukim.finki.booksreviews.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Reviewer;
import mk.ukim.finki.booksreviews.model.request.ReviewerRequest;
import mk.ukim.finki.booksreviews.repository.ReviewerRepository;
import mk.ukim.finki.booksreviews.service.ReviewerService;
import mk.ukim.finki.booksreviews.util.ValidationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ReviewerServiceImpl implements ReviewerService {

    private final ReviewerRepository reviewerRepository;

    @Override
    public List<Reviewer> findAll() {
        return reviewerRepository.findAll();
    }

    @Override
    public Page<Reviewer> findAllPageable(Pageable pageable) {
        return reviewerRepository.findAll(pageable);
    }

    @Override
    public List<Reviewer> findAllByReview(Long reviewId) {
        return reviewerRepository.findAllByReviews_Id(reviewId);
    }

    @Override
    public Optional<Reviewer> findById(Long id) {
        return reviewerRepository.findById(id);
    }

    @Override
    public Optional<Reviewer> registerReviewer(ReviewerRequest reviewerRequest) {
        if (!ValidationUtils.isValidUser(reviewerRequest.getEmail(), reviewerRequest.getPassword())) {
            return Optional.empty();
        }
        if (reviewerRepository.findByEmail(reviewerRequest.getEmail()).isPresent()) {
            return Optional.empty();
        }
        Reviewer reviewer = Reviewer.of(reviewerRequest.getFirstName(), reviewerRequest.getLastName(),
                reviewerRequest.getEmail(), reviewerRequest.getPassword(),
                reviewerRequest.getFavoriteQuote(), reviewerRequest.getBioDescription());

        return Optional.of(reviewerRepository.save(reviewer));
    }

    @Override
    public Optional<Reviewer> loginReviewer(ReviewerRequest reviewerRequest) {
        return reviewerRepository.findByEmailAndPassword(reviewerRequest.getEmail(), reviewerRequest.getPassword());
    }
}
