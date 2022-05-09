package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.entity.Library;
import mk.ukim.finki.booksreviews.model.request.LibraryRequest;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.repository.LibraryRepository;
import mk.ukim.finki.booksreviews.service.impl.LibraryServiceImpl;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LibraryServiceImplTest {

    List<Library> libraryList = List.of(
            Library.of("Library Location 1", "Library Name 1"),
            Library.of("Library Location 2", "Library Name 2"),
            Library.of("Library Location 3", "Library Name 3"),
            Library.of("Library Location 4", "Library Name 4"),
            Library.of("Library Location 5", "Library Name 5")
    );
    @Mock
    private LibraryRepository libraryRepository;
    @Mock
    private BookRepository bookRepository;
    private LibraryService libraryService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        this.libraryService = Mockito.spy(new LibraryServiceImpl(this.libraryRepository, this.bookRepository));
    }

    @Test
    public void testFindAll() {
        when(libraryRepository.findAll()).thenReturn(List.of()).thenReturn(libraryList);

        assertThat(libraryService.findAll()).isNotNull().isEmpty();
        assertThat(libraryService.findAll()).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(libraryList);
        verify(libraryRepository, times(2)).findAll();
    }

    @Test
    public void testFindAllPageable() {
        Pageable pageable = mock(Pageable.class);
        Page<Library> page = new PageImpl<>(libraryList);
        when(libraryRepository.findAll(pageable)).thenReturn(Page.empty()).thenReturn(page);

        assertThat(libraryService.findAllPageable(pageable)).isNotNull().isEmpty();
        assertThat(libraryService.findAllPageable(pageable)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(5).isEqualTo(page);
        verify(libraryRepository, times(2)).findAll(pageable);
    }

    @Test
    public void testFindAllByBook() {
        Long bookId = 1L;
        List<Library> filteredLibraries = List.of(libraryList.get(0));
        when(libraryRepository.findAllByBooks_Id(bookId)).thenReturn(List.of()).thenReturn(filteredLibraries);

        assertThat(libraryService.findAllByBook(bookId)).isNotNull().isEmpty();
        assertThat(libraryService.findAllByBook(bookId)).isNotNull().isNotEmpty().hasSizeGreaterThan(0).hasSize(1).isEqualTo(filteredLibraries);
        verify(libraryRepository, times(2)).findAllByBooks_Id(bookId);
    }

    @Test
    public void testFindById() {
        Long bookId = 1L;
        Library filteredLibrary = libraryList.get(0);
        when(libraryRepository.findById(bookId)).thenReturn(Optional.empty()).thenReturn(Optional.of(filteredLibrary));

        assertThat(libraryService.findById(bookId)).isNotNull().isNotPresent();
        assertThat(libraryService.findById(bookId)).isNotNull().isPresent().isEqualTo(Optional.of(filteredLibrary));
        verify(libraryRepository, times(2)).findById(bookId);
    }

    @Test
    public void testCreateLibrary() {
        Long libraryId = 1L;
        Library createdLibrary = libraryList.get(0);
        ReflectionTestUtils.setField(createdLibrary, "id", libraryId);
        LibraryRequest libraryRequest = new LibraryRequest("library name", "library location");

        when(libraryRepository.save(any(Library.class))).thenReturn(createdLibrary);

        assertThat(libraryService.createLibrary(libraryRequest)).isNotNull().isPresent().isEqualTo(Optional.of(createdLibrary));

        verify(libraryRepository, times(1)).save(any(Library.class));
    }

    @Test
    public void testAddBookToLibrary() {
        Long id = 1L;
        Long bookId = 2L;
        Library existingLibrary = libraryList.get(0);
        Book book = Book.of("title", "description", "genre",
                "reviewLink", "quote", true);

        when(libraryRepository.findById(id)).thenReturn(Optional.of(existingLibrary));
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        existingLibrary.addBook(book);
        when(libraryRepository.save(any(Library.class))).thenReturn(existingLibrary);

        Optional<Library> library = libraryService.addBookToLibrary(id, bookId);
        assertThat(library).isNotNull().isPresent();
        assertFalse(library.get().getBooks().isEmpty());
        assertEquals(book, library.get().getBooks().get(0));

        verify(libraryRepository, times(1)).findById(id);
        verify(bookRepository, times(1)).findById(bookId);
        verify(libraryRepository, times(1)).save(any(Library.class));
    }
}
