package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Author;
import mk.ukim.finki.booksreviews.model.request.AuthorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    Page<Author> findAllPageable(Pageable pageable);

    List<Author> findAllByBook(Long bookId);

    Optional<Author> findById(Long id);

    Optional<Author> registerAuthor(AuthorRequest authorRequest);

    Optional<Author> loginAuthor(AuthorRequest authorRequest);

    Optional<Author> addBookToAuthor(Long id, Long bookId);
}
