package mk.ukim.finki.booksreviews.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.request.BookRequest;
import mk.ukim.finki.booksreviews.repository.AuthorRepository;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.repository.LibraryRepository;
import mk.ukim.finki.booksreviews.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final LibraryRepository libraryRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllByGenre(String genre) {
        return bookRepository.findAllByGenreLike(String.format("%%%s%%", genre));
    }

    @Override
    public List<Book> searchBooks(String searchTerm) {
        return bookRepository.search(String.format("%%%s%%", searchTerm));
    }

    @Override
    public Page<Book> findAllPageable(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> findAllByReview(Long reviewId) {
        return bookRepository.findAllByReviews_Id(reviewId);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> createBook(BookRequest bookRequest) {
        Book book = bookRepository.save(Book.of(bookRequest.getTitle(), bookRequest.getDescription(), bookRequest.getGenre(),
                bookRequest.getPictureLink(), bookRequest.getQuote(), bookRequest.getAvailability()));
        authorRepository.findById(bookRequest.getAuthorId()).ifPresent(author -> {
            author.addBook(book);
            authorRepository.save(author);
        });
        if (Objects.nonNull(bookRequest.getLibraryId())) {
            libraryRepository.findById(bookRequest.getLibraryId()).ifPresent(library -> {
                library.addBook(book);
                libraryRepository.save(library);
            });
        }

        return Optional.of(book);
    }

    @Override
    public Optional<Book> updateBook(Long id, BookRequest bookRequest) {
        return findById(id).map(book -> {
            book.setTitle(bookRequest.getTitle());
            book.setDescription(bookRequest.getDescription());
            book.setGenre(bookRequest.getGenre());
            book.setQuote(bookRequest.getQuote());
            book.setPictureLink(bookRequest.getPictureLink());
            book.setAvailability(bookRequest.getAvailability());
            return bookRepository.save(book);
        });
    }
}
