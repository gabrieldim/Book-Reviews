package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.request.BookRequest;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> findAll();

    List<Book> findAllByReview(Long reviewId);

    Optional<Book> findById(Long id);

    Optional<Book> createBook(BookRequest bookRequest);

    Optional<Book> updateBook(Long id, BookRequest bookRequest);

    void addReviewToBook(Long id, Long reviewId);
}
