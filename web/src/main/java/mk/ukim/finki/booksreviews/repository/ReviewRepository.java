package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
