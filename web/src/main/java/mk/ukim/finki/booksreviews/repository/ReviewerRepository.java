package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.entity.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {
    List<Reviewer> findAllByReviews_Id(Long reviewId);

    Optional<Reviewer> findByEmail(String email);

    Optional<Reviewer> findByEmailAndPassword(String email, String password);
}