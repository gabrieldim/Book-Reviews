package mk.ukim.finki.booksreviews.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Author;
import mk.ukim.finki.booksreviews.model.request.AuthorRequest;
import mk.ukim.finki.booksreviews.service.AuthorService;
import mk.ukim.finki.booksreviews.util.ApiController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiController
@AllArgsConstructor
@RequestMapping("/api/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Author> getById(@PathVariable Long id) {
        return authorService.findById(id)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<Author> registerAuthor(@RequestBody @Valid AuthorRequest authorRequest) {
        return authorService.registerAuthor(authorRequest)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/login")
    public ResponseEntity<Author> loginAuthor(@RequestBody AuthorRequest authorRequest) {
        return authorService.loginAuthor(authorRequest)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/page")
    public Page<Author> getAllAuthorsPageable(Pageable pageable) {
        return authorService.findAllPageable(pageable);
    }

    @GetMapping("/book/{bookId}")
    public List<Author> getAllAuthorsByBook(@PathVariable Long bookId) {
        return authorService.findAllByBook(bookId);
    }

    @PostMapping("/{id}/book/{bookId}")
    public ResponseEntity<Author> addBookToAuthor(@PathVariable Long id, @PathVariable Long bookId) {
        return authorService.addBookToAuthor(id, bookId)
                .map(author -> ResponseEntity.ok().body(author))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
