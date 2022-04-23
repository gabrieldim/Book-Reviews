package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.entity.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {
    List<Reviewer> findAllByReviews_Id(Long reviewId);

    Optional<Reviewer> findByUser_Email(String email);

    Optional<Reviewer> findByUser_EmailAndUser_Password(String email, String password);
}