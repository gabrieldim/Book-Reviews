package mk.ukim.finki.booksreviews.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.request.BookRequest;
import mk.ukim.finki.booksreviews.repository.AuthorRepository;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.repository.LibraryRepository;
import mk.ukim.finki.booksreviews.repository.ReviewRepository;
import mk.ukim.finki.booksreviews.service.BookService;
import org.apache.commons.lang3.StringUtils;
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
    private final ReviewRepository reviewRepository;
    private final AuthorRepository authorRepository;
    private final LibraryRepository libraryRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
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
        Book book = Book.of(bookRequest.getTitle(), bookRequest.getDescription(), bookRequest.getGenre(),
                bookRequest.getPreviewLink(), bookRequest.getQuote(), bookRequest.getAvailability());
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
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public Optional<Book> updateBook(Long id, BookRequest bookRequest) {
        return findById(id).map(book -> {
            book.setDescription(StringUtils.isNotBlank(bookRequest.getDescription())
                    ? bookRequest.getDescription() : book.getDescription());
            book.setAvailability(Objects.nonNull(bookRequest.getAvailability())
                    ? bookRequest.getAvailability() : book.getAvailability());
            book.setGenre(StringUtils.isNotBlank(bookRequest.getGenre())
                    ? bookRequest.getGenre() : book.getGenre());
            book.setQuote(StringUtils.isNotBlank(bookRequest.getQuote())
                    ? bookRequest.getQuote() : book.getQuote());
            book.setPreviewLink(StringUtils.isNotBlank(bookRequest.getPreviewLink())
                    ? bookRequest.getPreviewLink() : book.getPreviewLink());
            book.setTitle(StringUtils.isNotBlank(bookRequest.getTitle())
                    ? bookRequest.getTitle() : book.getTitle());
            return bookRepository.save(book);
        });
    }

    @Override
    public void addReviewToBook(Long id, Long reviewId) {
        findById(id).ifPresent(book -> reviewRepository.findById(reviewId).ifPresent(review -> {
            book.addReview(review);
            bookRepository.save(book);
        }));
    }
}
