package mk.ukim.finki.booksreviews.service;

import mk.ukim.finki.booksreviews.model.entity.Library;
import mk.ukim.finki.booksreviews.model.request.LibraryRequest;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    List<Library> findAll();

    List<Library> findAllByBook(Long bookId);

    Optional<Library> findById(Long id);

    Optional<Library> createLibrary(LibraryRequest libraryRequest);

    void addBookToLibrary(Long id, Long bookId);
}
