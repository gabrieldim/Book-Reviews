package mk.ukim.finki.booksreviews.service.impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Library;
import mk.ukim.finki.booksreviews.model.request.LibraryRequest;
import mk.ukim.finki.booksreviews.repository.BookRepository;
import mk.ukim.finki.booksreviews.repository.LibraryRepository;
import mk.ukim.finki.booksreviews.service.LibraryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;

    @Override
    public List<Library> findAll() {
        return libraryRepository.findAll();
    }

    @Override
    public Page<Library> findAllPageable(Pageable pageable) {
        return libraryRepository.findAll(pageable);
    }

    @Override
    public List<Library> findAllByBook(Long bookId) {
        return libraryRepository.findAllByBooks_Id(bookId);
    }

    @Override
    public Optional<Library> findById(Long id) {
        return libraryRepository.findById(id);
    }

    @Override
    public Optional<Library> createLibrary(LibraryRequest libraryRequest) {
        Library library = Library.of(libraryRequest.getLocation(), libraryRequest.getName());
        return Optional.of(libraryRepository.save(library));
    }

    @Override
    public Optional<Library> addBookToLibrary(Long id, Long bookId) {
        return findById(id).flatMap(library -> bookRepository.findById(bookId).map(book -> {
            library.addBook(book);
            return libraryRepository.save(library);
        }));
    }
}
