package mk.ukim.finki.booksreviews.xport.rest;

import lombok.AllArgsConstructor;
import mk.ukim.finki.booksreviews.model.entity.Book;
import mk.ukim.finki.booksreviews.model.request.BookRequest;
import mk.ukim.finki.booksreviews.service.BookService;
import mk.ukim.finki.booksreviews.util.ApiController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@ApiController
@AllArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookRequest bookRequest) {
        return bookService.createBook(bookRequest)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest) {
        return bookService.updateBook(id, bookRequest)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/page")
    public Page<Book> getAllBooksPageable(Pageable pageable) {
        return bookService.findAllPageable(pageable);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String searchTerm) {
        return bookService.searchBooks(searchTerm);
    }

    @GetMapping("/genre")
    public List<Book> getAllBooksByGenre(@RequestParam(required = false) String genre) {
        return bookService.findAllByGenre(genre);
    }

    @GetMapping("/review/{reviewId}")
    public List<Book> getAllBooksByReview(@PathVariable Long reviewId) {
        return bookService.findAllByReview(reviewId);
    }
}
