package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.Reviewer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewerRepository extends JpaRepository<Reviewer, Long> {

}
