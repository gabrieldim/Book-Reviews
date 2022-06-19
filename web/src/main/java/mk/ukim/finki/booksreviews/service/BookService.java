package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.request.BookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    List<Book> findAllByGenre(String genre);

    List<Book> searchBooks(String searchTerm);

    Page<Book> findAllPageable(Pageable pageable);

    List<Book> findAllByReview(Long reviewId);

    Optional<Book> findById(Long id);

    Optional<Book> createBook(BookRequest bookRequest);

    Optional<Book> updateBook(Long id, BookRequest bookRequest);
}
