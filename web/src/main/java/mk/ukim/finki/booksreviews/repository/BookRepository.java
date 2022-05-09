package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByReviews_Id(Long reviewId);
}