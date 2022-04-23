package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Author;
import mk.ukim.finki.booksreviews.model.request.AuthorRequest;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();

    List<Author> findAllByBook(Long bookId);

    Optional<Author> findById(Long id);

    Optional<Author> registerAuthor(AuthorRequest authorRequest);

    Optional<Author> loginAuthor(AuthorRequest authorRequest);

    void addBookToAuthor(Long id, Long bookId);
}
