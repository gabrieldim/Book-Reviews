package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Library;
import mk.ukim.finki.booksreviews.model.request.LibraryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    List<Library> findAll();

    Page<Library> findAllPageable(Pageable pageable);

    List<Library> findAllByBook(Long bookId);

    Optional<Library> findById(Long id);

    Optional<Library> createLibrary(LibraryRequest libraryRequest);

    Optional<Library> addBookToLibrary(Long id, Long bookId);
}
