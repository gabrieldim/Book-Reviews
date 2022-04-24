package mk.ukim.finki.booksreviews.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Library;
import mk.ukim.finki.booksreviews.model.request.LibraryRequest;
import mk.ukim.finki.booksreviews.service.LibraryService;
import mk.ukim.finki.booksreviews.util.ApiController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiController
@AllArgsConstructor
@RequestMapping("/api/library")
public class LibraryController {

    private final LibraryService libraryService;

    @GetMapping
    public List<Library> getAllLibraries() {
        return libraryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getById(@PathVariable Long id) {
        return libraryService.findById(id)
                .map(library -> ResponseEntity.ok().body(library))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Library> createLibrary(@RequestBody @Valid LibraryRequest libraryRequest) {
        return libraryService.createLibrary(libraryRequest)
                .map(library -> ResponseEntity.ok().body(library))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/page")
    public Page<Library> getAllLibrariesPageable(Pageable pageable) {
        return libraryService.findAllPageable(pageable);
    }

    @GetMapping("/book/{bookId}")
    public List<Library> getAllLibrariesByBook(@PathVariable Long bookId) {
        return libraryService.findAllByBook(bookId);
    }

    @PostMapping("/{id}/book/{bookId}")
    public ResponseEntity<Library> addBookToLibrary(@PathVariable Long id, @PathVariable Long bookId) {
        return libraryService.addBookToLibrary(id, bookId)
                .map(library -> ResponseEntity.ok().body(library))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
