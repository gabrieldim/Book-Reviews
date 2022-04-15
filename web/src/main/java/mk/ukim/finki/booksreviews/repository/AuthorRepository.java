package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
