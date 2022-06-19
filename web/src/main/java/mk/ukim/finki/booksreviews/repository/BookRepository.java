package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByReviews_Id(Long reviewId);

    @Query("SELECT b FROM Book b WHERE b.title LIKE :searchTerm or b.description LIKE :searchTerm " +
            "or b.genre LIKE :searchTerm or b.quote LIKE :searchTerm")
    List<Book> search(@Param("searchTerm") String searchTerm);

    List<Book> findAllByGenreLike(String genre);
}