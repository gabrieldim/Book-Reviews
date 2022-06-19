package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Author;
import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.entity.Library;
import mk.ukim.finki.booksreviews.model.entity.User;
import mk.ukim.finki.booksreviews.model.request.BookRequest;
import mk.ukim.finki.booksreviews.repository.AuthorRepository;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.repository.LibraryRepository;
import mk.ukim.finki.booksreviews.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    List<Book> bookList = List.of(
            Book.of("Book 1", "Description 1", "Drama", "https://google.com/book-1", "Book Quote 1", true),
            Book.of("Book 2", "Description 2", "Thriller", "https://google.com/book-2", "Book Quote 2", false),
            Book.of("Book 3", "Description 3", "Drama", "https://google.com/book-3", "Book Quote 3", true),
            Book.of("Book 4", "Description 4", "Thriller", "https://google.com/book-4", "Book Quote 4", false),
            Book.of("Book 5", "Description 5", "Thriller", "https://google.com/book-5", "Book Quote 5", false)
    );

    User author = Author.of("Nikola", "Stanojkovski", "stanojkovski@gmail.com",
            "nikolaS@123", 31, LocalDateTime.now(), "Some bio", "Nicksta");
    Library library = Library.of("location", "name");

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private LibraryRepository libraryRepository;

    private BookService bookService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.bookService = Mockito.spy(new BookServiceImpl(this.bookRepository, this.authorRepository, this.libraryRepository));
    }

    @Test
    public void testFindAll() {
        when(bookRepository.findAll()).thenReturn(List.of()).thenReturn(bookList);

        assertThat(bookService.findAll()).isNotNull().isEmpty();
        assertThat(bookService.findAll()).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(bookList);
        verify(bookRepository, times(2)).findAll();
    }

    @Test
    public void testFindAllPageable() {
        Pageable pageable = mock(Pageable.class);
        Page<Book> page = new PageImpl<>(bookList);
        when(bookRepository.findAll(pageable)).thenReturn(Page.empty()).thenReturn(page);

        assertThat(bookService.findAllPageable(pageable)).isNotNull().isEmpty();
        assertThat(bookService.findAllPageable(pageable)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(page);
        verify(bookRepository, times(2)).findAll(pageable);
    }

    @Test
    public void testSearchBooks() {
        String searchTerm = "Book 3";
        when(bookRepository.search(anyString())).thenReturn(List.of()).thenReturn(List.of(bookList.get(2)));

        assertThat(bookService.searchBooks(searchTerm)).isNotNull().isEmpty();
        assertThat(bookService.searchBooks(searchTerm)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(1).isEqualTo(List.of(bookList.get(2)));
        verify(bookRepository, times(2)).search(String.format("%%%s%%", searchTerm));
    }

    @Test
    public void testFindAllByGenre() {
        String genreFilter = "Thriller";
        List<Book> filteredList = List.of(bookList.get(1), bookList.get(3), bookList.get(4));
        when(bookRepository.findAllByGenreLike(anyString())).thenReturn(List.of()).thenReturn(filteredList);

        assertThat(bookService.findAllByGenre(genreFilter)).isNotNull().isEmpty();
        assertThat(bookService.findAllByGenre(genreFilter)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(3).isEqualTo(filteredList);
        verify(bookRepository, times(2)).findAllByGenreLike(String.format("%%%s%%", genreFilter));
    }

    @Test
    public void testFindAllByReview() {
        Long reviewId = 1L;
        List<Book> filteredBooks = List.of(bookList.get(0));
        when(bookRepository.findAllByReviews_Id(reviewId)).thenReturn(List.of()).thenReturn(filteredBooks);

        assertThat(bookService.findAllByReview(reviewId)).isNotNull().isEmpty();
        assertThat(bookService.findAllByReview(reviewId)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(1).isEqualTo(filteredBooks);
        verify(bookRepository, times(2)).findAllByReviews_Id(reviewId);
    }

    @Test
    public void testFindById() {
        Long bookId = 1L;
        Book filteredBook = bookList.get(0);
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty()).thenReturn(Optional.of(filteredBook));

        assertThat(bookService.findById(bookId)).isNotNull().isNotPresent();
        assertThat(bookService.findById(bookId)).isNotNull().isPresent().isEqualTo(Optional.of(filteredBook));
        verify(bookRepository, times(2)).findById(bookId);
    }

    @Test
    public void testCreateBook() {
        Long bookId = 1L;
        Long authorId = 2L;
        Long libraryId = 3L;
        Book createdBookWithoutLibrary = bookList.get(0);
        Book createdBookWithLibrary = bookList.get(1);
        ReflectionTestUtils.setField(createdBookWithoutLibrary, "id", bookId);
        ReflectionTestUtils.setField(createdBookWithLibrary, "id", bookId);
        ReflectionTestUtils.setField(author, "id", authorId);
        ReflectionTestUtils.setField(library, "id", libraryId);
        BookRequest bookRequestWithoutLibrary = new BookRequest(createdBookWithoutLibrary.getTitle(), createdBookWithoutLibrary.getDescription(), createdBookWithoutLibrary.getGenre(), createdBookWithoutLibrary.getPictureLink(), createdBookWithoutLibrary.getQuote(), createdBookWithoutLibrary.getAvailability(), authorId, null);
        BookRequest bookRequestWithLibrary = new BookRequest(createdBookWithoutLibrary.getTitle(), createdBookWithoutLibrary.getDescription(), createdBookWithoutLibrary.getGenre(), createdBookWithoutLibrary.getPictureLink(), createdBookWithoutLibrary.getQuote(), createdBookWithoutLibrary.getAvailability(), authorId, libraryId);

        when(bookRepository.save(any(Book.class))).thenReturn(createdBookWithoutLibrary).thenReturn(createdBookWithLibrary);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of((Author) author));
        when(authorRepository.save(any(Author.class))).thenReturn((Author) author);
        when(libraryRepository.findById(libraryId)).thenReturn(Optional.of(library));
        when(libraryRepository.save(any(Library.class))).thenReturn(library);

        assertThat(bookService.createBook(bookRequestWithoutLibrary)).isNotNull().isPresent().isEqualTo(Optional.of(createdBookWithoutLibrary));
        assertThat(bookService.createBook(bookRequestWithLibrary)).isNotNull().isPresent().isEqualTo(Optional.of(createdBookWithLibrary));

        verify(bookRepository, times(2)).save(any(Book.class));
        verify(authorRepository, times(2)).findById(authorId);
        verify(authorRepository, times(2)).save(any(Author.class));
        verify(libraryRepository, times(1)).save(any(Library.class));
        verify(libraryRepository, times(1)).findById(libraryId);
    }

    @Test
    public void testUpdateBook() {
        Long bookId = 1L;
        Book createdBook = bookList.get(0);
        BookRequest bookRequest = new BookRequest("title", "description", "genre", "pictureLink", "quote", false, 3L, null);
        Book updatedBook = Book.of(bookRequest.getTitle(), bookRequest.getDescription(), bookRequest.getGenre(), bookRequest.getPictureLink(), bookRequest.getQuote(), bookRequest.getAvailability());

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(createdBook));
        when(bookRepository.save(any(Book.class))).thenReturn(updatedBook);

        assertThat(bookService.updateBook(bookId, bookRequest)).isNotNull().isPresent().isEqualTo(Optional.of(updatedBook));

        verify(bookRepository, times(1)).findById(bookId);
        verify(bookRepository, times(1)).save(updatedBook);
    }
}
