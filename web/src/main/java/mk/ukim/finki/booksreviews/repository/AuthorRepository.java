package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByBooks_Id(Long bookId);

    Optional<Author> findByUser_Email(String email);

    Optional<Author> findByUser_EmailAndUser_Password(String email, String password);
}