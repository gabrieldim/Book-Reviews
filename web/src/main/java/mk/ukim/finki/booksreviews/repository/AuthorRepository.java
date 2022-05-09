package mk.ukim.finki.booksreviews.repository;

import mk.ukim.finki.booksreviews.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAllByBooks_Id(Long bookId);

    Optional<Author> findByEmail(String email);

    Optional<Author> findByEmailAndPassword(String email, String password);
}