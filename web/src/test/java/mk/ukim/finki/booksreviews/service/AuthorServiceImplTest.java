package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Author;
import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.request.AuthorRequest;
import mk.ukim.finki.booksreviews.repository.AuthorRepository;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.service.impl.AuthorServiceImpl;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {

    List<Author> authorList = List.of(
            Author.of("Nikola", "Stanojkovski", "stanojkovski@gmail.com", "nikolaS@123", 31, LocalDateTime.now(), "Some bio", "Nicksta"),
            Author.of("Gabriel", "Dimitrievski", "dimitrievski@gmail.com", "dimitR1ev123", 25, LocalDateTime.now(), "Some bio", "Gabsta"),
            Author.of("Jovana", "Kocevska", "kocevska@gmail.com", "kovecsk@1a", 22, LocalDateTime.now(), "Some bio", "Jocksta"),
            Author.of("Petar", "Bujukovski", "bujukovski@gmail.com", "buko@vsk123", 15, LocalDateTime.now(), "Some bio", "Peksta"),
            Author.of("Merxhan", "Bajrami", "stanojkovski@gmail.com", "stan0s123", 63, LocalDateTime.now(), "Some bio", "Mocksta")
    );

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    private AuthorService authorService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.authorService = Mockito.spy(new AuthorServiceImpl(this.authorRepository, this.bookRepository));
    }

    @Test
    public void testFindAll() {
        when(authorRepository.findAll()).thenReturn(List.of()).thenReturn(authorList);

        assertThat(authorService.findAll()).isNotNull().isEmpty();
        assertThat(authorService.findAll()).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(authorList);
        verify(authorRepository, times(2)).findAll();
    }

    @Test
    public void testFindAllPageable() {
        Pageable pageable = mock(Pageable.class);
        Page<Author> page = new PageImpl<>(authorList);
        when(authorRepository.findAll(pageable)).thenReturn(Page.empty()).thenReturn(page);

        assertThat(authorService.findAllPageable(pageable)).isNotNull().isEmpty();
        assertThat(authorService.findAllPageable(pageable)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(page);
        verify(authorRepository, times(2)).findAll(pageable);
    }

    @Test
    public void testFindAllByBook() {
        Long bookId = 1L;
        List<Author> filteredAuthors = List.of(authorList.get(0));
        when(authorRepository.findAllByBooks_Id(bookId)).thenReturn(List.of()).thenReturn(filteredAuthors);

        assertThat(authorService.findAllByBook(bookId)).isNotNull().isEmpty();
        assertThat(authorService.findAllByBook(bookId)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(1).isEqualTo(filteredAuthors);
        verify(authorRepository, times(2)).findAllByBooks_Id(bookId);
    }

    @Test
    public void testFindById() {
        Long bookId = 1L;
        Author filteredAuthor = authorList.get(0);
        when(authorRepository.findById(bookId)).thenReturn(Optional.empty()).thenReturn(Optional.of(filteredAuthor));

        assertThat(authorService.findById(bookId)).isNotNull().isNotPresent();
        assertThat(authorService.findById(bookId)).isNotNull().isPresent().isEqualTo(Optional.of(filteredAuthor));
        verify(authorRepository, times(2)).findById(bookId);
    }

    @Test
    public void testRegisterAuthor() {
        Author registeredAuthor = authorList.get(0);
        when(authorRepository.save(Mockito.any(Author.class))).thenReturn(registeredAuthor);
        when(authorRepository.findByEmail(anyString())).thenReturn(Optional.of(authorList.get(1))).thenReturn(Optional.empty());

        AuthorRequest invalidEmailPasswordAuthorRequest = new AuthorRequest(registeredAuthor.getFirstName(), registeredAuthor.getLastName(),
                "invalidemail", "invalidpassword", registeredAuthor.getAge(), registeredAuthor.getBirthDate(),
                registeredAuthor.getBioDescription(), registeredAuthor.getArtName());
        assertThat(authorService.registerAuthor(invalidEmailPasswordAuthorRequest)).isNotNull().isNotPresent();

        AuthorRequest invalidEmailAuthorRequest = new AuthorRequest(registeredAuthor.getFirstName(), registeredAuthor.getLastName(),
                authorList.get(1).getEmail(), registeredAuthor.getPassword(), registeredAuthor.getAge(), registeredAuthor.getBirthDate(),
                registeredAuthor.getBioDescription(), registeredAuthor.getArtName());
        assertThat(authorService.registerAuthor(invalidEmailAuthorRequest)).isNotNull().isNotPresent();

        AuthorRequest validAuthorRequest = new AuthorRequest(registeredAuthor.getFirstName(), registeredAuthor.getLastName(),
                registeredAuthor.getEmail(), registeredAuthor.getPassword(), registeredAuthor.getAge(), registeredAuthor.getBirthDate(),
                registeredAuthor.getBioDescription(), registeredAuthor.getArtName());
        assertThat(authorService.registerAuthor(validAuthorRequest)).isNotNull().isPresent().isEqualTo(Optional.of(registeredAuthor));

        verify(authorRepository, times(1)).findByEmail(registeredAuthor.getEmail());
        verify(authorRepository, times(1)).save(any(Author.class));
    }

    @Test
    public void testLoginAuthor() {
        Author registeredAuthor = authorList.get(0);
        when(authorRepository.findByEmailAndPassword(registeredAuthor.getEmail(), registeredAuthor.getPassword())).thenReturn(Optional.of(registeredAuthor));

        assertThat(authorService.loginAuthor(new AuthorRequest(null, null,
                "invalidEmail", "invalidPassword",
                null, null, null, null))).isNotNull().isNotPresent();
        assertThat(authorService.loginAuthor(new AuthorRequest(null, null,
                registeredAuthor.getEmail(), registeredAuthor.getPassword(),
                null, null, null, null)))
                .isNotNull().isPresent().isEqualTo(Optional.of(registeredAuthor));

        verify(authorRepository, times(1)).findByEmailAndPassword(registeredAuthor.getEmail(), registeredAuthor.getPassword());
    }

    @Test
    public void testAddBookToAuthor() {
        Long id = 1L;
        Long bookId = 2L;
        Author registeredAuthor = authorList.get(0);
        Book book = Book.of("title", "description", "genre",
                "reviewLink", "quote", true);

        when(authorRepository.findById(id)).thenReturn(Optional.of(registeredAuthor));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        registeredAuthor.addBook(book);
        when(authorRepository.save(any(Author.class))).thenReturn(registeredAuthor);

        Optional<Author> author = authorService.addBookToAuthor(id, bookId);
        assertThat(author).isNotNull().isPresent();
        assertEquals(author.get().getNumberOfBooks(), registeredAuthor.getNumberOfBooks());
        assertEquals(author.get().getNumberOfBooks(), 2);
        assertFalse(author.get().getBooks().isEmpty());
        assertEquals(book, author.get().getBooks().get(0));

        verify(authorRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).findById(bookId);
        verify(authorRepository, times(1)).save(any(Author.class));
    }
}
