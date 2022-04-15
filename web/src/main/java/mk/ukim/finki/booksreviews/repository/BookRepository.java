package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
